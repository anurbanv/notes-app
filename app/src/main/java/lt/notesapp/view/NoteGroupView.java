package lt.notesapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import lt.notesapp.R;
import lt.notesapp.databinding.ViewNoteGroupBinding;
import lt.notesapp.model.NoteGroup;

public class NoteGroupView extends LinearLayout {

    private ViewNoteGroupBinding binding;

    public NoteGroupView(Context context) {
        super(context);
        init(context);
    }

    public NoteGroupView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NoteGroupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View root = inflater.inflate(R.layout.view_note_group, this, true);
        binding = ViewNoteGroupBinding.bind(root);
    }

    public void update(NoteGroup noteGroup) {
        binding.tvTitle.setText(noteGroup.getTitle());
        binding.tvNoteCount.setText(String.valueOf(noteGroup.getNotes().size()));
    }
}
