package br.com.fiap.marvelapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.marvelapp.BuildConfig
import br.com.fiap.marvelapp.domain.MarvelCharacterDataResultModel
import br.com.fiap.marvelapp.shared.DependencyFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterListViewModel : ViewModel() {

    val characterSuccessState = MutableLiveData<List<MarvelCharacterDataResultModel>>()
    val characterErrorState = MutableLiveData<String>()

    suspend fun listCharacters() {
        withContext(Dispatchers.Main) {
            val result = DependencyFactory.createRepository().listCharacters(
                DependencyFactory.timestamp,
                BuildConfig.MARVEL_API_KEY,
                DependencyFactory.hash
            )
            if (result.isSuccessful) {
                result.body()?.data?.results?.let {
                    characterSuccessState.value = it
                }
            } else {
                characterErrorState.value = "Infelizmente ocorreu um erro na API da Marvel!"
            }
        }
    }

}