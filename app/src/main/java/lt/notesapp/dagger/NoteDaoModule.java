package lt.notesapp.dagger;

import dagger.Module;
import dagger.Provides;
import lt.notesapp.dao.NoteDao;

@Module
public class NoteDaoModule {

    @Provides
    NoteDao getNoteDao() {
        return new NoteDao();
    }
}
