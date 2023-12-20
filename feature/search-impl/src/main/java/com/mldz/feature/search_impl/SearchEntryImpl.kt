package com.mldz.feature.search_impl

import androidx.compose.runtime.Composable
import com.mldz.feature.search.SearchEntry
import com.mldz.feature.search_impl.ui.SearchScreen
import org.koin.core.annotation.Single


@Single
class SearchEntryImpl: SearchEntry() {

    @Composable
    override fun Start() {
        SearchScreen()
    }
}