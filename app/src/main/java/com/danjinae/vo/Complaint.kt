package com.danjinae.vo

import android.os.Parcel
import android.os.Parcelable

data class Complaint(
    var aptId: Int = 0,
    var content: String? = null,
    var userId: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(aptId)
        parcel.writeString(content)
        parcel.writeInt(userId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Complaint> {
        override fun createFromParcel(parcel: Parcel): Complaint {
            return Complaint(parcel)
        }

        override fun newArray(size: Int): Array<Complaint?> {
            return arrayOfNulls(size)
        }
    }
}