package lt.notesapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import lt.notesapp.entity.NoteEntity;

@Dao
public interface RoomNoteDao {
    @Query("SELECT * FROM note WHERE group_id = :groupId ORDER BY id DESC")
    List<NoteEntity> getNotesByGroupId(int groupId);

    @Query("DELETE FROM note WHERE group_id = :groupId")
    void deleteAllByGroupId(int groupId);

    @Query("SELECT * FROM note WHERE id = :id")
    NoteEntity getNoteById(int id);

    @Insert
    void insert(NoteEntity note);

    @Delete
    void delete(NoteEntity note);

    @Update
    void update(NoteEntity note);
}
