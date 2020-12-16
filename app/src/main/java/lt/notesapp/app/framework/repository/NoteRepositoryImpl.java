package lt.notesapp.app.framework.repository;

import java.util.ArrayList;
import java.util.List;

import lt.notesapp.app.framework.entity.NoteEntity;
import lt.notesapp.app.framework.entity.NoteGroupEntity;
import lt.notesapp.app.framework.room.AppDatabase;
import lt.notesapp.core.domain.Note;
import lt.notesapp.core.domain.NoteGroup;
import lt.notesapp.core.repository.NoteRepository;

public class NoteRepositoryImpl implements NoteRepository {

    private final AppDatabase db;

    public NoteRepositoryImpl(AppDatabase db) {
        this.db = db;
    }

    @Override
    public void insertNote(Note note) {
        NoteEntity entity = new NoteEntity(note.getNoteGroup().getId(), note.getTitle(), note.getContent());
        db.noteDao().insert(entity);
    }

    @Override
    public void updateNote(Note note) {
        NoteEntity noteEntity = new NoteEntity(note);
        db.noteDao().update(noteEntity);
    }

    @Override
    public void deleteNote(Note note) {
        NoteEntity entity = new NoteEntity(note.getNoteGroup().getId(), note.getTitle(), note.getContent());
        entity.id = note.getId();
        db.noteDao().delete(entity);
    }

    @Override
    public List<Note> selectAll(int groupId) {
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
}
