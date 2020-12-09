package lt.notesapp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import lt.notesapp.AppDatabase;
import lt.notesapp.NotesApp;
import lt.notesapp.entity.NoteEntity;
import lt.notesapp.entity.NoteGroupEntity;
import lt.notesapp.model.Note;
import lt.notesapp.model.NoteGroup;

public class NoteDao {

    @Inject AppDatabase db;

    public NoteDao() {
        NotesApp.getInstance().getAppComponent().inject(this);
    }

    public List<Note> getNotesByGroupId(int groupId) {
        List<NoteEntity> noteEntities = db.noteDao().getNotesByGroupId(groupId);
        NoteGroupEntity noteGroupEntity = db.noteGroupDao().selectById(groupId);

        NoteGroup group = new NoteGroup(noteGroupEntity);

        List<Note> notes = new ArrayList<>();

        for (NoteEntity noteEntity : noteEntities) {
            Note note = new Note(noteEntity, group);
            notes.add(note);
        }

        return notes;
    }

    public void insertNote(Note note) {
        NoteEntity entity = new NoteEntity(note.getNoteGroup().getId(), note.getTitle(), note.getContent());
        db.noteDao().insert(entity);
    }

    public void insertOrUpdateNote(Note note) {
        if (note.getId() == 0) {
            insertNote(note);
        } else {
            updateNote(note);
        }
    }

    public void deleteNote(Note note) {
        NoteEntity entity = new NoteEntity(note.getNoteGroup().getId(), note.getTitle(), note.getContent());
        entity.id = note.getId();
        db.noteDao().delete(entity);
    }

    public void updateNote(Note note) {
        NoteEntity noteEntity = new NoteEntity(note);
        db.noteDao().update(noteEntity);
    }
}
