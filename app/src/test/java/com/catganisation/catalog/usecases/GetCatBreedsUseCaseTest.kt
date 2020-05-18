package com.catganisation.catalog.usecases

import com.catganisation.catalog.data.models.CatBreed
import com.catganisation.catalog.data.models.Image
import com.catganisation.catalog.data.remote.services.CatsApi
import com.catganisation.catalog.data.repositories.CatsMainRepository
import com.catganisation.catalog.data.repositories.base.CatsRepository
import com.catganisation.catalog.domain.mappers.CatBreedMapper
import com.catganisation.catalog.domain.models.holders.PageData
import com.catganisation.catalog.domain.usecases.breeds.GetCatBreedsUseCase
import com.catganisation.catalog.utils.TestSchedulerProvider
import com.catganisation.catalog.utils.providers.schedulers.SchedulerProvider
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

class GetCatBreedsUseCaseTest {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var catsApi: CatsApi

    private lateinit var testDataCatBreeds: List<CatBreed>
    private var testPage = 0
    private var testLimit = 5

    private lateinit var catsRepository: CatsRepository

    private lateinit var testUseCase: GetCatBreedsUseCase

    private lateinit var mapper: CatBreedMapper

    private lateinit var schedulerProvider: SchedulerProvider

    @Before
    fun setup() {
        testDataCatBreeds = listOf(
            CatBreed(
                "1", "name1", "description1", "temperament1",
                "C1", "Country1", "url.com"
            ),
            CatBreed(
                "2", "name2", null, "temperament2",
                "C2", "Country2", "url2.com"
            ),
            CatBreed()
        )

        whenever(catsApi.getCatBreeds(eq(testPage), eq(testLimit)))
            .thenReturn(Single.just(testDataCatBreeds))

        schedulerProvider = TestSchedulerProvider()

        catsRepository = CatsMainRepository(catsApi)
        mapper = CatBreedMapper()
        testUseCase = GetCatBreedsUseCase(catsRepository, mapper, schedulerProvider)
    }

    @Test
    fun testRepository() {
        val testObservable = catsRepository.getBreeds(testPage, testLimit)
            .test()
        testObservable.assertValue(testDataCatBreeds)
    }

    @Test
    fun testSuccess() {
        val testImages = ArrayList<Image>()
        testDataCatBreeds.forEach {
            val breedId = it.id ?: return@forEach
            val images = listOf(Image(breeds = listOf(it)))
            whenever(
                catsApi.getBreedImages(
                    eq(breedId),
                    anyInt(),
                    anyInt(),
                    anyString()
                )
            ).thenReturn(Single.just(images))
            testImages.addAll(images)
        }
        val testDomainCatBreeds = mapper.toDomain(testImages)

        val testSingle = testUseCase.perform(PageData(testPage, testLimit))
            .test()
        val validCatBreedCount = testDataCatBreeds.count { it.id != null }

        verify(catsApi).getCatBreeds(eq(testPage), eq(testLimit))
        verify(catsApi, times(validCatBreedCount))
            .getBreedImages(anyString(), anyInt(), anyInt(), anyString())

        testSingle.assertValue { result -> result == testDomainCatBreeds }
    }

    @Test
    fun testFailure() {
        val testException = RuntimeException("test exception")

        whenever(catsApi.getCatBreeds(eq(testPage), eq(testLimit)))
            .thenReturn(Single.error(testException))

        val testSingle = testUseCase.perform(PageData(testPage, testLimit))
            .test()

        testSingle.assertError(testException)
    }
}