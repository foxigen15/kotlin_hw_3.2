package ru.netology

data class Note(
    val noteId: Int,
    val title: String,
    val text: String,
    val comments: MutableList<Comment> = mutableListOf(), //создаем коллекцию комментариев
    val itIsDeleted: Boolean = false
    ) {
}