package hrcode.labs.registrodealumnos.domain

data class Student (
    val id: Int=0,
    val name: String,
    val lastName: String,
    val studentCode: String,
    val email: String
)
