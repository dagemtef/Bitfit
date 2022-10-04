package com.example.bitfit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SleepViewAdapter( private val context: Context, private val sleeps: List<DisplaySleep>): RecyclerView.Adapter<SleepViewAdapter.SleepViewHolder>()
    {
        inner class SleepViewHolder(private val sleepView: View) : RecyclerView.ViewHolder(sleepView) {

            var item: DisplaySleep? = null
            val dayOfWeek: TextView = sleepView.findViewById<View>(R.id.dayOfWeek) as TextView
            val sleepTime: TextView = sleepView.findViewById<View>(R.id.sleepTime) as TextView
            val wakeTime: TextView = sleepView.findViewById<View>(R.id.wakeTime) as TextView
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.single_sleep, parent, false)
            return SleepViewHolder(view)
        }

        override fun onBindViewHolder(holder: SleepViewHolder, position: Int) {
            val sleep = sleeps[position]

            holder.item = sleep
            holder.dayOfWeek.text = sleep.day
            holder.sleepTime.text = sleep.sleepTime
            holder.wakeTime.text = sleep.wakeTime
        }

        override fun getItemCount(): Int {
            return sleeps.size
        }
    }
