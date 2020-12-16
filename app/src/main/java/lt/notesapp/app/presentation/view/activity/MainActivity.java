package lt.notesapp.app.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import lt.notesapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static String APP_TAG = "NotesApp";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(this, NotesActivity.class);
            startActivity(intent);
        });
    }
}