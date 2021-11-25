package com.danjinae.vo

import android.os.Parcel
import android.os.Parcelable

data class Pageable(
    val offset: Int = 0,
    val pageNumber: Int = 0,
    val pageSize: Int = 0,
    val paged: Boolean? = true,
    val sort: Sort? = null,
    val unpaged: Boolean? = true
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readParcelable(Sort::class.java.classLoader),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(offset)
        parcel.writeInt(pageNumber)
        parcel.writeInt(pageSize)
        parcel.writeValue(paged)
        parcel.writeParcelable(sort, flags)
        parcel.writeValue(unpaged)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pageable> {
        override fun createFromParcel(parcel: Parcel): Pageable {
            return Pageable(parcel)
        }

        override fun newArray(size: Int): Array<Pageable?> {
            return arrayOfNulls(size)
        }
    }
}