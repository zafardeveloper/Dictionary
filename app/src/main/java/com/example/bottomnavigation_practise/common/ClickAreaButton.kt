package com.example.bottomnavigation_practise.common

import android.graphics.Rect
import android.view.TouchDelegate
import android.view.View

object ClickAreaButton {

    fun clickAreaButton(button: View) {

        val parent = button.parent as View

        parent.post {
            val rect = Rect()
            button.getHitRect(rect)
            rect.top -= 15
            rect.left -= 15
            rect.bottom += 15
            rect.right += 15
            parent.touchDelegate = TouchDelegate(rect, button)
        }
    }
}