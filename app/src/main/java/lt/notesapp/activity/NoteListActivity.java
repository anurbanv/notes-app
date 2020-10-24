package lt.notesapp.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import lt.notesapp.dao.NoteDao;
import lt.notesapp.databinding.ActivityNoteListBinding;
import lt.notesapp.model.Note;

public class NoteListActivity extends AppCompatActivity {

    private ActivityNoteListBinding binding;
    private NoteDao noteDao;
    private int groupId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoteListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(v -> finish());

        noteDao = new NoteDao(getApplicationContext());

        Bundle extras = getIntent().getExtras();
        groupId = extras.getInt("groupId");

        binding.btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddEditNoteActivity.class);
            intent.putExtra("groupId", groupId);
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
            runOnUiThread(() -> {
                binding.noteList.update(notes);
            });
        });
    }
}
