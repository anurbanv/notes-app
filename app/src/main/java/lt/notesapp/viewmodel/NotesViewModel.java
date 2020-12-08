package lt.notesapp.viewmodel;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import lt.notesapp.NotesApp;
import lt.notesapp.dao.NoteDao;
import lt.notesapp.model.Note;
import lt.notesapp.model.NoteGroup;

public class NotesViewModel extends ViewModel {

    @Inject NoteDao noteDao;
    private final MutableLiveData<List<Note>> notes = new MutableLiveData<>();
    private NoteGroup noteGroup;

    public NotesViewModel() {
        NotesApp.getInstance().getAppComponent().inject(this);
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }

    public void reloadNotes() {
        AsyncTask.execute(() -> {
            List<Note> allNotes = noteDao.getNotesByGroupId(noteGroup.getId());
            notes.postValue(allNotes);
        });
    }

    public void addOrUpdateNote(Note note) {
        AsyncTask.execute(() -> {
            noteDao.insertOrUpdateNote(note);
            reloadNotes();
        });
    }

    public void deleteNote(Note note) {
        AsyncTask.execute(() -> {
            noteDao.deleteNote(note);
            reloadNotes();
        });
    }

    public void setNoteGroup(NoteGroup noteGroup) {
        this.noteGroup = noteGroup;
        reloadNotes();
    }

    public NoteGroup getNoteGroup() {
        return noteGroup;
    }
}
