package com.ashudev05.nyaet.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ashudev05.nyaet.R
import com.ashudev05.nyaet.ui.theme.Typography

@Composable
fun TableRow(
    label: String,
    modifier: Modifier = Modifier,
    color: Color,
    hasArrow: Boolean = false,
    isDestructive: Boolean = false,
    detail: (@Composable RowScope.() -> Unit)? = null,
) {
    val textColor = if (isDestructive) MaterialTheme.colorScheme.error else color

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            style = Typography.bodyMedium,
            color = textColor,
            modifier = Modifier.padding(vertical = 10.dp)
        )
        if (hasArrow) {
            Icon(
                painter = painterResource(id = R.drawable.chevron_right_24px),
                contentDescription = "Right Arrow",
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }

        detail?.invoke(this)
    }
}