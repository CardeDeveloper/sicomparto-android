package com.iteso.proyecto_pdm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iteso.proyecto_pdm.beans.ItemAsociacion;

import java.util.List;

public class AsociacionAdapter extends RecyclerView.Adapter<AsociacionAdapter.AsociacionViewHolder> {
    private List<ItemAsociacion> items;

    public static class AsociacionViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;
        public TextView visitas;

        public AsociacionViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.item_product_image);
            nombre = (TextView) v.findViewById(R.id.item_product_title);
            visitas = (TextView) v.findViewById(R.id.item_product_phone);
        }
    }

    public AsociacionAdapter(List<ItemAsociacion> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public AsociacionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.asociaciones, viewGroup, false);
        return new AsociacionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AsociacionViewHolder viewHolder, int i) {
        viewHolder.imagen.setImageResource(items.get(i).getImage());
        viewHolder.nombre.setText(items.get(i).getTitle());
        viewHolder.visitas.setText("Teléfono:"+String.valueOf(items.get(i).getPhone()));
    }
}
