package lt.notesapp.activity;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import lt.notesapp.dao.NoteDao;
import lt.notesapp.databinding.ActivityGroupsBinding;
import lt.notesapp.model.NoteGroup;

public class GroupsActivity extends AppCompatActivity {

    private ActivityGroupsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGroupsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(v -> finish());
        binding.btnAdd.setOnClickListener(v -> {
            // todo start add activity
        });

        AsyncTask.execute(() -> {
            NoteDao noteDao = new NoteDao(getApplicationContext());
            List<NoteGroup> groups = noteDao.getAllGroups();
            runOnUiThread(() -> binding.groupList.update(groups));
        });
    }
}
