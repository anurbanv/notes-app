package lt.notesapp.events;

import lt.notesapp.model.Note;

public interface OnNoteDeleteListener {
    void onNoteDelete(Note note);
}
