package lt.notesapp.events;

import lt.notesapp.model.NoteGroup;

public interface OnGroupSubmitListener {
    void onGroupSubmit(NoteGroup noteGroup);
}
