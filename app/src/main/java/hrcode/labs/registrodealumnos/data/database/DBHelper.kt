package hrcode.labs.registrodealumnos.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import hrcode.labs.registrodealumnos.data.student.StudentContract

class DbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "StudentsRegister.db"
        private const val DATABASE_VERSION = 1

        private const val SQL_CREATE_TABLE = """
            CREATE TABLE ${StudentContract.TABLE_NAME} (
                ${StudentContract.Columns.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${StudentContract.Columns.NAME} TEXT NOT NULL,
                ${StudentContract.Columns.LASTNAME} TEXT NOT NULL,
                ${StudentContract.Columns.STUDENTCODE} TEXT NOT NULL,
                ${StudentContract.Columns.EMAIL} TEXT NOT NULL
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${StudentContract.TABLE_NAME}")
        onCreate(db)
    }
}