package com.lojbrooks.hospitals.ui.hospitaldetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lojbrooks.hospitals.domain.model.Hospital
import com.lojbrooks.hospitals.domain.usecase.GetHospitalQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HospitalDetailViewModel @Inject constructor(
    private val getHospitalQuery: GetHospitalQuery,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    init {
        val orgId = savedStateHandle.get<Int>("orgId")
        requireNotNull(orgId)
        fetchHospitalData(orgId)
    }

    private fun fetchHospitalData(orgId: Int) {
        viewModelScope.launch {
            _state.value = State.Loading
            _state.value = State.Data(getHospitalQuery(orgId))
        }
    }

    sealed class State {
        object Loading : State()
        data class Data(val hospital: Hospital) : State()
    }
}