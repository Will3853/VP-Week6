package com.william.week6.ui.screen

import android.R
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.william.week6.viewmodel.DashboardUiState
import com.william.week6.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(
    onCommunities: () -> Unit,
    viewModel: DashboardViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DashboardContent(
        uiState = uiState,
        onCommunities = onCommunities,
    )
}

@Composable
fun DashboardContent(
    uiState: DashboardUiState,
    onCommunities: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            val joinedCommunities = uiState.communityList.filter { it.joined }
            item {
                Column() {
                    Box(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column() {
                                Text("CampusTech Hub", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                                Text(
                                    "Student Profile & Portfolio Dashboard",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color(0xFF7A7979)
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(Color(0xFF4A49D1))
                                    .size(30.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("AP", fontWeight = FontWeight.SemiBold, color = Color.White)
                            }
                        }
                    }
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color(0xFFE5E7EB)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFFCFEFF))
                            .padding(15.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(
                                    elevation = 12.dp,
                                    shape = RoundedCornerShape(16.dp),
                                    ambientColor = Color.Black,
                                    spotColor = Color.Black
                                ),
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        Brush.linearGradient(
                                            colors = listOf(
                                                Color(0xFF1C2B86),
                                                Color(0xFF4A4FFF)
                                            )
                                        )
                                    )
                                    .padding(20.dp)
                            )
                            {
                                Row(
                                    Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(40.dp))
                                            .background(Color.White)
                                            .size(65.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            "AP",
                                            fontWeight = FontWeight.ExtraBold,
                                            color = Color(0xFF1C2B86),
                                            fontSize = 24.sp
                                        )
                                    }

                                    Column() {
                                        Box(
                                            modifier = Modifier
                                                .width(110.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                                .background(
                                                    Color(0xFFD3D3D3).copy(alpha = 0.2f)
                                                ),
                                            contentAlignment = Alignment.Center
                                        )
                                        {
                                            Text(
                                                "Mahasiswa Aktif",
                                                color = Color(0xFFE7E7E7),
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        }
                                        Text(
                                            "Aditya Pratama",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 20.sp,
                                            color = Color.White
                                        )
                                        Text(
                                            "Teknik Informatika  •  Angkatan 2025",
                                            fontSize = 12.sp,
                                            color = Color(0xFFE7E7E7)
                                        )
                                        Text("NIM: 123456789", fontSize = 12.sp, color = Color(0xFFE7E7E7))
                                    }
                                }
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        )
                        {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding()
                                    .border(
                                        width = 1.dp,
                                        color = Color(0xFFE5E7EB),
                                        shape = RoundedCornerShape(16.dp)
                                    )
                            ) {
                                Box(modifier = Modifier.padding(15.dp)) {
                                    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(10.dp))
                                                .background(Color(0xFFE0E9FC))
                                                .size(40.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                Icons.Filled.People,
                                                contentDescription = "",
                                                tint = Color(0xFF1C2B86)
                                            )
                                        }
                                        Text("${joinedCommunities.size}", fontWeight = FontWeight.ExtraBold, fontSize = 28.sp)
                                        Text("Joined Clubs", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                        Text(
                                            "Komunitas yang Diikuti",
                                            fontSize = 12.sp,
                                            color = Color(0xFF6B6B6B)
                                        )
                                    }
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding()
                                    .border(
                                        width = 1.dp,
                                        color = Color(0xFFE5E7EB),
                                        shape = RoundedCornerShape(16.dp)
                                    )
                            ) {
                                Box(modifier = Modifier.padding(15.dp)) {
                                    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(10.dp))
                                                .background(Color(0xFFE0E9FC))
                                                .size(40.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                Icons.Filled.ContentCopy,
                                                contentDescription = "",
                                                tint = Color(
                                                    0xFF6A32C2
                                                )
                                            )
                                        }
                                        Text("${uiState.projectList.size}", fontWeight = FontWeight.ExtraBold, fontSize = 28.sp)
                                        Text(
                                            "Total Projects",
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            "Seluruh Proyek Dibuat",
                                            fontSize = 12.sp,
                                            color = Color(0xFF6B6B6B)
                                        )
                                    }
                                }
                            }
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Filled.Star, contentDescription = "", tint = Color(0xFF0040FF))
                            Text("Semua Komunitas yang Diikuti (${joinedCommunities.size})", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.weight(1f))
                            Text("Status: Joined", fontSize = 12.sp, color = Color(0xFF0093F5), fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            if (joinedCommunities.isEmpty()) {
                item {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                        Text(
                            text = "Belum bergabung dengan komunitas",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            } else {
                items(joinedCommunities) { community ->
                    val projectCount = uiState.projectList.count {
                        it.communityId == community.id
                    }
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp).padding(bottom = 15.dp)
                            .border(
                            width = 1.dp,
                            color = Color(0xFFE5E7EB),
                            shape = RoundedCornerShape(16.dp)
                        )
                    ) {
                        Row(Modifier.fillMaxWidth().padding(10.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(45.dp)
                                    .clip(RoundedCornerShape(15.dp))
                                    .background(community.iconBackground)
                                    .padding(3.dp),
                                contentAlignment = Alignment.Center
                            ){
                                Icon(community.icon, contentDescription = "", tint = community.iconColor)
                            }

                            Column(
                            ) {
                                Text(
                                    text = community.name,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(text = "${community.category} • $projectCount Proyek", fontSize = 12.sp, color = Color(0xFF6B6B6B))
                            }
                            Spacer(modifier = Modifier.weight(1f))

                            Box(
                                modifier = Modifier
                                    .height(22.dp)
                                    .width(60.dp)
                                    .clip(RoundedCornerShape(15.dp))
                                    .border(
                                        width = 1.dp,
                                        color = Color(0xFFE0D6D6),
                                        shape = RoundedCornerShape(16.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ){
                                Text("Joined ✓", fontWeight = FontWeight.Bold, color = Color(0xFFA20000), fontSize = 10.sp)
                            }

                        }
                    }
                }
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(15.dp)
                ) {
                    Icon(Icons.Filled.Star, contentDescription = "", tint = Color(0xFF0040FF))
                    Text("Semua Proyek Mahasiswa (${uiState.projectList.size})", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.weight(1f))
                    Text("Total: ${uiState.projectList.size} Proyek", fontSize = 12.sp, color = Color(0xFF0093F5), fontWeight = FontWeight.Bold)
                }
            }

            if (uiState.projectList.isEmpty()) {
                item {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                        Text(
                            text = "Belum ada proyek yang dicatat",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            } else {
                items(uiState.projectList) { project ->
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
        HorizontalDivider(
            thickness = 1.dp,
            color = Color(0xFFE5E1E1)
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color(0x88ACD6FF))
                            .padding(3.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Icon(Icons.Filled.Home, contentDescription = "", tint = Color(0xFF263183))
                    }
                    Text("Dashboard", fontWeight = FontWeight.Bold)
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            onCommunities()
                        }
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Icon(Icons.Filled.People, contentDescription = "Communities")
                    Text("Communities")
                }
            }
        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDashboard() {
    DashboardScreen (
        onCommunities = {}
    )
}