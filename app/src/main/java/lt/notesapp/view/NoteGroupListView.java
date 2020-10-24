package lt.notesapp.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lt.notesapp.adapter.NoteGroupsAdapter;
import lt.notesapp.model.NoteGroup;

public class NoteGroupListView extends RecyclerView {

    private NoteGroupsAdapter adapter;

    public NoteGroupListView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public NoteGroupListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NoteGroupListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        adapter = new NoteGroupsAdapter();
        setAdapter(adapter);
        setHasFixedSize(true);
        setLayoutManager(new LinearLayoutManager(context));
    }

    public void update(List<NoteGroup> noteGroups) {
        adapter.setNoteGroups(noteGroups);
    }
}