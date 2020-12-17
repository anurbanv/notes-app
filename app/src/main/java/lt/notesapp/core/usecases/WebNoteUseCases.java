package lt.notesapp.core.usecases;

import lt.notesapp.core.repository.OnNotesRetrievedListener;
import lt.notesapp.core.repository.WebNoteRepository;

public class WebNoteUseCases {

    private final WebNoteRepository webNoteRepository;

    public WebNoteUseCases(WebNoteRepository webNoteRepository) {
        this.webNoteRepository = webNoteRepository;
    }

    public void getNotesFromWebService(OnNotesRetrievedListener onNotesRetrievedListener) {
        webNoteRepository.getNotesFromWebService(onNotesRetrievedListener);
    }

    public void getNotesFromFireStore(OnNotesRetrievedListener onFireStoreNotesRetrieved) {
        webNoteRepository.getNotesFromFireStore(onFireStoreNotesRetrieved);
    }
}
