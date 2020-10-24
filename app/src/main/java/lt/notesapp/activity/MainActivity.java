package lt.notesapp.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import lt.notesapp.AppDatabase;
import lt.notesapp.databinding.ActivityMainBinding;
import lt.notesapp.model.NoteGroup;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "NotesApp";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "notes_app_table").build();

        NoteGroup noteGroup = new NoteGroup("Group 1");

        binding.vNoteEdit.newNote(noteGroup);
    }
}