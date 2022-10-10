package com.example.bitfit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DashboardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val totalEntries = view.findViewById<TextView>(R.id.entryInput)
        val avgSleepTime = view.findViewById<TextView>(R.id.AvgSleepTime)
        val avgWakeTime = view.findViewById<TextView>(R.id.AvgWakeTime)
        val avgDuration = view.findViewById<TextView>(R.id.durationEntry)

        val args = this.arguments
        totalEntries.text = args?.get("entries").toString()
        avgSleepTime.text = args?.get("avgSleepTime").toString() + ":00"
        avgWakeTime.text = args?.get("avgWakeTime").toString() + ":00"
        avgDuration.text = args?.get("avgDuration").toString() + " Hours"

        return view
    }

    companion object {
        fun newInstance(): DashboardFragment {
            return DashboardFragment()
        }
    }
}