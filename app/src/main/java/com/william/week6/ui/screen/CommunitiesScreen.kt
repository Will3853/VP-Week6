package com.william.week6.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.william.week6.viewmodel.CommunitiesUiState
import com.william.week6.viewmodel.CommunitiesViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CommunitiesScreen(
    onHome: () -> Unit,
    viewModel: CommunitiesViewModel = viewModel(),
    onProject: (projectId: Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {
        CommunitiesContent(
            onProject = onProject,
            uiState = uiState,
            onHome = onHome,
            onJoinCommunity = viewModel::joinCommunity,
            onShowSnackbar = {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        "Harap bergabung ke komunitas terlebih dahulu!"
                    )
                }
            }
        )
    }
}
@Composable
fun CommunitiesContent(
    onProject: (projectId: Int) -> Unit,
    onJoinCommunity: (Int) -> Unit,
    uiState: CommunitiesUiState,
    onHome: () -> Unit,
    onShowSnackbar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(Modifier.weight(1f)) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column() {
                            Text("Communities Directory", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                            Text(
                                "Pilih Komunitas Untuk Membuka Club Projects",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF7A7979)
                            )
                        }
                        Icon(Icons.Filled.Search, contentDescription = "")
                    }
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color(0xFFE5E7EB)
                )
            }
            items(uiState.communityList) { community ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .padding(vertical = 10.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFFE0D6D6),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
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
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )

                            Text(text = "${community.category} • ${community.memberCount} Mahasiswa", fontSize = 12.sp, color = Color(0xFF6B6B6B))
                        }
                    }
                    Box(Modifier.padding(horizontal = 15.dp).padding(bottom = 15.dp)){
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(community.description, fontSize = 12.sp, color = Color(0xFF6B6B6B), fontWeight = FontWeight.W400)
                            HorizontalDivider(
                                thickness = 1.dp,
                                color = Color(0xFFE5E1E1)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()) {
                                if (community.joined) {
                                    Button(onClick = { onProject(community.id) },
                                        modifier =  Modifier
                                            .height(20.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Transparent,
                                            contentColor = Color(0xFF0080D5)
                                        ),
                                        contentPadding = PaddingValues(0.dp))
                                    {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                                        ) {
                                            Icon(Icons.Filled.LockOpen, contentDescription = "", tint = Color(0xFF0080D5), modifier = Modifier.size(15.dp))
                                            Text("Unlocked • Buka Proyek", fontSize = 10.sp, color = Color(0xFF0080D5))
                                            Icon(Icons.Filled.ArrowForward, contentDescription = "", modifier = Modifier.size(10.dp), tint = Color(0xFF0080D5))
                                        }
                                    }
                                    Spacer(Modifier.weight(1f))

                                    Button(
                                        onClick = {  },
                                        modifier = Modifier
                                            .height(30.dp)
                                            .width(80.dp),
                                        shape = RoundedCornerShape(15.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.White,
                                            contentColor = Color(0xFFA20000)
                                        ),
                                        border = BorderStroke(1.dp, Color(0xFFA20000)),
                                        contentPadding = PaddingValues(0.dp)
                                    ) {
                                        Text(
                                            "✓ Joined",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp
                                        )
                                    }
                                }else {
                                    Button(onClick = { onShowSnackbar() },
                                        modifier =  Modifier
                                            .height(20.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Transparent,
                                            contentColor = Color(0xFF0080D5)
                                        ),
                                        contentPadding = PaddingValues(0.dp))
                                    {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                                        ) {
                                            Icon(Icons.Filled.Lock, contentDescription = "", tint = Color(
                                                0xFF707070
                                            ), modifier = Modifier.size(15.dp))
                                            Text("Terkunci (Gate Akses)", fontSize = 10.sp, color = Color(0xFF707070))
                                            Icon(Icons.Filled.ArrowForward, contentDescription = "", modifier = Modifier.size(10.dp), tint = Color(0xFF707070))
                                        }
                                    }
                                    Spacer(Modifier.weight(1f))

                                    Button(
                                        onClick = {
                                            onJoinCommunity(community.id)
                                        },
                                        modifier = Modifier
                                            .height(30.dp)
                                            .width(80.dp),
                                        shape = RoundedCornerShape(15.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFF4162D3),
                                            contentColor = Color.White
                                        ),
                                        contentPadding = PaddingValues(0.dp)
                                    ) {
                                        Text(
                                            "+  Join",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp
                                        )
                                    }

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
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            onHome()
                        }
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Icon(Icons.Filled.Home, contentDescription = "Communities")
                    Text("Dashboard")
                }
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
                        Icon(Icons.Filled.People, contentDescription = "", tint = Color(0xFF263183))
                    }
                    Text("Communities", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCommunitiesScreen() {
    CommunitiesScreen (
        onProject = {},
        onHome = {}
    )
}