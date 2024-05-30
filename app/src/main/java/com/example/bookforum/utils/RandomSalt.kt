package com.example.bookforum.utils

import kotlin.random.Random

fun generateRandomSalt(): String {
    val elements = "abCD^&IfgUZ1st*()EFjeOPL_kcd.23V45whiWXYlMN69xyQzABGHuv!@7nor8#\$%mRSTpqJK,?>"
    val saltLength = Random.nextInt(0, 10)
    var randomSalt = ""
    for (i in 0..saltLength) {
        randomSalt += elements[Random.nextInt(0, elements.length)]
    }
    return randomSalt
}