package lt.notesapp.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_group")
public class NoteGroupEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;

    public NoteGroupEntity(String title) {
        this.title = title;
    }
}
