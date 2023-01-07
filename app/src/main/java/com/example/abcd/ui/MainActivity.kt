package com.example.abcd.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.abcd.R
import com.example.abcd.ui.fragment.AllNewsFragment
import com.example.abcd.ui.fragment.DetailNewsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val allNewsFragment = AllNewsFragment()
        val detailNewsFragment = DetailNewsFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.framelayoutFragment, allNewsFragment)
            addToBackStack(null)
            commit()
        }
    }
}