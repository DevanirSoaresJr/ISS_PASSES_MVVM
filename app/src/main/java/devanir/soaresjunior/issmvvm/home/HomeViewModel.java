package devanir.soaresjunior.issmvvm.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import devanir.soaresjunior.issmvvm.net.Response;
import devanir.soaresjunior.issmvvm.data.DataSource;
import devanir.soaresjunior.issmvvm.data.ISSRepository;
import devanir.soaresjunior.issmvvm.data.LocalDataSource;
import devanir.soaresjunior.issmvvm.data.RemoteDataSource;

public class HomeViewModel implements Observer {
    private final DataSource issRepository;

    private final MutableLiveData<List<Response>> responseLiveData = new MutableLiveData<>();
    public HomeViewModel(){
        issRepository = new ISSRepository(new RemoteDataSource(), new LocalDataSource());
    }
    public LiveData<List<Response>> getPassesLiveData(){
        return responseLiveData;
    }

    public void getPasses(String latitude, String longitude){
        issRepository.setObserver(this);
        issRepository.getPasses(latitude,longitude);
    }
    @Override
    public void update(Observable o, Object result) {
        List<Response> responses = (List<Response>) result;
        responseLiveData.setValue(responses);
    }
}
