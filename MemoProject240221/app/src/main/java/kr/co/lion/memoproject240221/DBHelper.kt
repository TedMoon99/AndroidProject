package kr.co.lion.memoproject240221

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Test.db", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        // Int -> integer
        // Double -> real
        // String -> text
        // 날짜 -> date
        val sql = """create table MemoTable
            | (idx integer primary key autoincrement,
            |  TITLE text not null,
            |  CONTENT integer not null,
            |  DATE integer not null)
        """.trimMargin()

        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}