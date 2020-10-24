package lt.notesapp.activity;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import lt.notesapp.dao.NoteDao;
import lt.notesapp.databinding.ActivityAddEditGroupBinding;

public class AddEditGroupActivity extends AppCompatActivity {

    private ActivityAddEditGroupBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NoteDao noteDao = new NoteDao(getApplicationContext());

        binding.btnBack.setOnClickListener(v -> finish());
        binding.vEditGroup.setOnSubmitListener(noteGroup -> AsyncTask.execute(() -> {
            noteDao.insertNoteGroup(noteGroup);
            runOnUiThread(this::finish);
        }));

        binding.vEditGroup.newGroup();
    }
}
