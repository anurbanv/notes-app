package lt.notesapp.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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

        groupsFragment = new GroupsFragment(this);
        addEditGroupFragment = new AddEditGroupFragment(this);
        notesFragment = new NotesFragment(this);
        addEditNoteFragment = new AddEditNoteFragment(this);

        showGroupsFragment();
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

    public void showAddEditNoteFragment() {
        replaceFragment(addEditNoteFragment);
    }

    public AddEditGroupFragment getAddEditGroupFragment() {
        return addEditGroupFragment;
    }

    public AddEditNoteFragment getAddEditNoteFragment() {
        return addEditNoteFragment;
    }

    private void replaceFragment(Fragment fragment) {
        runOnUiThread(() -> getSupportFragmentManager()
                .beginTransaction().replace(R.id.fragment, fragment).commitNow());
    }
}
