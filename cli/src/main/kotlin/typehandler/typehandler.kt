package main.typehandler

import kotlin.math.pow
import kotlin.math.absoluteValue


private val BITS_FACTOR = generateBitFactors()

private fun generateBitFactors(): List<Int> {
    var factors = mutableListOf<Int>()
    for (i in 0 ..< 31) {
        factors.add(0, 2.0.pow(i).toInt())
    }
    return factors.toList()
}

public fun floatToBitString(value: Float): String {
    var bits = value.toBits()
    return intToBitString(bits)
}

public fun intToBitString(value: Int): String {
    var bits = value
    var bitString = StringBuilder("")
    if (bits >= 0) {
        bitString.append("0")
    } else {
        bitString.append("1")
        bits = Int.MAX_VALUE - (bits.absoluteValue - 1)
    }
    for (factor in BITS_FACTOR) {
        if ((bits / factor) > 0) {
            bitString.append("1")
            bits -= factor
        } else {
            bitString.append("0")
        }
    }
    return bitString.toString()
}

public fun sliceToHex(slice: String): Char {
    var value = 0
    for ((idx, char) in slice.reversed().withIndex()) {
        if (char == '1') {
            value += 2.0.pow(idx).toInt()
        }
    }
    return value.digitToChar(radix = 16)
}

public fun floatToHexString(value: Float): String {
    var hexString = StringBuilder("")
    var bitString = floatToBitString(value)
    for (i in 0 ..< 32 / 4) {
        var slice = bitString.slice(i * 4..i * 4 + 3)
        hexString.append(sliceToHex(slice))
    }
    return hexString.toString()
}

public fun bitStringToFloat(value: String): Float {
    var number = 0.0
    for ((idx, char) in value.slice(1..<32).reversed().withIndex()) {
        if (char == '1') {
            number += 2.0.pow(idx)
        }
    }
    if (value[0] == '1'){
        number = number -1 - Int.MAX_VALUE  
    }
    return Float.fromBits(number.toInt())
}
