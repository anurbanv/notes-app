package lt.notesapp.app.presentation.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import lt.notesapp.app.presentation.view.activity.NotesActivity;
import lt.notesapp.app.presentation.viewmodel.GroupsViewModel;
import lt.notesapp.core.domain.NoteGroup;
import lt.notesapp.databinding.FragmentAddEditGroupBinding;

public class AddEditGroupFragment extends Fragment {

    private FragmentAddEditGroupBinding binding;
    private GroupsViewModel groupsViewModel;
    private final NotesActivity notesActivity;

    public AddEditGroupFragment(NotesActivity notesActivity) {
        this.notesActivity = notesActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddEditGroupBinding.inflate(inflater);

        groupsViewModel = new ViewModelProvider(requireActivity()).get(GroupsViewModel.class);

        binding.btnBack.setOnClickListener(v -> notesActivity.showGroupsFragment());

        binding.vEditGroup.setOnGroupSubmitListener(noteGroup -> {
            groupsViewModel.addOrUpdateNoteGroup(noteGroup);
            notesActivity.showGroupsFragment();
        });

        return binding.getRoot();
    }

    public void createGroup() {
        binding.vEditGroup.newGroup();
    }

    public void editGroup(NoteGroup noteGroup) {
        binding.vEditGroup.editGroup(noteGroup);
    }
}
