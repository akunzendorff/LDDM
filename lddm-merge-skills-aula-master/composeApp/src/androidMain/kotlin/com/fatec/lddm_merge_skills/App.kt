package com.fatec.lddm_merge_skills

import android.R.attr.fontWeight
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

import lddm_merge_skills.composeapp.generated.resources.Res
import lddm_merge_skills.composeapp.generated.resources.compose_multiplatform

private val Border = Color(0xFFE5E7EB)
private val Muted = Color(0xFF6B7280)

@Composable
fun App(){
    MaterialTheme {
        DashboardScreen()
    }
}

@Composable
@Preview
fun DashboardScreen() {

    // Estados
    var coursesCount by remember { mutableStateOf(0) }
    var lessonsCount by remember { mutableStateOf(0) }
    var questionsCount by remember { mutableStateOf(0) }
    var loading by remember { mutableStateOf(true) }

    // Escopo permite lançar tarefas assíncronas
    val scope = rememberCoroutineScope()

    fun refresh(){
        scope.launch {
            loading = true

            try {

                val courses = ApiClient.getCourses()
                coursesCount = courses.size

                lessonsCount = 0
                questionsCount = 0

            } catch (e: Exception){
                e.printStackTrace()
            }

            loading = false
        }
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier.fillMaxSize().statusBarsPadding().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // Header
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                Column {
                    Text("Painel Principal", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text("Visão Geral do Sistema", fontSize = 14.sp, color = Muted)
                    OutlinedButton(onClick = {}){
                        Text("Atualizar", color = Color.Black)
                    }

                }

                HorizontalDivider(color = Border)

                // Cards
                Row(Modifier.fillMaxWidth(), Arrangement.spacedBy(12.dp)) {
                    DashboardCard("Cursos", "0", Modifier.weight(1f))
                    DashboardCard("Lições", "0", Modifier.weight(1f))
                    DashboardCard("Questões", "0", Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun DashboardCard(title: String, value: String, modifier: Modifier = Modifier) {
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(0.dp),
        border = BorderStroke(1.dp, Border),
        colors = CardDefaults.outlinedCardColors(containerColor = Color.White)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(title, fontSize = 13.sp, color = Muted, fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(4.dp))
            Text(value, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        }
    }
}