package rs.raf.projekatjun.dejan_kulic_10619rn.models;

public class CityTime {

    private String abbreviation;
    private String client_ip;
    private String datetime;
    private Integer day_of_week;
    private Integer day_of_year;
    private boolean dst;
    private String dst_from;
    private String dst_offset;
    private String dst_untill;
    private String raw_offset;
    private String timezone;
    private String unixtime;
    private String utc_datetime;
    private String utc_offset;
    private Integer week_number;

    @Override
    public String toString() {
        return "CityTime{" +
                "abbreviation='" + abbreviation + '\'' +
                ", client_ip='" + client_ip + '\'' +
                ", datetime='" + datetime + '\'' +
                ", day_of_week=" + day_of_week +
                ", day_of_year=" + day_of_year +
                ", dst=" + dst +
                ", dst_from='" + dst_from + '\'' +
                ", dst_offset='" + dst_offset + '\'' +
                ", dst_untill='" + dst_untill + '\'' +
                ", raw_offset='" + raw_offset + '\'' +
                ", timezone='" + timezone + '\'' +
                ", unixtime='" + unixtime + '\'' +
                ", utc_datetime='" + utc_datetime + '\'' +
                ", utc_offset='" + utc_offset + '\'' +
                ", week_number=" + week_number +
                '}';
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public String getDatetime() {
        return datetime;
    }

    public Integer getDay_of_week() {
        return day_of_week;
    }

    public Integer getDay_of_year() {
        return day_of_year;
    }

    public boolean isDst() {
        return dst;
    }

    public String getDst_from() {
        return dst_from;
    }

    public String getDst_offset() {
        return dst_offset;
    }

    public String getDst_untill() {
        return dst_untill;
    }

    public String getRaw_offset() {
        return raw_offset;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getUnixtime() {
        return unixtime;
    }

    public String getUtc_datetime() {
        return utc_datetime;
    }

    public String getUtc_offset() {
        return utc_offset;
    }

    public Integer getWeek_number() {
        return week_number;
    }
}
