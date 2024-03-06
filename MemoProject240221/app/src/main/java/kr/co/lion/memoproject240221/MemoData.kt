package kr.co.lion.memoproject240221

import android.os.Parcel
import android.os.Parcelable

class MemoData(var title: String?, var content: String?, var date: String?): Parcelable {
    constructor(parcel: Parcel) : this(
        title = parcel.readString(),
        content = parcel.readString(),
        date = parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(content)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
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