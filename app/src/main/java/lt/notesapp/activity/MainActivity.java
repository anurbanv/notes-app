package lt.notesapp.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import lt.notesapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "NotesApp";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.vNoteEdit.setOnSubmitListener(noteGroup -> {
            Log.e(TAG, noteGroup.toString());
        });

        binding.vNoteEdit.newGroup();

    }
}