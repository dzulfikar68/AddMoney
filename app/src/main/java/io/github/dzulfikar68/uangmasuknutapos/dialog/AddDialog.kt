package io.github.dzulfikar68.uangmasuknutapos.dialog

import android.app.Activity
import android.app.Dialog
import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import io.github.dzulfikar68.uangmasuknutapos.R
import io.github.dzulfikar68.uangmasuknutapos.db.DatabaseContract
import io.github.dzulfikar68.uangmasuknutapos.model.MoneyHelper


class AddDialog(private var c: Activity) : Dialog(c), View.OnClickListener {
    private var yes: Button? = null
    private var from: EditText? = null
    private var desc: EditText? = null
    private var nominal: EditText? = null
    var isDone = false
    private lateinit var dbHelper: MoneyHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.add_dialog)

        dbHelper = MoneyHelper.getInstance(c)
        dbHelper.open()

        from = findViewById(R.id.from)
        desc = findViewById(R.id.description)
        nominal = findViewById(R.id.nominal)
        yes = findViewById(R.id.submit)
        yes?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.submit -> {
                val values = ContentValues()
                values.put(DatabaseContract.MoneyColumns.FROM, from?.text.toString().trim())
                values.put(DatabaseContract.MoneyColumns.DESCRIPTION, desc?.text.toString().trim())
                values.put(DatabaseContract.MoneyColumns.NOMINAL, nominal?.text.toString().trim())
                val result = dbHelper.insert(values)
                if (result > 0) {
                    isDone = true
                    dismiss()
                }
            }
            else -> {
            }
        }
        dismiss()
    }

}