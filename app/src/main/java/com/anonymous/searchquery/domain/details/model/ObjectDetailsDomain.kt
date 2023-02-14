package com.anonymous.searchquery.domain.details.model

data class ObjectDetailsDomain(
    val objectName: String,
    val title: String,
    val department: String,
    val imageURL: String,
    val additionalImages: List<String>
)
