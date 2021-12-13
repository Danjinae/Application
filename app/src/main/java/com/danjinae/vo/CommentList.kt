package com.danjinae.vo

import android.os.Parcel
import android.os.Parcelable

data  class CommentList(
    var content: MutableList<CommentResponse>,
    var pageable: Pageable,
    var last: Boolean = true,
    var totalPages: Int = 0,
    var totalElements: Int = 0,
    var size: Int = 0,
    var number: Int = 0,
    var sort: Sort,
    var first: Boolean = true,
    var numberOfElements: Int = 0,
    var empty: Boolean = true
) : Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("content"),
        TODO("pageable"),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        TODO("sort"),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (last) 1 else 0)
        parcel.writeInt(totalPages)
        parcel.writeInt(totalElements)
        parcel.writeInt(size)
        parcel.writeInt(number)
        parcel.writeByte(if (first) 1 else 0)
        parcel.writeInt(numberOfElements)
        parcel.writeByte(if (empty) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CommentList> {
        override fun createFromParcel(parcel: Parcel): CommentList {
            return CommentList(parcel)
        }

        override fun newArray(size: Int): Array<CommentList?> {
            return arrayOfNulls(size)
        }
    }
}