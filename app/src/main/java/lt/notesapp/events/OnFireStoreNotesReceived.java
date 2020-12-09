package lt.notesapp.events;

import java.util.List;

import lt.notesapp.model.Note;

public interface OnFireStoreNotesReceived {
    void onNotesRetrieved(List<Note> notes);
}
