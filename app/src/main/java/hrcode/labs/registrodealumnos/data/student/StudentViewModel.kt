package hrcode.labs.registrodealumnos.data.student

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hrcode.labs.registrodealumnos.domain.Student
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StudentViewModel(
    private val repository: StudentRepository
): ViewModel() {
    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students: StateFlow<List<Student>> = _students

    init {
        loadStudents()
    }


    fun loadStudents() {
        viewModelScope.launch {
            _students.value = repository.getAll()
        }
    }

    fun add(student: Student) {
        viewModelScope.launch {
            repository.add(student)
            loadStudents()
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch {
            repository.remove(id)
            loadStudents()
        }
    }
    override fun onCleared() {
        repository.close()
        super.onCleared()
    }
    fun sortStudents(sortOption: Pair<String, Boolean>) {
        val (field, ascending) = sortOption
        _students.value = when (field) {
            "name" -> {
                if (ascending) _students.value.sortedBy { it.name }
                else _students.value.sortedByDescending { it.name }
            }
            "lastName" -> {
                if (ascending) _students.value.sortedBy { it.lastName }
                else _students.value.sortedByDescending { it.lastName }
            }
            "studentCode" -> {
                if (ascending) _students.value.sortedBy { it.studentCode }
                else _students.value.sortedByDescending { it.studentCode }
            }
            else -> _students.value
        }
    }

    fun dummyData() {
        val dummyStudents = listOf(
            Student(1, "John", "Doe", "0202314001", "202314001@uns.edu.pe"),
            Student(2, "Jane", "Smith", "0202314002", "202314002@uns.edu.pe"),
            Student(3, "Alice", "Johnson", "0202314003", "202314003@uns.edu.pe"),
            Student(4, "Bob", "Brown", "0202314004", "202314004@uns.edu.pe"),
            Student(5, "Charlie", "Davis", "0202314005", "202314005@uns.edu.pe"))

        viewModelScope.launch {
            for (student in dummyStudents) {
                repository.add(student)
            }
            loadStudents()
        }
    }

}