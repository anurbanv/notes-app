package lt.notesapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import lt.notesapp.entity.NoteEntity;

@Dao
public interface RoomNoteDao {
    @Query("SELECT * FROM note WHERE group_id = :groupId ORDER BY id DESC")
    List<NoteEntity> getNotesByGroupId(int groupId);

    @Query("DELETE FROM note WHERE group_id = :groupId")
    void deleteAllByGroupId(int groupId);

    @Insert
    void insert(NoteEntity note);

    @Delete
    void delete(NoteEntity note);
}
