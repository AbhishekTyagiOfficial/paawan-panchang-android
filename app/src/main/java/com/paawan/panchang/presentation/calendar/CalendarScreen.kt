package com.paawan.panchang.presentation.calendar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.paawan.panchang.core.components.*
import com.paawan.panchang.core.theme.*
import java.util.Calendar

@Composable
fun CalendarScreen(
    viewModel: CalendarViewModel = hiltViewModel(),
    isOldDesign: Boolean = false
) {
    if (isOldDesign) {
        CalendarScreenOld(viewModel = viewModel)
        return
    }

    val state by viewModel.state.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.resetToToday()
    }

    val daysOfWeek = listOf(
        Pair("Sun", "रवि"),
        Pair("Mon", "सोम"),
        Pair("Tue", "मंगल"),
        Pair("Wed", "बुध"),
        Pair("Thu", "गुरु"),
        Pair("Fri", "शुक्र"),
        Pair("Sat", "शनि")
    )

    val festivalDays = setOf(11, 14, 21, 23, 26, 28) // Major festive day indicators

    // Today's ISO date for today-ring indicator
    val todayCal = Calendar.getInstance()
    val todayYear = todayCal.get(Calendar.YEAR)
    val todayMonth = todayCal.get(Calendar.MONTH) + 1
    val todayDay = todayCal.get(Calendar.DAY_OF_MONTH)
    val todayMonthStr = if (todayMonth < 10) "0$todayMonth" else "$todayMonth"
    val todayDayStr = if (todayDay < 10) "0$todayDay" else "$todayDay"
    val todayIso = "$todayYear-$todayMonthStr-$todayDayStr"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(4.dp))

        // =========================================================================
        // 1. Top Section Header with Quick "आज (Today)" Button
        // =========================================================================
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "मासिक पंचांग",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Vedic Monthly Calendar",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Quick Jump to Today
            OutlinedButton(
                onClick = { viewModel.resetToToday() },
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
            ) {
                Icon(
                    imageVector = Icons.Default.Today,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "आज (Today)",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        // =========================================================================
        // 2. Month Selector & 7-Day Grid Card
        // =========================================================================
        PaawanCard(
            elevation = 3.dp,
            shape = RoundedCornerShape(22.dp)
        ) {
            // Month navigation controls
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                    modifier = Modifier.size(38.dp)
                ) {
                    IconButton(onClick = { viewModel.previousMonth() }) {
                        Icon(
                            imageVector = Icons.Default.ChevronLeft,
                            contentDescription = "Previous Month",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = state.monthTitle,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = state.hinduMonthTitle,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                    modifier = Modifier.size(38.dp)
                ) {
                    IconButton(onClick = { viewModel.nextMonth() }) {
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = "Next Month",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Weekday Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                daysOfWeek.forEach { (en, hi) ->
                    val isSunday = en == "Sun"
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = hi,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (isSunday) TempleCrimson else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = en,
                            style = MaterialTheme.typography.labelSmall,
                            color = if (isSunday) TempleCrimson.copy(alpha = 0.8f) else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                        )
                    }
                }
            }

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                modifier = Modifier.padding(vertical = 10.dp)
            )

            // 7-Column Dates Grid
            val totalDays = state.totalDays
            val firstDayOffset = state.firstDayOffset
            val totalGridCells = firstDayOffset + totalDays

            val monthNum = state.currentMonth + 1
            val monthStr = if (monthNum < 10) "0$monthNum" else "$monthNum"

            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier.height(290.dp),
                userScrollEnabled = false,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(totalGridCells) { index ->
                    if (index < firstDayOffset) {
                        Box(modifier = Modifier.aspectRatio(1f))
                    } else {
                        val dayNum = index - firstDayOffset + 1
                        val dayStr = if (dayNum < 10) "0$dayNum" else "$dayNum"
                        val dateIso = "${state.currentYear}-$monthStr-$dayStr"
                        val isSelected = dateIso == state.selectedDateIso
                        val isToday = dateIso == todayIso
                        val hasFestival = festivalDays.contains(dayNum) && state.currentMonth == 8

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .aspectRatio(1f)
                                .clip(CircleShape)
                                .then(
                                    when {
                                        isSelected -> Modifier.background(
                                            Brush.linearGradient(
                                                listOf(SaffronPrimary, GoldAccent)
                                            )
                                        )
                                        isToday -> Modifier
                                            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f))
                                            .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
                                        hasFestival -> Modifier.background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f))
                                        else -> Modifier
                                    }
                                )
                                .clickable { viewModel.selectDate(dateIso) }
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "$dayNum",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = if (isSelected || isToday || hasFestival) FontWeight.Bold else FontWeight.Medium,
                                    color = when {
                                        isSelected -> Color.White
                                        isToday -> MaterialTheme.colorScheme.primary
                                        hasFestival -> MaterialTheme.colorScheme.onSecondaryContainer
                                        else -> MaterialTheme.colorScheme.onSurface
                                    }
                                )
                                if (hasFestival) {
                                    Box(
                                        modifier = Modifier
                                            .padding(top = 1.dp)
                                            .size(4.dp)
                                            .clip(CircleShape)
                                            .background(
                                                if (isSelected) Color.White else SaffronPrimary
                                            )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // =========================================================================
        // 3. Selected Date Detailed Panchang
        // =========================================================================
        val panchang = state.panchang
        if (panchang != null) {
            PaawanCard(
                elevation = 3.dp,
                shape = RoundedCornerShape(22.dp)
            ) {
                // Header of Selected Date
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(MaterialTheme.colorScheme.primaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Event,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = panchang.formattedDate,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "चयनित तिथि पंचांग विवरण",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    CategoryBadge(text = panchang.paksha, isAccent = true)
                }

                HorizontalDivider(
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                    modifier = Modifier.padding(vertical = 12.dp)
                )

                // Astrological Details
                PanchangMetricRow(
                    labelEn = "Lunar Day (Tithi)",
                    labelHi = "तिथि",
                    value = panchang.tithiName,
                    icon = Icons.Default.Brightness4
                )
                PanchangMetricRow(
                    labelEn = "Fortnight (Paksha)",
                    labelHi = "पक्ष",
                    value = panchang.paksha,
                    icon = Icons.Default.NightsStay
                )
                PanchangMetricRow(
                    labelEn = "Constellation (Nakshatra)",
                    labelHi = "नक्षत्र",
                    value = panchang.nakshatra,
                    icon = Icons.Default.Star
                )
                PanchangMetricRow(
                    labelEn = "Auspicious Union (Yoga)",
                    labelHi = "योग",
                    value = panchang.yoga,
                    icon = Icons.Default.SelfImprovement
                )
                PanchangMetricRow(
                    labelEn = "Half-Tithi (Karana)",
                    labelHi = "करण",
                    value = panchang.karana,
                    icon = Icons.Default.AutoAwesome
                )
                PanchangMetricRow(
                    labelEn = "Vikram Samvat",
                    labelHi = "विक्रम संवत",
                    value = "${panchang.vikramSamvat}",
                    icon = Icons.Default.CalendarToday
                )
                PanchangMetricRow(
                    labelEn = "Inauspicious Period (Rahu Kaal)",
                    labelHi = "राहु काल",
                    value = panchang.rahuKalam,
                    icon = Icons.Default.AccessTime,
                    isWarning = true
                )

                // Sunrise & Sunset Quick Bar
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.WbSunny,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = SaffronPrimary
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "सूर्योदय: ${panchang.sunrise}",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Brightness6,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = TempleCrimson
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "सूर्यास्त: ${panchang.sunset}",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

                // Special Occasion Banner
                if (panchang.isSpecialOccasion) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Celebration,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp),
                                tint = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = panchang.occasionSummary,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        } else if (state.isLoading) {
            PaawanLoadingView(message = "चयनित तिथि की गणना जारी है...")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
