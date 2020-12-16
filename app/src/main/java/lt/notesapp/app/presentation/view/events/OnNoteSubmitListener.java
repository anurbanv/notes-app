package lt.notesapp.app.presentation.view.events;

import lt.notesapp.core.domain.Note;

public interface OnNoteSubmitListener {
    void onNoteSubmit(Note note);
}
