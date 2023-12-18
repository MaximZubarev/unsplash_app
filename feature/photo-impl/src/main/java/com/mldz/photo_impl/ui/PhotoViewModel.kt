package com.mldz.photo_impl.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.android.annotation.KoinViewModel
import kotlin.math.log


@KoinViewModel
class PhotoViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val id: String = checkNotNull(savedStateHandle["photoIdArg"])

    private val _state = MutableStateFlow(id)
    val state = _state.asStateFlow()
}