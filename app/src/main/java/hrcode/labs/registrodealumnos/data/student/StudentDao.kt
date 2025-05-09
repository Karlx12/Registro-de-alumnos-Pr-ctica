package hrcode.labs.registrodealumnos.data.student

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import hrcode.labs.registrodealumnos.domain.Student

class StudentDao(private val db: SQLiteDatabase) {

    fun insert(student: Student): Long {
        val values = ContentValues().apply {
            put(StudentContract.Columns.NAME, student.name)
            put(StudentContract.Columns.LASTNAME, student.lastName)
            put(StudentContract.Columns.STUDENTCODE, student.studentCode)
            put(StudentContract.Columns.EMAIL, student.email)
        }
        return db.insert(StudentContract.TABLE_NAME, null, values)
    }

    fun getAll(): List<Student> {
        val students = mutableListOf<Student>()
        val cursor: Cursor = db.query(
            StudentContract.TABLE_NAME,
            null, null, null, null, null, null
        )

        cursor.use {
            while (it.moveToNext()) {
                students.add(
                    Student(
                        id = it.getInt(it.getColumnIndexOrThrow(StudentContract.Columns.ID)),
                        name = it.getString(it.getColumnIndexOrThrow(StudentContract.Columns.NAME)),
                        lastName = it.getString(it.getColumnIndexOrThrow(StudentContract.Columns.LASTNAME)),
                        studentCode = it.getString(it.getColumnIndexOrThrow(StudentContract.Columns.STUDENTCODE)),
                        email = it.getString(it.getColumnIndexOrThrow(StudentContract.Columns.EMAIL)),
                    )
                )
            }
        }
        return students
    }
    fun update(student: Student): Int {
        val values = ContentValues().apply {
            put(StudentContract.Columns.NAME, student.name)
            put(StudentContract.Columns.LASTNAME, student.lastName)
            put(StudentContract.Columns.STUDENTCODE, student.studentCode)
            put(StudentContract.Columns.EMAIL, student.email)
        }
        return db.update(
            StudentContract.TABLE_NAME,
            values,
            "${StudentContract.Columns.ID} = ?",
            arrayOf(student.id.toString())
        )
    }
    fun delete(id: Int): Int {
        return db.delete(
            StudentContract.TABLE_NAME,
            "${StudentContract.Columns.ID} = ?",
            arrayOf(id.toString())
        )
    }

}
