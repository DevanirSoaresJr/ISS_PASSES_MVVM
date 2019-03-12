package devanir.soaresjunior.issmvvm.home;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import devanir.soaresjunior.issmvvm.R;
import devanir.soaresjunior.issmvvm.net.Response;

public class MainActivity extends AppCompatActivity {
    EditText etLatitude, etLongitude;
    Button btnSearch;
    RecyclerView rvData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etLatitude = findViewById(R.id.etLatitude);
        etLongitude = findViewById(R.id.etLongitude);
        btnSearch = findViewById(R.id.btnResult);

        rvData = findViewById(R.id.rvData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvData.setLayoutManager(linearLayoutManager);
        final ISSAdapter issAdapter = new ISSAdapter();
        rvData.setAdapter(issAdapter);

        final HomeViewModel homeViewModel = new HomeViewModel();
        homeViewModel.getPassesLiveData().observe(this, new Observer<List<Response>>() {
            @Override
            public void onChanged(@Nullable List<Response> responses) {
                issAdapter.setData(responses);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                homeViewModel.getPasses(etLatitude.getText().toString(),etLongitude.getText().toString());
            }
        });
    }
}
