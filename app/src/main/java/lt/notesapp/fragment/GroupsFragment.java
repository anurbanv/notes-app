package lt.notesapp.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import javax.inject.Inject;

import lt.notesapp.activity.NotesActivity;
import lt.notesapp.dagger.AppComponent;
import lt.notesapp.dao.NoteDao;
import lt.notesapp.databinding.FragmentGroupsBinding;
import lt.notesapp.model.Note;
import lt.notesapp.model.NoteGroup;

public class GroupsFragment extends Fragment {

    @Inject NoteDao noteDao;
    private FragmentGroupsBinding binding;
    private final NotesActivity notesActivity;

    public GroupsFragment(NotesActivity notesActivity, AppComponent appComponent) {
        this.notesActivity = notesActivity;
        appComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentGroupsBinding.inflate(inflater);

        binding.btnBack.setOnClickListener(v -> notesActivity.finish());

        binding.btnAdd.setOnClickListener(v -> {
            notesActivity.showAddEditGroupFragment();
            notesActivity.getAddEditGroupFragment().createGroup();
        });

        binding.groupList.setOnEditClickListener(noteGroup -> {
            notesActivity.showAddEditGroupFragment();
            notesActivity.getAddEditGroupFragment().editGroup(noteGroup);
        });

        binding.groupList.setOnDeleteClickListener(noteGroup -> AsyncTask.execute(() -> {
            noteDao.deleteNoteGroup(noteGroup);
            updateGroupList();
        }));

        binding.groupList.setOnItemClickListener(noteGroup -> {
            notesActivity.showNotesFragment();
            NotesFragment notesFragment = notesActivity.getNotesFragment();
            notesFragment.setNoteGroup(noteGroup);
            AsyncTask.execute(() -> {
                List<Note> notes = noteDao.getNotesByGroupId(notesFragment.getNoteGroup().getId());
                container.post(() -> notesFragment.updateNotes(notes));
            });
        });

        return binding.getRoot();
    }

    public void updateGroupList() {
        AsyncTask.execute(() -> {
            List<NoteGroup> allGroups = noteDao.getAllGroups();
            getActivity().runOnUiThread(() -> binding.groupList.update(allGroups));
        });
    }
}
