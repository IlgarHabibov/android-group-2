package az.altacademy.androidgroup2.extensions

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import az.altacademy.androidgroup2.R

fun NavController.navigateWithFadeAnimation(directions: NavDirections){
    val option = NavOptions.Builder()
        .setExitAnim(R.anim.fade_out)
        .setPopExitAnim(R.anim.fade_out)
        .setEnterAnim(R.anim.fade_in)
        .setPopEnterAnim(R.anim.fade_in)
        .build()
    navigate(directions, option)
}

fun NavController.navigateWithSlideAnimation(directions: NavDirections){
    val option = NavOptions.Builder()
        .setExitAnim(R.anim.slide_out_left)
        .setPopExitAnim(R.anim.slide_out_right)
        .setEnterAnim(R.anim.slide_in_right)
        .setPopEnterAnim(R.anim.slide_in_left)
        .build()
    navigate(directions, option)
}


fun NavController.navigateWithFadeAnimation(actionId: Int, bundle: Bundle? = null){
    val option = NavOptions.Builder()
        .setExitAnim(R.anim.fade_out)
        .setPopExitAnim(R.anim.fade_out)
        .setEnterAnim(R.anim.fade_in)
        .setPopEnterAnim(R.anim.fade_in)
        .build()
    navigate(actionId, bundle, option)
}