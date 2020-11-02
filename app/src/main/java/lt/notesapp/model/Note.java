package lt.notesapp.model;

import com.google.firebase.firestore.DocumentSnapshot;

import lt.notesapp.entity.NoteEntity;
import lt.notesapp.rest.NoteObject;

public class Note {

    private int id;
    private NoteGroup noteGroup;
    private String title;
    private String content;

    public Note(NoteEntity noteEntity, NoteGroup noteGroup) {
        id = noteEntity.id;
        this.noteGroup = noteGroup;
        title = noteEntity.title;
        content = noteEntity.content;
    }

    public Note(NoteGroup noteGroup, String title, String content) {
        this.noteGroup = noteGroup;
        this.title = title;
        this.content = content;
    }

    public Note(NoteObject noteObject) {
        this.id = noteObject.getId();
        this.title = noteObject.getTitle();
        this.content = noteObject.getContent();
    }

    public Note(NoteGroup noteGroup) {
        this.noteGroup = noteGroup;
    }

    public Note(DocumentSnapshot document) {
        this.id = (Integer) document.get("id");
        this.title = (String) document.get("title");
        this.content = (String) document.get("content");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NoteGroup getNoteGroup() {
        return noteGroup;
    }

    public void setNoteGroup(NoteGroup noteGroup) {
        this.noteGroup = noteGroup;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", noteGroup=" + noteGroup +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
