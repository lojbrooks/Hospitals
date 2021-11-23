package com.lojbrooks.hospitals.test

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ActivityScenario
import com.lojbrooks.hospitals.MainActivity
import com.lojbrooks.hospitals.fakes.FakeHospitalApiClient
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import javax.inject.Inject

@HiltAndroidTest
class HospitalListTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createEmptyComposeRule()

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Inject
    lateinit var api: FakeHospitalApiClient


    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun teardown() {
        scenario.close()
    }

    @Test
    fun shouldShowHospitals() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        composeTestRule.onNodeWithText("Hospital 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("HOS1").assertIsDisplayed()

        composeTestRule.onNodeWithText("Hospital 2").assertIsDisplayed()
        composeTestRule.onNodeWithText("HOS2").assertIsDisplayed()
    }

    @Test
    fun whenFetchHospitalsFails_shouldShowError() {
        api.getHospitalsResponse = Response.error(500, "".toResponseBody())
        scenario = ActivityScenario.launch(MainActivity::class.java)

        composeTestRule.onNodeWithText("Failed to fetch hospital data").assertIsDisplayed()
        composeTestRule.onNodeWithText("Try again").assertIsDisplayed()
    }

    @Test
    fun whenClickTryAgainAndFetchHosptialsSucceeds_shouldShowHospitals() {
        api.getHospitalsResponse = Response.error(500, "".toResponseBody())
        scenario = ActivityScenario.launch(MainActivity::class.java)
        composeTestRule.onNodeWithText("Try again").assertIsDisplayed()

        api.getHospitalsResponse = FakeHospitalApiClient.DEFAULT_HOSPITALS_RESPONSE
        composeTestRule.onNodeWithText("Try again").performClick()

        composeTestRule.onNodeWithText("Hospital 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("HOS1").assertIsDisplayed()

        composeTestRule.onNodeWithText("Hospital 2").assertIsDisplayed()
        composeTestRule.onNodeWithText("HOS2").assertIsDisplayed()
    }

    @Test
    fun whenClickTryAgainAndFetchHospitalsFails_shouldShowError() {
        api.getHospitalsResponse = Response.error(500, "".toResponseBody())
        scenario = ActivityScenario.launch(MainActivity::class.java)

        composeTestRule.onNodeWithText("Try again").performClick()

        composeTestRule.onNodeWithText("Failed to fetch hospital data").assertIsDisplayed()
        composeTestRule.onNodeWithText("Try again").assertIsDisplayed()
    }

    @Test
    fun whenClickHospital_shouldShowHospitalDetail() {
        scenario = ActivityScenario.launch(MainActivity::class.java)

        composeTestRule.onNodeWithText("Hospital 1").performClick()

        composeTestRule.onNodeWithText("Hospital 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("HOS1").assertIsDisplayed()
        composeTestRule.onNodeWithText("123 Hospital Street").assertIsDisplayed()
        composeTestRule.onNodeWithText("Worthing").assertIsDisplayed()
        composeTestRule.onNodeWithText("West Sussex").assertIsDisplayed()
        composeTestRule.onNodeWithText("BN1 1AA").assertIsDisplayed()
        composeTestRule.onNodeWithText("0123456789").assertIsDisplayed()
        composeTestRule.onNodeWithText("email@test.com").assertIsDisplayed()
        composeTestRule.onNodeWithText("www.test.com").assertIsDisplayed()
    }
}