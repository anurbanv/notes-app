package lt.notesapp.core.usecases;

import java.util.List;

import lt.notesapp.core.domain.NoteGroup;
import lt.notesapp.core.repository.GroupRepository;

public class GroupUseCases {

    private final GroupRepository groupRepository;

    public GroupUseCases(GroupRepository groupRepository){
        this.groupRepository = groupRepository;
    }

    public void addGroup(NoteGroup noteGroup) {
        groupRepository.insertGroup(noteGroup);
    }

    public void updateGroup(NoteGroup noteGroup) {
        groupRepository.updateGroup(noteGroup);
    }

    public void deleteGroup(NoteGroup noteGroup) {
        groupRepository.deleteGroup(noteGroup);
    }

    public List<NoteGroup> getAllGroups() {
        return groupRepository.selectAll();
    }
}
