package lt.notesapp.fragment;

import android.os.AsyncTask;

import java.util.List;

import lt.notesapp.activity.NotesActivity;
import lt.notesapp.dao.NoteDao;
import lt.notesapp.model.Note;
import lt.notesapp.model.NoteGroup;

public class NotesPresenter {

    private final NotesActivity notesActivity;
    private final NoteDao noteDao;
    private NoteGroup noteGroup;
    private NotesFragment notesFragment;

    public NotesPresenter(NotesActivity notesActivity, NoteDao noteDao) {
        this.notesActivity = notesActivity;
        this.noteDao = noteDao;
    }

    public void setNotesFragment(NotesFragment notesFragment) {
        this.notesFragment = notesFragment;
    }

    public void onBackClick() {
        notesActivity.showGroupsFragment();
        notesActivity.getGroupsPresenter().updateGroupList();
    }

    public void onNoteAddClick() {
        notesActivity.showAddEditNoteFragment();
        notesActivity.getAddEditNoteFragment().createNote(noteGroup);
    }

    public void onNoteEditClick(Note note) {
        notesActivity.showAddEditNoteFragment();
        notesActivity.getAddEditNoteFragment().editNote(note);
    }

    public void onNoteDeleteClick(Note note) {
        AsyncTask.execute(() -> {
            noteDao.deleteNote(note);
            updateNotes();
        });
    }

    public void setNoteGroup(NoteGroup noteGroup) {
        this.noteGroup = noteGroup;
    }

    public NoteGroup getNoteGroup() {
        return noteGroup;
    }

    public void updateNotes() {
        AsyncTask.execute(() -> {
            List<Note> notes = noteDao.getNotesByGroupId(noteGroup.getId());
            notesFragment.updateNotes(notes);
        });
    }
}
