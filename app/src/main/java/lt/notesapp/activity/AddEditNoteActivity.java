package lt.notesapp.activity;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import lt.notesapp.NotesApp;
import lt.notesapp.dao.NoteDao;
import lt.notesapp.databinding.ActivityAddEditNoteBinding;
import lt.notesapp.model.Note;
import lt.notesapp.model.NoteGroup;

public class AddEditNoteActivity extends AppCompatActivity {

    private ActivityAddEditNoteBinding binding;
    @Inject NoteDao noteDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NotesApp.getInstance().getAppComponent().inject(this);


        binding.btnBack.setOnClickListener(v -> finish());

        Bundle extras = getIntent().getExtras();

        int groupId = extras.getInt("groupId");
        AsyncTask.execute(() -> {
            NoteGroup noteGroup = noteDao.getNoteGroupById(groupId);
            runOnUiThread(() -> {
                if (extras.containsKey("noteId")) {
                    int noteId = extras.getInt("noteId");
                    AsyncTask.execute(() -> {
                        Note note = noteDao.getNoteById(noteId);
                        runOnUiThread(() -> {
                            binding.vNoteEdit.editNote(note);
                            binding.vNoteEdit.setOnSubmitListener(note1 -> {
                                AsyncTask.execute(() -> noteDao.updateNote(note1));
                                runOnUiThread(this::finish);
                            });
                        });
                    });
                } else {
                    runOnUiThread(() -> {
                        binding.vNoteEdit.newNote(noteGroup);
                        binding.vNoteEdit.setOnSubmitListener(note -> AsyncTask.execute(() -> {
                            noteDao.insertNote(note);
                            runOnUiThread(this::finish);
                        }));
                    });
                }
            });
        });
    }
}
