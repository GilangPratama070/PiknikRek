package com.gilangpratama.piknikrek.ui.onboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilangpratama.piknikrek.data.repository.setting.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val settingRepository: SettingRepository
): ViewModel() {

    fun isFirstTime() = settingRepository.isFirstTime()

    fun setIsFirstTime(isFirstTime: Boolean) = viewModelScope.launch {
        settingRepository.setIsFirstTime(isFirstTime)
    }
}