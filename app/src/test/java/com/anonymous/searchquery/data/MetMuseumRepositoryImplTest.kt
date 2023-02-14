package com.anonymous.searchquery.data

import com.anonymous.searchquery.data.api.MetMuseumService
import com.anonymous.searchquery.data.details.model.ObjectDetailsNetwork
import com.anonymous.searchquery.data.search.model.SearchNetwork
import com.anonymous.searchquery.data.search.repository.MetMuseumRepositoryImpl
import com.anonymous.searchquery.data.utils.NetworkToDomainMapper
import com.anonymous.searchquery.domain.details.model.ObjectDetailsDomain
import com.anonymous.searchquery.domain.search.model.SearchDomain
import com.anonymous.searchquery.domain.utils.NetworkResult
import com.anonymous.searchquery.utils.fakeEmptySearchApiResponse
import com.anonymous.searchquery.utils.fakeSearchApiResponse
import com.anonymous.searchquery.utils.getFakeEmptySearchDomain
import com.anonymous.searchquery.utils.getFakeEmptySearchNetwork
import com.anonymous.searchquery.utils.getFakeSearchDomain
import com.anonymous.searchquery.utils.getFakeSearchNetwork
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName

class MetMuseumRepositoryImplTest {
    private lateinit var repository: MetMuseumRepositoryImpl
    private val service: MetMuseumService = mockk()
    private val mapper: NetworkToDomainMapper<SearchNetwork, List<SearchDomain?>> = mockk()
    private val detailsMapper: NetworkToDomainMapper<ObjectDetailsNetwork, ObjectDetailsDomain> = mockk()

    @Before
    fun setup() {
        repository = MetMuseumRepositoryImpl(service, mapper, Dispatchers.Default, detailsMapper)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @DisplayName("collect search result on valid keyword")
    @Test
    fun searchOnValidKeyword() {
        runTest {
            val keyWord = "valid"

            coEvery {
                service.searchQuery(keyWord)
            } returns fakeSearchApiResponse()

            every {
                mapper.mapTo(getFakeSearchNetwork())
            } returns getFakeSearchDomain()

            val result = repository.doSearch(keyWord).first()
            Assertions.assertTrue(result is NetworkResult.Success)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @DisplayName("collect empty search result on valid keyword")
    @Test
    fun searchOnInValidKeyword() {
        runTest {
            val keyWord = "inValid"

            coEvery {
                service.searchQuery(keyWord)
            } returns fakeEmptySearchApiResponse()

            every {
                mapper.mapTo(getFakeEmptySearchNetwork())
            } returns getFakeEmptySearchDomain()

            val result = repository.doSearch(keyWord).first()
            Assertions.assertTrue(result is NetworkResult.Success)
        }
    }
}
