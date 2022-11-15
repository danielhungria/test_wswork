package com.example.carappwswork.model

import com.example.carappwswork.core.Constants

data class HeaderItem(
    val titulo: String
): AdapterItem {
    override fun itemViewType() = Constants.ADAPTER_ITEM_HEADER_TYPE
 }