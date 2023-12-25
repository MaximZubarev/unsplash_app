package com.mldz.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import com.mldz.core.ui.icon.Icons


@Composable
fun Location(
    location: String?,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.HorizontalOrVertical = Arrangement.Center
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement,
        modifier = modifier
    ) {
        location?.let {
            Icon(
                imageVector = Icons.Place,
                contentDescription = null,
                modifier = Modifier.alpha(0.4f)
            )
            Text(
                text = location,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray,
            )
        }
    }
}