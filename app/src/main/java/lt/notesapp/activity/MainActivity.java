package lt.notesapp.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import lt.notesapp.dao.NoteDao;
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

        NoteDao noteDao = new NoteDao(getApplicationContext());

        AsyncTask.execute(() -> {
            List<NoteGroup> allGroups = noteDao.getAllGroups();

            for (NoteGroup allGroup : allGroups) {
                Log.e(TAG, allGroup.toString());
            }
        });
    }
}