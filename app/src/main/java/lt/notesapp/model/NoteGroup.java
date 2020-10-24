package lt.notesapp.model;

import java.util.ArrayList;
import java.util.List;

public class NoteGroup {

    private int id;
    private String title;
    private List<Note> notes = new ArrayList<>();

    public NoteGroup(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "NoteGroup{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", notes=" + notes +
                '}';
    }
}
