package com.lojbrooks.hospitals.ui.hospitallist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lojbrooks.hospitals.domain.exception.DataFetchException
import com.lojbrooks.hospitals.domain.model.Hospital
import com.lojbrooks.hospitals.domain.model.Sector
import com.lojbrooks.hospitals.domain.usecase.GetHospitalsQuery
import com.lojbrooks.hospitals.rules.CoroutineTestRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class HospitalListViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private lateinit var viewModel: HospitalListViewModel

    private val getHospitalsQuery: GetHospitalsQuery = mock()

    private fun initViewModel() {
        viewModel = HospitalListViewModel(getHospitalsQuery)
    }

    @Test
    fun `WHEN init THEN show loading`() = runBlockingTest {
        coroutineRule.testDispatcher.pauseDispatcher()

        initViewModel()

        val state = viewModel.state.first()
        coroutineRule.testDispatcher.resumeDispatcher()
        assertThat(state, equalTo(HospitalListViewModel.State.Loading))
    }

    @Test
    fun `WHEN init AND fetch hospitals succeeds THEN show hospitals`() = runBlockingTest {
        val hospitals = listOf(mock<Hospital>())
        whenever(getHospitalsQuery()).thenReturn(Result.success(hospitals))

        initViewModel()

        assertThat(
            (viewModel.state.value as HospitalListViewModel.State.Data).hospitals,
            equalTo(hospitals)
        )
    }

    @Test
    fun `WHEN init AND fetch hospitals fails THEN show error state`() = runBlockingTest {
        whenever(getHospitalsQuery()).thenReturn(Result.failure(DataFetchException()))

        initViewModel()

        assertThat(viewModel.state.value, equalTo(HospitalListViewModel.State.Error))
    }

    @Test
    fun `GIVEN error state WHEN onTryAgainClicked AND fetch hospitals succeeds THEN show hospitals`() =
        runBlockingTest {
            whenever(getHospitalsQuery()).thenReturn(Result.failure(DataFetchException()))
            initViewModel()

            val hospitals = listOf(mock<Hospital>())
            whenever(getHospitalsQuery()).thenReturn(Result.success(hospitals))

            viewModel.onTryAgainClicked()

            assertThat(
                (viewModel.state.value as HospitalListViewModel.State.Data).hospitals,
                equalTo(hospitals)
            )
        }

    @Test
    fun `GIVEN error state WHEN onTryAgainClicked AND fetch hospitals fails THEN show error state`() =
        runBlockingTest {
            whenever(getHospitalsQuery()).thenReturn(Result.failure(DataFetchException()))
            initViewModel()

            viewModel.onTryAgainClicked()

            assertThat(viewModel.state.value, equalTo(HospitalListViewModel.State.Error))
        }

    @Test
    fun `WHEN options menu opened THEN state updated`() = runBlockingTest {
        val hospitals = listOf(mock<Hospital>())
        whenever(getHospitalsQuery()).thenReturn(Result.success(hospitals))
        initViewModel()

        viewModel.onOptionsMenuExpandedChanged(true)

        assertTrue((viewModel.state.value as HospitalListViewModel.State.Data).optionsMenuExpanded)
    }

    @Test
    fun `WHEN options menu closed THEN state updated`() = runBlockingTest {
        val hospitals = listOf(mock<Hospital>())
        whenever(getHospitalsQuery()).thenReturn(Result.success(hospitals))
        initViewModel()

        viewModel.onOptionsMenuExpandedChanged(false)

        assertFalse((viewModel.state.value as HospitalListViewModel.State.Data).optionsMenuExpanded)
    }

    @Test
    fun `WHEN nhs filter toggled on THEN state updated`() = runBlockingTest {
        val hospitals = listOf(
            Hospital(
                orgId = 1,
                name = "Hospital 1",
                orgCode = "org 1",
                sector = Sector.NHS,
                address = mock(),
                contactDetails = mock()
            ),
            Hospital(
                orgId = 2,
                name = "Hospital 2",
                orgCode = "org 2",
                sector = Sector.INDEPENDENT,
                address = mock(),
                contactDetails = mock()
            ),
            Hospital(
                orgId = 3,
                name = "Hospital 3",
                orgCode = "org 3",
                sector = Sector.OTHER,
                address = mock(),
                contactDetails = mock()
            ),
        )
        whenever(getHospitalsQuery()).thenReturn(Result.success(hospitals))
        initViewModel()

        viewModel.onFilterToggled()

        assertTrue((viewModel.state.value as HospitalListViewModel.State.Data).isFilterChecked)
        assertThat(
            (viewModel.state.value as HospitalListViewModel.State.Data).hospitals.size,
            equalTo(1)
        )
        assertTrue((viewModel.state.value as HospitalListViewModel.State.Data).hospitals.all { it.sector == Sector.NHS })
    }
}