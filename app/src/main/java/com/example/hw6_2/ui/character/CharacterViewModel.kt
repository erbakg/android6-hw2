package com.example.hw6_2.ui.character

import androidx.lifecycle.ViewModel
import com.example.hw6_2.data.repository.CharacterRepository


class CharacterViewModel(private val repository: CharacterRepository) :
    ViewModel() {

    fun getCharacter(url: String) = repository.fetchCharacter(url)

    fun getEpisodes(urls: List<String>) = repository.fetchEpisodes(urls)

    fun getEpisode(url: String) = repository.fetchEpisode(url)

}