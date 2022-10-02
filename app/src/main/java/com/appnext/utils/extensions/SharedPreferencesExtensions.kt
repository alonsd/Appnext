package com.appnext.utils.extensions

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import com.appnext.utils.application.App
import com.appnext.utils.constants.SharedPreferences.APPNEXT_SHARED_PREFERENCES
import com.appnext.utils.constants.SharedPreferences.FETCHED_WEEKLY_DATA_FOR_THE_FIRST_TIME
import com.appnext.utils.constants.SharedPreferences.LAST_TIME_DATA_FETCHED


var SharedPreferences.lastTimeWeeklyDataFetch
    get() = getLong(LAST_TIME_DATA_FETCHED, 0)
    set(value) {
        editSharedPreferences { editor ->
            editor.put(LAST_TIME_DATA_FETCHED to value)
        }
    }

var SharedPreferences.fetchedWeeklyDataForTheFirstTime
    get() = getBoolean(FETCHED_WEEKLY_DATA_FOR_THE_FIRST_TIME, false)
    set(value) {
        editSharedPreferences { editor ->
            editor.put(FETCHED_WEEKLY_DATA_FOR_THE_FIRST_TIME to value)
        }
    }


val Fragment.sharedPreferences: SharedPreferences
    get() = requireActivity().getSharedPreferences(APPNEXT_SHARED_PREFERENCES, MODE_PRIVATE)

val sharedPreferences: SharedPreferences
    get() = App.applicationContext().getSharedPreferences(APPNEXT_SHARED_PREFERENCES, MODE_PRIVATE)


private fun SharedPreferences.editSharedPreferences(operation: (SharedPreferences.Editor) -> Unit) {
    val editSharePreferences = edit()
    operation(editSharePreferences)
    editSharePreferences.apply()
}

private fun SharedPreferences.Editor.put(pair: Pair<String, Any?>) {
    val key = pair.first
    when (val value = pair.second) {
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Boolean -> putBoolean(key, value)
        is Long -> putLong(key, value)
        is Float -> putFloat(key, value)
    }
}
