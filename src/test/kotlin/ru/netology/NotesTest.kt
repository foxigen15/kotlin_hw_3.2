package ru.netology

import org.junit.Test

import org.junit.Assert.*

class NotesTest {

    @Test
    fun addNote() {
        val notes = Notes()
        val result = notes.add(title = "Note", text = "Some text") > 0

        assertTrue(result)
    }

    @Test
    fun createCommentTrue() {
        val noteId = 1
        val title = "Note"
        val text = "Some text for note"
        val message = "comment for Note"

        val notes = Notes()
        notes.add(title = title, text = text)
        val result = notes.createComment(noteId = noteId, message = message) > 0

        assertTrue(result)
    }

    @Test
    fun createCommentFalse() {
        val noteId = 25
        val title = "Note"
        val text = "Some text for note"
        val message = "comment for Note"

        val notes = Notes()
        notes.add(title = title, text = text)
        val result = notes.createComment(noteId = noteId, message = message) == 0

        assertTrue(result)
    }

    @Test
    fun deleteTrue() {
        val noteId = 1
        val title = "Note"
        val text = "Some text for note"

        val notes = Notes()
        notes.add(title = title, text = text)
        val result = notes.delete(noteId)

        assertTrue(result)
    }

    @Test
    fun deleteFalse() {
        val noteId = 25
        val title = "Note"
        val text = "Some text for note"

        val notes = Notes()
        notes.add(title = title, text = text)
        val result = notes.delete(noteId)

        assertFalse(result)
    }

    @Test
    fun deleteCommentTrue() {
        val noteId = 1
        val title = "Note"
        val text = "Some text for note"

        val commentId = 1
        val message = "Some text for comment"

        val notes = Notes()
        notes.add(title = title, text = text)
        notes.createComment(noteId = noteId, message = message)

        val result = notes.deleteComment(commentId)
        assertTrue(result)
    }

    @Test
    fun deleteCommentFalse() {
        val noteId = 1
        val title = "Note"
        val text = "Some text for note"

        val commentId = 25
        val message = "Some text for comment"

        val notes = Notes()
        notes.add(title = title, text = text)
        notes.createComment(noteId = noteId, message = message)

        val result = notes.deleteComment(commentId)
        assertFalse(result)
    }

    @Test
    fun editTrue() {
        val noteId = 1
        val title = "Note"
        val text = "Some text for note"

        val titleNewEdited = "New title"
        val textNewEdited = "Some New text"

        val notes = Notes()
        notes.add(title = title, text = text)
        val result = notes.edit(noteId = noteId, title = titleNewEdited, text = textNewEdited)

        assertTrue(result)
    }

    @Test
    fun editFalse() {
        val noteId = 25
        val title = "Note"
        val text = "Some text for note"

        val titleNewEdited = "New title"
        val textNewEdited = "Some New text"

        val notes = Notes()
        notes.add(title = title, text = text)
        val result = notes.edit(noteId = noteId, title = titleNewEdited, text = textNewEdited)

        assertFalse(result)
    }

    @Test
    fun editCommentTrue() {
        val noteId = 1
        val title = "Note"
        val text = "Some text for note"

        val commentId = 1
        val message = "Some text for comment"
        val messageNewEdited = "Some New text for comment"

        val notes = Notes()
        notes.add(title = title, text = text)
        notes.createComment(noteId = noteId, message = message)
        val result = notes.editComment(commentId = commentId, message = messageNewEdited)

        assertTrue(result)
    }

    @Test
    fun editCommentFalse() {
        val noteId = 1
        val title = "Note"
        val text = "Some text for note"

        val commentId = 25
        val message = "Some text for comment"
        val messageNewEdited = "Some New text for comment"

        val notes = Notes()
        notes.add(title = title, text = text)
        notes.createComment(noteId = noteId, message = message)
        val result = notes.editComment(commentId = commentId, message = messageNewEdited)

        assertFalse(result)
    }

    @Test
    fun get() {
        val title1 = "Note 1"
        val text1 = "Some text for note 1"
        val title2 = "Note 2"
        val text2 = "Some text for note 2"
        val title3 = "Note 3"
        val text3 = "Some text for note 3"
        val title4 = "Note 4"
        val text4 = "Some text for note 4"

        val listOfNoteIds = setOf<Int>(1, 2, 3)
        val notes = Notes()
        notes.add(title = title1, text = text1)
        notes.add(title = title2, text = text2)
        notes.add(title = title3, text = text3)
        notes.add(title = title4, text = text4)

        val result = notes.get(listOfNoteIds = listOfNoteIds)

        assertEquals(3, result.size)
    }

    @Test
    fun getById() {
        val noteId = 1
        val title1 = "Note"
        val text1 = "Some text for note"

        val notes = Notes()
        notes.add(title = title1, text = text1)
        val result = notes.getById(noteId)

        assertEquals(1, result.noteId)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getByIdNoteNotFoundException() {
        val noteId = 0
        val title1 = "Note"
        val text1 = "Some text for note"

        val notes = Notes()
        notes.add(title = title1, text = text1)

        notes.getById(noteId = noteId)
    }

    @Test
    fun getComments() {
        val noteId = 1
        val title = "Note"
        val text = "Some text for note"
        val comment1 = "Some text for comment 1"
        val comment2 = "Some text for comment 2"

        val notes = Notes()
        notes.add(title = title, text = text)
        notes.createComment(noteId = noteId, comment1)
        notes.createComment(noteId = noteId, comment2)
        val result = notes.getComments(noteId = noteId)

        assertEquals(2, result.size)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getCommentsNotFoundException() {
        val noteId = 25
        val title = "Note"
        val text = "Some text for note"
        val comment1 = "Some text for comment 1"
        val comment2 = "Some text for comment 2"

        val notes = Notes()
        notes.add(title = title, text = text)
        notes.createComment(noteId = noteId, comment1)
        notes.createComment(noteId = noteId, comment2)

        notes.getComments(noteId = noteId)
    }

    @Test
    fun restoreCommentTrue() {
        val noteId = 1
        val title = "Note"
        val text = "Some text for note"
        val commentId = 1
        val message = "Some text for comment"

        val notes = Notes()
        notes.add(title = title, text = text)
        notes.createComment(noteId = noteId, message = message)
        notes.deleteComment(commentId)
        val result = notes.restoreComment(commentId = commentId)

        assertTrue(result)
    }

    @Test
    fun restoreCommentFalse() {
        val noteId = 1
        val title = "Note"
        val text = "Some text for note"
        val commentId = 25
        val message = "Some text for comment"

        val notes = Notes()
        notes.add(title = title, text = text)
        notes.createComment(noteId = noteId, message = message)
        notes.deleteComment(commentId)
        val result = notes.restoreComment(commentId = commentId)

        assertFalse(result)
    }

}