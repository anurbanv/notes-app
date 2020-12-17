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
import lt.notesapp.app.presentation.viewmodel.NotesViewModel;
import lt.notesapp.databinding.FragmentNotesBinding;

public class NotesFragment extends Fragment {

    private FragmentNotesBinding binding;
    private final NotesActivity notesActivity;
    private GroupsViewModel groupsViewModel;
    private NotesViewModel notesViewModel;

    public NotesFragment(NotesActivity notesActivity) {
        this.notesActivity = notesActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNotesBinding.inflate(inflater);

        groupsViewModel = new ViewModelProvider(requireActivity()).get(GroupsViewModel.class);
        notesViewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);

        notesViewModel.getNotes().observe(getViewLifecycleOwner(),
                notes -> binding.noteList.update(notes));

        binding.btnBack.setOnClickListener(v -> notesActivity.showGroupsFragment());

        binding.btnAdd.setOnClickListener(v -> {
            notesActivity.showAddEditNoteFragment();
            notesActivity.getAddEditNoteFragment().createNote(notesViewModel.getNoteGroup());
        });

        binding.noteList.setOnEditClickListener(note -> {
            notesActivity.showAddEditNoteFragment();
            notesActivity.getAddEditNoteFragment().editNote(note);
        });

        binding.noteList.setOnDeleteClickListener(note -> {
            notesViewModel.deleteNote(note);
            groupsViewModel.reloadNoteGroups();
        });

        binding.tvTitle.setText(notesViewModel.getNoteGroup().getTitle());

        return binding.getRoot();
    }
}
