package lt.notesapp.app.presentation.view.events;

import lt.notesapp.core.domain.Note;

public interface OnNoteEditListener {
    void onNoteEdit(Note note);
}
