package com.example.unsplash.presentation.search_photos

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplash.common.NetworkResponse
import com.example.unsplash.domain.use_case.SearchPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class SearchPhotosViewModel @Inject constructor(
    private val searchPhotosUseCase: SearchPhotoUseCase
) :  ViewModel(){
    private val _state = mutableStateOf(SearchPhotosState())
    val state: State<SearchPhotosState> = _state
    var query by mutableStateOf("tokyo")

    init {
        searchPhotos()
    }
    fun searchPhotos(): Flow<SearchPhotosState> = flow<SearchPhotosState> {
        //searchPhotosUseCase().invoke()と同じ意味
        searchPhotosUseCase(query).onEach { result ->
            when (result) {
                is NetworkResponse.Success -> {
                    _state.value =
                        SearchPhotosState(
                            photos = result.data ?: emptyList(),
                            isLoading = false
                        )
                }

                is NetworkResponse.Failure -> {
                    _state.value = SearchPhotosState(error = result.error)
                }

                is NetworkResponse.Loading -> {
                    _state.value = SearchPhotosState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
