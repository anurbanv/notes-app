package lt.notesapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import lt.notesapp.R;
import lt.notesapp.databinding.ViewNoteEditBinding;
import lt.notesapp.model.Note;

public class NoteEditView extends LinearLayout {

    public interface OnSubmitListener {
        void onSubmit(Note note);
    }

    private ViewNoteEditBinding binding;
    private OnSubmitListener onSubmitListener;

    public NoteEditView(Context context) {
        super(context);
        init(context);
    }

    public NoteEditView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NoteEditView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_note_edit, this, true);
        binding = ViewNoteEditBinding.bind(view);

        binding.btnSubmit.setOnClickListener(v -> {
            if (onSubmitListener != null) {
                String title = binding.etTitle.getText().toString();
                String content = binding.etContent.getText().toString();
                Note note = new Note(title, content);
                onSubmitListener.onSubmit(note);
            }
        });
    }

    public void setOnSubmitListener(OnSubmitListener onSubmitListener) {
        this.onSubmitListener = onSubmitListener;
    }

    public void newNote() {
        binding.tvTitle.setText("New note");
        binding.btnSubmit.setText("Create");
    }

    public void editNote(Note note) {
        binding.tvTitle.setText(note.getTitle());
        binding.etTitle.setText(note.getTitle());
        binding.etContent.setText(note.getContent());
        binding.btnSubmit.setText("Edit");
    }
}
