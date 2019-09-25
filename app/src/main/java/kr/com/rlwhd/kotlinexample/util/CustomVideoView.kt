package kr.com.rlwhd.kotlinexample.util

import android.content.Context
import android.util.AttributeSet
import android.widget.VideoView

class CustomVideoView: VideoView {
    private var mListener: PlayPauseListener? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun pause() {
        super.pause()
            mListener?.onPause()
    }

    override fun start() {
        super.start()
            mListener?.onPlay()
    }

    fun setPlayPauseListener(listener: PlayPauseListener) {
        mListener = listener
    }

    interface PlayPauseListener {
        fun onPlay()
        fun onPause()
    }
}