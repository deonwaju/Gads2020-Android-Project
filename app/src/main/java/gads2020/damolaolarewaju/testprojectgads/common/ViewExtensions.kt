package gads2020.damolaolarewaju.testprojectgads.common

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, message, duration).show()
}
fun View.visible(){
    visibility = View.VISIBLE
}
fun View.gone(){
    visibility = View.GONE
}