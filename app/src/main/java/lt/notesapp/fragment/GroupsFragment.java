package lt.notesapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import lt.notesapp.activity.NotesActivity;
import lt.notesapp.databinding.FragmentGroupsBinding;
import lt.notesapp.viewmodel.GroupsViewModel;
import lt.notesapp.viewmodel.NotesViewModel;

public class GroupsFragment extends Fragment {

    private FragmentGroupsBinding binding;
    private final NotesActivity notesActivity;
    private GroupsViewModel groupsViewModel;
    private NotesViewModel notesViewModel;

    public GroupsFragment(NotesActivity notesActivity) {
        this.notesActivity = notesActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentGroupsBinding.inflate(inflater);

        groupsViewModel = new ViewModelProvider(requireActivity()).get(GroupsViewModel.class);
        notesViewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);

        groupsViewModel.getNoteGroups().observe(getViewLifecycleOwner(),
                noteGroups -> binding.groupList.update(noteGroups));

        binding.btnBack.setOnClickListener(v -> notesActivity.finish());

        binding.btnAdd.setOnClickListener(v -> {
            notesActivity.showAddEditGroupFragment();
            notesActivity.getAddEditGroupFragment().createGroup();
        });

        binding.groupList.setOnEditClickListener(noteGroup -> {
            notesActivity.showAddEditGroupFragment();
            notesActivity.getAddEditGroupFragment().editGroup(noteGroup);
        });

        binding.groupList.setOnDeleteClickListener(noteGroup -> groupsViewModel.deleteNoteGroup(noteGroup));

        binding.groupList.setOnItemClickListener(noteGroup -> {
            notesViewModel.setNoteGroup(noteGroup);
            notesActivity.showNotesFragment();
        });

        return binding.getRoot();
    }
}
