package lt.notesapp.activity;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import lt.notesapp.dao.NoteDao;
import lt.notesapp.databinding.ActivityAddEditGroupBinding;
import lt.notesapp.model.NoteGroup;

public class AddEditGroupActivity extends AppCompatActivity {

    private ActivityAddEditGroupBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(v -> finish());

        NoteDao noteDao = new NoteDao(getApplicationContext());

        Bundle extras = getIntent().getExtras();

        if (extras != null && extras.containsKey("groupId")) {
            AsyncTask.execute(() -> {
                int groupId = extras.getInt("groupId");
                NoteGroup group = noteDao.getNoteGroupById(groupId);
                runOnUiThread(() -> {
                    binding.vEditGroup.setOnSubmitListener(noteGroup -> AsyncTask.execute(() -> {
                        noteGroup.setId(groupId);
                        noteDao.updateNoteGroup(noteGroup);
                        runOnUiThread(this::finish);
                    }));

                    binding.vEditGroup.editGroup(group);
                });
            });
        } else {
            binding.vEditGroup.setOnSubmitListener(noteGroup -> AsyncTask.execute(() -> {
                noteDao.insertNoteGroup(noteGroup);
                runOnUiThread(this::finish);
            }));

            binding.vEditGroup.newGroup();
        }
    }
}
