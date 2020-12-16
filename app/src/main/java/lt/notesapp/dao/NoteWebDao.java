package lt.notesapp.dao;

import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import lt.notesapp.app.framework.NotesApp;
import lt.notesapp.app.framework.entity.NoteObject;
import lt.notesapp.app.presentation.view.events.OnFireStoreNotesReceived;
import lt.notesapp.core.domain.Note;
import lt.notesapp.rest.NotesApi;
import retrofit2.Call;
import retrofit2.Response;

import static lt.notesapp.app.presentation.view.activity.MainActivity.APP_TAG;

public class NoteWebDao {

    @Inject NotesApi notesApi;
    @Inject FirebaseFirestore fireStore;

    public NoteWebDao() {
        NotesApp.getInstance().getAppComponent().inject(this);
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

    public void getNotesFromFireStore(OnFireStoreNotesReceived onFireStoreNotesRetrieved) {
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
