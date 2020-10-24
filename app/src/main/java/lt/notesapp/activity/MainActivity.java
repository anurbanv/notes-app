package lt.notesapp.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import lt.notesapp.databinding.ActivityMainBinding;
import lt.notesapp.model.Note;
import lt.notesapp.model.NoteGroup;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<NoteGroup> noteGroups = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            NoteGroup noteGroup = new NoteGroup("Group " + i);
            noteGroups.add(noteGroup);
        }

        Note note = new Note("Title 1", "Content\ncontent content");

        binding.vNoteEdit.editNote(note);
    }
}