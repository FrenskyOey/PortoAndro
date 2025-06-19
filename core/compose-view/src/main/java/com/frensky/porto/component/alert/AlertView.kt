package com.frensky.porto.component.alert

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.frensky.porto.compose.R
import com.frensky.porto.theme.PTypography
import com.frensky.porto.util.rippleClickable

@Composable
fun AlertView(
    modifier: Modifier = Modifier,
    type: AlertType,
    title: String? = null,
    message: String? = null,
    showLeftIcon: Boolean = true,
    showCloseIcon: Boolean = true,
    onCloseIconClicked: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(8.dp),
                color = type.borderColor
            )
            .background(type.containerColor)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (showLeftIcon) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = ImageVector.vectorResource(type.icon),
                tint = type.messageColor,
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            if (!title.isNullOrBlank()) {
                Box(
                    modifier = Modifier.heightIn(min = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = title,
                        style = PTypography.captionLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = type.titleColor
                        )
                    )
                }
            }
            if (!title.isNullOrBlank() && !message.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
            }
            if (!message.isNullOrBlank()) {
                Box(
                    modifier = Modifier.heightIn(min = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = message,
                        style = PTypography.captionLarge.copy(
                            color = type.messageColor
                        )
                    )
                }
            }
        }
        if (showCloseIcon) {
            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .rippleClickable {
                        onCloseIconClicked()
                    },
                imageVector = ImageVector.vectorResource(R.drawable.ic_x),
                tint = type.messageColor,
                contentDescription = "close"
            )
        }
    }
}

@Preview
@Composable
fun AlertInformation() {
    AlertView(
        modifier = Modifier.fillMaxWidth(),
        type = AlertType.INFORMATION,
        title = "Title",
        message = "Messages"
    )
}

@Preview
@Composable
fun AlertWarning() {
    AlertView(
        modifier = Modifier.fillMaxWidth(),
        type = AlertType.WARNING,
        title = "Title",
        message = "Messages"
    )
}

@Preview
@Composable
fun AlertPositive() {
    AlertView(
        modifier = Modifier.fillMaxWidth(),
        type = AlertType.POSITIVE,
        title = "Title",
        message = "Messages"
    )
}

@Preview
@Composable
fun AlertNegative() {
    AlertView(
        modifier = Modifier.fillMaxWidth(),
        type = AlertType.NEGATIVE,
        title = "Title",
        message = "Messages"
    )
}

@Preview
@Composable
fun AlertNeutral() {
    AlertView(
        modifier = Modifier.fillMaxWidth(),
        type = AlertType.NEUTRAL,
        title = "Title",
        message = "Messages"
    )
}