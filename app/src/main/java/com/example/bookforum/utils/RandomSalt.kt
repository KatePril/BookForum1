package com.example.bookforum.utils

import kotlin.random.Random

fun generateRandomSalt(): String {
    val elements = "abCD^&*()EFcdeOPL_.23VWXY45whijklMN69xyzABGHIfgUZ1stuv!@78#$%mnorQRSTpqJK,?>"
    val saltLength = Random.nextInt(0, 10)
    var randomSalt = ""
    for (i in 0..saltLength) {
        randomSalt += elements[Random.nextInt(0, elements.length)]
    }
    return randomSalt
}