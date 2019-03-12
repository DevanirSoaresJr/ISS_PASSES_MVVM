package devanir.soaresjunior.issmvvm.data;

import java.util.Observer;

public interface DataSource {
    void getPasses(String latitude, String longitude);
    void setObserver(Observer observer);
}
