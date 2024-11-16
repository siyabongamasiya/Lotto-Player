package lotto.project.lottoplayer.Presentation.HomeScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import lotto.project.lottoplayer.Presentation.Uitls.LIST_KEY

class HomeViewModel (private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val numbers = savedStateHandle.getStateFlow<List<Int>>(LIST_KEY, emptyList())

    fun generateLottoNumbers() {
        val list = (1..52).shuffled().take(5).sorted().toMutableList()
        val bonus = (1..20).shuffled().take(1)[0]
        list.add(bonus)
        savedStateHandle[LIST_KEY] = list
    }

    fun generateDailyNumbers(){
        savedStateHandle[LIST_KEY] = (1..36).shuffled().take(5).sorted()
    }

    fun generatePowerNumbers() {
        val list = (1..50).shuffled().take(5).sorted().toMutableList()
        val bonus = (1..20).shuffled().take(1)[0]
        list.add(bonus)
        savedStateHandle[LIST_KEY] = list
    }
}

