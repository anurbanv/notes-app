package lt.notesapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import lt.notesapp.R;
import lt.notesapp.databinding.ViewNoteGroupEditBinding;
import lt.notesapp.events.OnGroupSubmitListener;
import lt.notesapp.model.NoteGroup;

public class NoteGroupEditView extends LinearLayout {

    private ViewNoteGroupEditBinding binding;
    private OnGroupSubmitListener onGroupSubmitListener;
    private NoteGroup noteGroup;

    public NoteGroupEditView(Context context) {
        super(context);
        init(context);
    }

    public NoteGroupEditView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NoteGroupEditView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_note_group_edit, this, true);
        binding = ViewNoteGroupEditBinding.bind(view);
        binding.btnSubmit.setOnClickListener(v -> {
            if (onGroupSubmitListener != null) {
                noteGroup.setTitle(binding.etTitle.getText().toString());
                onGroupSubmitListener.onGroupSubmit(noteGroup);
            }
        });
    }

    public void setOnGroupSubmitListener(OnGroupSubmitListener onGroupSubmitListener) {
        this.onGroupSubmitListener = onGroupSubmitListener;
    }

    public void newGroup() {
        noteGroup = new NoteGroup();
        binding.tvTitle.setText("New group");
        binding.etTitle.setText("");
        binding.btnSubmit.setText("Create");
    }

    public void editGroup(NoteGroup noteGroup) {
        this.noteGroup = noteGroup;
        binding.tvTitle.setText(noteGroup.getTitle());
        binding.etTitle.setText(noteGroup.getTitle());
        binding.btnSubmit.setText("Edit");
    }
}
