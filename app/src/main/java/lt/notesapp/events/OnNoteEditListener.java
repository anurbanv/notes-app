package lt.notesapp.events;

import lt.notesapp.model.Note;

public interface OnNoteEditListener {
    void onNoteEdit(Note note);
}
