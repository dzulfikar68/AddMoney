package io.github.dzulfikar68.uangmasuknutapos.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Money(
    var id: Int = 0,
    var from: String? = null,
    var description: String? = null,
    var nominal: String? = null
) : Parcelable