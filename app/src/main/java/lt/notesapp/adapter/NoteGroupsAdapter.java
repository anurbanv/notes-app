package lt.notesapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lt.notesapp.R;
import lt.notesapp.databinding.ViewNoteGroupBinding;
import lt.notesapp.model.NoteGroup;

public class NoteGroupsAdapter extends RecyclerView.Adapter<NoteGroupsAdapter.ViewHolder> {

    private List<NoteGroup> noteGroups = new ArrayList<>();

    public void setNoteGroups(List<NoteGroup> noteGroups) {
        this.noteGroups = noteGroups;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_note_group, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoteGroup noteGroup = noteGroups.get(position);
        holder.binding.tvTitle.setText(noteGroup.getTitle());
        holder.binding.tvNoteCount.setText(String.valueOf(noteGroup.getNotes().size()));
    }

    @Override
    public int getItemCount() {
        return noteGroups.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ViewNoteGroupBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ViewNoteGroupBinding.bind(itemView);
        }
    }

}
