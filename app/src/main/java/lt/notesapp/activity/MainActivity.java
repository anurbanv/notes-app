package lt.notesapp.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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

        NoteGroup noteGroup = new NoteGroup("Group");

        for (int i = 0; i < 20; i++) {
            Note note = new Note("Title", "Content " + i);
            noteGroup.getNotes().add(note);
        }

        binding.lvNotes.update(noteGroup.getNotes());
    }
}