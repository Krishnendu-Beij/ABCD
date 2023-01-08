package com.example.abcd.ui.fragment


import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.abcd.R
import com.example.abcd.modelclasses.Article


class DetailNewsFragment : Fragment(R.layout.fragment_detail_news) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            val selectedArticle = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                getSerializable("article", Article::class.java)
            } else getSerializable("article")

            Log.d("SELECTED_ITEM", (selectedArticle as Article).toString())
        }
    }

}