package com.lojbrooks.hospitals.ui.hospitaldetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lojbrooks.hospitals.R
import com.lojbrooks.hospitals.domain.model.Address
import com.lojbrooks.hospitals.domain.model.ContactDetails
import com.lojbrooks.hospitals.domain.model.Hospital
import com.lojbrooks.hospitals.ui.common.HospitalsAppBar
import com.lojbrooks.hospitals.ui.common.LoadingIndicator

@Composable
fun HospitalDetailScreen(viewModel: HospitalDetailViewModel, onNavigateUp: () -> Unit) {

    val state by viewModel.state.collectAsState()

    Column {
        HospitalsAppBar(showUpNavigation = true, onNavigateUp)
        when (val currentState = state) {
            HospitalDetailViewModel.State.Loading -> LoadingIndicator()
            is HospitalDetailViewModel.State.Data -> Column {
                HospitalDetail(currentState.hospital)
            }
        }
    }

}

@Composable
fun HospitalDetail(hospital: Hospital) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(text = hospital.name, style = MaterialTheme.typography.h6)
        Text(text = hospital.orgCode, style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(16.dp))
        AddressDetails(hospital.address)
        Spacer(modifier = Modifier.height(16.dp))
        ContactDetails(hospital.contactDetails)
    }
}

@Composable
private fun AddressDetails(address: Address) {
    Text(text = stringResource(id = R.string.address), style = MaterialTheme.typography.h6)
    address.address1?.let {
        Text(text = it, style = MaterialTheme.typography.body1)
    }
    address.address2?.let {
        Text(text = it, style = MaterialTheme.typography.body1)
    }
    address.address3?.let {
        Text(text = it, style = MaterialTheme.typography.body1)
    }
    address.city?.let {
        Text(text = it, style = MaterialTheme.typography.body1)
    }
    address.county?.let {
        Text(text = it, style = MaterialTheme.typography.body1)
    }
    address.postcode?.let {
        Text(text = it, style = MaterialTheme.typography.body1)
    }
}

@Composable
private fun ContactDetails(contactDetails: ContactDetails) {
    Text(text = stringResource(id = R.string.contact_details), style = MaterialTheme.typography.h6)
    contactDetails.phone?.let {
        Text(text = it, style = MaterialTheme.typography.body1)
    }
    contactDetails.email?.let {
        Text(text = it, style = MaterialTheme.typography.body1)
    }
    contactDetails.website?.let {
        Text(text = it, style = MaterialTheme.typography.body1)
    }
}
