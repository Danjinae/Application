package com.danjinae.vo

import android.os.Parcel
import android.os.Parcelable

data class ComplaintList(
    var content: MutableList<ComplaintListResponse>? = null,
    var pageable: Pageable?,
    var totalelements: Int,
    var totalPages: Int,
    var last: Boolean = true,
    var sort: Sort?,
    var numberOfElements: Int,
    var first: Boolean = true,
    var empty: Boolean = true
) : Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("content"),
        parcel.readParcelable(Pageable::class.java.classLoader),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readParcelable(Sort::class.java.classLoader),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(pageable, flags)
        parcel.writeInt(totalelements)
        parcel.writeInt(totalPages)
        parcel.writeByte(if (last) 1 else 0)
        parcel.writeParcelable(sort, flags)
        parcel.writeInt(numberOfElements)
        parcel.writeByte(if (first) 1 else 0)
        parcel.writeByte(if (empty) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ComplaintList> {
        override fun createFromParcel(parcel: Parcel): ComplaintList {
            return ComplaintList(parcel)
        }

        override fun newArray(size: Int): Array<ComplaintList?> {
            return arrayOfNulls(size)
        }
    }
}