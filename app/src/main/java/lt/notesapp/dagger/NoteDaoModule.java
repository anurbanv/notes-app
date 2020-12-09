package lt.notesapp.dagger;

import dagger.Module;
import dagger.Provides;
import lt.notesapp.dao.NoteDao;
import lt.notesapp.dao.NoteGroupDao;
import lt.notesapp.dao.NoteWebDao;

@Module
public class NoteDaoModule {

    @ComponentScope
    @Provides
    NoteDao getNoteDao() {
        return new NoteDao();
    }

    @ComponentScope
    @Provides
    NoteGroupDao getNoteGroupDao() {
        return new NoteGroupDao();
    }

    @ComponentScope
    @Provides
    NoteWebDao getNoteWebDao() {
        return new NoteWebDao();
    }
}
