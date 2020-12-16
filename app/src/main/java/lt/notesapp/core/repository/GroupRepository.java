package lt.notesapp.core.repository;

import java.util.List;

import lt.notesapp.core.domain.NoteGroup;

public interface GroupRepository {

    void insertGroup(NoteGroup noteGroup);

    void updateGroup(NoteGroup noteGroup);

    void deleteGroup(NoteGroup noteGroup);

    List<NoteGroup> selectAll();
}
