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
import lt.notesapp.model.NoteGroup;

public class GroupsFragment extends Fragment {

    private FragmentGroupsBinding binding;
    private final GroupsPresenter presenter;

    public GroupsFragment(GroupsPresenter groupsPresenter) {
        groupsPresenter.setGroupsFragment(this);
        presenter = groupsPresenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentGroupsBinding.inflate(inflater);
        binding.btnBack.setOnClickListener(v -> presenter.onBackClick());
        binding.btnAdd.setOnClickListener(v -> presenter.onAddGroupClick());
        binding.groupList.setOnEditClickListener(presenter::onEditGroupClick);
        binding.groupList.setOnDeleteClickListener(presenter::onDeleteGroupClick);
        binding.groupList.setOnItemClickListener(presenter::onGroupClick);
        return binding.getRoot();
    }

    public void updateGroupList(List<NoteGroup> groups) {
        binding.groupList.update(groups);
    }
}
