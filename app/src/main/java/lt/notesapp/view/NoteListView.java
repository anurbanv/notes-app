package lt.notesapp.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lt.notesapp.adapter.NotesAdapter;
import lt.notesapp.events.OnNoteDeleteListener;
import lt.notesapp.events.OnNoteEditListener;
import lt.notesapp.model.Note;

public class NoteListView extends RecyclerView {

    private NotesAdapter adapter;

    public NoteListView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public NoteListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NoteListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        adapter = new NotesAdapter();
        setAdapter(adapter);
        setHasFixedSize(true);
        setLayoutManager(new LinearLayoutManager(context));
    }

    public void setOnDeleteClickListener(OnNoteDeleteListener listener) {
        adapter.setOnNoteDeleteListener(listener);
    }

    public void setOnEditClickListener(OnNoteEditListener listener) {
        adapter.setOnNoteEditListener(listener);
    }

    public void update(List<Note> notes) {
        adapter.setNotes(notes);
    }
}
