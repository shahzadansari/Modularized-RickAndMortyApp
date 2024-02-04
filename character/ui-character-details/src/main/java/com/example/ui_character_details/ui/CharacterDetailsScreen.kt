package com.example.ui_character_details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.character_domain.Character
import com.example.character_domain.example
import com.example.components.DefaultScreenUI
import com.example.components.isInPreview
import com.example.modularized_rickandmortyapp.components.R as componentsR

@Composable
fun CharacterDetailsScreen(
    state: CharacterDetailsState,
    onTriggerEvent: (event: CharacterDetailsEvent) -> Unit
) {
    DefaultScreenUI(
        isLoading = state.isLoading,
        errorQueue = state.errorQueue,
        onRemoveHeadFromQueue = { onTriggerEvent(CharacterDetailsEvent.RemoveHeadFromQueue) }
    ) {
        state.character?.let { character ->
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    if (!isInPreview) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(character.imageUrl)
                                .placeholder(componentsR.drawable.white_background)
                                .error(componentsR.drawable.error_image)
                                .crossfade(true)
                                .build(),
                            contentDescription = character.name,
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .height(150.dp)
                                .clip(CircleShape)
                        )
                    } else {
                        Image(
                            painter = painterResource(id = componentsR.drawable.character_avatar),
                            contentDescription = character.name,
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .clip(CircleShape)
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(12.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text(text = character.name, style = MaterialTheme.typography.headlineMedium)
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Status: ${character.status.name}", style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Species: ${character.species}", style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Origin: ${character.origin.name}", style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewCharacterDetailsScreen() {
    CharacterDetailsScreen(state = CharacterDetailsState(character = Character.example), onTriggerEvent = {})
}