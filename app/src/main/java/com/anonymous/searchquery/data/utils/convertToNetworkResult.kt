package com.anonymous.searchquery.data.utils

import com.anonymous.searchquery.domain.utils.NetworkResult
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess

internal fun <I, O> convertToNetworkResult(
    data: ApiResponse<I>,
    mapper: NetworkToDomainMapper<I, O>
): NetworkResult<O> {
    var result: NetworkResult<O>? = null

    data.onSuccess {
        result = NetworkResult.Success(mapper.mapTo(this.data))
    }.onError {
        result = NetworkResult.Error()
    }.onException {
        result = NetworkResult.Error()
    }

    return result ?: NetworkResult.Error()
}
