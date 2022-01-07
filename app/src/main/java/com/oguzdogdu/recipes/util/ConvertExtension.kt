package com.oguzdogdu.recipes.util

 fun Boolean.convertToExpression(): String {
    return if(equals(true)) {
        "Yes"
    } else {
        "No"
    }
}