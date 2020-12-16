package lt.notesapp.app.presentation.view.events;

import lt.notesapp.core.domain.Note;

public interface OnNoteDeleteListener {
    void onNoteDelete(Note note);
}
