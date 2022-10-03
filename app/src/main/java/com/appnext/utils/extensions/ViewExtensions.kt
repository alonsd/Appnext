package com.appnext.utils.extensions

import android.content.Context
import android.util.TypedValue
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


fun getDpUnitsAsInt(units : Float, context: Context): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, units, context.resources.displayMetrics).toInt()
}

inline fun Fragment.launchAndRepeatWithViewLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
            block()
        }
    }
}

fun <T, VH : RecyclerView.ViewHolder>
        RecyclerView.setVerticalAdapter(
    context: Context,
    adapter: ListAdapter<T, VH>
) {
    this.adapter = adapter
    this.setHasFixedSize(true)
    this.layoutManager = LinearLayoutManager(context)
}

fun <T, VH : RecyclerView.ViewHolder>
        RecyclerView.setHorizontalAdapter(
    context: Context,
    adapter: ListAdapter<T, VH>
) {
    this.adapter = adapter
    this.setHasFixedSize(true)
    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
}


























