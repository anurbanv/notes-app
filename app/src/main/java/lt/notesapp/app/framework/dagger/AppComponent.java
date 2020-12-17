package lt.notesapp.app.framework.dagger;

import dagger.Component;
import lt.notesapp.app.presentation.view.activity.NotesActivity;
import lt.notesapp.app.presentation.view.fragment.AddEditGroupFragment;
import lt.notesapp.app.presentation.view.fragment.AddEditNoteFragment;
import lt.notesapp.app.presentation.view.fragment.GroupsFragment;
import lt.notesapp.app.presentation.view.fragment.NotesFragment;
import lt.notesapp.app.presentation.viewmodel.GroupsViewModel;
import lt.notesapp.app.presentation.viewmodel.NotesViewModel;

@Component(modules = {
        NoteDaoModule.class,
        ContextModule.class,
        RetrofitModule.class,
        AppDatabaseModule.class,
        NotesApiModule.class,
        FireStoreModule.class,
        UseCasesModule.class,
})
@ComponentScope
public interface AppComponent {

    void inject(NotesActivity notesActivity);

    void inject(GroupsFragment groupsFragment);

    void inject(AddEditGroupFragment addEditGroupFragment);

    void inject(NotesFragment notesFragment);

    void inject(AddEditNoteFragment addEditNoteFragment);

    void inject(GroupsViewModel groupsViewModel);

    void inject(NotesViewModel notesViewModel);
}
