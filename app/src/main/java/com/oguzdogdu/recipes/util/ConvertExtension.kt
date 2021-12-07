package com.oguzdogdu.recipes.util

 fun Boolean.setOf(): String {
    return if(equals(true)) {
        "Yes"
    } else {
        "No"
    }
}