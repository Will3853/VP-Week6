package com.william.week6.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.william.week6.ui.widget.AddProjectDialog
import com.william.week6.viewmodel.ProjectUiState
import com.william.week6.viewmodel.ProjectViewModel

@Composable
fun ProjectScreen(
    onBack: () -> Unit,
    viewModel: ProjectViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ProjectContent(
        uiState = uiState,
        onBack = onBack,
        onNameChange = viewModel::onNameChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onTechStackChange = viewModel::onTechStackChange,
        onSave = viewModel::onSubmit,
        onDeleteProject = viewModel::onDeleteProject
    )
}

@Composable
fun ProjectContent(
    uiState: ProjectUiState,
    onBack: () -> Unit,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onTechStackChange: (String) -> Unit,
    onSave: () -> Unit,
    onDeleteProject: (String) -> Unit
) {
    var showAddProjectDialog by remember { mutableStateOf(false) }
    val community = uiState.community
    if (community != null){
        val projectList = uiState.projectList.filter { it.communityId == community.id }
        Box {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier.padding(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")

                    }
                    Column() {
                        Text(
                            text = uiState.community.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Text(
                            text = uiState.community.category,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF7A7979)
                        )
                    }
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color(0xFFE5E7EB)
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    item {
                        Column {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 15.dp, vertical = 12.dp),
                                shape = RoundedCornerShape(24.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFFE8F1FF)
                                ),
                                border = BorderStroke(
                                    1.dp,
                                    Color(0xFFC5D9F2)
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 2.dp
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp, vertical = 12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column(
                                    ) {
                                        Text(
                                            text = community.name,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF263F78)
                                        )

                                        Text(
                                            text = "${community.memberCount} Anggota • ${projectList.size} Proyek Aktif",
                                            fontSize = 12.sp,
                                            color = Color(0xFF6089C5)
                                        )
                                    }

                                    Surface(
                                        shape = RoundedCornerShape(50),
                                        color = Color.White,
                                        border = BorderStroke(
                                            1.dp,
                                            Color(0xFFE7CACA)
                                        )
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(
                                                horizontal = 14.dp,
                                                vertical = 6.dp
                                            ),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = "Joined",
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFFB45B5B)
                                            )

                                            Spacer(modifier = Modifier.width(4.dp))

                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = "Joined",
                                                tint = Color(0xFFB45B5B),
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    }
                                }
                            }
                            Row(Modifier.padding(15.dp)) {
                                Icon(
                                    Icons.Filled.ContentCopy,
                                    contentDescription = "",
                                    tint = Color(
                                        0xFF6A32C2
                                    )
                                )
                                Text("Daftar Proyek Mahasiswa (${projectList.size})", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            }
                        }

                    }

                    items(projectList) { project ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp, vertical = 8.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            border = BorderStroke(1.dp, Color(0xFFE5E7EB))
                        ) {
                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(15.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(48.dp)
                                            .background(
                                                project.iconBackground,
                                                RoundedCornerShape(16.dp)
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = project.icon,
                                            contentDescription = "",
                                            tint = project.iconColor,
                                            modifier = Modifier.size(32.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(12.dp))

                                    Column {
                                        Text(
                                            text = project.name,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp
                                        )

                                        Text(
                                            text = project.description,
                                            fontSize = 12.sp,
                                            color = Color.Gray
                                        )
                                    }
                                    Spacer(Modifier.weight(1f))
                                    TextButton(
                                        modifier = Modifier.size(40.dp),
                                        onClick = { onDeleteProject(project.id) },
                                        contentPadding = PaddingValues(0.dp),
                                        colors = ButtonDefaults.textButtonColors(
                                            containerColor = Color(0xFFFF7C7C),
                                        ),

                                    ) {
                                        Text("-", color = Color(0xFFC93030), fontSize = 30.sp, textAlign = TextAlign.Center)

                                    }
                                }
                                FlowRow(
                                    Modifier.padding(15.dp),
                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                                    verticalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    project.techStack.forEach { tech ->
                                        Box(
                                            modifier = Modifier
                                                .background(
                                                    Color(0xF2E9F4FF),
                                                    RoundedCornerShape(16.dp)
                                                )
                                                .height(25.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = tech,
                                                modifier = Modifier.padding(
                                                    horizontal = 8.dp
                                                ),
                                                fontSize = 10.sp,
                                                color = Color(0xFF2D2D2D)
                                            )
                                        }
                                    }
                                }

                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = Color(0xFFE5E7EB)
                                )

                                Row(modifier = Modifier.padding(15.dp)) {
                                    Text("ID: ${project.id}", fontSize = 12.sp, color = Color(0xFF2D2D2D))
                                    Spacer(Modifier.weight(1f))
                                    Box(
                                        modifier = Modifier
                                            .height(22.dp)
                                            .width(70.dp)
                                            .clip(RoundedCornerShape(15.dp))
                                            .background(Color(0xFFFFF4CF)),
                                        contentAlignment = Alignment.Center
                                    ){
                                        Text("• ${project.status}", fontWeight = FontWeight.Bold, color = Color(
                                            0xFFA98600
                                        ), fontSize = 10.sp,)
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Button(
                onClick = { showAddProjectDialog = true },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(24.dp)
                    .height(64.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFDCE8FF),
                    contentColor = Color(0xFF304F9B)
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp
                ),
                contentPadding = PaddingValues(horizontal = 24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Project",
                    modifier = Modifier.size(28.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Add Project",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        if (showAddProjectDialog) {
            AddProjectDialog(
                communityName = community.name,
                projectName = uiState.name,
                projectDescription = uiState.description,
                selectedTechStacks = uiState.techStack,

                onNameChange = onNameChange,
                onDescriptionChange = onDescriptionChange,
                onTechStackChange = onTechStackChange,

                onDismiss = {

                    showAddProjectDialog = false
                },

                onSave = {
                    onSave()
                    showAddProjectDialog = false
                }
            )
        }
    } else {
        Row(
            modifier = Modifier.padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBack
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")

            }
            Column() {
                Text("Data pemeran tidak ditemukan.", modifier = Modifier.padding(16.dp))
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProjectScreen() {
    ProjectContent(
        uiState = ProjectUiState(),
        onBack = {},
        onNameChange = {},
        onDescriptionChange = {},
        onTechStackChange = {},
        onSave = {},
        onDeleteProject = {}
    )
}