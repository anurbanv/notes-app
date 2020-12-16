package lt.notesapp.app.presentation.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import lt.notesapp.R;
import lt.notesapp.app.framework.NotesApp;
import lt.notesapp.app.framework.dagger.AppComponent;
import lt.notesapp.app.presentation.view.fragment.AddEditGroupFragment;
import lt.notesapp.app.presentation.view.fragment.AddEditNoteFragment;
import lt.notesapp.app.presentation.view.fragment.GroupsFragment;
import lt.notesapp.app.presentation.view.fragment.NotesFragment;
import lt.notesapp.databinding.ActivityNotesBinding;

public class NotesActivity extends AppCompatActivity {

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
