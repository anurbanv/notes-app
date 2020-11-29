package lt.notesapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import lt.notesapp.databinding.FragmentAddEditGroupBinding;
import lt.notesapp.model.NoteGroup;

public class AddEditGroupFragment extends Fragment {

    private FragmentAddEditGroupBinding binding;
    private final AddEditGroupPresenter presenter;

    public AddEditGroupFragment(AddEditGroupPresenter presenter) {
        presenter.setAddEditGroupFragment(this);
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddEditGroupBinding.inflate(inflater);
        binding.btnBack.setOnClickListener(v -> presenter.onBackClick());
        binding.vEditGroup.setOnGroupSubmitListener(presenter::onGroupSubmit);
        return binding.getRoot();
    }

    public void createGroup() {
        binding.vEditGroup.newGroup();
    }

    public void editGroup(NoteGroup noteGroup) {
        binding.vEditGroup.editGroup(noteGroup);
    }
}
