package com.example.constraintlayoutanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnticipateInterpolator
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.constraintlayoutanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var isDetailLayout = false
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.constraintLayout.setOnClickListener {
            if (isDetailLayout)
                swapFrames(R.layout.activity_main) // switch to default layout
            else
                swapFrames(R.layout.activity_main_detail)// switch to detail layout
        }
    }

    private fun swapFrames(layoutId: Int) {

        val constraintSet = ConstraintSet()
        constraintSet.clone(this, layoutId)

        val transition = ChangeBounds()
//        transition.interpolator = BounceInterpolator()
//        transition.interpolator = AnticipateInterpolator()
//        transition.interpolator = AnticipateOvershootInterpolator()
//        transition.interpolator = DecelerateInterpolator()
        transition.interpolator = AccelerateInterpolator()
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(binding.constraintLayout, transition)
        constraintSet.applyTo(binding.constraintLayout)
        isDetailLayout = !isDetailLayout
    }
}