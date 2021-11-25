package com.danjinae.vo

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class PostList(
    var content: ArrayList<Post>?,
    var pageable: Pageable,
    var last: Boolean,
    var totalPages: Int,
    var totalElements: Int,
    var size: Int,
    var number: Int,
    var sort: Sort,
    var first: Boolean,
    var numberOfElements: Int,
    var empty: Boolean
    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Post),
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
        parcel.writeTypedList(content)
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

    companion object CREATOR : Parcelable.Creator<PostList> {
        override fun createFromParcel(parcel: Parcel): PostList {
            return PostList(parcel)
        }

        override fun newArray(size: Int): Array<PostList?> {
            return arrayOfNulls(size)
        }
    }

}
