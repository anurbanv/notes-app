package lt.notesapp.app.presentation.view.events;

import lt.notesapp.core.domain.NoteGroup;

public interface OnGroupDeleteListener {
    void onGroupDelete(NoteGroup noteGroup);
}
