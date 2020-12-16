package lt.notesapp.app.framework.dagger;

import dagger.Module;
import dagger.Provides;
import lt.notesapp.app.framework.repository.GroupRepositoryImpl;
import lt.notesapp.app.framework.repository.NoteRepositoryImpl;
import lt.notesapp.app.framework.room.AppDatabase;
import lt.notesapp.core.repository.GroupRepository;
import lt.notesapp.core.repository.NoteRepository;
import lt.notesapp.dao.NoteWebDao;

@Module(includes = AppDatabaseModule.class)
public class NoteDaoModule {

    @ComponentScope
    @Provides
    NoteRepository getNoteRepository(AppDatabase appDatabase) {
        return new NoteRepositoryImpl(appDatabase);
    }

    @ComponentScope
    @Provides
    GroupRepository getGroupRepository(AppDatabase appDatabase) {
        return new GroupRepositoryImpl(appDatabase);
    }

    @ComponentScope
    @Provides
    NoteWebDao getNoteWebDao() {
        return new NoteWebDao();
    }
}
