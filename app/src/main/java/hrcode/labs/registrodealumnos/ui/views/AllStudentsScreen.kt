package hrcode.labs.registrodealumnos.ui.views


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import hrcode.labs.registrodealumnos.data.student.StudentViewModel
import hrcode.labs.registrodealumnos.domain.Student
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AllStudentsScreen(navController: NavController, viewModel: StudentViewModel) {
    val students by viewModel.students.collectAsState()
    ExpandableList(students, viewModel)
}

@Composable
fun ExpandableList(students: List<Student>, viewModel: StudentViewModel) {
    var currentSort by remember { mutableStateOf("name" to true) }

    Column {
        SortButton(
            currentSort,
            onSortChanged = { sortOption ->
                currentSort = sortOption
                viewModel.sortStudents(sortOption)
            }
        )

        LazyColumn {
            items(students) { student ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(
                            text = "${student.lastName}, ${student.name}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = student.studentCode,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Text(
                            text = student.email,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun SortButton(currentSort: Pair<String, Boolean>,
               onSortChanged: (Pair<String, Boolean>) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    val sortOptions = listOf(
        "name" to "Nombre",
        "lastName" to "Apellido",
        "studentCode" to "Código"
    )

    Box {
        OutlinedButton (
            onClick = { expanded = true }
        ) {
            val (field, ascending) = currentSort
            val fieldName = sortOptions.find { it.first == field }?.second ?: field
            val icon = if (ascending) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown

            Text("Ordenar por: $fieldName")
            Icon(
                imageVector = icon,
                contentDescription = if (ascending) "Ascendente" else "Descendente",
                modifier = Modifier.size(18.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            sortOptions.forEach { (fieldKey, fieldName) ->
                // Opción ascendente
                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("$fieldName (A-Z)")
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowUp,
                                contentDescription = "Ascendente",
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    },
                    onClick = {
                        onSortChanged(fieldKey to true)
                        expanded = false
                    }
                )

                // Opción descendente
                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("$fieldName (Z-A)")
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "Descendente",
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    },
                    onClick = {
                        onSortChanged(fieldKey to false)
                        expanded = false
                    }
                )
            }
        }
    }
}

