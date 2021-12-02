package com.danjinae.vo

import android.os.Parcel
import android.os.Parcelable

data class VehicleResponse(
    var endDate: String? = null,
    var guest: Boolean = true,
    var number: String? = null,
    var phone: String? = null,
    var startDate: String? = null,
    var userId: Int = 0,
    var vehicleId: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(endDate)
        parcel.writeByte(if (guest) 1 else 0)
        parcel.writeString(number)
        parcel.writeString(phone)
        parcel.writeString(startDate)
        parcel.writeInt(userId)
        parcel.writeInt(vehicleId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VehicleResponse> {
        override fun createFromParcel(parcel: Parcel): VehicleResponse {
            return VehicleResponse(parcel)
        }

        override fun newArray(size: Int): Array<VehicleResponse?> {
            return arrayOfNulls(size)
        }
    }
}
