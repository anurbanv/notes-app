package lt.notesapp.dagger;

import dagger.Component;
import lt.notesapp.activity.NotesActivity;
import lt.notesapp.dao.NoteDao;
import lt.notesapp.fragment.AddEditGroupFragment;
import lt.notesapp.fragment.AddEditNoteFragment;
import lt.notesapp.fragment.GroupsFragment;
import lt.notesapp.fragment.NotesFragment;
import lt.notesapp.viewmodel.GroupsViewModel;
import lt.notesapp.viewmodel.NotesViewModel;

@Component(modules = {
        NoteDaoModule.class,
        ContextModule.class,
        RetrofitModule.class,
        AppDatabaseModule.class,
        NotesApiModule.class,
        FireStoreModule.class,
})
@ComponentScope
public interface AppComponent {

    void inject(NoteDao noteDao);

    void inject(NotesActivity notesActivity);

    void inject(GroupsFragment groupsFragment);

    void inject(AddEditGroupFragment addEditGroupFragment);

    void inject(NotesFragment notesFragment);

    void inject(AddEditNoteFragment addEditNoteFragment);

    void inject(GroupsViewModel groupsViewModel);

    void inject(NotesViewModel notesViewModel);
}
