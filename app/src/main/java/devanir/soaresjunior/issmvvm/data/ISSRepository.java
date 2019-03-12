package devanir.soaresjunior.issmvvm.data;

import java.util.Observable;
import java.util.Observer;

public class ISSRepository extends Observable implements Observer, DataSource {
    private final DataSource remoteDataSource;
    private  final DataSource localDataSource;

    public ISSRepository(DataSource remoteDataSource, DataSource localDataSource){
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }


    @Override
    public void getPasses(String latitude, String longitude) {
        remoteDataSource.setObserver(this);
        remoteDataSource.getPasses(latitude,longitude);
    }


    @Override
    public void setObserver(Observer observer) {
        addObserver(observer);
    }

    @Override
    public void update(Observable o, Object result) {
        setChanged();
        notifyObservers(result);
    }
}
