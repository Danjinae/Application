package com.danjinae.vo

import android.os.Parcel
import android.os.Parcelable

data class NoticeListResponse(
    var id: Int = 0,
    var content: String? = null,
    var startDate: String? = null,
    var endDate: String? = null,
    var catId: Int = 0,
    var read: Boolean = true
    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        TODO("content"),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(startDate)
        parcel.writeString(endDate)
        parcel.writeInt(catId)
        parcel.writeByte(if (read) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NoticeListResponse> {
        override fun createFromParcel(parcel: Parcel): NoticeListResponse {
            return NoticeListResponse(parcel)
        }

        override fun newArray(size: Int): Array<NoticeListResponse?> {
            return arrayOfNulls(size)
        }
    }
}