package com.mldz.profile_impl.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mldz.core.common.base.BaseViewModel
import com.mldz.profile_api.usecase.GetUserProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.annotation.KoinViewModel


@KoinViewModel
class ProfileViewModel(
    savedStateHandle: SavedStateHandle,
    private val getUserProfileUseCase: GetUserProfileUseCase
) : BaseViewModel<ProfileContract.Event, ProfileContract.State, ProfileContract.Effect>() {

    private val username: String = checkNotNull(savedStateHandle["profileIdArg"])

    private val usernameState = MutableStateFlow(username)

    init {
        loadProfile()
    }

    override fun createInitialState(): ProfileContract.State {
        return ProfileContract.State(isLoading = true)
    }

    override fun handleEvent(event: ProfileContract.Event) {
        when (event) {
            is ProfileContract.Event.OnRepeatLoad -> restartLoad()
            else -> {}
        }
    }

    private fun restartLoad() {
        setState {
            currentState.copy(
                isLoading = true,
                error = null,
                profile = null
            )
        }
        loadProfile()
    }

    private fun loadProfile() {
        usernameState
            .onEach {
                val profile = getUserProfileUseCase(username = it)
                setState {
                    currentState.copy(
                        isLoading = false,
                        error = null,
                        profile = profile
                    )
                }
            }
            .catch {
                setState {
                    currentState.copy(
                        isLoading = false,
                        error = it.message,
                        profile = null
                    )
                }
            }
            .launchIn(viewModelScope)
    }
}