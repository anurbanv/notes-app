package lt.notesapp.app.presentation.viewmodel;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import lt.notesapp.app.framework.NotesApp;
import lt.notesapp.core.domain.Note;
import lt.notesapp.core.domain.NoteGroup;
import lt.notesapp.core.usecases.NoteUseCases;

public class NotesViewModel extends ViewModel {

    @Inject NoteUseCases noteUseCases;
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
            List<Note> allNotes = noteUseCases.getAllNotes(noteGroup.getId());
            notes.postValue(allNotes);
        });
    }

    public void addOrUpdateNote(Note note) {
        AsyncTask.execute(() -> {
            if (note.getId() == 0) {
                noteUseCases.addNote(note);
            } else {
                noteUseCases.updateNote(note);
            }
            reloadNotes();
        });
    }

    public void deleteNote(Note note) {
        AsyncTask.execute(() -> {
            noteUseCases.deleteNote(note);
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
