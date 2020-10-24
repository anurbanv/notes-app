package lt.notesapp.activity;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import lt.notesapp.dao.NoteDao;
import lt.notesapp.databinding.ActivityAddEditNoteBinding;
import lt.notesapp.model.NoteGroup;

public class AddEditNoteActivity extends AppCompatActivity {

    private ActivityAddEditNoteBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(v -> finish());

        NoteDao noteDao = new NoteDao(getApplicationContext());

        Bundle extras = getIntent().getExtras();

        int groupId = extras.getInt("groupId");
        AsyncTask.execute(() -> {
            NoteGroup noteGroup = noteDao.getNoteGroupById(groupId);
            runOnUiThread(() -> {
                binding.vNoteEdit.newNote(noteGroup);
                binding.vNoteEdit.setOnSubmitListener(note -> AsyncTask.execute(() -> {
                    noteDao.insertNote(note);
                    runOnUiThread(this::finish);
                }));
            });
        });
    }
}
