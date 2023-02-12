package com.anonymous.searchquery.presentaiton.search.viewmodel

sealed class UserEvent {
    data class Search(val keyword: String) : UserEvent()
}
