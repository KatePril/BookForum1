package com.example.bookforum.utils

import com.example.bookforum.utils.secretItems.ALGORITHM
import com.example.bookforum.utils.secretItems.ITERATIONS
import com.example.bookforum.utils.secretItems.KEY_LENGTH
import com.example.bookforum.utils.secretItems.SALT
import com.example.bookforum.utils.secretItems.SECRET
import java.security.spec.KeySpec
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

@OptIn(ExperimentalStdlibApi::class)
fun hashPassword(password: String): String {
    val combinedSalt = "$SALT$SECRET".toByteArray()
    val factory: SecretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM)
    val spec: KeySpec = PBEKeySpec(password.toCharArray(), combinedSalt, ITERATIONS, KEY_LENGTH)
    val key: SecretKey = factory.generateSecret(spec)
    val hash: ByteArray = key.encoded
    return hash.toHexString()
}