package com.catganisation.catalog.presentation.cats

import androidx.lifecycle.MutableLiveData
import com.catganisation.catalog.domain.models.holders.PageData
import com.catganisation.catalog.domain.models.CatBreedDto
import com.catganisation.catalog.domain.usecases.account.LogoutUseCase
import com.catganisation.catalog.domain.usecases.breeds.GetCatBreedsUseCase
import com.catganisation.catalog.presentation.common.BaseViewModel
import com.catganisation.catalog.utils.SingleLiveEvent
import com.paginate.Paginate
import javax.inject.Inject

private const val PAGE_SIZE = 20

class CatBreedsViewModel @Inject constructor(
    private val getCatBreedsUseCase: GetCatBreedsUseCase,
    private val logoutUseCase: LogoutUseCase
) : BaseViewModel(), Paginate.Callbacks {

    val error = SingleLiveEvent<String>()
    val catBreedViewDataList = MutableLiveData<List<CatBreedViewData>>()
    val openDetails = SingleLiveEvent<CatBreedDto>()
    val openLogin = SingleLiveEvent<Unit>()

    private var currentPage = 0
    private var hasLoadedAllItems = false
    private var isLoading = false
    private var catBreeds = listOf<CatBreedDto>()

    init {
        loadBreeds()
    }

    override fun onLoadMore() {
        currentPage++
        loadBreeds()
    }

    override fun isLoading(): Boolean = isLoading

    override fun hasLoadedAllItems(): Boolean = hasLoadedAllItems

    fun logout() {
        logoutUseCase.perform(Unit)
            .safeSubscribe({ openLogin.value = Unit })
    }

    private fun loadBreeds() {
        isLoading = true
        getCatBreedsUseCase.perform(
            PageData(
                currentPage,
                PAGE_SIZE
            )
        )
            .map { catBreeds ->
                hasLoadedAllItems = catBreeds.size < PAGE_SIZE
                this.catBreeds += catBreeds
                this.catBreeds.map { it.toViewData() }
            }
            .doFinally { isLoading = false }
            .safeSubscribe({
                catBreedViewDataList.value = it
            }, {
                error.value = it.message
            })
    }

    private fun CatBreedDto.toViewData() = CatBreedViewData(
        catBreed = this,
        onClicked = { openDetails.value = it }
    )
}