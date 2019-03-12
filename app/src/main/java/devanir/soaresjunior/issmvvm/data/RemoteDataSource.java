package devanir.soaresjunior.issmvvm.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import devanir.soaresjunior.issmvvm.Constants;
import devanir.soaresjunior.issmvvm.net.ISSResponse;
import devanir.soaresjunior.issmvvm.net.ISSService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource extends Observable implements DataSource {

    private final ISSService issService;

    public RemoteDataSource() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        issService = retrofit.create(ISSService.class);

    }

    @Override
    public void getPasses(String latitude, String longitude) {
        final List<devanir.soaresjunior.issmvvm.net.Response> passes = new ArrayList<>();
        issService.getISSPasses(latitude, longitude)
                .enqueue(new Callback<ISSResponse>() {
                    @Override
                    public void onResponse(Call<ISSResponse> call, Response<ISSResponse> response) {
                        if (response.isSuccessful()&& response.body().getResponse() != null){
                            passes.clear();
                            passes.addAll(response.body().getResponse());
                            setChanged();
                            notifyObservers(passes);
                        }
                    }

                    @Override
                    public void onFailure(Call<ISSResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

                    @Override
                    public void setObserver(Observer observer) {

                    }
                }
