package lt.notesapp.app.framework.repository;

import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lt.notesapp.app.framework.entity.NoteObject;
import lt.notesapp.app.framework.rest.NotesApi;
import lt.notesapp.core.domain.Note;
import lt.notesapp.core.repository.OnNotesRetrievedListener;
import lt.notesapp.core.repository.WebNoteRepository;
import retrofit2.Call;
import retrofit2.Response;

import static lt.notesapp.app.presentation.view.activity.MainActivity.APP_TAG;

public class WebNoteRepositoryImpl implements WebNoteRepository {

    private final NotesApi notesApi;
    private final FirebaseFirestore firebaseFirestore;

    public WebNoteRepositoryImpl(NotesApi notesApi, FirebaseFirestore firebaseFirestore) {
        this.notesApi = notesApi;
        this.firebaseFirestore = firebaseFirestore;
    }

    @Override
    public void getNotesFromWebService(OnNotesRetrievedListener onNotesRetrievedListener) {
        try {
            Call<List<NoteObject>> apiNotes = notesApi.getNotes();

            Response<List<NoteObject>> response = apiNotes.execute();

            if (!response.isSuccessful()) {
                Log.e(APP_TAG, String.valueOf(response.code()));
                onNotesRetrievedListener.onNotesRetrieved(new ArrayList<>());
                return;
            }

            List<NoteObject> body = response.body();

            List<Note> notes = new ArrayList<>();

            for (NoteObject noteObject : body) {
                notes.add(new Note(noteObject));
            }

            onNotesRetrievedListener.onNotesRetrieved(notes);
        } catch (IOException e) {
            Log.e(APP_TAG, "API error", e);
            onNotesRetrievedListener.onNotesRetrieved(new ArrayList<>());
        }
    }

    @Override
    public void getNotesFromFireStore(OnNotesRetrievedListener onFireStoreNotesRetrieved) {
        CollectionReference notesRef = firebaseFirestore.collection("notes");

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
