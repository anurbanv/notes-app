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
import lt.notesapp.databinding.FragmentAddEditNoteBinding;
import lt.notesapp.model.Note;
import lt.notesapp.model.NoteGroup;
import lt.notesapp.viewmodel.GroupsViewModel;
import lt.notesapp.viewmodel.NotesViewModel;

public class AddEditNoteFragment extends Fragment {

    private FragmentAddEditNoteBinding binding;
    private final NotesActivity notesActivity;
    private GroupsViewModel groupsViewModel;
    private NotesViewModel notesViewModel;

    public AddEditNoteFragment(NotesActivity notesActivity) {
        this.notesActivity = notesActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddEditNoteBinding.inflate(inflater);

        groupsViewModel = new ViewModelProvider(requireActivity()).get(GroupsViewModel.class);
        notesViewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);

        binding.btnBack.setOnClickListener(v -> notesActivity.showNotesFragment());

        binding.vNoteEdit.setOnNoteSubmitListener(note -> {
            notesViewModel.addOrUpdateNote(note);
            groupsViewModel.reloadNoteGroups();
            notesActivity.showNotesFragment();
        });

        return binding.getRoot();
    }

    public void createNote(NoteGroup noteGroup) {
        binding.vNoteEdit.newNote(noteGroup);
    }

    public void editNote(Note note) {
        binding.vNoteEdit.editNote(note);
    }
}
