package com.anonymous.searchquery.presentaiton.search.events

sealed class UserEvent {
    data class Search(val keyword: String) : UserEvent()
}
