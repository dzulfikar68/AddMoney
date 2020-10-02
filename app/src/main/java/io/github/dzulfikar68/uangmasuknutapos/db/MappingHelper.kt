package io.github.dzulfikar68.uangmasuknutapos.db

import android.database.Cursor
import io.github.dzulfikar68.uangmasuknutapos.model.Money

object MappingHelper {
    fun mapCursorToArrayList(notesCursor: Cursor?): ArrayList<Money> {
        val notesList = ArrayList<Money>()
        notesCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.MoneyColumns._ID))
                val from = getString(getColumnIndexOrThrow(DatabaseContract.MoneyColumns.FROM))
                val description =
                    getString(getColumnIndexOrThrow(DatabaseContract.MoneyColumns.DESCRIPTION))
                val nominal =
                    getString(getColumnIndexOrThrow(DatabaseContract.MoneyColumns.NOMINAL))
                notesList.add(Money(id, from, description, nominal))
            }
        }
        return notesList
    }
}