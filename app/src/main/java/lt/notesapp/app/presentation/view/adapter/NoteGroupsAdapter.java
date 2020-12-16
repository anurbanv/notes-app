package lt.notesapp.app.presentation.view.adapter;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lt.notesapp.app.presentation.view.events.OnGroupClickListener;
import lt.notesapp.app.presentation.view.events.OnGroupDeleteListener;
import lt.notesapp.app.presentation.view.events.OnGroupEditListener;
import lt.notesapp.app.presentation.view.view.NoteGroupView;
import lt.notesapp.core.domain.NoteGroup;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class NoteGroupsAdapter extends RecyclerView.Adapter<NoteGroupsAdapter.ViewHolder> {

    private List<NoteGroup> noteGroups = new ArrayList<>();
    private OnGroupEditListener onGroupEditListener;
    private OnGroupDeleteListener onGroupDeleteListener;
    private OnGroupClickListener onGroupClickListener;

    public void setNoteGroups(List<NoteGroup> noteGroups) {
        this.noteGroups = noteGroups;
        notifyDataSetChanged();
    }

    public void setOnGroupEditListener(OnGroupEditListener onGroupEditListener) {
        this.onGroupEditListener = onGroupEditListener;
    }

    public void setOnGroupDeleteListener(OnGroupDeleteListener onGroupDeleteListener) {
        this.onGroupDeleteListener = onGroupDeleteListener;
    }

    public void setOnGroupClickListener(OnGroupClickListener onGroupClickListener) {
        this.onGroupClickListener = onGroupClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NoteGroupView noteGroupView = new NoteGroupView(parent.getContext());
        noteGroupView.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        return new ViewHolder(noteGroupView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoteGroup noteGroup = noteGroups.get(position);
        holder.noteGroupView.update(noteGroup);

        holder.noteGroupView.setOnEditClickListener(v -> {
            if (onGroupEditListener != null) onGroupEditListener.onGroupEdit(noteGroup);
        });

        holder.noteGroupView.setOnDeleteClickListener(v -> {
            if (onGroupDeleteListener != null) onGroupDeleteListener.onGroupDelete(noteGroup);
        });

        holder.noteGroupView.setOnClickListener(v -> {
            if (onGroupClickListener != null) onGroupClickListener.onGroupClick(noteGroup);
        });
    }

    @Override
    public int getItemCount() {
        return noteGroups.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        NoteGroupView noteGroupView;

        public ViewHolder(@NonNull NoteGroupView noteGroupView) {
            super(noteGroupView);
            this.noteGroupView = noteGroupView;
        }
    }

}
