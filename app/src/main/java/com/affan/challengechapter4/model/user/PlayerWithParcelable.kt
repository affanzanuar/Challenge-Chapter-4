package com.affan.challengechapter4.model.user

import android.os.Parcel
import android.os.Parcelable

data class PlayerWithParcelable (val playerName : String?) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(playerName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlayerWithParcelable> {
        override fun createFromParcel(parcel: Parcel): PlayerWithParcelable {
            return PlayerWithParcelable(parcel)
        }

        override fun newArray(size: Int): Array<PlayerWithParcelable?> {
            return arrayOfNulls(size)
        }
    }
}