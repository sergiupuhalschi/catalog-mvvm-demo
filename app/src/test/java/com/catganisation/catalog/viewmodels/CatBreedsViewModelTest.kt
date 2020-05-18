package com.catganisation.catalog.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.catganisation.catalog.domain.models.CatBreed
import com.catganisation.catalog.domain.usecases.account.LogoutUseCase
import com.catganisation.catalog.domain.usecases.breeds.GetCatBreedsUseCase
import com.catganisation.catalog.presentation.cats.CatBreedViewData
import com.catganisation.catalog.presentation.cats.CatBreedsViewModel
import com.catganisation.catalog.utils.providers.StringProvider
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CatBreedsViewModelTest {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    @Mock
    lateinit var getCatBreedsUseCase: GetCatBreedsUseCase

    @Mock
    lateinit var logoutUseCase: LogoutUseCase

    @Mock
    lateinit var stringProvider: StringProvider

    @Mock
    lateinit var onCatBreedClicked: (CatBreed) -> (Unit)


    private lateinit var testViewModel: CatBreedsViewModel

    private lateinit var testCatBreeds: List<CatBreedViewData>


    @Before
    fun setup() {
        testViewModel = CatBreedsViewModel(getCatBreedsUseCase, logoutUseCase, stringProvider)
        testCatBreeds = listOf(
            CatBreedViewData(
                CatBreed("", "", "", "", "", "", "", ""),
                onClicked = onCatBreedClicked
            )
        )
    }

    @Test
    fun testFail() {
        val runtimeException = RuntimeException("my_exception")
        whenever(getCatBreedsUseCase.perform()).thenReturn(
            Single.error(runtimeException)
        )
        testViewModel.onLoadMore()

        assertNotNull(testViewModel.error.value)
        assertEquals(testViewModel.error.value, anyString())
    }
}