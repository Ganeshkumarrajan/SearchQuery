package com.anonymous.searchquery.presentaiton.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anonymous.searchquery.R
import com.anonymous.searchquery.domain.details.model.ObjectDetailsDomain
import com.anonymous.searchquery.domain.details.usecase.GetObjectDetailsUseCase
import com.anonymous.searchquery.domain.utils.NetworkResult
import com.anonymous.searchquery.presentaiton.details.model.ObjectDetailsUI
import com.anonymous.searchquery.presentaiton.search.mapper.DomainToUIMapper
import com.anonymous.searchquery.presentaiton.util.ErrorType
import com.anonymous.searchquery.presentaiton.util.ResourceManager
import com.anonymous.searchquery.presentaiton.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ObjectDetailsViewModel @Inject constructor(
    private val getObjectDetailsUseCase: GetObjectDetailsUseCase,
    private val mapper: DomainToUIMapper<ObjectDetailsDomain, ObjectDetailsUI>,
    private val resourceManger: ResourceManager
) :
    ViewModel() {
    private val _objectDetails = MutableStateFlow<UIState<ObjectDetailsUI>>(UIState.Nothing())
    val objectDetails: StateFlow<UIState<ObjectDetailsUI>> = _objectDetails

    fun objectDetails(id: Long) {
        _objectDetails.value = UIState.Loading()
        viewModelScope.launch {
            getObjectDetailsUseCase.getDetails(id).collectLatest {
                parseObjectDetails(it)
            }
        }
    }

    private fun parseObjectDetails(data: NetworkResult<ObjectDetailsDomain>) {
        _objectDetails.value = when (data) {
            is NetworkResult.Success -> {
                UIState.Success(mapper.map(data.data))
            }
            is NetworkResult.Error -> {
                UIState.Error(
                    resourceManger.getErrorMessage(
                        ErrorType.API_CALL_ERROR
                    )
                )
            }
        }
    }
}

class ObjectDetailsMapperDomainToUI(private val resourceManger: ResourceManager) :
    DomainToUIMapper<ObjectDetailsDomain, ObjectDetailsUI> {
    override fun map(input: ObjectDetailsDomain): ObjectDetailsUI =
        ObjectDetailsUI(
            listOf(
                resourceManger.getString(R.string.object_name) + input.objectName,
                resourceManger.getString(R.string.title) + input.title,
                resourceManger.getString(R.string.department) + input.department
            ),
            input.imageURL,
            input.additionalImages
        )
}
