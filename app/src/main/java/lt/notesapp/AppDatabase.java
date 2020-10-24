package lt.notesapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import lt.notesapp.dao.RoomNoteDao;
import lt.notesapp.dao.RoomNoteGroupDao;
import lt.notesapp.entity.NoteEntity;
import lt.notesapp.entity.NoteGroupEntity;

@Database(entities = {NoteEntity.class, NoteGroupEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RoomNoteDao noteDao();
    public abstract RoomNoteGroupDao noteGroupDao();
}
