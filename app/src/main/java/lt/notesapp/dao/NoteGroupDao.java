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

public class NoteGroupDao {

    @Inject AppDatabase db;

    public NoteGroupDao() {
        NotesApp.getInstance().getAppComponent().inject(this);
    }

    public void insertOrUpdateGroup(NoteGroup noteGroup) {
        if (noteGroup.getId() == 0) {
            insertNoteGroup(noteGroup);
        } else {
            updateNoteGroup(noteGroup);
        }
    }

    private void insertNoteGroup(NoteGroup noteGroup) {
        NoteGroupEntity entity = new NoteGroupEntity(noteGroup.getTitle());
        db.noteGroupDao().insert(entity);
    }

    private void updateNoteGroup(NoteGroup noteGroup) {
        NoteGroupEntity entity = new NoteGroupEntity(noteGroup.getTitle());
        entity.id = noteGroup.getId();
        db.noteGroupDao().update(entity);
    }

    public void deleteNoteGroup(NoteGroup noteGroup) {
        NoteGroupEntity entity = new NoteGroupEntity(noteGroup.getTitle());
        entity.id = noteGroup.getId();
        db.noteGroupDao().delete(entity);
        db.noteDao().deleteAllByGroupId(noteGroup.getId());
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
}
