package com.picker.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.picker.MainCoroutineRule
import com.picker.data.repository.FakeMainRepository
import com.picker.dummyPickItems
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var repository: FakeMainRepository
    lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        repository = FakeMainRepository()
        mainViewModel = MainViewModel(repository)
    }


    @Test
    fun `Insert and read pick items`() = coroutineRule.runBlockingTest {
        dummyPickItems.forEach {
            mainViewModel.savePickItem(it)
        }
        assertThat(mainViewModel.allPickItems.first()).isEqualTo(dummyPickItems)
    }
}