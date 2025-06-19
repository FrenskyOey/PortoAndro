package com.frensky.porto.component.bottomSheet

import android.os.Handler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.frensky.porto.component.bottomSheet.sheet.SingleChoiceSheetContent
import com.frensky.porto.component.button.ButtonView
import com.frensky.porto.compose.R
import com.frensky.porto.theme.PColor
import com.frensky.porto.theme.PTypography
import com.frensky.porto.util.rippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetView(
    sheetType: BottomSheetType,
    sheetState: SheetState,
    title: String,
    modifier: Modifier = Modifier,
    buttonLabel: String = "OK",
    showHandle: Boolean = true,
    showBottomButton: Boolean = true,
    showCloseButton: Boolean = true,
    onDismissRequest: () -> Unit,
    onManualDismiss: (BottomSheetResult?) -> Unit,
) {
    var selectedResult by remember { mutableStateOf<BottomSheetResult?>(null) }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        dragHandle = {
            if (showHandle) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Box(
                        modifier = Modifier
                            .size(width = 80.dp, height = 4.dp)
                            .clip(CircleShape)
                            .background(PColor.neutral.neutral_50),
                    )
                }
            }
        },
        containerColor = PColor.white,
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
        ),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .padding(
                        vertical = if (showHandle) 8.dp else 12.dp,
                    ),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = 24.dp),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Text(
                        text = title,
                        style = PTypography.bodyLargeBold,
                    )
                }
                if (showCloseButton) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .rippleClickable { onManualDismiss(null) },
                        imageVector = ImageVector.vectorResource(R.drawable.ic_x),
                        tint = PColor.primary.base,
                        contentDescription = "",
                    )
                }
            }
            when (sheetType) {
                is BottomSheetType.Custom -> sheetType.content()
                is BottomSheetType.SingleChoice -> SingleChoiceSheetContent(
                    data = sheetType,
                    modifier = modifier,
                    onSelectedValueUpdated = { item ->
                        selectedResult = item
                        if (sheetType.isCloseWhenItemSelected) {
                            onManualDismiss(item)
                        }
                    })
            }
            if (showBottomButton) {
                ButtonView (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 16.dp),
                    text = buttonLabel,
                    onClick = {
                        Handler().postDelayed({
                            onManualDismiss(selectedResult)
                        }, 300)
                    },
                )
            }
        }
    }
}