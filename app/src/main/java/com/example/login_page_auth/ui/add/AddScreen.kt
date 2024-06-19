import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.login_page_auth.AddPet
import com.example.login_page_auth.AddUIState
import com.example.login_page_auth.PenyediaViewModel
import com.example.login_page_auth.PetTopAppBar
import com.example.login_page_auth.R
import com.example.login_page_auth.navigation.DestinasiNavigasi
import kotlinx.coroutines.launch
object DestinasiAdd : DestinasiNavigasi {
    override val route = "item_entry"
    override val titleRes = "Pet Hotel"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    addViewModel: AddViewModel = viewModel(factory = PenyediaViewModel.Factory),

    ){


    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()


    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            PetTopAppBar(
                title = DestinasiAdd.titleRes,


                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ){ innerPadding ->
        Box(

            modifier = Modifier
                .background(Color(255, 206, 206, 255))
                .fillMaxSize()
        )
        EntryBody(
            addUIState = addViewModel.addUIState,
            onPetValueChange = addViewModel::updateAddUIState,
            onSaveClick = {
                coroutineScope.launch {
                    addViewModel.addPet()
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

@Composable
fun EntryBody(
    addUIState: AddUIState,
    onPetValueChange: (AddPet) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .padding(12.dp)
    ) {
        FormInput(
            addPet = addUIState.addPet,
            onValueChange = onPetValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                Color(255, 122, 122, 255)
            )


        ) {
            Text("Add")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    addPet: AddPet,
    modifier: Modifier = Modifier,
    onValueChange: (AddPet) -> Unit = {},
    enabled: Boolean = true
) {
    Column(

        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)

    ) {
        OutlinedTextField(
            value = addPet.namapet,
            onValueChange = { onValueChange(addPet.copy(namapet = it)) },
            label = { Text("Nama Hewan") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = enabled,
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
        )
        OutlinedTextField(
            value = addPet.jenispet,
            onValueChange = { onValueChange(addPet.copy(jenispet = it)) },
            label = { Text("Jenis Hewan") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = enabled,
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
        )
        OutlinedTextField(
            value = addPet.telpon,
            onValueChange = { onValueChange(addPet.copy(telpon = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(text = "Telpon") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = enabled,
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
        )

    }
}