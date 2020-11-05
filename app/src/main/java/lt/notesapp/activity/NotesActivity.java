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
import lt.notesapp.dao.NoteDao;
import lt.notesapp.databinding.ActivityNotesBinding;
import lt.notesapp.fragment.AddEditGroupFragment;
import lt.notesapp.fragment.AddEditNoteFragment;
import lt.notesapp.fragment.GroupsFragment;
import lt.notesapp.fragment.NotesFragment;
import lt.notesapp.model.Note;
import lt.notesapp.model.NoteGroup;

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
        NotesApp.getInstance().getAppComponent().inject(this);

        groupsFragment = new GroupsFragment();
        addEditGroupFragment = new AddEditGroupFragment();
        notesFragment = new NotesFragment();
        addEditNoteFragment = new AddEditNoteFragment();

        addEditGroupFragment.setOnBackListener(v -> {
            replaceFragment(groupsFragment);
            updateGroupList();
        });

        addEditGroupFragment.setOnGroupSubmitListener(this::addOrUpdateGroup);

        groupsFragment.setOnBackListener(v -> finish());
        groupsFragment.setOnAddGroupListener(v -> {
            replaceFragment(addEditGroupFragment);
            addEditGroupFragment.createGroup();
        });

        groupsFragment.setOnGroupEditListener(noteGroup -> {
            replaceFragment(addEditGroupFragment);
            addEditGroupFragment.editGroup(noteGroup);
        });

        groupsFragment.setOnGroupDeleteListener(this::deleteGroup);

        groupsFragment.setOnGroupClickListener(noteGroup -> {
            replaceFragment(notesFragment);
            notesFragment.setNoteGroup(noteGroup);
            updateNoteList();
        });

        notesFragment.setOnBackListener(v -> {
            replaceFragment(groupsFragment);
            updateGroupList();
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
        updateGroupList();
    }

    private void updateGroupList() {
        AsyncTask.execute(() -> {
            List<NoteGroup> groups = noteDao.getAllGroups();
            runOnUiThread(() -> groupsFragment.updateGroupList(groups));
        });
    }

    private void deleteGroup(NoteGroup noteGroup) {
        AsyncTask.execute(() -> {
            noteDao.deleteNoteGroup(noteGroup);
            runOnUiThread(this::updateGroupList);
        });
    }

    private void addOrUpdateGroup(NoteGroup noteGroup) {
        AsyncTask.execute(() -> {
            noteDao.insertOrUpdateGroup(noteGroup);
            runOnUiThread(() -> {
                replaceFragment(groupsFragment);
                updateGroupList();
            });
        });
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
