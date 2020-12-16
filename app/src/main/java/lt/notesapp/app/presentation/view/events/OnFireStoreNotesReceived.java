package lt.notesapp.app.presentation.view.events;

import java.util.List;

import lt.notesapp.core.domain.Note;

public interface OnFireStoreNotesReceived {
    void onNotesRetrieved(List<Note> notes);
}
