package lt.notesapp.core.usecases;

import java.util.List;

import lt.notesapp.core.domain.Note;
import lt.notesapp.core.repository.NoteRepository;

public class NoteUseCases {

    private final NoteRepository noteRepository;

    public NoteUseCases(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void addNote(Note note) {
        noteRepository.insertNote(note);
    }

    public void updateNote(Note note) {
        noteRepository.updateNote(note);
    }

    public void deleteNote(Note note) {
        noteRepository.deleteNote(note);
    }

    public List<Note> getAllNotes(int groupId) {
        return noteRepository.selectAll(groupId);
    }
}
