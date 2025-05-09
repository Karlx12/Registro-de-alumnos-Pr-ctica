package hrcode.labs.registrodealumnos.data.student

object  StudentContract {

        const val TABLE_NAME = "students"
        object Columns {
            const val ID = "id"
            const val NAME = "name"
            const val LASTNAME = "lastName"
            const val STUDENTCODE = "studentCode"
            const val EMAIL = "email"
        }


}