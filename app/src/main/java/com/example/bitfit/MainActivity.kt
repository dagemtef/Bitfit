package com.example.bitfit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var sleepRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private val sleeps = mutableListOf<DisplaySleep>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val showAdapter = SleepViewAdapter(this, sleeps)
        sleepRecyclerView = findViewById(R.id.recyclerView)
        sleepRecyclerView.adapter = showAdapter
        sleepRecyclerView.layoutManager = LinearLayoutManager(this)


        /*lifecycleScope.launch(IO) {
            (application as SleepApplication).db.sleepDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplaySleep(
                        entity.day,
                        entity.sleepTime,
                        entity.wakeTime
                    )
                }.also { mappedList ->
                    sleeps.clear()
                    sleeps.addAll(mappedList)
                    showAdapter.notifyDataSetChanged()
                }
            }
        }*/


        val wakeTime = findViewById<TextView>(R.id.wakeTimeInput)
        val sleepTime = findViewById<TextView>(R.id.sleepTimeInput)
        val dayOfWeek = findViewById<TextView>(R.id.dayInput)


        findViewById<Button>(R.id.button).setOnClickListener {
            val thisDay = dayOfWeek.text.toString()
            val thisSleepTime = sleepTime.text.toString()
            val thisWakeTime = wakeTime.text.toString()
            dayOfWeek.text = ""
            sleepTime.text = ""
            wakeTime.text = ""

            val thisSleep = DisplaySleep(thisDay, thisSleepTime, thisWakeTime)
            sleeps.add(thisSleep)
            showAdapter.notifyDataSetChanged()

            /*lifecycleScope.launch(Dispatchers.IO) {
                (application as SleepApplication).db.sleepDao().insert(
                    SleepEntity(
                        day = thisSleep.day,
                        sleepTime = thisSleep.sleepTime,
                        wakeTime = thisSleep.wakeTime
                    )
                )
            }*/
        }
    }
}
