package com.jbkalit.presentation.util

import java.text.DecimalFormat

object NumberUtils {

    fun formatPriceChanges(value: Double): String = DecimalFormat("##.##").format(value)
    fun formatPrice(value: Double): String = DecimalFormat("#,###").format(value)

}
