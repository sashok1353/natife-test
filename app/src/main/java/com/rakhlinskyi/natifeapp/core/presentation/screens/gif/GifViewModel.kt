package com.rakhlinskyi.natifeapp.core.presentation.screens.gif

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.rakhlinskyi.natifeapp.core.data.mappers.toGif
import com.rakhlinskyi.natifeapp.core.data.repository.GifsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GifViewModel @Inject constructor(
    private val gifRepository: GifsRepository
): ViewModel() {
    val getAllGifs = gifRepository.getAllGifs().map { pagingData ->
        pagingData.map { it.toGif() }
    }.cachedIn(viewModelScope)

    fun removeGifById(gifId: String) {
        viewModelScope.launch {
            gifRepository.removeGifById(gifId)
        }
    }
}