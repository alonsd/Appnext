package com.appnext.utils.extensions

fun List<Int>.findHighestValue(): Int {
    var result : Int = 0
    forEach {
        if (it > result) result = it
    }
    return result
}