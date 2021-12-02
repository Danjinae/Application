package com.danjinae.vo

import android.os.Parcel
import android.os.Parcelable

data class VehicleRequest (
    var endDate: String? = null,
    var number: String? = null,
    var phone: String? = null,
    var startDate: String? = null,
    var userId: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        //parcel.readInt(),
        //parcel.readString(),
        //parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(userId)
        parcel.writeString(phone)
        parcel.writeString(number)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VehicleRequest> {
        override fun createFromParcel(parcel: Parcel): VehicleRequest {
            return VehicleRequest(parcel)
        }

        override fun newArray(size: Int): Array<VehicleRequest?> {
            return arrayOfNulls(size)
        }
    }
}

