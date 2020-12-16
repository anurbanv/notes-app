package lt.notesapp.app.presentation.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import lt.notesapp.R;
import lt.notesapp.app.presentation.view.events.OnNoteSubmitListener;
import lt.notesapp.core.domain.Note;
import lt.notesapp.core.domain.NoteGroup;
import lt.notesapp.databinding.ViewNoteEditBinding;

public class NoteEditView extends LinearLayout {

    private ViewNoteEditBinding binding;
    private OnNoteSubmitListener onNoteSubmitListener;
    private Note note;

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
            if (onNoteSubmitListener != null) {
                String title = binding.etTitle.getText().toString();
                String content = binding.etContent.getText().toString();
                note.setTitle(title);
                note.setContent(content);
                onNoteSubmitListener.onNoteSubmit(note);
            }
        });
    }

    public void setOnNoteSubmitListener(OnNoteSubmitListener onNoteSubmitListener) {
        this.onNoteSubmitListener = onNoteSubmitListener;
    }

    public void newNote(NoteGroup noteGroup) {
        note = new Note(noteGroup);
        binding.tvTitle.setText("New note");
        binding.etTitle.setText("");
        binding.etContent.setText("");
        binding.btnSubmit.setText("Create");
    }

    public void editNote(Note note) {
        this.note = note;
        binding.tvTitle.setText(note.getTitle());
        binding.etTitle.setText(note.getTitle());
        binding.etContent.setText(note.getContent());
        binding.btnSubmit.setText("Edit");
    }
}
