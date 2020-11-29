package lt.notesapp.dagger;

import dagger.Component;
import lt.notesapp.activity.NotesActivity;
import lt.notesapp.dao.NoteDao;
import lt.notesapp.fragment.AddEditGroupFragment;
import lt.notesapp.fragment.GroupsFragment;

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
}
