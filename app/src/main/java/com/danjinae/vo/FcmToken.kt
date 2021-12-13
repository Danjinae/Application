package com.danjinae.vo

import android.os.Parcel
import android.os.Parcelable

data class FcmToken(
    var token: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(token)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FcmToken> {
        override fun createFromParcel(parcel: Parcel): FcmToken {
            return FcmToken(parcel)
        }

        override fun newArray(size: Int): Array<FcmToken?> {
            return arrayOfNulls(size)
        }
    }
}