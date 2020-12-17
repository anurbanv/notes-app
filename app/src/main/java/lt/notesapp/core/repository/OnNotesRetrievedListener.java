package lt.notesapp.core.repository;

import java.util.List;

import lt.notesapp.core.domain.Note;

public interface OnNotesRetrievedListener {
    void onNotesRetrieved(List<Note> notes);
}
