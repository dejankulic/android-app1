package rs.raf.projekatjun.dejan_kulic_10619rn.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rs.raf.projekatjun.dejan_kulic_10619rn.models.CityTime;

public interface CityApi {

    @GET("/api/timezone/Europe/{city}")
    Call<CityTime> getCities(@Path("city") String city);
}
