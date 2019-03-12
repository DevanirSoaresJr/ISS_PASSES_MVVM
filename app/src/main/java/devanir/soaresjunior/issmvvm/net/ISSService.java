package devanir.soaresjunior.issmvvm.net;

import devanir.soaresjunior.issmvvm.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ISSService {

@GET(Constants.ENDPOINT)
    Call<ISSResponse>getISSPasses(@Query("lat") String latitude, @Query("lon") String longitude);
}
