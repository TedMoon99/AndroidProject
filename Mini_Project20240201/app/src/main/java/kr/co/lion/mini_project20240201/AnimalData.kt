package kr.co.lion.mini_project20240201

import android.os.Parcel
import android.os.Parcelable


data class AnimalData(var name:String?, var type: String?): Parcelable {
    constructor(parcel: Parcel) : this(
        // 객체를 읽어온다.
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        // 객체에 작성한다.
        dest.writeString(name)
        dest.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AnimalData> {
        override fun createFromParcel(parcel: Parcel): AnimalData {
            return AnimalData(parcel)
        }

        override fun newArray(size: Int): Array<AnimalData?> {
            return arrayOfNulls(size)
        }
    }


}