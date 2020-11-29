package lt.notesapp.dagger;

import com.google.firebase.firestore.FirebaseFirestore;

import dagger.Module;
import dagger.Provides;

@Module
public class FireStoreModule {

    @ComponentScope
    @Provides
    FirebaseFirestore getFirebaseFireStore() {
        return FirebaseFirestore.getInstance();
    }
}
