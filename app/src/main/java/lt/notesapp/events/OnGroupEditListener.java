package lt.notesapp.events;

import lt.notesapp.model.NoteGroup;

public interface OnGroupEditListener {
    void onGroupEdit(NoteGroup noteGroup);
}
