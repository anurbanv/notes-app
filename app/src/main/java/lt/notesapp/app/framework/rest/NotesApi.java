package lt.notesapp.app.framework.rest;

import java.util.List;

import lt.notesapp.app.framework.entity.NoteObject;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NotesApi {

    @GET("notes")
    Call<List<NoteObject>> getNotes();
}
