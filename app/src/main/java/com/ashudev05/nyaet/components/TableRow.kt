package com.ashudev05.nyaet.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ashudev05.nyaet.R
import com.ashudev05.nyaet.ui.theme.Typography

@Composable
fun TableRow(
    label: String,
    onClick: (String) -> Unit,
    color: Color,
    hasArrow: Boolean = false,
    isDestructive: Boolean = false
) {
    val textColor = if (isDestructive) MaterialTheme.colorScheme.error else color

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(label) }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = Typography.bodyMedium, color = textColor)
        if (hasArrow) {
            Icon(
                painter = painterResource(id = R.drawable.chevron_right_24px),
                contentDescription = "Right Arrow"
            )
        }
    }
}