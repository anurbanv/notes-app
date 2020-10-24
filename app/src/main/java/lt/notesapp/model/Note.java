package lt.notesapp.model;

public class Note {

    private int id;
    private NoteGroup noteGroup;
    private String title;
    private String content;

    public Note(NoteGroup noteGroup, String title, String content) {
        this.noteGroup = noteGroup;
        this.title = title;
        this.content = content;
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
