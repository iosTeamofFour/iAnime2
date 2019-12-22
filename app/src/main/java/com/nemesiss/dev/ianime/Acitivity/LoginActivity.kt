package com.nemesiss.dev.ianime.Acitivity

import Utils.AsUri
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.nemesiss.dev.ianime.Adapter.SplashScrollImageAdapter
import com.nemesiss.dev.ianime.R
import kotlinx.android.synthetic.main.activity_login.*
import android.animation.ObjectAnimator


import android.animation.AnimatorSet

import android.widget.Button
import android.view.MotionEvent

import android.widget.EditText
import android.app.Activity
import android.content.Context
import android.content.Intent

import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginActivity : iAnimeActivity() {

    private var LeftAdapter = SplashScrollImageAdapter(ArrayList())
    private var CenterAdapter = SplashScrollImageAdapter(ArrayList())
    private var RightAdapter = SplashScrollImageAdapter(ArrayList())

    private var LeftLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    private var CenterLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    private var RightLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    private var ScrollMessageHandler: Handler = Handler { msg ->
        when (msg.what) {
            1 -> LeftScroller()
            2 -> CenterScroller()
            3 -> RightScroller()
        }
        return@Handler true
    }

    private lateinit var LeftScroller: () -> Unit
    private lateinit var CenterScroller: () -> Unit
    private lateinit var RightScroller: () -> Unit

    private lateinit var logoView: ImageView
    private lateinit var loginFragment: View
    private lateinit var registerFragment: View
    private lateinit var registerButton: Button
    private lateinit var backButton: Button
    private lateinit var loginButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ApplyAdapterToImageScroller()
        LoadScrollImagesToRecycle()
        findView()
        LeftScroller = ScrollImageActionFactory(Splash_LeftRecycle, LeftAdapter, 1)
        CenterScroller = ScrollImageActionFactory(Splash_CenterRecycle, CenterAdapter, 2)
        RightScroller = ScrollImageActionFactory(Splash_RightRecycle, RightAdapter, 3)
        LeftScroller()
        CenterScroller()
        RightScroller()
        Animator1()
        registerButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

                Animator2()
            }

        })


        loginFragment.loginArrowRight.setOnClickListener {
            startActivity(Intent(this@LoginActivity, WorksIndexActivity::class.java))
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                val view = currentFocus
                hideKeyboard(ev, view, this@LoginActivity)//调用方法判断是否需要隐藏键盘
            }

            else -> {
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    fun hideKeyboard(event: MotionEvent, view: View?, activity: Activity) {
        try {
            if (view != null && view is EditText) {
                val location = intArrayOf(0, 0)
                view.getLocationInWindow(location)
                val left = location[0]
                val top = location[1]
                val right = left + view.width
                val bootom = top + view.height
                // 判断焦点位置坐标是否在空间内，如果位置在控件外，则隐藏键盘
                if (event.rawX < left || event.rawX > right
                    || event.y < top || event.rawY > bootom
                ) {
                    // 隐藏键盘
                    val token = view.windowToken
                    val inputMethodManager = activity
                        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(
                        token,
                        InputMethodManager.HIDE_NOT_ALWAYS
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun findView() {
        logoView = findViewById(R.id.logo_imageView)
        loginFragment = findViewById(R.id.loginFragment)
        registerFragment = findViewById(R.id.registerFragment)
        registerButton = findViewById(R.id.registerButton)
        registerButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                Animator2()
            }

        })
        backButton = findViewById(R.id.back_button)
        backButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                Animator3()
            }
        })
    }

    private fun Animator1() {
        val animator1 = ObjectAnimator.ofFloat(logoView, "alpha", 1f, 0f)
        val animator2 = ObjectAnimator.ofFloat(loginFragment, "alpha", 0f, 1f)
        val animSet = AnimatorSet()
        animSet.play(animator2).after(animator1)
        animSet.duration = 1000
        animSet.start()
    }

    private fun Animator2() {
        registerFragment.visibility = View.VISIBLE
        loginFragment.visibility = View.GONE
        val animator3 = ObjectAnimator.ofFloat(loginFragment, "alpha", 1f, 0f)
        val animator4 = ObjectAnimator.ofFloat(registerFragment, "alpha", 0f, 1f)
        val animSet = AnimatorSet()
        animSet.play(animator4).after(animator3)
        animSet.duration = 10
        animSet.start()
    }

    private fun Animator3() {
        registerFragment.visibility = View.GONE
        loginFragment.visibility = View.VISIBLE
        val animator3 = ObjectAnimator.ofFloat(loginFragment, "alpha", 0f, 1f)
        val animator4 = ObjectAnimator.ofFloat(registerFragment, "alpha", 1f, 0f)
        val animSet = AnimatorSet()
        animSet.play(animator3).after(animator4)
        animSet.duration = 10
        animSet.start()
    }

    private fun ApplyAdapterToImageScroller() {
        arrayOf(Splash_LeftRecycle, Splash_CenterRecycle, Splash_RightRecycle)
            .zip(
                arrayOf(
                    Pair(LeftAdapter, LeftLayoutManager),
                    Pair(CenterAdapter, CenterLayoutManager),
                    Pair(RightAdapter, RightLayoutManager)
                )
            )
            .forEach { (view, pair) ->
                val (adapter, manager) = pair
                view.adapter = adapter
                view.layoutManager = manager
            }
    }

    private fun LoadScrollImagesToRecycle() {

        Thread {
            for (i in 0..11) {
                val fileName = "image${i % 4}.jpg"
                val bitmap = Glide.with(this)
                    .asBitmap()
                    .load(assets.AsUri(fileName))
                    .submit()
                    .get()
                LeftAdapter.bitmaps.add(bitmap)
                CenterAdapter.bitmaps.add(bitmap)
                RightAdapter.bitmaps.add(bitmap)
                runOnUiThread {
                    LeftAdapter.notifyDataSetChanged()
                    CenterAdapter.notifyDataSetChanged()
                    RightAdapter.notifyDataSetChanged()
                }
            }
        }.start()
    }

    private fun ScrollImageActionFactory(
        recyclerView: RecyclerView,
        adapters: SplashScrollImageAdapter,
        exec: Int
    ): () -> Unit {
        val rv = recyclerView
        var lc: View? = null
        return {
            if (rv.canScrollVertically(1)) {
                val view = rv.getChildAt(0)

                if (lc == null) {
                    lc = view
                }

                if (lc != view) {

                    val top = adapters.bitmaps.removeAt(0)
                    adapters.bitmaps.add(top)
                    lc = view
                    runOnUiThread {
                        adapters.notifyDataSetChanged()
                        rv.scrollToPosition(0)
                        rv.scrollBy(0, 1)
                    }
                } else {
                    rv.scrollBy(0, 1)
                }
            }

            rv.post {
                ScrollMessageHandler.sendEmptyMessageDelayed(exec, 16)
            }
        }
    }
}
