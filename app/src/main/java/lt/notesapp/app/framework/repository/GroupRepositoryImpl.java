package lt.notesapp.app.framework.repository;

import java.util.ArrayList;
import java.util.List;

import lt.notesapp.app.framework.entity.NoteEntity;
import lt.notesapp.app.framework.entity.NoteGroupEntity;
import lt.notesapp.app.framework.room.AppDatabase;
import lt.notesapp.core.domain.Note;
import lt.notesapp.core.domain.NoteGroup;
import lt.notesapp.core.repository.GroupRepository;

public class GroupRepositoryImpl implements GroupRepository {

    private final AppDatabase db;

    public GroupRepositoryImpl(AppDatabase db) {
        this.db = db;
    }

    @Override
    public void insertGroup(NoteGroup noteGroup) {
        NoteGroupEntity entity = new NoteGroupEntity(noteGroup.getTitle());
        db.noteGroupDao().insert(entity);
    }

    @Override
    public void updateGroup(NoteGroup noteGroup) {
        NoteGroupEntity entity = new NoteGroupEntity(noteGroup.getTitle());
        entity.id = noteGroup.getId();
        db.noteGroupDao().update(entity);
    }

    @Override
    public void deleteGroup(NoteGroup noteGroup) {
        NoteGroupEntity entity = new NoteGroupEntity(noteGroup.getTitle());
        entity.id = noteGroup.getId();
        db.noteGroupDao().delete(entity);
        db.noteDao().deleteAllByGroupId(noteGroup.getId());
    }

    @Override
    public List<NoteGroup> selectAll() {
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
