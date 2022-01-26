package rs.raf.projekatjun.dejan_kulic_10619rn.retrofit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rs.raf.projekatjun.dejan_kulic_10619rn.models.CityTime;
import timber.log.Timber;

public class CityTimeService {

    private CityApi api;


    private MutableLiveData<CityTime> easternStandardTime = new MutableLiveData<>();

    private static final String BASE_URL = "http://worldtimeapi.org";

    public CityTimeService() {

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .readTimeout(60,TimeUnit.SECONDS);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(logging);

        OkHttpClient okHttpClient = clientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        api = retrofit.create(CityApi.class);
    }

    public LiveData<CityTime> getEasternStandardTime() {
        return easternStandardTime;
    }

    public void invokeCityService(String region, String city) {

        Call<CityTime> call = api.getCities(city);

        call.enqueue(new Callback<CityTime>() {
            @Override
            public void onResponse(
                    Call<CityTime> call,
                    Response<CityTime> response) {

                Timber.e("$$$$" + response.body());
                Timber.e("####");
                if (response.isSuccessful()) {
                    easternStandardTime.setValue(response.body());
                }
            }

            @Override
            public void onFailure(
                    Call<CityTime> call,
                    Throwable t) {

            }
        });
    }


}
