package lt.notesapp.app.presentation.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import lt.notesapp.app.presentation.view.activity.NotesActivity;
import lt.notesapp.app.presentation.viewmodel.NotesViewModel;
import lt.notesapp.databinding.FragmentNotesBinding;

public class WebNotesFragment extends Fragment {

    private FragmentNotesBinding binding;
    private NotesViewModel notesViewModel;
    private final NotesActivity notesActivity;

    public WebNotesFragment(NotesActivity notesActivity) {
        this.notesActivity = notesActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNotesBinding.inflate(inflater, container, false);

        notesViewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);

        binding.btnAdd.setVisibility(View.GONE);

        binding.btnBack.setOnClickListener(v -> notesActivity.showGroupsFragment());

        binding.tvTitle.setText("Web Notes");

        notesViewModel.getNotesFromWebService(notes ->
                requireActivity().runOnUiThread(() -> binding.noteList.update(notes)));

        return binding.getRoot();
    }
}
