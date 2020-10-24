package lt.notesapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import lt.notesapp.databinding.ActivityMainBinding;
import lt.notesapp.model.Note;
import lt.notesapp.model.NoteGroup;
import lt.notesapp.views.NoteGroupView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Note note = new Note("Title", "Content");
        Note note1 = new Note("Title", "Content");

        NoteGroup noteGroup = new NoteGroup("Group");
        noteGroup.getNotes().add(note);
        noteGroup.getNotes().add(note1);

        NoteGroupView noteGroupView = new NoteGroupView(this);

        binding.llRoot.addView(noteGroupView);
        noteGroupView.update(noteGroup);

    }
}