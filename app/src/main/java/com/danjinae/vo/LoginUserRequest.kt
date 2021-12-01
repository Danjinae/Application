package com.danjinae.vo

import android.os.Parcel
import android.os.Parcelable

data class LoginUserRequest(
    var password: String? = null,
    var phone: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(password)
        parcel.writeString(phone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoginUserRequest> {
        override fun createFromParcel(parcel: Parcel): LoginUserRequest {
            return LoginUserRequest(parcel)
        }

        override fun newArray(size: Int): Array<LoginUserRequest?> {
            return arrayOfNulls(size)
        }
    }
}
