package devanir.soaresjunior.issmvvm.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import devanir.soaresjunior.issmvvm.R;
import devanir.soaresjunior.issmvvm.net.Response;

public class ISSAdapter extends RecyclerView.Adapter<ISSAdapter.vHolder>{

    private  List<Response> responses= new ArrayList<>();

    public void setData(List<Response> info){
        responses.clear();
        responses.addAll(info);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public vHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.iss_recycler,viewGroup,false);
        return new vHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull vHolder itemViewHolder, int position) {
        Response response = responses.get(position);
        vHolder.tvDuration.setText(response.getDuration().toString());
        vHolder.tvRiseTime.setText(response.getRisetime().toString());


    }

    @Override
    public int getItemCount() {
        return responses.size();
    }

    static class vHolder extends RecyclerView.ViewHolder {
        static TextView tvDuration;
        static TextView tvRiseTime;
        public vHolder(@NonNull View itemView) {
            super(itemView);
            tvDuration = itemView.findViewById(R.id.tvDuration);
            tvRiseTime= itemView.findViewById(R.id.tvRiseTime);

        }

    }
}
