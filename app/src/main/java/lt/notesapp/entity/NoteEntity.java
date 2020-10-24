package lt.notesapp.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note")
public class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "group_id")
    public int groupId;
    public String title;
    public String content;

    public NoteEntity(int groupId, String title, String content) {
        this.groupId = groupId;
        this.title = title;
        this.content = content;
    }
}
