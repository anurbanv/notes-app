package lt.notesapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import lt.notesapp.databinding.FragmentNoteListBinding;
import lt.notesapp.events.OnNoteDeleteListener;
import lt.notesapp.events.OnNoteEditListener;
import lt.notesapp.model.Note;
import lt.notesapp.model.NoteGroup;

public class NotesFragment extends Fragment {

    private FragmentNoteListBinding binding;
    private View.OnClickListener onBackListener;
    private View.OnClickListener onAddListener;
    private OnNoteEditListener onNoteEditListener;
    private OnNoteDeleteListener onNoteDeleteListener;
    private NoteGroup noteGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNoteListBinding.inflate(inflater);
        binding.btnBack.setOnClickListener(onBackListener);
        binding.btnAdd.setOnClickListener(onAddListener);
        binding.noteList.setOnEditClickListener(onNoteEditListener);
        binding.noteList.setOnDeleteClickListener(onNoteDeleteListener);
        return binding.getRoot();
    }

    public void setNoteGroup(NoteGroup noteGroup) {
        this.noteGroup = noteGroup;
    }

    public NoteGroup getNoteGroup() {
        return noteGroup;
    }

    public void updateNotes(List<Note> notes) {
        binding.noteList.update(notes);
    }

    public void setOnBackListener(View.OnClickListener onBackListener) {
        this.onBackListener = onBackListener;
    }

    public void setOnAddListener(View.OnClickListener onAddListener) {
        this.onAddListener = onAddListener;
    }

    public void setOnNoteEditListener(OnNoteEditListener onNoteEditListener) {
        this.onNoteEditListener = onNoteEditListener;
    }

    public void setOnNoteDeleteListener(OnNoteDeleteListener onNoteDeleteListener) {
        this.onNoteDeleteListener = onNoteDeleteListener;
    }
}
