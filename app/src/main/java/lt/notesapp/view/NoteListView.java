package lt.notesapp.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lt.notesapp.adapter.NotesAdapter;
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
        setHasFixedSize(true);
        setAdapter(adapter);
        setLayoutManager(new LinearLayoutManager(context));
    }

    public void update(List<Note> notes) {
        adapter.setNotes(notes);
        adapter.notifyDataSetChanged();
    }
}
