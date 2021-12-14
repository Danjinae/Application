package com.danjinae.vo

import android.os.Parcel
import android.os.Parcelable

data class Auth(
    var aptId: Int = 0,
    var impId: String? = null,
    var phone: String? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(aptId)
        parcel.writeString(impId)
        parcel.writeString(phone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Auth> {
        override fun createFromParcel(parcel: Parcel): Auth {
            return Auth(parcel)
        }

        override fun newArray(size: Int): Array<Auth?> {
            return arrayOfNulls(size)
        }
    }
}