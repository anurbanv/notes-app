package lt.notesapp.app.presentation.viewmodel;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import lt.notesapp.app.framework.NotesApp;
import lt.notesapp.core.domain.NoteGroup;
import lt.notesapp.core.usecases.GroupUseCases;

public class GroupsViewModel extends ViewModel {

    @Inject GroupUseCases groupUseCases;
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
            List<NoteGroup> allGroups = groupUseCases.getAllGroups();
            groups.postValue(allGroups);
        });
    }

    public void addOrUpdateNoteGroup(NoteGroup noteGroup) {
        AsyncTask.execute(() -> {
            if (noteGroup.getId() == 0) {
                groupUseCases.addGroup(noteGroup);
            } else {
                groupUseCases.updateGroup(noteGroup);
            }
            reloadNoteGroups();
        });
    }

    public void deleteNoteGroup(NoteGroup noteGroup) {
        AsyncTask.execute(() -> {
            groupUseCases.deleteGroup(noteGroup);
            reloadNoteGroups();
        });
    }
}
