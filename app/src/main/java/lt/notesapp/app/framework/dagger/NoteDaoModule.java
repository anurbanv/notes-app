package lt.notesapp.app.framework.dagger;

import com.google.firebase.firestore.FirebaseFirestore;

import dagger.Module;
import dagger.Provides;
import lt.notesapp.app.framework.repository.GroupRepositoryImpl;
import lt.notesapp.app.framework.repository.NoteRepositoryImpl;
import lt.notesapp.app.framework.repository.WebNoteRepositoryImpl;
import lt.notesapp.app.framework.rest.NotesApi;
import lt.notesapp.app.framework.room.AppDatabase;
import lt.notesapp.core.repository.GroupRepository;
import lt.notesapp.core.repository.NoteRepository;
import lt.notesapp.core.repository.WebNoteRepository;

@Module(includes = {AppDatabaseModule.class, NotesApiModule.class, FireStoreModule.class})
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
    WebNoteRepository getNoteWebDao(NotesApi notesApi, FirebaseFirestore firebaseFirestore) {
        return new WebNoteRepositoryImpl(notesApi, firebaseFirestore);
    }
}
