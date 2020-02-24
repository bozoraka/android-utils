package com.bozoraka.utils.format

import com.bozoraka.utils.misc.orZero
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

const val NON_BREAKING_SPACE = '\u00A0'

fun BigDecimal?.formatWithMinimumFraction(scale: Int = 2, suffix: String = ""): String {
    val df = DecimalFormat("###,###.########")
    val dfs = DecimalFormatSymbols()
    dfs.groupingSeparator = NON_BREAKING_SPACE
    dfs.decimalSeparator = '.'
    df.decimalFormatSymbols = dfs
    df.groupingSize = 3
    var out = df.format(this.orZero().setScale(scale, BigDecimal.ROUND_FLOOR))
    if (suffix.isNotBlank()) {
        out = out.plus(NON_BREAKING_SPACE).plus(suffix)
    }
    return out
}
