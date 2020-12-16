package lt.notesapp.core.repository;

import java.util.List;

import lt.notesapp.core.domain.Note;

public interface NoteRepository {

    void insertNote(Note note);

    void updateNote(Note note);

    void deleteNote(Note note);

    List<Note> selectAll(int groupId);
}
