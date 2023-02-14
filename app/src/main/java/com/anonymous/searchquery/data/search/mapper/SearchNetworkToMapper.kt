package com.anonymous.searchquery.data.search.mapper

import com.anonymous.searchquery.data.search.model.SearchNetwork
import com.anonymous.searchquery.data.utils.NetworkToDomainMapper
import com.anonymous.searchquery.domain.search.model.SearchDomain

class SearchNetworkToMapper : NetworkToDomainMapper<SearchNetwork, List<SearchDomain?>> {
    override fun mapTo(input: SearchNetwork): List<SearchDomain?> {
        val list = input.objectIDs?.map { item ->
            item?.let {
                SearchDomain(it)
            }
        }

        return list ?: emptyList<SearchDomain>()
    }
}
