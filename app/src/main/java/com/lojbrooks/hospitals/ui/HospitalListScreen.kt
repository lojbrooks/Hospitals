package com.lojbrooks.hospitals.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lojbrooks.hospitals.R
import com.lojbrooks.hospitals.domain.model.Hospital
import com.lojbrooks.hospitals.ui.common.LoadingIndicator

@Composable
fun HospitalListScreen(viewModel: HospitalListViewModel) {
    val state by viewModel.state.collectAsState()

    Column {
        when (val currentState = state) {
            HospitalListViewModel.State.Loading -> LoadingIndicator()
            HospitalListViewModel.State.Error -> HospitalListError(viewModel::onTryAgainClicked)
            is HospitalListViewModel.State.Data -> HospitalList(
                hospitals = currentState.hospitals
            )
        }
    }
}

@Composable
fun HospitalList(hospitals: List<Hospital>) {
    LazyColumn(modifier = Modifier.padding(vertical = 8.dp)) {
        items(hospitals) { hospital ->
            HospitalCard(hospital = hospital)
        }
    }
}

@Composable
fun HospitalCard(hospital: Hospital) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = hospital.name, style = MaterialTheme.typography.h6)
            Text(
                text = hospital.orgCode,
                style = MaterialTheme.typography.body1
            )
        }
    }
}


@Composable
fun HospitalListError(onTryAgainClicked: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = stringResource(id = R.string.hospital_list_error))
        TextButton(onClick = onTryAgainClicked) {
            Text(text = stringResource(id = R.string.try_again_button))
        }
    }
}