package com.lojbrooks.hospitals.ui.hospitallist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lojbrooks.hospitals.domain.model.Hospital
import com.lojbrooks.hospitals.domain.model.Sector
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
        refreshHospitalData()
    }

    private fun refreshHospitalData() {
        viewModelScope.launch {
            val dataState = (_state.value as? State.Data)
            _state.value = State.Loading

            getHospitalsQuery().fold(
                onSuccess = {
                    val filterNhs = dataState?.isFilterChecked ?: false
                    val hospitals = if(filterNhs) {
                        it.filter { hospital -> hospital.sector == Sector.NHS }
                    } else it

                    _state.value = dataState?.copy(hospitals = hospitals) ?: State.Data(hospitals = hospitals)
                },
                onFailure = {
                    _state.value = State.Error
                }
            )
        }
    }

    fun onTryAgainClicked() {
        refreshHospitalData()
    }

    fun onOptionsMenuExpandedChanged(isExpanded: Boolean) {
        (_state.value as? State.Data)?.let {
            _state.value = it.copy(optionsMenuExpanded = isExpanded)
        }
    }

    fun onFilterToggled() {
        (_state.value as? State.Data)?.let {
            _state.value = it.copy(isFilterChecked = !it.isFilterChecked)
            refreshHospitalData()
        }
    }

    sealed class State {
        object Loading : State()
        object Error : State()
        data class Data(
            val hospitals: List<Hospital>,
            val optionsMenuExpanded: Boolean = false,
            val isFilterChecked: Boolean = false
        ) : State()
    }
}