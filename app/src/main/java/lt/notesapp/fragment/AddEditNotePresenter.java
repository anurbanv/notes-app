package lt.notesapp.fragment;

import android.os.AsyncTask;

import lt.notesapp.activity.NotesActivity;
import lt.notesapp.dao.NoteDao;
import lt.notesapp.model.Note;
import lt.notesapp.model.NoteGroup;

public class AddEditNotePresenter {

    private final NotesActivity notesActivity;
    private final NoteDao noteDao;
    private AddEditNoteFragment addEditNoteFragment;

    public AddEditNotePresenter(NotesActivity notesActivity, NoteDao noteDao) {
        this.notesActivity = notesActivity;
        this.noteDao = noteDao;
    }

    public void setAddEditNoteFragment(AddEditNoteFragment addEditNoteFragment) {
        this.addEditNoteFragment = addEditNoteFragment;
    }

    public void onBackClick() {
        notesActivity.showNotesFragment();
        notesActivity.getNotesPresenter().updateNotes();
    }

    public void onNoteSubmit(Note note) {
        AsyncTask.execute(() -> {
            noteDao.insertOrUpdateNote(note);
            notesActivity.showNotesFragment();
            notesActivity.getNotesPresenter().updateNotes();
        });
    }

    public void createNote(NoteGroup noteGroup) {
        addEditNoteFragment.createNote(noteGroup);
    }

    public void editNote(Note note) {
        addEditNoteFragment.editNote(note);
    }
}
