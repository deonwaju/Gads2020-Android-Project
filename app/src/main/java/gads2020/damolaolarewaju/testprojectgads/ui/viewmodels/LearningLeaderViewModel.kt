package gads2020.damolaolarewaju.testprojectgads.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import gads2020.damolaolarewaju.testprojectgads.models.LearningLeaderModel
import gads2020.damolaolarewaju.testprojectgads.models.ResourceModel
import gads2020.damolaolarewaju.testprojectgads.networking.repositories.HomeRepository

class LearningLeaderViewModel
@ViewModelInject constructor(private val homeRepository: HomeRepository): ViewModel() {
    private val _toast: MutableLiveData<String?> = MutableLiveData()
    val toast : LiveData<String?>
    get() = _toast
    private val _learningLeadersModel: MutableLiveData<List<LearningLeaderModel>> = MutableLiveData()
    val learningLeadersModel : LiveData<List<LearningLeaderModel>>
    get() = _learningLeadersModel
    private val _loadingState: MutableLiveData<Boolean> = MutableLiveData()
    val loadingState : LiveData<Boolean>
    get() = _loadingState
    /**
     * get Learning Leaders and update leadingLeaders LiveData
     */
    fun fetchLearningLeaders(){
        _loadingState.postValue(true)
        viewModelScope.launch {
            when (val result = homeRepository.fetchLearningLeaders()){
                is ResourceModel.Success -> {
                    _loadingState.postValue(false)
                    val leaders = ArrayList<LearningLeaderModel>()
                    result.data?.forEach {
                        leaders.add(it)
                    }

                    //Timber.d(leaders.size.toString())
                    _learningLeadersModel.value = leaders
                }
                is ResourceModel.Loading -> {
                    _loadingState.value = false
                }
                is ResourceModel.Error -> {
                    _toast.value = result.message.toString()
                }
            }
        }
    }
}