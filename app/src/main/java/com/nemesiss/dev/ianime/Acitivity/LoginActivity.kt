package com.nemesiss.dev.ianime.Acitivity


import Utils.AsUri
import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.nemesiss.dev.ianime.Adapter.SplashScrollImageAdapter
import com.nemesiss.dev.ianime.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private var LeftAdapter = SplashScrollImageAdapter(ArrayList())
    private var CenterAdapter = SplashScrollImageAdapter(ArrayList())
    private var RightAdapter = SplashScrollImageAdapter(ArrayList())

    private var LeftLayoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
    private var CenterLayoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
    private var RightLayoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)

    private var ScrollMessageHandler : Handler = Handler {
        msg ->
        when (msg.what) {
            1 -> LeftScroller()
            2 -> CenterScroller()
            3 -> RightScroller()
        }
        return@Handler true
    }

    private lateinit var LeftScroller : ()->Unit
    private lateinit var CenterScroller : ()->Unit
    private lateinit var RightScroller : ()->Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ApplyAdapterToImageScroller()
        LoadScrollImagesToRecycle()

        LeftScroller = ScrollImageActionFactory(Splash_LeftRecycle,LeftAdapter,1)
        CenterScroller = ScrollImageActionFactory(Splash_CenterRecycle,CenterAdapter,2)
        RightScroller = ScrollImageActionFactory(Splash_RightRecycle,RightAdapter,3)

        LeftScroller()
        CenterScroller()
        RightScroller()
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
            .forEach {
                (view, pair) ->
                val (adapter, manager) = pair
                view.adapter = adapter
                view.layoutManager = manager
            }
    }
    private fun LoadScrollImagesToRecycle() {

        Thread {
            for (i in 0..11) {
                val fileName = "image-${i % 4}.jpg"
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

    private fun ScrollImageActionFactory(recyclerView : RecyclerView,adapters: SplashScrollImageAdapter, exec : Int) : ()->Unit {
        val rv = recyclerView
        var lc : View? = null
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
                        rv.scrollBy(0,1)
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
