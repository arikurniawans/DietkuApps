package com.example.dietkuapps.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dietkuapps.Activity.DetailSenamActivity;
import com.example.dietkuapps.Kelas.ClassSenam;
import com.example.dietkuapps.Kelas.SharedVariabel;
import com.example.dietkuapps.R;

import java.util.List;

public class AdapterSenam extends RecyclerView.Adapter<AdapterSenam.MyViewHolder> {

    private Context mContext;
    private List<ClassSenam> senamList;
    public Button btn_detailal;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtIdSenam,txtNama;
        public CardView cv_main;
        public RelativeLayout relaList;


        public MyViewHolder(View view) {
            super(view);
            cv_main = view.findViewById(R.id.cardlist_itemsenam);
            relaList = view.findViewById(R.id.relaList);
            txtIdSenam = view.findViewById(R.id.txtIdSenam);
            txtNama = view.findViewById(R.id.txtNamaSenam);
            btn_detailal = view.findViewById(R.id.btn_detailsenam);
        }
    }

    public AdapterSenam(Context mContext, List<ClassSenam> senamList) {
        this.mContext = mContext;
        this.senamList = senamList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlist_senam, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (senamList.isEmpty()) {
            Toast.makeText(mContext.getApplicationContext(), "Belum ada data", Toast.LENGTH_LONG).show();
        } else {
            final ClassSenam senamClassku = senamList.get(position);

            holder.txtIdSenam.setText(senamClassku.getIdSenam());
            holder.txtNama.setText(senamClassku.getNamaSenam());

            btn_detailal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedVariabel.NamaSenam = senamClassku.getNamaSenam();
                    SharedVariabel.FotoSenam = senamClassku.getFotoSenam();
                    SharedVariabel.Deskripsi = senamClassku.getDeskripsi();
                    Intent intent = new Intent(mContext.getApplicationContext(), DetailSenamActivity.class);
                    intent.putExtra("id_senam",senamClassku.getIdSenam());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return senamList.size();
    }

}