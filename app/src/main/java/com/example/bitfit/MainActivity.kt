package com.example.bitfit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.bitfit.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), Communicator{
    private lateinit var binding: ActivityMainBinding
    var totalEntries = 0
    var sumSleepTime = 0
    var sumWakeTime = 0
    var sumDuration = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val dashboardFragment: Fragment = DashboardFragment()
        val sleepScheduleFragment: Fragment = SleepScheduleFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener {item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.action_music -> fragment = dashboardFragment
                R.id.action_favorites -> fragment = sleepScheduleFragment
            }
            replaceFragment(fragment)
            true
        }

        bottomNavigationView.selectedItemId = R.id.action_favorites
    }



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


            /*lifecycleScope.launch(Dispatchers.IO) {
                (application as SleepApplication).db.sleepDao().insert(
                    SleepEntity(
                        day = thisSleep.day,
                        sleepTime = thisSleep.sleepTime,
                        wakeTime = thisSleep.wakeTime
                    )
                )
            }*/

    private fun replaceFragment(thisFragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.sleep_frame_layout, thisFragment)
        fragmentTransaction.commit()
    }

    override fun passDataCom(
        entries: String,
        sleepTime: String,
        wakeTime: String,
        duration: String
    ) {
        totalEntries += 1
        sumSleepTime += sleepTime.toInt()
        sumWakeTime += wakeTime.toInt()
        sumDuration += duration.toInt()

        var avgSleepTime = sumSleepTime/totalEntries
        var avgWakeTime = sumWakeTime/totalEntries
        var avgDuration = sumDuration/totalEntries

        val bundle = Bundle()
        bundle.putString("entries", totalEntries.toString())
        bundle.putString("avgSleepTime", avgSleepTime.toString())
        bundle.putString("avgWakeTime", avgWakeTime.toString())
        bundle.putString("avgDuration", avgDuration.toString())

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragmentB = DashboardFragment()
        fragmentB.arguments = bundle
        fragmentTransaction.replace(R.id.sleep_frame_layout, fragmentB)
        fragmentTransaction.commit()
    }

}
