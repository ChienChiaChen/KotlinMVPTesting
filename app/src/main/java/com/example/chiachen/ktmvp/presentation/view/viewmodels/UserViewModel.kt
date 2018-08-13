package com.example.chiachen.ktmvp.presentation.view.viewmodels

import android.os.Parcel
import android.os.Parcelable

data class UserViewModel(val userId: Long,
                         val displayName: String,
                         val reputation: Long,
                         val profileImage: String) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<UserViewModel> = object : Parcelable.Creator<UserViewModel> {
            override fun createFromParcel(source: Parcel): UserViewModel = UserViewModel(source)
            override fun newArray(size: Int): Array<UserViewModel?> = arrayOfNulls(size)
        }
    }
    constructor(source: Parcel) : this(
            source.readLong(),
            source.readString(),
            source.readLong(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(userId)
        dest.writeString(displayName)
        dest.writeLong(reputation)
        dest.writeString(profileImage)
    }
}