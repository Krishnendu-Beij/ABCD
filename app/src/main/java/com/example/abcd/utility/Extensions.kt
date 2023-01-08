package com.example.abcd.utility

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.abcd.R

fun AppCompatActivity.replaceFragment(fragment: Fragment, args: Bundle? = null) {
    val fragmentManager = supportFragmentManager
    val transaction = fragmentManager.beginTransaction()
    transaction.replace(R.id.framelayoutFragment, fragment::class.java, args)
    transaction.addToBackStack(null)
    transaction.commit()
}