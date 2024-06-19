import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.login_page_auth.AddPet
import com.example.login_page_auth.AddUIState
import com.example.login_page_auth.repositori.PetRepositori
import com.example.login_page_auth.toPet

class AddViewModel(private val petRepositori: PetRepositori) : ViewModel(){
    
    var addUIState by mutableStateOf(AddUIState())
        private set
    fun updateAddUIState(addPet: AddPet){
        addUIState = AddUIState(addPet = addPet)
    }
    
    suspend fun addPet(){
        petRepositori.add(addUIState.addPet.toPet())
    }
} 