package lt.notesapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import lt.notesapp.activity.NotesActivity;
import lt.notesapp.databinding.FragmentAddEditGroupBinding;
import lt.notesapp.events.OnGroupSubmitListener;
import lt.notesapp.model.NoteGroup;

public class AddEditGroupFragment extends Fragment {

    private FragmentAddEditGroupBinding binding;
    private View.OnClickListener onBackListener;
    private OnGroupSubmitListener onGroupSubmitListener;
    private NotesActivity notesActivity;

    public AddEditGroupFragment(NotesActivity notesActivity) {
        this.notesActivity = notesActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddEditGroupBinding.inflate(inflater);
        binding.btnBack.setOnClickListener(onBackListener);
        binding.vEditGroup.setOnGroupSubmitListener(onGroupSubmitListener);
        return binding.getRoot();
    }

    public void createGroup() {
        binding.vEditGroup.newGroup();
    }

    public void editGroup(NoteGroup noteGroup) {
        binding.vEditGroup.editGroup(noteGroup);
    }

    public void setOnBackListener(View.OnClickListener onBackListener) {
        this.onBackListener = onBackListener;
    }

    public void setOnGroupSubmitListener(OnGroupSubmitListener onGroupSubmitListener) {
        this.onGroupSubmitListener = onGroupSubmitListener;
    }
}
