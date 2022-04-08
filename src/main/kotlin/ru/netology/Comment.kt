package ru.netology

data class Comment(
    val commentId: Int,
    val noteId: Int,
    val message: String,
    val itIsDeleted: Boolean = false
)