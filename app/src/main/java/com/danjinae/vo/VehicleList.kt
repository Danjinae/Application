package com.danjinae.vo

import android.os.Parcel
import android.os.Parcelable

data class VehicleList(
    var content: MutableList<VehicleResponse>? = null,
    var pageable: Pageable?,
    var totalPages: Int = 0,
    var totalElements: Int = 0,
    var last: Boolean = true,
    var size: Int = 0,
    var number: Int = 0,
    var sort: Sort?,
    var numberOfElements: Int = 0,
    var first: Boolean = true,
    var empty: Boolean = true
) : Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("content"),
        parcel.readParcelable(Pageable::class.java.classLoader),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readParcelable(Sort::class.java.classLoader),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(pageable, flags)
        parcel.writeInt(totalPages)
        parcel.writeInt(totalElements)
        parcel.writeByte(if (last) 1 else 0)
        parcel.writeInt(size)
        parcel.writeInt(number)
        parcel.writeParcelable(sort, flags)
        parcel.writeInt(numberOfElements)
        parcel.writeByte(if (first) 1 else 0)
        parcel.writeByte(if (empty) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VehicleList> {
        override fun createFromParcel(parcel: Parcel): VehicleList {
            return VehicleList(parcel)
        }

        override fun newArray(size: Int): Array<VehicleList?> {
            return arrayOfNulls(size)
        }
    }
}
