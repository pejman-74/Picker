package com.picker.ui.fragment

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.picker.R
import com.picker.data.repository.FakeMainRepository
import com.picker.data.repository.MainRepositoryInterface
import com.picker.di.DatabaseModule
import com.picker.di.DatabaseModuleTest
import com.picker.di.RepositoryModule
import com.picker.dummyPickItems
import com.picker.launchFragmentInHiltContainer
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@HiltAndroidTest
@UninstallModules(DatabaseModule::class, DatabaseModuleTest::class, RepositoryModule::class)
class HomeFragmentTest {
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
    fun checkPickItemWithEmptyDB_shouldShowErrorSnackBar() {
        launchFragmentInHiltContainer<HomeFragment>()
        onView(withId(R.id.btn_pick)).perform(click())
        onView(withText(context.getString(R.string.db_is_empty))).check(matches(isDisplayed()))
    }

    @Test
    fun checkPickItem_shouldShowPickItem() = runBlockingTest {

        repository.savePickItem(dummyPickItems[0])

        launchFragmentInHiltContainer<HomeFragment>()
        onView(withId(R.id.btn_pick)).perform(click())
        onView(withText(dummyPickItems[0].title)).check(matches(isDisplayed()))
        onView(withText(dummyPickItems[0].description)).check(matches(isDisplayed()))
    }
}