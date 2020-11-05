package lt.notesapp.dao;

import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import lt.notesapp.AppDatabase;
import lt.notesapp.NotesApp;
import lt.notesapp.entity.NoteEntity;
import lt.notesapp.entity.NoteGroupEntity;
import lt.notesapp.model.Note;
import lt.notesapp.model.NoteGroup;
import lt.notesapp.rest.NoteObject;
import lt.notesapp.rest.NotesApi;
import retrofit2.Call;
import retrofit2.Response;

import static lt.notesapp.activity.MainActivity.APP_TAG;

public class NoteDao {

    public interface OnFireStoreNotesRetrieved {
        void onNotesRetrieved(List<Note> notes);
    }

    @Inject AppDatabase db;
    @Inject NotesApi notesApi;
    @Inject FirebaseFirestore fireStore;

    public NoteDao() {
        NotesApp.getInstance().getAppComponent().inject(this);
    }

    public void insertOrUpdateGroup(NoteGroup noteGroup) {
        if (noteGroup.getId() == 0) {
            insertNoteGroup(noteGroup);
        } else {
            updateNoteGroup(noteGroup);
        }
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
        db.noteDao().deleteAllByGroupId(noteGroup.getId());
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

    public Note getNoteById(int id) {
        NoteEntity noteEntity = db.noteDao().getNoteById(id);
        NoteGroupEntity entity = db.noteGroupDao().selectById(noteEntity.groupId);
        NoteGroup group = new NoteGroup(entity);
        return new Note(noteEntity, group);
    }

    public void updateNote(Note note) {
        NoteEntity noteEntity = new NoteEntity(note);
        db.noteDao().update(noteEntity);
    }

    public List<Note> getNotesFromWebService() {
        try {
            Call<List<NoteObject>> apiNotes = notesApi.getNotes();

            Response<List<NoteObject>> response = apiNotes.execute();

            if (!response.isSuccessful()) {
                Log.e(APP_TAG, String.valueOf(response.code()));
                return new ArrayList<>();
            }

            List<NoteObject> body = response.body();

            List<Note> notes = new ArrayList<>();

            for (NoteObject noteObject : body) {
                notes.add(new Note(noteObject));
            }

            return notes;

        } catch (IOException e) {
            Log.e(APP_TAG, "API error", e);
            return new ArrayList<>();
        }
    }

    public void getNotesFromFireStore(OnFireStoreNotesRetrieved onFireStoreNotesRetrieved) {
        CollectionReference notesRef = fireStore.collection("notes");

        notesRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                List<DocumentSnapshot> documents = task.getResult().getDocuments();

                List<Note> notes = new ArrayList<>();

                for (DocumentSnapshot document : documents) {
                    notes.add(new Note(document));
                }

                onFireStoreNotesRetrieved.onNotesRetrieved(notes);
            }
        });
    }
}
