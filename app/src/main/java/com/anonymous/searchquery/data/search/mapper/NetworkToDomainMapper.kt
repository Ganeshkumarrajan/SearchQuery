package com.anonymous.searchquery.data.search.mapper

interface NetworkToDomainMapper<I, O> {
    fun mapTo(input: I): O
}
