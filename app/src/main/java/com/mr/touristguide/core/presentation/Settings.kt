package com.mr.touristguide.core.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mr.touristguide.R
import com.mr.touristguide.core.presentation.data.GuideViewModel
import com.mr.touristguide.core.presentation.data.SettingsViewModel
import com.mr.touristguide.ui.theme.textLarge
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel,
    guideViewModel: GuideViewModel
) {
    val maxImagesValues = listOf(10, 20, 50, 100)
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(all = 5.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
//        Surface(modifier = Modifier.padding(all=4.dp), shape = MaterialTheme.shapes.small) {
//            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.background(Color.LightGray)) {
        Row() {
            Text(text = stringResource(id = R.string.settings_num_of_images_option))
        }
        for (value in maxImagesValues) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = value == settingsViewModel.maxImagesState,
                    onClick = { settingsViewModel.setMaxImages(value) })
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = value.toString())
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = -1 == settingsViewModel.maxImagesState,
                onClick = { settingsViewModel.setMaxImages(-1) })
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = stringResource(id = R.string.unlimited))
        }
//            }
//        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(id = R.string.settings_news_caching_option))
            Switch(
                checked = settingsViewModel.newsCachingState,
                onCheckedChange = { settingsViewModel.setNewsCaching(!settingsViewModel.newsCachingState) })
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(id = R.string.language))
            LanguageSpinner(settingsViewModel = settingsViewModel, guideViewModel = guideViewModel)
        }
    }
}

@Composable
fun LanguageSpinner(settingsViewModel: SettingsViewModel, guideViewModel: GuideViewModel) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Row(
            modifier = Modifier.clickable { expanded = !expanded },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = settingsViewModel.languageText, style = textLarge)
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = stringResource(id = R.string.dropdown_arrow)
            )
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                settingsViewModel.languagesList.forEach { language ->
                    DropdownMenuItem(
                        text = { Text(text = language.name) },
                        onClick = {
                            expanded =
                                false; guideViewModel.loadEverything(language.tag); settingsViewModel.setLanguage(
                            language
                        );
                        })
                }
            }
        }
    }

}