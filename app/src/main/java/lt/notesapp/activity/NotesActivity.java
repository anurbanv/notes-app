package lt.notesapp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

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
import lt.notesapp.fragment.AddEditGroupPresenter;
import lt.notesapp.fragment.AddEditNoteFragment;
import lt.notesapp.fragment.GroupsFragment;
import lt.notesapp.fragment.GroupsPresenter;
import lt.notesapp.fragment.NotesFragment;
import lt.notesapp.fragment.NotesPresenter;

public class NotesActivity extends AppCompatActivity {

    @Inject NoteDao noteDao;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private GroupsFragment groupsFragment;
    private AddEditGroupFragment addEditGroupFragment;
    private NotesFragment notesFragment;
    private AddEditNoteFragment addEditNoteFragment;
    private GroupsPresenter groupsPresenter;
    private AddEditGroupPresenter addEditGroupPresenter;
    private NotesPresenter notesPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNotesBinding binding = ActivityNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppComponent appComponent = NotesApp.getInstance().getAppComponent();
        appComponent.inject(this);

        groupsPresenter = new GroupsPresenter(this, noteDao);
        addEditGroupPresenter = new AddEditGroupPresenter(this, noteDao);
        notesPresenter = new NotesPresenter(this, noteDao);

        groupsFragment = new GroupsFragment(groupsPresenter, handler);
        addEditGroupFragment = new AddEditGroupFragment(addEditGroupPresenter);
        notesFragment = new NotesFragment(notesPresenter, handler);
        addEditNoteFragment = new AddEditNoteFragment(this, appComponent);

        showGroupsFragment();
        groupsPresenter.updateGroupList();
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

    public GroupsPresenter getGroupsPresenter() {
        return groupsPresenter;
    }

    public AddEditGroupPresenter getAddEditGroupPresenter() {
        return addEditGroupPresenter;
    }

    public NotesPresenter getNotesPresenter() {
        return notesPresenter;
    }

    public AddEditNoteFragment getAddEditNoteFragment() {
        return addEditNoteFragment;
    }

    private void replaceFragment(Fragment fragment) {
        runOnUiThread(() -> getSupportFragmentManager()
                .beginTransaction().replace(R.id.fragment, fragment).commitNow());
    }
}
