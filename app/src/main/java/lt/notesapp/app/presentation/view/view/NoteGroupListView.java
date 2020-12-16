package lt.notesapp.app.presentation.view.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lt.notesapp.app.presentation.view.adapter.NoteGroupsAdapter;
import lt.notesapp.app.presentation.view.events.OnGroupClickListener;
import lt.notesapp.app.presentation.view.events.OnGroupDeleteListener;
import lt.notesapp.app.presentation.view.events.OnGroupEditListener;
import lt.notesapp.core.domain.NoteGroup;

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

    public void setOnEditClickListener(OnGroupEditListener listener) {
        adapter.setOnGroupEditListener(listener);
    }

    public void setOnDeleteClickListener(OnGroupDeleteListener listener) {
        adapter.setOnGroupDeleteListener(listener);
    }

    public void setOnItemClickListener(OnGroupClickListener listener) {
        adapter.setOnGroupClickListener(listener);
    }

    public void update(List<NoteGroup> noteGroups) {
        adapter.setNoteGroups(noteGroups);
    }
}
