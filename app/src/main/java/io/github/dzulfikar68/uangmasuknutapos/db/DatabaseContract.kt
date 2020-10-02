package io.github.dzulfikar68.uangmasuknutapos.db

import android.provider.BaseColumns

internal class DatabaseContract {

    internal class MoneyColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "money"
            const val _ID = "_id"
            const val FROM = "people"
            const val DESCRIPTION = "description"
            const val NOMINAL = "nominal"
        }
    }
}