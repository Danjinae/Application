package com.danjinae.vo

import android.os.Parcel
import android.os.Parcelable

data class CommentResponse(
    var comment: String? = null,
    var commentId: Int = 0,
    var postId: Int = 0,
    var usrId: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(comment)
        parcel.writeInt(commentId)
        parcel.writeInt(postId)
        parcel.writeInt(usrId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CommentResponse> {
        override fun createFromParcel(parcel: Parcel): CommentResponse {
            return CommentResponse(parcel)
        }

        override fun newArray(size: Int): Array<CommentResponse?> {
            return arrayOfNulls(size)
        }
    }
}