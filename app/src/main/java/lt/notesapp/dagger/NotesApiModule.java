package lt.notesapp.dagger;

import dagger.Module;
import dagger.Provides;
import lt.notesapp.rest.NotesApi;
import retrofit2.Retrofit;

@Module(includes = RetrofitModule.class)
public class NotesApiModule {

    @Provides
    NotesApi getNotesApi(Retrofit retrofit) {
        return retrofit.create(NotesApi.class);
    }
}
