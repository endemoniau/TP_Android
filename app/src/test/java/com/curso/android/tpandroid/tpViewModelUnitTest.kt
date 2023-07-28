package com.curso.android.tpandroid

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import model.ComparadorModel
import model.tpViewModel
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals


class tpViewModelUnitTest {
    private lateinit var viewModel:tpViewModel

    @get:Rule
    val instantTaskRule= InstantTaskExecutorRule()
    @OptIn(ExperimentalCoroutinesApi::class)
    val dispatcher=StandardTestDispatcher()


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun initTest(){
        Dispatchers.setMain(dispatcher)
        viewModel= tpViewModel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun dispatcherReset(){
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun checkViewModelInit()= runTest {

        val messageValue=viewModel.resultMessage.value?.toString()
        assertEquals("-",messageValue)
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun chekResultOK()= runTest {
        launch {
            viewModel.setOkMessage("OK")
            viewModel.setFailMessage("NOT OK")
            val testText:ComparadorModel=ComparadorModel("TextOK","TextNOTOK")
            viewModel.checkStrings(testText)
        }

        advanceUntilIdle()

        val value=  viewModel.resultMessage.value
        assertEquals("NOT OK",value)
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun chekResultNotOk()= runTest {
        launch {
            viewModel.setOkMessage("OK")
            viewModel.setFailMessage("NOT OK")
            val testText=ComparadorModel("TextOK","TextOK")
            viewModel.checkStrings(testText)
        }
        advanceUntilIdle()
        val value=  viewModel.resultMessage.value
        assertEquals("OK",value)
    }
}