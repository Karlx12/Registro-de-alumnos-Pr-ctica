package hrcode.labs.registrodealumnos

import android.app.Application
import hrcode.labs.registrodealumnos.data.student.StudentRepository

class App: Application() {
    val studentRepository by lazy  {
        StudentRepository(applicationContext)
    }
}