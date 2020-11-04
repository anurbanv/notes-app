package lt.notesapp.dagger;

import android.content.Context;

import androidx.room.Room;

import dagger.Module;
import dagger.Provides;
import lt.notesapp.AppDatabase;

@Module(includes = ContextModule.class)
public class AppDatabaseModule {

    @Provides
    AppDatabase getAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "notes_app_table").build();
    }
}
