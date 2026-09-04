package com.paawan.panchang.presentation.festival

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.paawan.panchang.domain.model.Festival
import com.paawan.panchang.domain.util.FestivalTranslationEngine

@Composable
fun FestivalScreen(
    viewModel: FestivalViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "त्यौहार एवं पर्व (Indian Festivals)",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(state.festivals.size) { index ->
                val festival = state.festivals[index]
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.selectFestival(festival) }
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${festival.titleHi} (${festival.titleEn})",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 8.dp)
                            )
                            Surface(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = festival.category,
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "🗓 ${festival.formattedDate}",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f, fill = false)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = festival.tithiName,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.secondary,
                                fontWeight = FontWeight.Medium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Text(
                            text = festival.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }

    // Festival Detail Dialog Modal with Scrollable Language Dropdown Button
    state.selectedFestival?.let { festival ->
        var selectedLangCode by remember { mutableStateOf("hi") }
        var dropdownExpanded by remember { mutableStateOf(false) }

        val currentLangOption = FestivalTranslationEngine.supportedLanguages.find { it.code == selectedLangCode }
            ?: FestivalTranslationEngine.supportedLanguages.first()

        val translation = FestivalTranslationEngine.getTranslation(festival, selectedLangCode)

        AlertDialog(
            onDismissRequest = { viewModel.selectFestival(null) },
            confirmButton = {
                TextButton(onClick = { viewModel.selectFestival(null) }) {
                    Text(if (selectedLangCode == "ur" || selectedLangCode == "ar") "إغلاق / بند کریں" else "Close")
                }
            },
            title = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = translation.title,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )

                    // Scrollable Language Selection Dropdown Button
                    Box(modifier = Modifier.fillMaxWidth()) {
                        OutlinedButton(
                            onClick = { dropdownExpanded = true },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Language,
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp),
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "${currentLangOption.flag}  ${currentLangOption.name}",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Select Language",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        DropdownMenu(
                            expanded = dropdownExpanded,
                            onDismissRequest = { dropdownExpanded = false },
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .heightIn(max = 280.dp)
                        ) {
                            FestivalTranslationEngine.supportedLanguages.forEach { option ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = "${option.flag}  ${option.name}",
                                            fontWeight = if (option.code == selectedLangCode) FontWeight.Bold else FontWeight.Normal,
                                            color = if (option.code == selectedLangCode) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                        )
                                    },
                                    onClick = {
                                        selectedLangCode = option.code
                                        dropdownExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            },
            text = {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text("${translation.dateLabel}: ${festival.formattedDate}", fontWeight = FontWeight.SemiBold)
                    Text("${translation.tithiLabel}: ${festival.tithiName}")
                    Text("${translation.muhuratLabel}: ${festival.muhuratTiming}", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)

                    HorizontalDivider()

                    Text("📖 ${translation.kathaHeader}", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    Text(translation.description, style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.height(4.dp))

                    Text("🪔 ${translation.vidhiHeader}", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    Text(translation.pujaVidhi, style = MaterialTheme.typography.bodyMedium)

                    if (translation.mantras.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("🕉 ${translation.mantrasHeader}", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                        translation.mantras.forEach { mantra ->
                            Text("• $mantra", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        )
    }
}
