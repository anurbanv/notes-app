package lt.notesapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import lt.notesapp.entity.NoteGroupEntity;

@Dao
public interface RoomNoteGroupDao {
    @Query("SELECT * FROM note_group")
    List<NoteGroupEntity> selectAll();

    @Insert
    void insert(NoteGroupEntity noteGroup);

    @Delete
    void delete(NoteGroupEntity noteGroup);
}
