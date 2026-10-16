
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

private val PageBg = Color(0xFFF1F4F8)
private val CardBg = Color(0xFFFFFFFF)
private val Blue = Color(0xFF007FAD)
private val MainText = Color(0xFF18212F)
private val SecondaryText = Color(0xFF5D6878)

private data class Species(
    val name: String,
    val scientificName: String,
    val category: String,
    val emoji: String,
    val aliases: String,
    val care: String
)

private val speciesList = listOf(
    Species(
        "Curly Hair Tarantula",
        "Tliltocatl albopilosus",
        "Tarantulas", "🕷️",
        "curly hair honduras",
        "Terrestrial species. Provide secure substrate, a hide, a water dish, and appropriate ventilation."
    ),
    Species(
        "Greenbottle Blue",
        "Chromatopelma cyaneopubescens",
        "Tarantulas", "🕷️",
        "gbb green bottle blue",
        "An arboreal-leaning webbing species. Provide anchor points, a hide, a water dish, and good ventilation."
    ),
    Species(
        "Cobalt Blue",
        "Cyriopagopus lividus",
        "Tarantulas", "🕷️",
        "cobalt blue",
        "A defensive fossorial species. Provide deep substrate and a secure enclosure; avoid unnecessary handling."
    ),
    Species(
        "Electric Blue",
        "Chilobrachys natanicharum",
        "Tarantulas", "🕷️",
        "electric blue",
        "A fast, fossorial species. Provide deep substrate, webbing anchors, and a secure enclosure."
    ),
    Species(
        "Indian Ornamental",
        "Poecilotheria regalis",
        "Tarantulas", "🕷️",
        "pokie ornamental",
        "A fast arboreal species. Provide vertical cork bark, cross-ventilation, and a secure enclosure."
    ),
    Species(
        "Emperor Scorpion",
        "Pandinus imperator",
        "Scorpions", "🦂",
        "emperor",
        "Provide a deep, moisture-retaining substrate, a hide, a shallow water dish, and good ventilation."
    ),
    Species(
        "Asian Forest Scorpion",
        "Heterometrus spinifer",
        "Scorpions", "🦂",
        "forest scorpion",
        "Provide deep substrate for burrowing, a hide, a water dish, and suitable humidity with ventilation."
    ),
    Species(
        "Deathstalker",
        "Leiurus quinquestriatus",
        "Scorpions", "🦂",
        "death stalker",
        "Highly venomous. For experienced keepers only; use escape-proof housing and never handle."
    ),
    Species(
        "Giant Centipede",
        "Scolopendra subspinipes",
        "Centipedes", "🐛",
        "scolopendra",
        "Fast and capable of delivering a medically significant bite. Use a secure enclosure and never handle."
    ),
    Species(
        "Leopard Gecko",
        "Eublepharis macularius",
        "Reptiles", "🦎",
        "leopard gecko",
        "Needs a temperature gradient, suitable hides, calcium supplementation, and an insect-based diet."
    ),
    Species(
        "Bearded Dragon",
        "Pogona vitticeps",
        "Reptiles", "🦎",
        "bearded dragon",
        "Needs appropriate UVB lighting, a temperature gradient, and a varied, species-appropriate diet."
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { KapetzHome() }
    }
}

@Composable
fun KapetzHome() {
    var isFilipino by remember { mutableStateOf(true) }
    var search by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }
    var selectedSpecies by remember { mutableStateOf<Species?>(null) }

    val categories = listOf(
        "All", "Tarantulas", "Scorpions", "Centipedes", "Reptiles"
    )

    val results = speciesList.filter { species ->
        val matchesSearch =
            search.isBlank() ||
            species.name.contains(search, ignoreCase = true) ||
            species.scientificName.contains(search, ignoreCase = true) ||
            species.category.contains(search, ignoreCase = true) ||
            species.aliases.contains(search, ignoreCase = true)

        val matchesCategory =
            selectedCategory == "All" ||
            species.category == selectedCategory

        matchesSearch && matchesCategory
    }

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = PageBg
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(18.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            "KAPETZ AI",
                            color = Blue,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            "Exotic Pet Care Assistant",
                            color = SecondaryText,
                            fontSize = 13.sp
                        )
                    }

                    OutlinedButton(
                        onClick = { isFilipino = !isFilipino }
                    ) {
                        Text(if (isFilipino) "FIL" else "ENG")
                    }
                }

                Spacer(Modifier.height(24.dp))

                Text(
                    if (isFilipino) "Maligayang pagdating, Keeper!"
                    else "Welcome, Keeper!",
                    color = MainText,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    if (isFilipino)
                        "Tuklasin ang mga species at alamin ang responsableng pag-aalaga."
                    else
                        "Explore species and learn responsible exotic pet care.",
                    color = SecondaryText
                )

                Spacer(Modifier.height(20.dp))

                OutlinedTextField(
                    value = search,
                    onValueChange = { search = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            if (isFilipino)
                                "Pangalan o scientific name..."
                            else
                                "Common or scientific name..."
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = MainText,
                        unfocusedTextColor = MainText,
                        focusedBorderColor = Blue,
                        unfocusedBorderColor = Color(0xFFB8C3D0),
                        focusedContainerColor = CardBg,
                        unfocusedContainerColor = CardBg,
                        cursorColor = Blue
                    )
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    if (isFilipino) "MGA KATEGORYA" else "CATEGORIES",
                    color = Blue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                Spacer(Modifier.height(8.dp))

                categories.forEach { category ->
                    if (category == "All" ||
                        category == "Tarantulas" ||
                        category == "Scorpions" ||
                        category == "Centipedes" ||
                        category == "Reptiles"
                    ) {
                        FilterChip(
                            selected = selectedCategory == category,
                            onClick = { selectedCategory = category },
                            label = {
                                Text(
                                    when {
                                        isFilipino && category == "All" -> "Lahat"
                                        isFilipino && category == "Tarantulas" -> "Tarantula"
                                        isFilipino && category == "Scorpions" -> "Alakdan"
                                        isFilipino && category == "Centipedes" -> "Alupihan"
                                        isFilipino && category == "Reptiles" -> "Reptile"
                                        else -> category
                                    }
                                )
                            },
                            modifier = Modifier.padding(end = 4.dp)
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    if (isFilipino)
                        "MGA SPECIES (${results.size})"
                    else
                        "SPECIES (${results.size})",
                    color = Blue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                Spacer(Modifier.height(8.dp))

                if (results.isEmpty()) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = CardBg
                        )
                    ) {
                        Text(
                            if (isFilipino)
                                "Walang tugma. Subukan ang ibang pangalan o scientific name."
                            else
                                "No matches. Try another common or scientific name.",
                            color = MainText,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                results.forEach { species ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = CardBg
                        ),
                        onClick = { selectedSpecies = species }
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(species.emoji, fontSize = 28.sp)
                            Spacer(Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    species.name,
                                    color = MainText,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    species.scientificName,
                                    color = SecondaryText,
                                    fontSize = 13.sp
                                )
                                Text(
                                    species.category,
                                    color = Blue,
                                    fontSize = 12.sp
                                )
                            }
                            Text("›", color = Blue, fontSize = 26.sp)
                        }
                    }
                }

                selectedSpecies?.let { species ->
                    Spacer(Modifier.height(16.dp))

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFE1F3FA)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(Modifier.padding(18.dp)) {
                            Text(
                                if (isFilipino) "GABAY SA PAG-AALAGA"
                                else "CARE GUIDE",
                                color = Blue,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                species.name,
                                color = MainText,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                species.scientificName,
                                color = SecondaryText
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                species.care,
                                color = MainText
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                if (isFilipino)
                                    "Pangkalahatang gabay lamang ito. I-verify ang pangangailangan ng species mula sa mapagkakatiwalaang sources."
                                else
                                    "This is general guidance. Verify species-specific needs with reliable sources.",
                                color = SecondaryText,
                                fontSize = 12.sp
                            )
                            TextButton(
                                onClick = { selectedSpecies = null }
                            ) {
                                Text(
                                    if (isFilipino) "Isara" else "Close"
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(20.dp))

                Card(
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
                                "Ang kasalukuyang bersyon ay may lokal na species list. Ikokonekta pa natin ang AI chat at mas kumpletong care database."
                            else
                                "This version has a local species list. AI chat and a larger care database still need to be connected.",
                            color = MainText
                        )
                    }
                }

                Spacer(Modifier.height(24.dp))
            }
        }
    }
}
