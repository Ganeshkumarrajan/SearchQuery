package com.anonymous.searchquery.data.details.mapper

import com.anonymous.searchquery.data.details.model.ObjectDetailsNetwork
import com.anonymous.searchquery.data.utils.NetworkToDomainMapper
import com.anonymous.searchquery.domain.details.model.ObjectDetailsDomain

class ObjectDetailsNetworkToDomainMapper :
    NetworkToDomainMapper<ObjectDetailsNetwork, ObjectDetailsDomain> {
    override fun mapTo(input: ObjectDetailsNetwork): ObjectDetailsDomain =
        ObjectDetailsDomain(
            input.objectName ?: "",
            input.title ?: "",
            input.department ?: "",
            input.primaryImage ?: "",
            input.additionalImages ?: emptyList<String>()
        )
}
