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
import lt.notesapp.databinding.FragmentNoteListBinding;
import lt.notesapp.model.Note;
import lt.notesapp.model.NoteGroup;

public class NotesFragment extends Fragment {

    @Inject NoteDao noteDao;
    private FragmentNoteListBinding binding;
    private NoteGroup noteGroup;
    private final NotesActivity notesActivity;

    public NotesFragment(NotesActivity notesActivity, AppComponent appComponent) {
        this.notesActivity = notesActivity;
        appComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNoteListBinding.inflate(inflater);

        binding.btnBack.setOnClickListener(v -> {
            notesActivity.showGroupsFragment();
            notesActivity.getGroupsFragment().updateGroupList();
        });

        binding.btnAdd.setOnClickListener(v -> {
            notesActivity.showAddEditNoteFragment();
            notesActivity.getAddEditNoteFragment().createNote(noteGroup);
        });

        binding.noteList.setOnEditClickListener(note -> {
            notesActivity.showAddEditNoteFragment();
            notesActivity.getAddEditNoteFragment().editNote(note);
        });

        binding.noteList.setOnDeleteClickListener(note -> AsyncTask.execute(() -> {
            noteDao.deleteNote(note);
            updateNotes();
        }));

        return binding.getRoot();
    }

    public void setNoteGroup(NoteGroup noteGroup) {
        this.noteGroup = noteGroup;
    }

    public NoteGroup getNoteGroup() {
        return noteGroup;
    }

    public void updateNotes() {
        AsyncTask.execute(() -> {
            List<Note> notes = noteDao.getNotesByGroupId(noteGroup.getId());
            getActivity().runOnUiThread(() -> binding.noteList.update(notes));
        });
    }
}
