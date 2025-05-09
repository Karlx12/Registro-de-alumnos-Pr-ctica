package hrcode.labs.registrodealumnos.data.student

import android.content.Context
import hrcode.labs.registrodealumnos.data.database.DbHelper
import hrcode.labs.registrodealumnos.domain.Student

class StudentRepository(context: Context) {

    private val dbHelper = DbHelper(context)
    private val dao by lazy { StudentDao(dbHelper.writableDatabase) }

    fun add(student: Student): Long{
        val studentSearched= dao.getAll().find { it.studentCode == student.studentCode }
        if (studentSearched != null) {
            return -1
        }
        return dao.insert(student)
    }
    fun getAll(): List<Student> = dao.getAll()
    fun remove(id:Int): Int = dao.delete(id)

    fun close() {
        dbHelper.close()
    }

}