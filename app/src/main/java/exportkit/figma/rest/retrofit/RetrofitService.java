package exportkit.figma.rest.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;

    public RetrofitService() {
        initializeRetrofit();
    }


    /**
     * Создание подключения к RESTFul приложению
     */
    public void initializeRetrofit() {
        this.retrofit = new Retrofit
                .Builder()
                .baseUrl("http://51.250.87.66:8080")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit() {
        return this.retrofit;
    }
}
