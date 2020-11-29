package lt.notesapp.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import lt.notesapp.databinding.FragmentNoteListBinding;
import lt.notesapp.model.Note;

public class NotesFragment extends Fragment {

    private FragmentNoteListBinding binding;

    private final NotesPresenter presenter;
    private final Handler handler;

    public NotesFragment(NotesPresenter presenter, Handler handler) {
        presenter.setNotesFragment(this);
        this.presenter = presenter;
        this.handler = handler;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNoteListBinding.inflate(inflater);
        binding.btnBack.setOnClickListener(v -> presenter.onBackClick());
        binding.btnAdd.setOnClickListener(v -> presenter.onNoteAddClick());
        binding.noteList.setOnEditClickListener(presenter::onNoteEditClick);
        binding.noteList.setOnDeleteClickListener(presenter::onNoteDeleteClick);
        return binding.getRoot();
    }

    public void updateNotes(List<Note> notes) {
        handler.post(() -> binding.noteList.update(notes));
    }
}
