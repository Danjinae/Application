package com.danjinae.vo

import android.os.Parcel
import android.os.Parcelable

data class Sort(
    val empty: Boolean = true,
    val sorted: Boolean = true,
    val unsorted: Boolean = true
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Boolean::class.java.classLoader) as Boolean,
        parcel.readValue(Boolean::class.java.classLoader) as Boolean,
        parcel.readValue(Boolean::class.java.classLoader) as Boolean
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(empty)
        parcel.writeValue(sorted)
        parcel.writeValue(unsorted)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Sort> {
        override fun createFromParcel(parcel: Parcel): Sort {
            return Sort(parcel)
        }

        override fun newArray(size: Int): Array<Sort?> {
            return arrayOfNulls(size)
        }
    }
}