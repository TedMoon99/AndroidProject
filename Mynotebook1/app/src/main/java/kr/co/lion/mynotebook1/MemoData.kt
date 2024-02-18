package kr.co.lion.mynotebook1

import android.os.Parcel
import android.os.Parcelable

class MemoData(var title: String?, var content:String?, var date: String?): Parcelable {
    constructor(parcel: Parcel) : this(
        title = parcel.readString(),
        content = parcel.readString(),
        date = parcel.readString()
    ) {
    }
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(content)
        dest.writeString(date)
    }

    companion object CREATOR : Parcelable.Creator<MemoData> {
        override fun createFromParcel(parcel: Parcel): MemoData {
            return MemoData(parcel)
        }

        override fun newArray(size: Int): Array<MemoData?> {
            return arrayOfNulls(size)
        }
    }

}