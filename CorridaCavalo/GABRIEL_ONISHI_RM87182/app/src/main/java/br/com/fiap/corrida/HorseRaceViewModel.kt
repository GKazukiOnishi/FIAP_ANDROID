package br.com.fiap.corrida

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.random.Random

class HorseRaceViewModel : ViewModel() {

    var redHorseProgress = MutableLiveData<Int>(0)
    var greenHorseProgress = MutableLiveData<Int>(0)

    suspend fun startRace() {
        redHorseProgress.value = 0
        greenHorseProgress.value = 0
        withContext(Dispatchers.Default) {
            while (redHorseProgress.value!! < 100 && greenHorseProgress.value!! < 100) {
                val randomRed = Random.nextInt(0, 21)
                val randomGreen = Random.nextInt(0, 21)
                delay(1500)

                withContext(Dispatchers.Main) {
                    redHorseProgress.value = redHorseProgress.value!! + randomRed
                    greenHorseProgress.value = greenHorseProgress.value!! + randomGreen
                }
            }
        }
    }

}