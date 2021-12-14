package com.danjinae.vo

import android.os.Parcel
import android.os.Parcelable

data class PostResponse(
    var aptId: Int = 0,
    var content: String? = null,
    var postId: Int = 0,
    var title: String? = null,
    var userId: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(aptId)
        parcel.writeString(content)
        parcel.writeInt(postId)
        parcel.writeString(title)
        parcel.writeInt(userId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PostResponse> {
        override fun createFromParcel(parcel: Parcel): PostResponse {
            return PostResponse(parcel)
        }

        override fun newArray(size: Int): Array<PostResponse?> {
            return arrayOfNulls(size)
        }
    }
}