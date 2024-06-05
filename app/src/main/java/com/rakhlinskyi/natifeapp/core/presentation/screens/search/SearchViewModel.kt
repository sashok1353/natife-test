package com.rakhlinskyi.natifeapp.core.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.rakhlinskyi.natifeapp.core.data.mappers.toGif
import com.rakhlinskyi.natifeapp.core.data.repository.GifsRepository
import com.rakhlinskyi.natifeapp.core.domain.models.GifModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: GifsRepository
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedGifs = MutableStateFlow<PagingData<GifModel>>(PagingData.empty())
    val searchedImages = _searchedGifs

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchGifs(query: String) {
        viewModelScope.launch {
            repository.searchGifs(query = query).cachedIn(viewModelScope).collect {
                _searchedGifs.value = it.map { giftEntity -> giftEntity.toGif() }
            }
        }
    }

}