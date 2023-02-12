package com.anonymous.searchquery.presentaiton

import com.anonymous.searchquery.domain.search.usecase.SearchDomain
import com.anonymous.searchquery.presentaiton.search.viewmodel.SearchResultMapperDomainToUI
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName

class SearchResultMapperDomainToUITest {
    private lateinit var mapper: SearchResultMapperDomainToUI
    @Before
    fun setup() {
        mapper = SearchResultMapperDomainToUI()
    }

    @DisplayName("covert valid domain data to UI data ")
    @Test
    fun convertValidDomainData() {
        val result = mapper.map(getFakeData())
        assert(result.size == 3)
        Assert.assertEquals(result[0].idObjects, 1L)
        Assert.assertEquals(result[2].idObjects, 3L)
    }

    private fun getFakeData(): List<SearchDomain> =
        listOf(
            SearchDomain(1),
            SearchDomain(2),
            SearchDomain(3)
        )
}
