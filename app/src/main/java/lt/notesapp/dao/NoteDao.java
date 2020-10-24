package lt.notesapp.dao;

import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import lt.notesapp.AppDatabase;
import lt.notesapp.entity.NoteEntity;
import lt.notesapp.entity.NoteGroupEntity;
import lt.notesapp.model.Note;
import lt.notesapp.model.NoteGroup;

public class NoteDao {

    private final AppDatabase db;

    public NoteDao(Context applicationContext) {
        db = Room.databaseBuilder(applicationContext, AppDatabase.class, "notes_app_table").build();
    }

    public void insertNoteGroup(NoteGroup noteGroup) {
        NoteGroupEntity entity = new NoteGroupEntity(noteGroup.getTitle());
        db.noteGroupDao().insert(entity);
    }

    public void updateNoteGroup(NoteGroup noteGroup) {
        NoteGroupEntity entity = new NoteGroupEntity(noteGroup.getTitle());
        entity.id = noteGroup.getId();
        db.noteGroupDao().update(entity);
    }

    public void deleteNoteGroup(NoteGroup noteGroup) {
        NoteGroupEntity entity = new NoteGroupEntity(noteGroup.getTitle());
        entity.id = noteGroup.getId();
        db.noteGroupDao().delete(entity);
    }

    public NoteGroup getNoteGroupById(int id) {
        NoteGroupEntity entity = db.noteGroupDao().selectById(id);
        return new NoteGroup(entity);
    }

    public List<NoteGroup> getAllGroups() {
        List<NoteGroup> noteGroups = new ArrayList<>();

        List<NoteGroupEntity> entities = db.noteGroupDao().selectAll();

        for (NoteGroupEntity entity : entities) {

            List<NoteEntity> noteEntities = db.noteDao().getNotesByGroupId(entity.id);

            NoteGroup noteGroup = new NoteGroup(entity);
            List<Note> notes = new ArrayList<>();

            for (NoteEntity noteEntity : noteEntities) {
                Note note = new Note(noteEntity, noteGroup);
                notes.add(note);
            }

            noteGroup.setNotes(notes);
            noteGroups.add(noteGroup);
        }

        return noteGroups;
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
}
