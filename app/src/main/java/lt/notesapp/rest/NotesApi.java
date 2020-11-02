package lt.notesapp.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NotesApi {

    @GET("notes")
    Call<List<NoteObject>> getNotes();
}
