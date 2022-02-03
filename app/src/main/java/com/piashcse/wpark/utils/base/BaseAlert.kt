/*
 * Created Date: <13/4/21>
 * Copyright (c) 2021 Plus Style Corp. All rights reserved.
 * Last modified Date: <30/8/2021>
 */
package com.piashcse.wpark.utils.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.text.method.ScrollingMovementMethod
import android.view.Gravity
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.piashcse.wpark.R
import com.piashcse.wpark.utils.AppConstants
import com.piashcse.wpark.utils.dpToPx
import com.piashcse.wpark.utils.hide

class BaseAlert(val context: Context) {
    private lateinit var dialog: Dialog
    fun setTitleAndDescription(
        title: String,
        description: String?,
        buttonText: String,
        clickListener: () -> Unit
    ) {
        if (::dialog.isInitialized.not()) {
            dialog = Dialog(context)
            dialog.setContentView(R.layout.common_alert)
            dialog.setCancelable(false)
            val colorDrawable = ColorDrawable(Color.TRANSPARENT)
            val inset = InsetDrawable(
                colorDrawable,
                AppConstants.CommonDialog.CUSTOM_DIALOG_LEFT_MARGIN.dpToPx(),
                0,
                AppConstants.CommonDialog.CUSTOM_DIALOG_RIGHT_MARGIN.dpToPx(),
                0
            )
            dialog.window?.setBackgroundDrawable(inset)
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window?.setLayout(width, height)
            dialog.window?.setGravity(Gravity.CENTER)
        }
        dialog.show()

        val titleTextView = dialog.findViewById<AppCompatTextView>(R.id.alert_title_text_view)
        val descriptionTextView = dialog.findViewById<AppCompatTextView>(R.id.description_text_View)
        val okButton = dialog.findViewById<AppCompatButton>(R.id.ok_button)
        okButton.text = buttonText

        descriptionTextView.movementMethod = ScrollingMovementMethod()
        descriptionTextView.maxHeight = AppConstants.CommonDialog.MAX_HEIGHT

        titleTextView.text = title
        descriptionTextView.text = description
        if (title.isEmpty()) {
            titleTextView.hide()
        }

        okButton.setOnClickListener {
            dialog.dismiss()
            clickListener()
        }

    }
}