package com.picker.data.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.picker.data.database.PickDatabase
import com.picker.di.DatabaseModule
import com.picker.dummyPickItems
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
@UninstallModules(DatabaseModule::class)
class PickItemDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var db: PickDatabase

    lateinit var dao: PickItemDao

    @Before
    fun setUp() {
        hiltRule.inject()
        dao = db.picItemDao()
    }

    @Test
    fun testSaveAndRead() = runBlockingTest {
        dummyPickItems.forEach {
            dao.save(it)
        }
        assertThat(dao.allPickItems().first()).isEqualTo(dummyPickItems)
    }

    @Test
    fun testIdAssertion() = runBlockingTest {
        dummyPickItems.forEach {
            dao.save(it)
        }
        assertThat(dao.allPickItems().first()[0].id).isEqualTo(1)
        assertThat(dao.allPickItems().first()[1].id).isEqualTo(2)
    }

}