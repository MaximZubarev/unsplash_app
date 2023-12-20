package com.mldz.core.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mldz.core.ui.R
import com.mldz.core.ui.preview.BlackWhiteBackgroundPreview
import com.mldz.core.ui.preview.ThemePreviews
import com.mldz.core.ui.theme.GrayMercury


@Composable
fun ErrorLoading(
    message: String?,
    onRepeat: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.dizzy_robot), contentDescription = message)
        Spacer(modifier = Modifier.size(40.dp))
        Text(
            text = stringResource(id = R.string.smth_wrong),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(10.dp))
        message?.let {
            Text(
                text = stringResource(id = R.string.have_error) + message,
                style = MaterialTheme.typography.labelLarge,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.size(40.dp))
        OutlinedButton(
            onClick = onRepeat,
            border = BorderStroke(1.dp, GrayMercury)
        ) {
            Text(
                text = stringResource(id = R.string.try_again)
            )
        }
    }
}

@Preview
@BlackWhiteBackgroundPreview
@Composable
fun ErrorLoadingPreview() {
    ErrorLoading(message = "some message some messagesome messagesome message", onRepeat = { /*TODO*/ })
}