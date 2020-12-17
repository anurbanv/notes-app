package lt.notesapp.app.presentation.view.adapter;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lt.notesapp.app.presentation.view.events.OnNoteDeleteListener;
import lt.notesapp.app.presentation.view.events.OnNoteEditListener;
import lt.notesapp.app.presentation.view.view.NoteView;
import lt.notesapp.core.domain.Note;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Note> notes = new ArrayList<>();
    private OnNoteDeleteListener onNoteDeleteListener;
    private OnNoteEditListener onNoteEditListener;

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public void setOnNoteDeleteListener(OnNoteDeleteListener onNoteDeleteListener) {
        this.onNoteDeleteListener = onNoteDeleteListener;
    }

    public void setOnNoteEditListener(OnNoteEditListener onNoteEditListener) {
        this.onNoteEditListener = onNoteEditListener;
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

        if (onNoteDeleteListener != null) {
            holder.noteView.setOnDeleteClickListener(v -> onNoteDeleteListener.onNoteDelete(note));
        }

        if (onNoteEditListener != null) {
            holder.noteView.setOnEditClickListener(v -> onNoteEditListener.onNoteEdit(note));
        }
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
