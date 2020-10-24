package lt.notesapp.adapter;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lt.notesapp.model.NoteGroup;
import lt.notesapp.view.NoteGroupView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class NoteGroupsAdapter extends RecyclerView.Adapter<NoteGroupsAdapter.ViewHolder> {

    public interface OnEditClickListener {
        void onEditClick(NoteGroup noteGroup);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(NoteGroup noteGroup);
    }

    private List<NoteGroup> noteGroups = new ArrayList<>();
    private OnEditClickListener onEditClickListener;
    private OnDeleteClickListener onDeleteClickListener;

    public void setNoteGroups(List<NoteGroup> noteGroups) {
        this.noteGroups = noteGroups;
        notifyDataSetChanged();
    }

    public void setOnEditClickListener(OnEditClickListener onEditClickListener) {
        this.onEditClickListener = onEditClickListener;
    }

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
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
            if (onEditClickListener != null) onEditClickListener.onEditClick(noteGroup);
        });

        holder.noteGroupView.setOnDeleteClickListener(v -> {
            if (onDeleteClickListener != null) onDeleteClickListener.onDeleteClick(noteGroup);
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
