package com.frensky.porto.component.bottomSheet

import androidx.compose.runtime.Composable

sealed interface BottomSheetType {
    data class Custom(val content: @Composable () -> Unit) : BottomSheetType

    data class SingleChoice(
        val itemList: List<BottomSheetResult.SelectorItem>,
        val initialSelectedId: String? = null,
        val isCloseWhenItemSelected: Boolean = false,
    ) : BottomSheetType
}

sealed interface BottomSheetResult {
    data class SelectorItem(
        val id: String, val label: String
    ) : BottomSheetResult
}