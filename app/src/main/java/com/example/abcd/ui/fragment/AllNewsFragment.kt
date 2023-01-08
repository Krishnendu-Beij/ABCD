package com.example.abcd.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.abcd.R
import com.example.abcd.adapter.AppAdapter
import com.example.abcd.api.RetrofitInstance
import com.example.abcd.modelclasses.Article
import com.example.abcd.modelclasses.NewsResponse
import com.example.abcd.utility.replaceFragment
import kotlinx.android.synthetic.main.fragment_all_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AllNewsFragment : Fragment() {
    lateinit var myadapter: AppAdapter
    private var articles = mutableListOf<Article>()
    var pageNumber = 1
    var totalResults = -1
    var isScrolling = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myadapter = AppAdapter(articles)
        recyclerviewABCD.adapter = myadapter
        val myLayOutManager = LinearLayoutManager(context)
        recyclerviewABCD.layoutManager = myLayOutManager
        recyclerviewABCD.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.d(
                    "AllNewsFragment",
                    "First Visible Item ${myLayOutManager.findFirstVisibleItemPosition()}"
                )
                Log.d("AllNewsFragment", "Total Cont ${myLayOutManager.itemCount}")
                if (totalResults > myLayOutManager.itemCount && myLayOutManager.findFirstVisibleItemPosition() >= myLayOutManager.itemCount - 6) {
                    pageNumber++
                    getNews()
                }

            }
        })

        myadapter.onTap =  { article ->
            Log.d("TRACK_ITEM_CLICK", article.toString())
            (context as AppCompatActivity).replaceFragment(
                fragment = DetailNewsFragment(),
                args = bundleOf().apply {
                    putSerializable("article", article)
                }
            )
        }
        getNews()

    }

    private fun getNews() {
        Log.d("AllNewsFragment", "Request For Page : $pageNumber")
        val news = RetrofitInstance.myApi.getNews("in", pageNumber)
        news.enqueue(object : Callback<NewsResponse?> {
            override fun onResponse(call: Call<NewsResponse?>, response: Response<NewsResponse?>) {
                Log.d("AllNewsFragment", "before if Statement")
                val data = response.body()
                Log.d("AllNewsFragment", "after val data ${data.toString()}")
                if (data != null) {
                    Log.d("AllNewsFragment", data.toString())
                    totalResults = data.totalResults
                    articles.addAll(data.articles)
                    myadapter.notifyDataSetChanged()
                } else {
                    Log.d(
                        "AllNewsFragment",
                        "Api Providing No Data, Developer accounts request limit exceeded "
                    )
                    Toast.makeText(
                        context,
                        "Api Providing No Data, request limit exceeded",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<NewsResponse?>, t: Throwable) {
                Log.d("AllNewsFragment", "Error in Fetching News", t)
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().finish()
    }
}