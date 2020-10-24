package lt.notesapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import lt.notesapp.R;
import lt.notesapp.databinding.ViewNoteGroupEditBinding;
import lt.notesapp.model.NoteGroup;

public class NoteGroupEditView extends LinearLayout {

    public interface OnSubmitListener {
        void onSubmit(NoteGroup noteGroup);
    }

    private ViewNoteGroupEditBinding binding;
    private OnSubmitListener onSubmitListener;

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
            if (onSubmitListener != null) {
                String title = binding.etTitle.getText().toString();
                NoteGroup noteGroup = new NoteGroup(title);
                onSubmitListener.onSubmit(noteGroup);
            }
        });
    }

    public void setOnSubmitListener(OnSubmitListener onSubmitListener) {
        this.onSubmitListener = onSubmitListener;
    }

    public void newGroup() {
        binding.tvTitle.setText("New group");
        binding.btnSubmit.setText("Create");
    }

    public void editGroup(NoteGroup noteGroup) {
        binding.tvTitle.setText(noteGroup.getTitle());
        binding.etTitle.setText(noteGroup.getTitle());
        binding.btnSubmit.setText("Edit");
    }
}
