package com.example.testapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.R
import com.example.testapp.database.entity.BootTime
import com.example.testapp.workers.AppActionWorker

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var messageTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        messageTextView = view.findViewById(R.id.tv_message)
        viewModel.updateTimesLiveData.observe(viewLifecycleOwner, ::showBootTimes)
        viewModel.loadLastBootTimes()
        startUpdateWorker()
    }

    private fun showBootTimes(bootTimes: List<BootTime>) {
        if (bootTimes.isEmpty()) {
            messageTextView.setText(R.string.no_boots_message)
        } else {
            val message = StringBuilder()
            bootTimes.forEachIndexed { index, bootTime ->
                message.append("${index + 1} - ${bootTime.time}\n")
            }
            messageTextView.text = message
        }
    }

    private fun startUpdateWorker() {
        val context = context ?: return
        AppActionWorker.startWorker(context)
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}