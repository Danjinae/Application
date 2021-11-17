package com.danjinae.vo

import android.os.Parcel
import android.os.Parcelable

data class GuestVehicleModel (
    var userId: Int = 0,
    var phone: String? = null,
    var number: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
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

    companion object CREATOR : Parcelable.Creator<GuestVehicleModel> {
        override fun createFromParcel(parcel: Parcel): GuestVehicleModel {
            return GuestVehicleModel(parcel)
        }

        override fun newArray(size: Int): Array<GuestVehicleModel?> {
            return arrayOfNulls(size)
        }
    }
}

