package lt.notesapp.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import lt.notesapp.activity.NotesActivity;
import lt.notesapp.dagger.AppComponent;
import lt.notesapp.dao.NoteDao;
import lt.notesapp.databinding.FragmentAddEditGroupBinding;
import lt.notesapp.model.NoteGroup;

public class AddEditGroupFragment extends Fragment {

    @Inject NoteDao noteDao;
    private FragmentAddEditGroupBinding binding;
    private final NotesActivity notesActivity;

    public AddEditGroupFragment(NotesActivity notesActivity, AppComponent appComponent) {
        this.notesActivity = notesActivity;
        appComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddEditGroupBinding.inflate(inflater);

        binding.btnBack.setOnClickListener(v -> {
            notesActivity.showGroupsFragment();
            notesActivity.getGroupsPresenter().updateGroupList();
        });

        binding.vEditGroup.setOnGroupSubmitListener(noteGroup -> AsyncTask.execute(() -> {
            noteDao.insertOrUpdateGroup(noteGroup);
            container.post(() -> {
                notesActivity.showGroupsFragment();
                notesActivity.getGroupsPresenter().updateGroupList();
            });
        }));

        return binding.getRoot();
    }

    public void createGroup() {
        binding.vEditGroup.newGroup();
    }

    public void editGroup(NoteGroup noteGroup) {
        binding.vEditGroup.editGroup(noteGroup);
    }
}
