package com.picker.ui.fragment

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.google.common.truth.Truth.assertThat
import com.picker.R
import com.picker.data.repository.FakeMainRepository
import com.picker.data.repository.MainRepositoryInterface
import com.picker.di.DatabaseModule
import com.picker.di.DatabaseModuleTest
import com.picker.di.RepositoryModule
import com.picker.launchFragmentInHiltContainer
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
@HiltAndroidTest
@UninstallModules(DatabaseModule::class, DatabaseModuleTest::class, RepositoryModule::class)
class AddFragmentTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)
    private val context: Context = ApplicationProvider.getApplicationContext()

    @BindValue
    @JvmField
    val repository: MainRepositoryInterface = FakeMainRepository()

    @Before
    fun setUp() {
        hiltRule.inject()

    }

    @Test
    fun trySavePickItemWithEmptyTitleTextInput() {
        launchFragmentInHiltContainer<AddFragment>()

        onView(withId(R.id.menu_item_save)).perform(click())

        onView(withText(context.getString(R.string.title_empty))).check(matches(isDisplayed()))
    }

    @Test
    fun trySavePickItem() = runBlockingTest {
        launchFragmentInHiltContainer<AddFragment>()

        onView(withId(R.id.ti_title)).perform(typeText("Test title"))
        onView(withId(R.id.ti_description)).perform(typeText("Test description"))

        onView(withId(R.id.menu_item_save)).perform(click())

        onView(withText(context.getString(R.string.saved))).check(matches(isDisplayed()))

        assertThat(repository.allPickItems().first().first().title).endsWith("Test title")
        assertThat(repository.allPickItems().first().first().description).endsWith("Test description")
        assertThat(repository.allPickItems().first().first().crateTime).isNotEmpty()
    }
}