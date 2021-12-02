package com.danjinae.vo

import android.os.Parcel
import android.os.Parcelable

data class AptListResponse(
    var aptId: Int = 0,
    var name: String? = null,
    var address: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address)
        parcel.writeInt(aptId)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AptListResponse> {
        override fun createFromParcel(parcel: Parcel): AptListResponse {
            return AptListResponse(parcel)
        }

        override fun newArray(size: Int): Array<AptListResponse?> {
            return arrayOfNulls(size)
        }
    }
}
