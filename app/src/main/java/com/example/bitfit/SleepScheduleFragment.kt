@file:Suppress("DEPRECATION")

package com.example.bitfit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class SleepScheduleFragment : Fragment() {
    private val sleeps = mutableListOf<DisplaySleep>()
    private lateinit var sleepRecyclerView: RecyclerView
    private lateinit var showAdapter: SleepViewAdapter
    lateinit var communicator: Communicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sleep_schedule, container, false)

        // Add these configurations for the recyclerView and to configure the adapter
        val layoutManager = LinearLayoutManager(context)
        sleepRecyclerView = view.findViewById(R.id.recyclerView)
        sleepRecyclerView.layoutManager = layoutManager
        sleepRecyclerView.setHasFixedSize(true)
        showAdapter = SleepViewAdapter(view.context, sleeps)
        sleepRecyclerView.adapter = showAdapter

        // Update the return statement to return the inflated view from above
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wakeTime = view.findViewById<TextView>(R.id.wakeTimeInput)
        val sleepTime = view.findViewById<TextView>(R.id.sleepTimeInput)
        val dayOfWeek = view.findViewById<TextView>(R.id.dayInput)

        var entries = 0
        var duration = 0

        communicator = activity as Communicator

        view.findViewById<Button>(R.id.button).setOnClickListener {
            entries += 1

            val thisDay = dayOfWeek.text.toString()
            val thisSleepTime = sleepTime.text.toString()
            val thisWakeTime = wakeTime.text.toString()

            val format = SimpleDateFormat("HH:mm")
            val date1: Date = format.parse(thisSleepTime) as Date
            val date2: Date = format.parse(thisWakeTime) as Date

            duration = (date2.hours - date1.hours)


            dayOfWeek.text = ""
            sleepTime.text = ""
            wakeTime.text = ""


            communicator.passDataCom(entries.toString(), date1.hours.toString(), date2.hours.toString(),duration.toString())

            val thisSleep = DisplaySleep(thisDay, thisSleepTime, thisWakeTime)
            sleeps.add(thisSleep)
            showAdapter?.notifyDataSetChanged()
        }
    }

    companion object {
        fun newInstance(): SleepScheduleFragment {
            return SleepScheduleFragment()
        }
    }
}