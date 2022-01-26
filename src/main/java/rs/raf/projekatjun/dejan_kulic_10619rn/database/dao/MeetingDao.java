package rs.raf.projekatjun.dejan_kulic_10619rn.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import rs.raf.projekatjun.dejan_kulic_10619rn.database.entity.Meeting;

@Dao
public interface MeetingDao {

    @Delete
    void delete(Meeting meeting);

    @Insert
    public long insert(Meeting meeting);

    @Query("SELECT * FROM meetings")
    LiveData<List<Meeting>> getAllData();
}
