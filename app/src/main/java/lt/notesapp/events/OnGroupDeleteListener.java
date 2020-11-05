package lt.notesapp.events;

import lt.notesapp.model.NoteGroup;

public interface OnGroupDeleteListener {
    void onGroupDelete(NoteGroup noteGroup);
}
