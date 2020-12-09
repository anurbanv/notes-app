package lt.notesapp.viewmodel;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import lt.notesapp.NotesApp;
import lt.notesapp.dao.NoteGroupDao;
import lt.notesapp.model.NoteGroup;

public class GroupsViewModel extends ViewModel {

    @Inject NoteGroupDao noteGroupDao;
    private final MutableLiveData<List<NoteGroup>> groups = new MutableLiveData<>();

    public GroupsViewModel() {
        NotesApp.getInstance().getAppComponent().inject(this);
        reloadNoteGroups();
    }

    public LiveData<List<NoteGroup>> getNoteGroups() {
        return groups;
    }

    public void reloadNoteGroups() {
        AsyncTask.execute(() -> {
            List<NoteGroup> allGroups = noteGroupDao.getAllGroups();
            groups.postValue(allGroups);
        });
    }

    public void addOrUpdateNoteGroup(NoteGroup noteGroup) {
        AsyncTask.execute(() -> {
            noteGroupDao.insertOrUpdateGroup(noteGroup);
            reloadNoteGroups();
        });
    }

    public void deleteNoteGroup(NoteGroup noteGroup) {
        AsyncTask.execute(() -> {
            noteGroupDao.deleteNoteGroup(noteGroup);
            reloadNoteGroups();
        });
    }
}
