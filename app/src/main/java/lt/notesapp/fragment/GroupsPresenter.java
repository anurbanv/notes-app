package lt.notesapp.fragment;

import android.os.AsyncTask;
import android.os.Handler;

import java.util.List;

import lt.notesapp.activity.NotesActivity;
import lt.notesapp.dao.NoteDao;
import lt.notesapp.model.NoteGroup;

public class GroupsPresenter {

    private final NotesActivity notesActivity;
    private final NoteDao noteDao;
    private final Handler handler;
    private GroupsFragment groupsFragment;

    public GroupsPresenter(NotesActivity notesActivity, NoteDao noteDao, Handler handler) {
        this.notesActivity = notesActivity;
        this.noteDao = noteDao;
        this.handler = handler;
    }

    public void setGroupsFragment(GroupsFragment groupsFragment) {
        this.groupsFragment = groupsFragment;
    }

    public void onBackClick() {
        notesActivity.finish();
    }

    public void onAddGroupClick() {
        notesActivity.showAddEditGroupFragment();
        notesActivity.getAddEditGroupFragment().createGroup();
    }

    public void onEditGroupClick(NoteGroup noteGroup) {
        notesActivity.showAddEditGroupFragment();
        notesActivity.getAddEditGroupFragment().editGroup(noteGroup);
    }

    public void onDeleteGroupClick(NoteGroup noteGroup) {
        AsyncTask.execute(() -> {
            noteDao.deleteNoteGroup(noteGroup);
            updateGroupList();
        });
    }

    public void onGroupClick(NoteGroup noteGroup) {
        notesActivity.showNotesFragment();
        notesActivity.getNotesFragment().setNoteGroup(noteGroup);
        notesActivity.getNotesFragment().updateNotes();
    }

    public void updateGroupList() {
        AsyncTask.execute(() -> {
            List<NoteGroup> allGroups = noteDao.getAllGroups();
            handler.post(() -> groupsFragment.updateGroupList(allGroups));
        });
    }
}
