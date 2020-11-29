package lt.notesapp.dagger;

import dagger.Module;
import dagger.Provides;
import lt.notesapp.dao.NoteDao;

@Module
public class NoteDaoModule {

    @ComponentScope
    @Provides
    NoteDao getNoteDao() {
        return new NoteDao();
    }
}
