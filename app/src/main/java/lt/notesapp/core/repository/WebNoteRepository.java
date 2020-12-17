package lt.notesapp.core.repository;

public interface WebNoteRepository {

    void getNotesFromWebService(OnNotesRetrievedListener onNotesRetrievedListener);

    void getNotesFromFireStore(OnNotesRetrievedListener onNotesRetrievedListener);
}
