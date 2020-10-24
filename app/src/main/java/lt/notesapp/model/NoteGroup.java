package lt.notesapp.model;

import java.util.ArrayList;
import java.util.List;

public class NoteGroup {

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

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
