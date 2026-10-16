
package com.kapetz.ai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val DarkBg = Color(0xFF090C12)
private val CardBg = Color(0xFF101520)
private val Blue = Color(0xFF00E5FF)
private val LightText = Color(0xFFE2E8F0)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KapetzHome()
        }
    }
}

@Composable
fun KapetzHome() {
    var isFilipino by remember { mutableStateOf(true) }
    var search by remember { mutableStateOf("") }
    var selected by remember { mutableStateOf("Home") }

    val title = if (isFilipino) "KAPETZ AI" else "KAPETZ AI"
    val subtitle = if (isFilipino)
        "Exotic Pet Care Assistant"
    else
        "Exotic Pet Care Assistant"

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = DarkBg
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            title,
                            color = Blue,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            subtitle,
                            color = LightText,
                            fontSize = 13.sp
                        )
                    }

                    TextButton(
                        onClick = { isFilipino = !isFilipino }
                    ) {
                        Text(
                            if (isFilipino) "FIL" else "ENG",
                            color = Blue
                        )
                    }
                }

                Spacer(Modifier.height(24.dp))

                Text(
                    if (isFilipino) "Maligayang pagdating, Keeper!" else "Welcome, Keeper!",
                    color = LightText,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    if (isFilipino)
                        "Alamin ang tamang pag-aalaga sa iyong exotic pets."
                    else
                        "Learn responsible care for your exotic pets.",
                    color = LightText
                )

                Spacer(Modifier.height(20.dp))

                OutlinedTextField(
                    value = search,
                    onValueChange = { search = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            if (isFilipino) "Maghanap ng species..." else "Search species..."
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = LightText,
                        unfocusedTextColor = LightText,
                        focusedBorderColor = Blue,
                        unfocusedBorderColor = Color.Gray,
                        cursorColor = Blue
                    )
                )

                Spacer(Modifier.height(24.dp))

                Text(
                    if (isFilipino) "EXPLORE SPECIES" else "EXPLORE SPECIES",
                    color = Blue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                Spacer(Modifier.height(12.dp))

                val species = listOf(
                    "Tarantulas" to "🕷️",
                    "Scorpions" to "🦂",
                    "Centipedes" to "🐛",
                    "Reptiles" to "🦎"
                ).filter {
                    it.first.contains(search, ignoreCase = true)
                }

                species.forEach { (name, emoji) ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = CardBg
                        ),
                        onClick = { selected = name }
                    ) {
                        Row(
                            modifier = Modifier.padding(18.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(emoji, fontSize = 28.sp)
                            Spacer(Modifier.width(14.dp))
                            Column {
                                Text(
                                    name,
                                    color = LightText,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    if (isFilipino) "Tingnan ang gabay sa pag-aalaga"
                                    else "Explore care guides",
                                    color = Color.LightGray,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(20.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = CardBg
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(Modifier.padding(18.dp)) {
                        Text(
                            "KAPETZ AI ASSISTANT",
                            color = Blue,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            if (isFilipino)
                                "Ang AI chat at detalyadong care sheets ay susunod nating bubuuin."
                            else
                                "AI chat and detailed care sheets will be added in the next steps.",
                            color = LightText
                        )
                    }
                }

                Spacer(Modifier.height(20.dp))

                Text(
                    "Selected: $selected",
                    color = Blue,
                    fontSize = 12.sp
                )
            }
        }
    }
}
