package lt.notesapp.adapter;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lt.notesapp.model.Note;
import lt.notesapp.view.NoteView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    public interface OnDeleteClickListener {
        void onDeleteClick(Note note);
    }

    public interface OnEditClickListener {
        void onEditClick(Note note);
    }

    private List<Note> notes = new ArrayList<>();
    private OnDeleteClickListener onDeleteClickListener;
    private OnEditClickListener onEditClickListener;

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }

    public void setOnEditClickListener(OnEditClickListener onEditClickListener) {
        this.onEditClickListener = onEditClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NoteView noteView = new NoteView(parent.getContext());
        noteView.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        return new ViewHolder(noteView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.noteView.update(note);

        holder.noteView.setOnDeleteClickListener(v -> {
            if (onDeleteClickListener != null) onDeleteClickListener.onDeleteClick(note);
        });

        holder.noteView.setOnEditClickListener(v -> {
            if (onEditClickListener != null) onEditClickListener.onEditClick(note);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        NoteView noteView;

        public ViewHolder(@NonNull NoteView noteView) {
            super(noteView);
            this.noteView = noteView;
        }
    }
}
