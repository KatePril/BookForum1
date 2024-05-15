package com.example.bookforum.data.daos

enum class MemberRight(val value: Int) {
    NOT_ADMIN(0),
    ADMIN(1),
    OWNER(2)
}