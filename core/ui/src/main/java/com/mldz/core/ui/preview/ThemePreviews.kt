package com.mldz.core.ui.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview


@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
annotation class ThemePreviews

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Preview(showBackground = true, backgroundColor = 0xFF000000)
annotation class BlackWhiteBackgroundPreview
