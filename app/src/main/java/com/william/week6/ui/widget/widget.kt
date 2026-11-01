package com.william.week6.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddProjectDialog(
    communityName: String,
    projectName: String,
    projectDescription: String,
    selectedTechStacks: List<String>,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onTechStackChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {
    val techStacks = listOf(
        "Kotlin",
        "Jetpack Compose",
        "Firebase",
        "Ktor",
        "Room",
        "Python",
        "Google Maps SDK"
    )

    AlertDialog(
        onDismissRequest = {
        },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        shape = RoundedCornerShape(24.dp),
        containerColor = Color.White,

        title = {
            Column {
                Text(
                    text = "Add Project",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Komunitas: $communityName",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        },

        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column {
                    Text(
                        text = "Judul Proyek *",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )

                    OutlinedTextField(
                        value = projectName,
                        onValueChange = onNameChange,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        singleLine = true,
                        placeholder = {
                            Text("Masukkan judul proyek")
                        }
                    )
                }

                Column {
                    Text(
                        text = "Deskripsi Proyek (Minimal 3 Baris) *",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 13.sp
                    )

                    OutlinedTextField(
                        value = projectDescription,
                        onValueChange = onDescriptionChange,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        minLines = 3,
                        placeholder = {
                            Text("Masukkan deskripsi proyek")
                        }
                    )
                }
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Tech Stack (Multi-Selection)",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "${selectedTechStacks.size} Dipilih",
                            fontSize = 11.sp,
                            color = Color(0xFF5275C9),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        techStacks.forEach { tech ->
                            val isSelected = tech in selectedTechStacks

                            FilterChip(
                                selected = isSelected,
                                onClick = {
                                    onTechStackChange(tech)
                                },
                                label = {
                                    Text(
                                        text = tech,
                                        fontSize = 11.sp
                                    )
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = if (isSelected) {
                                            Icons.Default.Check
                                        } else {
                                            Icons.Default.Add
                                        },
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp)
                                    )
                                },
                                shape = RoundedCornerShape(10.dp),
                                border = FilterChipDefaults.filterChipBorder(
                                    enabled = true,
                                    selected = isSelected,
                                    borderColor = Color.LightGray,
                                    selectedBorderColor = Color(0xFF405ED0),
                                    borderWidth = 1.dp,
                                    selectedBorderWidth = 1.5.dp
                                ),
                                colors = FilterChipDefaults.filterChipColors(
                                    containerColor = Color.White,
                                    labelColor = Color.DarkGray,
                                    selectedContainerColor = Color(0xFFE8EDFF),
                                    selectedLabelColor = Color(0xFF405ED0),
                                    iconColor = Color.Gray,
                                    selectedLeadingIconColor = Color(0xFF405ED0)
                                )
                            )
                        }
                    }
                }
            }
        },

        confirmButton = {
            Button(
                onClick = onSave,
                enabled = projectName.isNotBlank() &&
                        projectDescription.isNotBlank() &&
                        selectedTechStacks.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF405ED0)
                )
            ) {
                Text("Save")
            }
        },

        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = "Cancel",
                    color = Color(0xFF405ED0)
                )
            }
        }
    )
}