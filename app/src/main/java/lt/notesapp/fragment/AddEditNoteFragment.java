package lt.notesapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import lt.notesapp.databinding.FragmentAddEditNoteBinding;
import lt.notesapp.events.OnNoteSubmitListener;
import lt.notesapp.model.Note;
import lt.notesapp.model.NoteGroup;

public class AddEditNoteFragment extends Fragment {

    private FragmentAddEditNoteBinding binding;
    private View.OnClickListener onBackListener;
    private OnNoteSubmitListener onNoteSubmitListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddEditNoteBinding.inflate(inflater);
        binding.btnBack.setOnClickListener(onBackListener);
        binding.vNoteEdit.setOnNoteSubmitListener(onNoteSubmitListener);
        return binding.getRoot();
    }

    public void createNote(NoteGroup noteGroup) {
        binding.vNoteEdit.newNote(noteGroup);
    }

    public void editNote(Note note) {
        binding.vNoteEdit.editNote(note);
    }

    public void setOnBackListener(View.OnClickListener onBackListener) {
        this.onBackListener = onBackListener;
    }

    public void setOnNoteSubmitListener(OnNoteSubmitListener onNoteSubmitListener) {
        this.onNoteSubmitListener = onNoteSubmitListener;
    }
}
