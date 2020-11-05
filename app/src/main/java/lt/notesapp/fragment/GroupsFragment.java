package lt.notesapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import lt.notesapp.databinding.FragmentGroupsBinding;
import lt.notesapp.events.OnGroupClickListener;
import lt.notesapp.events.OnGroupDeleteListener;
import lt.notesapp.events.OnGroupEditListener;
import lt.notesapp.model.NoteGroup;

public class GroupsFragment extends Fragment {

    private FragmentGroupsBinding binding;
    private View.OnClickListener onBackListener;
    private View.OnClickListener onAddGroupListener;
    private OnGroupEditListener onGroupEditListener;
    private OnGroupDeleteListener onGroupDeleteListener;
    private OnGroupClickListener onGroupClickListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentGroupsBinding.inflate(inflater);
        binding.btnBack.setOnClickListener(onBackListener);
        binding.btnAdd.setOnClickListener(onAddGroupListener);
        binding.groupList.setOnEditClickListener(onGroupEditListener);
        binding.groupList.setOnDeleteClickListener(onGroupDeleteListener);
        binding.groupList.setOnItemClickListener(onGroupClickListener);
        return binding.getRoot();
    }

    public void updateGroupList(List<NoteGroup> noteGroups) {
        binding.groupList.update(noteGroups);
    }

    public void setOnBackListener(View.OnClickListener onBackListener) {
        this.onBackListener = onBackListener;
    }

    public void setOnAddGroupListener(View.OnClickListener onAddGroupListener) {
        this.onAddGroupListener = onAddGroupListener;
    }

    public void setOnGroupEditListener(OnGroupEditListener onGroupEditListener) {
        this.onGroupEditListener = onGroupEditListener;
    }

    public void setOnGroupDeleteListener(OnGroupDeleteListener onGroupDeleteListener) {
        this.onGroupDeleteListener = onGroupDeleteListener;
    }

    public void setOnGroupClickListener(OnGroupClickListener onGroupClickListener) {
        this.onGroupClickListener = onGroupClickListener;
    }
}
