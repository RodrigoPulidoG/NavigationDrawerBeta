package com.example.rodpro.navigationdrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<String> names;

    public MyAdapter (Context context, int layout, List<String> names){
        this.context = context;
        this.layout = layout;
        this.names = names;
    }
    @Override
    public int getCount() {return this.names.size();}

    @Override
    public Object getItem(int position) {return this.names.get(position);}

    @Override
    public long getItemId(int id) {return id;}

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        //View Holder Pattern
        ViewHolder holder;

        if (convertView == null) {
            //Inflamos la vista que llega del layout personalizado
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(layout, null);

            holder = new ViewHolder();
            holder.nameTextview = convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        //Trae el nombre actual depediendo de la posicion
        String currentName = names.get(position);
        //currentName = (String) getItem(position);

        //Referencia el elemento a modificar y lo rellena
        holder.nameTextview.setText(currentName);

         //Debuelve la vita inflada y modificada con los datos
         return convertView;

    }

    static class ViewHolder{
        private TextView nameTextview;
    }
}
