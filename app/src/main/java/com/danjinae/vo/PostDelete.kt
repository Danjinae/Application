package com.danjinae.vo

import android.os.Parcel
import android.os.Parcelable

data class PostDelete(
    var content: MutableList<PostResponse>,
    var empty: Boolean,
    var first: Boolean,
    var last: Boolean,
    var number: Int,
    var numberOfElements: Int,
    var pageable: Pageable?,
    var size: Int,
    var sort: Sort?,
    var totalElements: Int,
    var totalPages: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("content"),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readParcelable(Pageable::class.java.classLoader),
        parcel.readInt(),
        parcel.readParcelable(Sort::class.java.classLoader),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (empty) 1 else 0)
        parcel.writeByte(if (first) 1 else 0)
        parcel.writeByte(if (last) 1 else 0)
        parcel.writeInt(number)
        parcel.writeInt(numberOfElements)
        parcel.writeParcelable(pageable, flags)
        parcel.writeInt(size)
        parcel.writeParcelable(sort, flags)
        parcel.writeInt(totalElements)
        parcel.writeInt(totalPages)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PostDelete> {
        override fun createFromParcel(parcel: Parcel): PostDelete {
            return PostDelete(parcel)
        }

        override fun newArray(size: Int): Array<PostDelete?> {
            return arrayOfNulls(size)
        }
    }
}