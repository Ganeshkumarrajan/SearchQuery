package com.anonymous.searchquery.presentaiton.search.mapper

import com.anonymous.searchquery.domain.search.model.SearchDomain
import com.anonymous.searchquery.presentaiton.search.model.SearchUI

class SearchResultMapperDomainToUI() : DomainToUIMapper<List<SearchDomain?>, List<SearchUI>> {
    override fun map(input: List<SearchDomain?>): List<SearchUI> =
        input.map {
            SearchUI(it?.objectId ?: 0)
        }
}

interface DomainToUIMapper<I, O> {
    fun map(input: I): O
}
