package lt.notesapp.fragment;

import android.os.AsyncTask;

import lt.notesapp.activity.NotesActivity;
import lt.notesapp.dao.NoteDao;
import lt.notesapp.model.NoteGroup;

public class AddEditGroupPresenter {

    private final NotesActivity notesActivity;
    private final NoteDao noteDao;
    private AddEditGroupFragment addEditGroupFragment;

    public AddEditGroupPresenter(NotesActivity notesActivity, NoteDao noteDao) {
        this.notesActivity = notesActivity;
        this.noteDao = noteDao;
    }

    public void setAddEditGroupFragment(AddEditGroupFragment addEditGroupFragment) {
        this.addEditGroupFragment = addEditGroupFragment;
    }

    public void onBackClick() {
        notesActivity.showGroupsFragment();
        notesActivity.getGroupsPresenter().updateGroupList();
    }

    public void onGroupSubmit(NoteGroup noteGroup) {
        AsyncTask.execute(() -> {
            noteDao.insertOrUpdateGroup(noteGroup);
            notesActivity.showGroupsFragment();
            notesActivity.getGroupsPresenter().updateGroupList();
        });
    }

    public void createGroup() {
        addEditGroupFragment.createGroup();
    }

    public void editGroup(NoteGroup noteGroup) {
        addEditGroupFragment.editGroup(noteGroup);
    }
}
