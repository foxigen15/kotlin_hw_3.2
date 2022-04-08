package ru.netology

class Notes {
    private val notes = mutableListOf<Note>()
    private var noteId = 0
    private var commentId = 0

    fun add(title: String, text: String): Int {
        noteId += 1
        notes.add(Note(title = title, text = text, noteId = noteId))
        return noteId
    }

    fun createComment(noteId: Int, message: String): Int {
        for (note in notes) {
            if (note.noteId == noteId) {
                commentId += 1
                note.comments.add(Comment(message = message, commentId = commentId, noteId = noteId))
                return commentId
            }
        }
        return 0
    }

    fun delete(noteId: Int): Boolean {
        for (note in notes) {
            if (note.noteId == noteId) {
                val deletedNote = note.copy(itIsDeleted = true)
                notes.remove(note)
                notes.add(deletedNote)
                return true
            }
        }
        return false
    }

    fun deleteComment(commentId: Int): Boolean {
        for (note in notes) {
            for (comment in note.comments) {
                if (comment.commentId == commentId) {
                    val deletedComment = comment.copy(itIsDeleted = true)
                    note.comments.remove(comment)
                    note.comments.add(deletedComment)
                    return true
                }
            }
        }
        return false
    }

    fun edit(noteId: Int, title: String, text: String): Boolean {
        for (note in notes) {
            if (note.noteId == noteId) {
                val editedNote = note.copy(title = title, text = text)
                notes.remove(note)
                notes.add(editedNote)
                return true
            }
        }
        return false
    }

    fun editComment(commentId: Int, message: String): Boolean {
        for (note in notes) {
            for (comment in note.comments) {
                if (comment.commentId == commentId) {
                    val editedComment = comment.copy(message = message)
                    note.comments.remove(comment)
                    note.comments.add(editedComment)
                    return true
                }
            }
        }
        return false
    }

    fun get(listOfNoteIds: Set<Int>): MutableList<Note> {
        val listOfNotes = mutableListOf<Note>()
        for (noteId in listOfNoteIds) {
            for (note in notes) {
                if (note.noteId == noteId) {
                    listOfNotes.add(note)
                }
            }
        }
        return listOfNotes
    }

    fun getById(noteId: Int): Note {
        for (note in notes) {
            if (note.noteId == noteId) {
                return note
            }
        }
        throw NoteNotFoundException("Does not exist note with id $noteId")
    }

    fun getComments(noteId: Int): MutableList<Comment> {
        for (note in notes) {
            if (note.noteId == noteId) {
                return note.comments
            }
        }
        throw NoteNotFoundException("no note with id $noteId")
    }

    fun restoreComment(commentId: Int): Boolean {
        for (note in notes) {
            for (comment in note.comments) {
                if (comment.commentId == commentId) {
                    val restoreComment = comment.copy(itIsDeleted = false)
                    note.comments.remove(comment)
                    note.comments.add(restoreComment)
                    return true
                }
            }
        }
        return false
    }

}