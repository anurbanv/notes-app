package lt.notesapp;

import androidx.multidex.MultiDexApplication;

import com.google.firebase.FirebaseApp;

import lt.notesapp.dagger.AppComponent;
import lt.notesapp.dagger.ContextModule;
import lt.notesapp.dagger.DaggerAppComponent;

public class NotesApp extends MultiDexApplication {

    private AppComponent appComponent;

    private static NotesApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);

        instance = this;

        appComponent = DaggerAppComponent.builder().contextModule(new ContextModule(this)).build();
    }

    public static NotesApp getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
