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
import lt.notesapp.databinding.FragmentAddEditNoteBinding;
import lt.notesapp.model.Note;
import lt.notesapp.model.NoteGroup;

public class AddEditNoteFragment extends Fragment {

    @Inject NoteDao noteDao;
    private FragmentAddEditNoteBinding binding;
    private final NotesActivity notesActivity;

    public AddEditNoteFragment(NotesActivity notesActivity, AppComponent appComponent) {
        this.notesActivity = notesActivity;
        appComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddEditNoteBinding.inflate(inflater);

        binding.btnBack.setOnClickListener(v -> {
            notesActivity.showNotesFragment();
            notesActivity.getNotesFragment().updateNotes();
        });

        binding.vNoteEdit.setOnNoteSubmitListener(note -> AsyncTask.execute(() -> {
            noteDao.insertOrUpdateNote(note);
            container.post(() -> {
                notesActivity.showNotesFragment();
                notesActivity.getNotesFragment().updateNotes();
            });
        }));

        return binding.getRoot();
    }

    public void createNote(NoteGroup noteGroup) {
        binding.vNoteEdit.newNote(noteGroup);
    }

    public void editNote(Note note) {
        binding.vNoteEdit.editNote(note);
    }
}
