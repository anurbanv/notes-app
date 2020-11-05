package lt.notesapp.events;

import lt.notesapp.model.Note;

public interface OnNoteSubmitListener {
    void onNoteSubmit(Note note);
}
