/*
 * This source file was generated by the Gradle 'init' task
 */
package main

import main.typehandler.*

fun flipWords(value: Float): Float {
    var bitString = floatToBitString(value)
    bitString = String.format("%s%s", bitString.slice(16..31), bitString.slice(0..15))
    return bitStringToFloat(bitString)
}

fun shiftRegisters(registers: Array<Float>, shift: Int): Float {
    if (shift !in -1..1) {
        throw IllegalArgumentException("Register shift must be within -1 to 1")
    }
    val bitStrings = registers.map({ floatToBitString(it) })
    val startIndexSlice = Float.SIZE_BITS + (shift * 2 * Byte.SIZE_BITS)
    val endIndexSlice = startIndexSlice + Float.SIZE_BITS
    val shiftedBitString = bitStrings.joinToString("").slice(startIndexSlice..endIndexSlice)
    return bitStringToFloat(shiftedBitString)
}

fun parseArgs(args: Array<String>): Map<String, String>{
    return args.toList().chunked(2).associate { it[0].replace("--", "") to it[1] }

}

fun main(args: Array<String>) {
    val argsMap = parseArgs(args)
    println(argsMap)
}
