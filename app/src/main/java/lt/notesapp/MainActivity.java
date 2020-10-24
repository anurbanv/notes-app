package lt.notesapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import lt.notesapp.model.Note;
import lt.notesapp.views.NoteView;

public class MainActivity extends AppCompatActivity {

    lt.notesapp.databinding.ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = lt.notesapp.databinding.ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Note note = new Note("Title", "Content");
        NoteView noteView = new NoteView(this);

        binding.llRoot.addView(noteView);
        noteView.update(note);

    }
}