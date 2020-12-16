package lt.notesapp.app.framework.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import lt.notesapp.app.framework.entity.NoteEntity;
import lt.notesapp.app.framework.entity.NoteGroupEntity;

@Database(entities = {NoteEntity.class, NoteGroupEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RoomNoteDao noteDao();

    public abstract RoomNoteGroupDao noteGroupDao();
}
