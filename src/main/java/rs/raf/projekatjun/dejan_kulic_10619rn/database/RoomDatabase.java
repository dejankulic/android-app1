package rs.raf.projekatjun.dejan_kulic_10619rn.database;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;

import rs.raf.projekatjun.dejan_kulic_10619rn.database.dao.MeetingDao;
import rs.raf.projekatjun.dejan_kulic_10619rn.database.entity.Meeting;

@Database(version = 2,
        entities = {
                Meeting.class,
        },
        exportSchema = false
)

public abstract class RoomDatabase extends androidx.room.RoomDatabase {
    private static RoomDatabase singleton;
    public abstract MeetingDao meetingDao();

    public static RoomDatabase getDatabase(final Context context) {
        if (singleton == null) {
            synchronized (RoomDatabase.class) {
                if (singleton == null) {
                    singleton = Room.databaseBuilder(
                            context.getApplicationContext(),
                            RoomDatabase.class,
                            "my_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return singleton;
    }

    private static androidx.room.RoomDatabase.Callback callback =
            new androidx.room.RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDatabaseAsync(singleton).execute();
                }
            };

    private static class PopulateDatabaseAsync extends AsyncTask<Void, Void, Void> {

        private final MeetingDao meetingDao;

        PopulateDatabaseAsync(RoomDatabase myRoomDatabase) {
            meetingDao = myRoomDatabase.meetingDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
//            long person1 =
//                    meetingDao.insert(new Meeting("Maja","Test","test","test","test"));
            return null;
        }
    }


}
