package lt.notesapp.activity;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.List;

import javax.inject.Inject;

import lt.notesapp.NotesApp;
import lt.notesapp.R;
import lt.notesapp.dagger.AppComponent;
import lt.notesapp.dao.NoteDao;
import lt.notesapp.databinding.ActivityNotesBinding;
import lt.notesapp.fragment.AddEditGroupFragment;
import lt.notesapp.fragment.AddEditNoteFragment;
import lt.notesapp.fragment.GroupsFragment;
import lt.notesapp.fragment.NotesFragment;
import lt.notesapp.model.Note;

public class NotesActivity extends AppCompatActivity {

    @Inject NoteDao noteDao;
    private GroupsFragment groupsFragment;
    private AddEditGroupFragment addEditGroupFragment;
    private NotesFragment notesFragment;
    private AddEditNoteFragment addEditNoteFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNotesBinding binding = ActivityNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppComponent appComponent = NotesApp.getInstance().getAppComponent();
        appComponent.inject(this);

        groupsFragment = new GroupsFragment(this, appComponent);
        addEditGroupFragment = new AddEditGroupFragment(this, appComponent);
        notesFragment = new NotesFragment(this);
        addEditNoteFragment = new AddEditNoteFragment(this);

        notesFragment.setOnBackListener(v -> {
            replaceFragment(groupsFragment);
            groupsFragment.updateGroupList();
        });

        notesFragment.setOnAddListener(v -> {
            replaceFragment(addEditNoteFragment);
            addEditNoteFragment.createNote(notesFragment.getNoteGroup());
        });

        notesFragment.setOnNoteDeleteListener(this::deleteNote);

        notesFragment.setOnNoteEditListener(note -> {
            replaceFragment(addEditNoteFragment);
            addEditNoteFragment.editNote(note);
        });

        addEditNoteFragment.setOnBackListener(v -> {
            replaceFragment(notesFragment);
            updateNoteList();
        });

        addEditNoteFragment.setOnNoteSubmitListener(this::addOrUpdateNote);

        replaceFragment(groupsFragment);
        groupsFragment.updateGroupList();
    }

    public void showGroupsFragment() {
        replaceFragment(groupsFragment);
    }

    public void showAddEditGroupFragment() {
        replaceFragment(addEditGroupFragment);
    }

    public void showNotesFragment() {
        replaceFragment(notesFragment);
    }

    public NotesFragment getNotesFragment() {
        return notesFragment;
    }

    public AddEditGroupFragment getAddEditGroupFragment() {
        return addEditGroupFragment;
    }

    public GroupsFragment getGroupsFragment() {
        return groupsFragment;
    }

    private void updateNoteList() {
        AsyncTask.execute(() -> {
            List<Note> notes = noteDao.getNotesByGroupId(notesFragment.getNoteGroup().getId());
            runOnUiThread(() -> notesFragment.updateNotes(notes));
        });
    }

    private void deleteNote(Note note) {
        AsyncTask.execute(() -> {
            noteDao.deleteNote(note);
            runOnUiThread(this::updateNoteList);
        });
    }

    private void addOrUpdateNote(Note note) {
        AsyncTask.execute(() -> {
            noteDao.insertOrUpdateNote(note);
            runOnUiThread(() -> {
                replaceFragment(notesFragment);
                updateNoteList();
            });
        });
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commitNow();
    }
}
