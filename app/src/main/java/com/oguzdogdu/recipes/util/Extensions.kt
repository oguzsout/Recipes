package com.oguzdogdu.recipes.util

import com.facebook.drawee.view.SimpleDraweeView

fun Boolean.convertToExpression(): String {
    return if(equals(true)) {
        "Yes"
    } else {
        "No"
    }
}
fun SimpleDraweeView.loadImage(url: String?) {
    setImageURI(url)

}