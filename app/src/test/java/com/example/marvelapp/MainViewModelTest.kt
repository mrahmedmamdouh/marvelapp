package com.example.marvelapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvelapp.data.retrofit.ApiInterface
import com.example.marvelapp.ui.main.MainViewModel
import com.nhaarman.mockitokotlin2.mock
import io.mockk.MockKAnnotations
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val api = mock<ApiInterface>()
    lateinit var mainViewModel: MainViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(api, "Hulk")
    }

    @Test
    fun testApi() {
        mainViewModel.charactersLiveData.observeForever {}
        Assert.assertNotNull(mainViewModel.charactersLiveData.value)
    }
}