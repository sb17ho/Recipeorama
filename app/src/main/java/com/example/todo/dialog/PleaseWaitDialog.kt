package com.example.todo.dialog

import android.app.Activity
import android.app.AlertDialog
import com.example.todo.R

class PleaseWaitDialog(activity: Activity) {

    private val alertDialogCreate by lazy {
        AlertDialog.Builder(activity)
            .setView(activity.layoutInflater.inflate(R.layout.please_wait_dialog, null))
            .setCancelable(true)
            .create()
    }

    fun showDialog() {
        alertDialogCreate.show()
    }

    fun closeDialog() {
        alertDialogCreate.dismiss()
    }
}