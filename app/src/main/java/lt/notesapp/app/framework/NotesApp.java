package lt.notesapp.app.framework;

import androidx.multidex.MultiDexApplication;

import com.google.firebase.FirebaseApp;

import lt.notesapp.app.framework.dagger.AppComponent;
import lt.notesapp.app.framework.dagger.ContextModule;
import lt.notesapp.app.framework.dagger.DaggerAppComponent;

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
