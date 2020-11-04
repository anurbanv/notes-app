package lt.notesapp.dagger;

import dagger.Component;
import lt.notesapp.activity.AddEditGroupActivity;
import lt.notesapp.activity.AddEditNoteActivity;
import lt.notesapp.activity.GroupsActivity;
import lt.notesapp.activity.NoteListActivity;
import lt.notesapp.dao.NoteDao;

@Component(modules = {
        NoteDaoModule.class,
        ContextModule.class,
        RetrofitModule.class,
        AppDatabaseModule.class,
        NotesApiModule.class,
        FireStoreModule.class,
})
public interface AppComponent {

    void inject(AddEditGroupActivity addEditGroupActivity);

    void inject(NoteDao noteDao);

    void inject(AddEditNoteActivity addEditNoteActivity);

    void inject(GroupsActivity groupsActivity);

    void inject(NoteListActivity noteListActivity);
}
