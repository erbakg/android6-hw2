package com.example.hw6_2.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.hw6_2.data.models.Character
import com.example.hw6_2.data.repository.CharactersRepository


class CharactersViewModel(repository: CharactersRepository) : ViewModel() {

    val characterList: LiveData<PagingData<Character>> =
        repository.getCharacters()
            .cachedIn(viewModelScope)

}