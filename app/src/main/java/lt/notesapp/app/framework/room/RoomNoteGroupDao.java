package lt.notesapp.app.framework.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import lt.notesapp.app.framework.entity.NoteGroupEntity;

@Dao
public interface RoomNoteGroupDao {
    @Query("SELECT * FROM note_group ORDER BY id DESC")
    List<NoteGroupEntity> selectAll();

    @Query("SELECT * FROM note_group WHERE id=:id")
    NoteGroupEntity selectById(int id);

    @Insert
    void insert(NoteGroupEntity noteGroup);

    @Update
    void update(NoteGroupEntity noteGroup);

    @Delete
    void delete(NoteGroupEntity noteGroup);
}
