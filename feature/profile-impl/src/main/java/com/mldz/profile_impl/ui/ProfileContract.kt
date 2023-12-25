package com.mldz.profile_impl.ui

import com.mldz.core.common.base.UiEffect
import com.mldz.core.common.base.UiEvent
import com.mldz.core.common.base.UiState
import com.mldz.profile_api.models.Profile


interface ProfileContract {

    sealed class Event: UiEvent {
        object OnRepeatLoad: Event()
    }

    data class State(
        val isLoading: Boolean = false,
        val error: String? = null,
        val profile: Profile? = null
    ): UiState

    sealed class Effect: UiEffect {

    }

}