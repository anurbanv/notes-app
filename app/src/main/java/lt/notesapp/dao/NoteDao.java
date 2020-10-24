package lt.notesapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import lt.notesapp.entity.NoteEntity;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note")
    List<NoteEntity> selectAll();

    @Insert
    void insert(NoteEntity note);

    @Delete
    void delete(NoteEntity note);
}
