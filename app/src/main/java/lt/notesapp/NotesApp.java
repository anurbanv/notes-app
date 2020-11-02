package lt.notesapp;

import androidx.multidex.MultiDexApplication;

import com.google.firebase.FirebaseApp;

public class NotesApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
