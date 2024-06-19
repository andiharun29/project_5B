package com.example.login_page_auth.ui.edit

import EntryBody
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.login_page_auth.PenyediaViewModel
import com.example.login_page_auth.PetTopAppBar
import com.example.login_page_auth.navigation.DestinasiNavigasi
import kotlinx.coroutines.launch

object EditDestination : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes = "Edit Pet"
    const val petId = "itemtId"
    val routeWithArgs = "${EditDestination.route}/{$petId}"

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory )
){
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            PetTopAppBar(
                title = EditDestination.titleRes ,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) {innerPadding ->
        EntryBody(
            addUIState = viewModel.petUiState ,
            onPetValueChange = viewModel::updateUIState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updatePet()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
            )
    }
}