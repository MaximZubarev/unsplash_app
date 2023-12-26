package com.mldz.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mldz.core.ui.theme.RedPomegranate


@Composable
fun Error(
    text: String,
    modifier: Modifier = Modifier
) {
    Column {
        Spacer(modifier = Modifier.weight(1f))
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(RedPomegranate)
                    .padding(10.dp),
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}

@Preview
@Composable
private fun ErrorPreview() {
    Column {
        Error(
            text = "some error",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
    }
}