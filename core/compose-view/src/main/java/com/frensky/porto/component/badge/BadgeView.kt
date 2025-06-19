package com.frensky.porto.component.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.frensky.porto.component.badge.BadgeSpec.BadgeSpecs.large
import com.frensky.porto.component.badge.BadgeSpec.BadgeSpecs.small
import com.frensky.porto.theme.PTypography
import com.frensky.porto.util.conditional
import com.frensky.porto.util.rippleClickable

@Composable
fun BadgeView(
    text: String,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    color: BadgeColor = BadgeColor.Yellow,
    spec: BadgeSpec = small,
    minTextLines: Int = 1,
    identifier: String? = null,
    extra: Map<String, Any> = mapOf(),
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .heightIn(min = 16.dp)
            .clip(spec.shape)
            .background(color.containerColor)
            .border(
                width = 1.dp,
                color = color.borderColor,
                shape = spec.shape
            )
            .conditional(onClick != null) {
                rippleClickable {
                    onClick?.invoke()
                }
            }
            .padding(
                if (icon == null) spec.noIconPadding else spec.hasIconPadding
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.let {
            Icon(
                modifier = Modifier.size(spec.iconSize),
                painter = it,
                tint = color.tintColor,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
        Text(
            text = text,
            minLines = minTextLines,
            style = PTypography.captionSmallBold.copy(
                color = color.tintColor,
                textAlign = TextAlign.Center
            )
        )
    }
}

@Preview
@Composable
private fun BadgeSizePreview() {
    Column(
        modifier = Modifier.width(240.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // small badge
        BadgeView(text = "Label")

        // large badge
        BadgeView(text = "Label", spec = large)

        // small badge with icon
        BadgeView(text = "Label", icon = rememberVectorPainter(Icons.Default.Add))

        // large badge with icon
        BadgeView(text = "Label", spec = large, icon = rememberVectorPainter(Icons.Default.Add))

        // custom badge
        BadgeView(
            text = "Label",
            spec = BadgeSpec(
                hasIconPadding = PaddingValues(
                    horizontal = 20.dp, vertical = 8.dp
                ),
                iconSize = 20.dp,
                shape = RectangleShape
            ),
            icon = rememberVectorPainter(Icons.Default.Add)
        )
    }
}

@Preview
@Composable
private fun BadgeColorsPreview() {
    Column(
        modifier = Modifier.width(240.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // yellow badge
        BadgeView(text = "Label", icon = rememberVectorPainter(Icons.Default.Add))

        // blue badge
        BadgeView(text = "Label", color = BadgeColor.Blue, icon = rememberVectorPainter(Icons.Default.Add))

        // red badge
        BadgeView(text = "Label", color = BadgeColor.Red, icon = rememberVectorPainter(Icons.Default.Add))

        // green badge
        BadgeView(text = "Label", color = BadgeColor.Green, icon = rememberVectorPainter(Icons.Default.Add))

        // grey badge
        BadgeView(text = "Label", color = BadgeColor.Grey, icon = rememberVectorPainter(Icons.Default.Add))

        // purple badge
        BadgeView(text = "Label", color = BadgeColor.Purple, icon = rememberVectorPainter(Icons.Default.Add))
    }
}