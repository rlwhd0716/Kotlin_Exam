package kr.com.rlwhd.kotlinexample.util

import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

class PlayerOnInitializedListener: YouTubePlayer.OnInitializedListener {
    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?, b: Boolean) {
        youTubePlayer?.loadVideo("SGWohw86Xk8")
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, youTubeInitializationResult: YouTubeInitializationResult?) {
    }

}