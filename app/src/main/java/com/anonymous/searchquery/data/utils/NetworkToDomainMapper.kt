package com.anonymous.searchquery.data.utils

interface NetworkToDomainMapper<I, O> {
    fun mapTo(input: I): O
}
