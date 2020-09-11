package gads2020.damolaolarewaju.testprojectgads.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import gads2020.damolaolarewaju.testprojectgads.models.ResourceModel
import gads2020.damolaolarewaju.testprojectgads.networking.repositories.HomeRepository

class SubmitDataViewModel @ViewModelInject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {
    private val _loadingState: MutableLiveData<Boolean> = MutableLiveData()
    val loadingState: LiveData<Boolean>
    get() = _loadingState
    private val _submissionSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val submissionSuccess: LiveData<Boolean>
    get() = _submissionSuccess
    private val _toast: MutableLiveData<String> = MutableLiveData()
    val toast: LiveData<String>
    get() = _toast
    fun submitProject(
        firstName: String, lastName: String, emailAddress: String, projectLink: String
    ) = viewModelScope.launch {
        _loadingState.postValue(true)
        val result = homeRepository.submitProject(
            firstName, lastName, emailAddress, projectLink
        )
        when (result){
            is ResourceModel.Success -> {
                _submissionSuccess.postValue(true)
                _loadingState.postValue(false)
            }
            is ResourceModel.Loading -> {
                _loadingState.postValue(true)
                _submissionSuccess.postValue(false)
            }
            is ResourceModel.Error -> {
                _submissionSuccess.postValue(false)
                _loadingState.postValue(false)
                _toast.value = result.message
            }
        }
    }
}