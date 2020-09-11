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

import com.example.dietkuapps.Activity.DetailDietActivity;
import com.example.dietkuapps.Activity.DetailSenamActivity;
import com.example.dietkuapps.Kelas.ClassDiet;
import com.example.dietkuapps.Kelas.ClassSenam;
import com.example.dietkuapps.Kelas.SharedVariabel;
import com.example.dietkuapps.R;

import java.util.List;

public class AdapterDiet extends RecyclerView.Adapter<AdapterDiet.MyViewHolder> {

    private Context mContext;
    private List<ClassDiet> dietList;
    public Button btn_detailal;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtIdDiet,txtNamaDiet;
        public CardView cv_main;
        public RelativeLayout relaList;


        public MyViewHolder(View view) {
            super(view);
            cv_main = view.findViewById(R.id.cardlist_itemdiet);
            relaList = view.findViewById(R.id.relaList);
            txtIdDiet = view.findViewById(R.id.txtIdDiet);
            txtNamaDiet = view.findViewById(R.id.txtNamaDiet);
            btn_detailal = view.findViewById(R.id.btn_detaildiet);
        }
    }

    public AdapterDiet(Context mContext, List<ClassDiet> dietList) {
        this.mContext = mContext;
        this.dietList = dietList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlist_diet, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (dietList.isEmpty()) {
            Toast.makeText(mContext.getApplicationContext(), "Belum ada data", Toast.LENGTH_LONG).show();
        } else {
            final ClassDiet dietClassku = dietList.get(position);

            holder.txtIdDiet.setText(dietClassku.getIdDiet());
            holder.txtNamaDiet.setText(dietClassku.getNamaDiet());

            btn_detailal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedVariabel.NamaDiet = dietClassku.getNamaDiet();
                    SharedVariabel.Panduan = dietClassku.getPanduan();
                    SharedVariabel.Manfaat = dietClassku.getManfaat();
                    SharedVariabel.Makanan = dietClassku.getMakanan();
                    Intent intent = new Intent(mContext.getApplicationContext(), DetailDietActivity.class);
                    intent.putExtra("id_diet",dietClassku.getIdDiet());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return dietList.size();
    }

}
