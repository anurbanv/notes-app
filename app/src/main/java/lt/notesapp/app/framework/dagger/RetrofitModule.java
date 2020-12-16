package lt.notesapp.app.framework.dagger;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    @ComponentScope
    @Provides
    Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/anurbanv/notes-app-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
