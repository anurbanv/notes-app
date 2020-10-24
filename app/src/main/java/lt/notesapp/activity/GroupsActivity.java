package lt.notesapp.activity;

import android.content.Intent;
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
    private NoteDao noteDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGroupsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(v -> finish());
        binding.btnAdd.setOnClickListener(v ->
                startActivity(new Intent(this, AddEditGroupActivity.class)));
        noteDao = new NoteDao(getApplicationContext());

        binding.groupList.setOnEditClickListener(noteGroup -> {
            Intent intent = new Intent(this, AddEditGroupActivity.class);
            intent.putExtra("groupId", noteGroup.getId());
            startActivity(intent);
        });

        binding.groupList.setOnDeleteClickListener(noteGroup -> AsyncTask.execute(() -> {
            noteDao.deleteNoteGroup(noteGroup);
            updateGroupList();
        }));

        binding.groupList.setOnItemClickListener(noteGroup -> {
            Intent intent = new Intent(this, NoteListActivity.class);
            intent.putExtra("groupId", noteGroup.getId());
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateGroupList();
    }

    private void updateGroupList() {
        AsyncTask.execute(() -> {
            List<NoteGroup> groups = noteDao.getAllGroups();
            runOnUiThread(() -> binding.groupList.update(groups));
        });
    }
}
