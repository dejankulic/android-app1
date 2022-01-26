package rs.raf.projekatjun.dejan_kulic_10619rn.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meetings")
public class Meeting {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;
    private String description;
    private String date;
    private String time;
    private String url;
    private String odabrano;

    public Meeting(String name, String description, String date, String time, String url, String odabrano) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.url = url;
        this.odabrano = odabrano;
    }

    public String getOdabrano() {
        return odabrano;
    }

    public void setOdabrano(String odabrano) {
        this.odabrano = odabrano;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
