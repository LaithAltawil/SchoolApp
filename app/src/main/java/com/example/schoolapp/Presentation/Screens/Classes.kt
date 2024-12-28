package com.example.schoolapp.Presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.schoolapp.Data.classInfo
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.Presentation.VM.States.SessionLoadingState
import com.example.schoolapp.Presentation.VM.States.SessionState
import com.example.schoolapp.datasource.online.model.SessionModel

@Composable
fun StudentClass(
    mainViewModel: MainViewModel,
    navController: NavController
) {
    val sessionState by mainViewModel.sessionState.collectAsState()
    val loadingState by mainViewModel.sessionLoadingState.collectAsState()

    // Initialize sessions when screen is first loaded
    LaunchedEffect(Unit) {
        mainViewModel.initializeSessions()
    }

    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ClassesScreen(
                sessionState = sessionState,
                loadingState = loadingState,
                onDayChanged = { mainViewModel.setCurrentDay(it) },
                onBackClick = { navController.popBackStack()},
                mainViewModel = mainViewModel
            )
        }
    }
}

@Composable
private fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun ClassesScreen(
    sessionState: SessionState,
    loadingState: SessionLoadingState,
    onDayChanged: (String) -> Unit,
    onBackClick: () -> Unit,
    mainViewModel: MainViewModel
) {
    Scaffold(
        topBar = {
            ClassesTopBar(onBackClick = onBackClick)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            DaySelector(
                currentDay = sessionState.currentDay,
                availableDays = sessionState.availableDays,
                onDaySelected = onDayChanged
            )

            when (loadingState) {
                is SessionLoadingState.LoadingData -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is SessionLoadingState.Error -> {
                    ErrorMessage(message = loadingState.message)
                }

                else -> {
                    SessionsList(sessions = sessionState.sessions,
                        mainViewModel = mainViewModel)
                }
            }
        }
    }
}

@Composable
private fun ClassesTopBar(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
            .height(120.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    Icons.Outlined.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            Text(
                text = "Classes",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
private fun DaySelector(
    currentDay: String,
    availableDays: List<String>,
    onDaySelected: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(availableDays) { day ->
            DayChip(
                day = day,
                selected = day == currentDay,
                onClick = { onDaySelected(day) }
            )
        }
    }
}

@Composable
private fun DayChip(
    day: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        color = if (selected) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = if (selected) 4.dp else 0.dp
    ) {
        Text(
            text = day,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = if (selected) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun SessionsList(
    sessions: List<SessionModel>, mainViewModel: MainViewModel
) {
    if (sessions.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No sessions for this day",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(sessions) { session ->
                SessionCard(
                    session = session,
                    mainViewModel = mainViewModel
                )
            }
        }
    }
}

@Composable
private fun SessionCard(
    session: SessionModel,
    mainViewModel: MainViewModel
) {
    val teacherName by mainViewModel.teacherName.collectAsState()
    LaunchedEffect(session.sessionTeacherId) {
        mainViewModel.fetchTeacherName(session.sessionTeacherId)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Session ${session.session}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = session.day,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = session.sessionTeacherSubject,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = when {
                    teacherName != null -> "Teacher: $teacherName"
                    else -> "Loading teacher..."
                },
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ErrorMessage(message: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
    }
}

