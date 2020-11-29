package lt.notesapp;

import android.util.Log;

public final class Logger {

    public static final String TAG = "NotesApp";

    public static void debug(String message) {
        Log.d(TAG, message);
    }

    public static void debug(int message) {
        Log.d(TAG, String.valueOf(message));
    }
}
