package com.frensky.porto.component.bottomSheet.sheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.frensky.porto.component.bottomSheet.BottomSheetResult
import com.frensky.porto.component.bottomSheet.BottomSheetType
import com.frensky.porto.component.radioButton.RadioButtonView
import com.frensky.porto.theme.PColor
import com.frensky.porto.theme.PTypography

@Composable
internal fun SingleChoiceSheetContent(
    data: BottomSheetType.SingleChoice,
    onSelectedValueUpdated: (BottomSheetResult) -> Unit,
    modifier: Modifier = Modifier
) {
    var currentSelectedValue by remember { mutableStateOf(data.initialSelectedId) }
    val selectedTextStyle = PTypography.bodyLargeBold.copy(
        color = PColor.primary.base
    )
    val normalTextStyle = PTypography.bodyLarge.copy(
        color = PColor.neutral.neutral_90
    )

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(data.itemList) { item ->
            val isSelected = item.id == currentSelectedValue
            Column(
                modifier = modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            currentSelectedValue = item.id
                            onSelectedValueUpdated(
                                BottomSheetResult.SelectorItem(
                                    id = item.id,
                                    label = item.label
                                )
                            )
                        }
                        .padding(
                            vertical = 12.dp,
                            horizontal = 16.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = item.label,
                        style = if (isSelected) selectedTextStyle else normalTextStyle
                    )
                    RadioButtonView(
                        selected = isSelected,
                        color = if (isSelected) PColor.primary.base else PColor.neutral.neutral_50,
                        onClick = {
                            currentSelectedValue = item.id
                            onSelectedValueUpdated(
                                BottomSheetResult.SelectorItem(
                                    id = item.id,
                                    label = item.label
                                )
                            )
                        }
                    )
                }
                HorizontalDivider(color = PColor.neutral.neutral_30)
            }
        }
    }
}