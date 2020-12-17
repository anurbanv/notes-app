package lt.notesapp.app.framework.dagger;

import dagger.Module;
import dagger.Provides;
import lt.notesapp.app.framework.rest.NotesApi;
import retrofit2.Retrofit;

@Module(includes = RetrofitModule.class)
public class NotesApiModule {

    @ComponentScope
    @Provides
    NotesApi getNotesApi(Retrofit retrofit) {
        return retrofit.create(NotesApi.class);
    }
}
