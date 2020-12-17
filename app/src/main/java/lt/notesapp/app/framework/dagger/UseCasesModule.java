package lt.notesapp.app.framework.dagger;

import dagger.Module;
import dagger.Provides;
import lt.notesapp.core.repository.GroupRepository;
import lt.notesapp.core.repository.NoteRepository;
import lt.notesapp.core.repository.WebNoteRepository;
import lt.notesapp.core.usecases.GroupUseCases;
import lt.notesapp.core.usecases.NoteUseCases;
import lt.notesapp.core.usecases.WebNoteUseCases;

@Module(includes = NoteDaoModule.class)
public class UseCasesModule {

    @ComponentScope
    @Provides
    GroupUseCases getGroupUseCases(GroupRepository groupRepository) {
        return new GroupUseCases(groupRepository);
    }

    @ComponentScope
    @Provides
    NoteUseCases getNoteUseCases(NoteRepository noteRepository) {
        return new NoteUseCases(noteRepository);
    }

    @ComponentScope
    @Provides WebNoteUseCases getWebNoteUseCases(WebNoteRepository webNoteRepository) {
        return new WebNoteUseCases(webNoteRepository);
    }
}
