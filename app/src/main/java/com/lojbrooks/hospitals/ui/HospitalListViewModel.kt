package com.lojbrooks.hospitals.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lojbrooks.hospitals.domain.model.Hospital
import com.lojbrooks.hospitals.domain.usecase.GetHospitalsQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HospitalListViewModel @Inject constructor(
    private val getHospitalsQuery: GetHospitalsQuery
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    init {
        fetchHospitalData()
    }

    private fun fetchHospitalData() {
        viewModelScope.launch {
            _state.value = State.Loading

            getHospitalsQuery().fold(
                onSuccess = {
                    _state.value = State.Data(it)
                },
                onFailure = {
                    _state.value = State.Error
                }
            )
        }
    }

    fun onTryAgainClicked() {
        fetchHospitalData()
    }

    sealed class State {
        object Loading : State()
        object Error : State()
        data class Data(val hospitals: List<Hospital>) : State()
    }
}