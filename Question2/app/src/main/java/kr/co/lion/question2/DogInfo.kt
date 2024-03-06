package kr.co.lion.question2

import android.os.Parcel
import android.os.Parcelable

class DogInfo(var name : String?, var type : String?, var age : Int) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DogInfo> {
        override fun createFromParcel(parcel: Parcel): DogInfo {
            return DogInfo(parcel)
        }

        override fun newArray(size: Int): Array<DogInfo?> {
            return arrayOfNulls(size)
        }
    }

}