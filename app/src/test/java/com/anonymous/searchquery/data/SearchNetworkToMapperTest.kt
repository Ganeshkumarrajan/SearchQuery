package com.anonymous.searchquery.data

import com.anonymous.searchquery.data.search.mapper.NetworkToDomainMapper
import com.anonymous.searchquery.data.search.model.SearchNetwork
import com.anonymous.searchquery.domain.search.usecase.SearchDomain
import com.anonymous.searchquery.utils.getFakeEmptySearchNetwork
import com.anonymous.searchquery.utils.getFakeSearchNetwork
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName

class SearchNetworkToMapperTest {
    private lateinit var mapper: NetworkToDomainMapper<SearchNetwork, List<SearchDomain?>>

    @Before
    fun setup() {
        mapper = SearchNetworkToMapper()
    }

    @DisplayName("convert valid search result data of network to domain")
    @Test
    fun mapValidNetworkDataTODomain() {
        val result = mapper.mapTo(getFakeSearchNetwork())
        Assertions.assertTrue(result?.size == 3)
        Assertions.assertTrue(result?.get(0)?.objectId == 1L)
        Assertions.assertTrue(result?.get(1)?.objectId == 2L)
    }

    @DisplayName("convert empty search result data of network to domain")
    @Test
    fun mapEmptyResultNetworkDataTODomain() {
        val result = mapper.mapTo(getFakeEmptySearchNetwork())
        Assertions.assertTrue(result?.isEmpty() ?: false)
        Assertions.assertTrue(result?.size == 0)
    }
}
