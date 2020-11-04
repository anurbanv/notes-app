package lt.notesapp.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import javax.inject.Inject;

import lt.notesapp.NotesApp;
import lt.notesapp.dao.NoteDao;
import lt.notesapp.databinding.ActivityNoteListBinding;
import lt.notesapp.model.Note;

public class NoteListActivity extends AppCompatActivity {

    private ActivityNoteListBinding binding;
    @Inject NoteDao noteDao;
    private int groupId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoteListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NotesApp.getInstance().getAppComponent().inject(this);

        binding.btnBack.setOnClickListener(v -> finish());

        Bundle extras = getIntent().getExtras();
        groupId = extras.getInt("groupId");

        binding.btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddEditNoteActivity.class);
            intent.putExtra("groupId", groupId);
            startActivity(intent);
        });

        binding.noteList.setOnDeleteClickListener(note -> AsyncTask.execute(() -> {
            noteDao.deleteNote(note);
            updateList();
        }));

        binding.noteList.setOnEditClickListener(note -> {
            Intent intent = new Intent(this, AddEditNoteActivity.class);
            intent.putExtra("groupId", groupId);
            intent.putExtra("noteId", note.getId());
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    private void updateList() {
        AsyncTask.execute(() -> {
            List<Note> notes = noteDao.getNotesByGroupId(groupId);
            runOnUiThread(() -> binding.noteList.update(notes));
        });
    }
}
