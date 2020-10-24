package lt.notesapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import lt.notesapp.dao.NoteDao;
import lt.notesapp.dao.NoteGroupDao;
import lt.notesapp.entity.NoteEntity;
import lt.notesapp.entity.NoteGroupEntity;

@Database(entities = {NoteEntity.class, NoteGroupEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
    public abstract NoteGroupDao noteGroupDao();
}
