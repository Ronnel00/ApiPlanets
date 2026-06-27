package edu.ucne.apiplanets.presentation.character.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import edu.ucne.apiplanets.domain.model.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    viewModel: CharacterListViewModel = hiltViewModel(),
    onCharacterClick: (Int) -> Unit,
    onMenuClick: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CharacterListBodyScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onCharacterClick = onCharacterClick,
        onMenuClick = onMenuClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListBodyScreen(
    state: CharacterListUiState,
    onEvent: (CharacterListUiEvent) -> Unit,
    onCharacterClick: (Int) -> Unit,
    onMenuClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Personajes Dragon Ball") },
                navigationIcon = {
                    IconButton(onClick = onMenuClick) {
                        Icon(Icons.Default.Menu, contentDescription = "Menú")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            ElevatedCard(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = state.filterName,
                        onValueChange = { onEvent(CharacterListUiEvent.UpdateFilterName(it)) },
                        label = { Text("Buscar por nombre...") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = state.filterRace,
                        onValueChange = { onEvent(CharacterListUiEvent.UpdateFilterRace(it)) },
                        label = { Text("Buscar por raza...") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = state.filterGender,
                        onValueChange = { onEvent(CharacterListUiEvent.UpdateFilterGender(it)) },
                        label = { Text("Buscar por género...") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Button(
                        onClick = { onEvent(CharacterListUiEvent.Search) },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Buscar")
                    }
                }
            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            if (state.error != null) {
                Text(
                    "Error: ${state.error}",
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }

            LazyColumn(
                contentPadding = PaddingValues(16.dp)
            ) {
                items(state.characters) { character ->
                    CharacterItem(
                        character = character,
                        onClick = { onCharacterClick(character.id) }
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Composable
fun CharacterItem(
    character: Character,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    character.name,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Raza: ${character.race}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Text(
                    "Género: ${character.gender}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterListBodyScreenPreview() {
    val sampleCharacters = listOf(
        Character(1, "Goku", "Saiyan", "Male", "El más poderoso", ""),
        Character(2, "Vegeta", "Saiyan", "Male", "Príncipe Saiyan", "")
    )
    MaterialTheme {
        CharacterListBodyScreen(
            state = CharacterListUiState(characters = sampleCharacters),
            onEvent = {},
            onCharacterClick = {}
        )
    }
}