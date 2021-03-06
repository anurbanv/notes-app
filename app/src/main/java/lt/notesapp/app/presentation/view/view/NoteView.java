package lt.notesapp.app.presentation.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import lt.notesapp.R;
import lt.notesapp.core.domain.Note;
import lt.notesapp.databinding.ViewNoteBinding;

public class NoteView extends LinearLayout {

    private ViewNoteBinding binding;

    public NoteView(Context context) {
        super(context);
        init(context);
    }

    public NoteView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NoteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View root = inflater.inflate(R.layout.view_note, this, true);
        binding = ViewNoteBinding.bind(root);
    }

    public void update(Note note) {
        binding.tvTitle.setText(note.getTitle());
        binding.tvContent.setText(note.getContent());
    }

    public void setOnDeleteClickListener(OnClickListener listener) {
        binding.btnDelete.setOnClickListener(listener);
        binding.btnDelete.setVisibility(VISIBLE);
    }

    public void setOnEditClickListener(OnClickListener listener) {
        binding.btnEdit.setOnClickListener(listener);
        binding.btnEdit.setVisibility(VISIBLE);
    }
}
