package gads2020.damolaolarewaju.testprojectgads.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import gads2020.damolaolarewaju.testprojectgads.models.ResourceModel
import gads2020.damolaolarewaju.testprojectgads.models.IQSkillLeaderModel
import gads2020.damolaolarewaju.testprojectgads.networking.repositories.HomeRepository

class IQSkillLeaderViewModel
@ViewModelInject constructor(private val homeRepository: HomeRepository) : ViewModel(){
    private val _toast: MutableLiveData<String?> = MutableLiveData()
    val toast : LiveData<String?>
        get() = _toast
    private val _learningLeaders: MutableLiveData<List<IQSkillLeaderModel>> = MutableLiveData()
    val learningLeaders : LiveData<List<IQSkillLeaderModel>>
        get() = _learningLeaders
    /**
     * get Learning Leaders and update leadingLeaders LiveData
     */
    fun fetchIQLeaders(){
        viewModelScope.launch {
            when (val result = homeRepository.fetchIQLeaders()){
                is ResourceModel.Loading -> {

                }
                is ResourceModel.Success -> {
                    _learningLeaders.value = result.data
                }
                is ResourceModel.Error -> {
                    _toast.value = result.message
                }
            }
        }
    }
}