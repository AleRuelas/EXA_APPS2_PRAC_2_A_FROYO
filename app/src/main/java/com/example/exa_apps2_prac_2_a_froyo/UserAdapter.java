package com.example.exa_apps2_prac_2_a_froyo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    Context context;
    ArrayList<UserClass> list;

    public UserAdapter(Context context, ArrayList<UserClass> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public View getView(int position, View convertView,  ViewGroup parent) {
        TextView txtUsuario,txtApellido, txtNombre;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.layout_user, null);

        txtApellido = itemView.findViewById(R.id.txtApellido);
        txtNombre = itemView.findViewById(R.id.txtNombre);
        txtUsuario = itemView.findViewById(R.id.txtUsuario);

        String apellido = list.get(position).getApellido();
        String nombre = list.get(position).getNombre();
        String usuario = list.get(position).getUsuario();

        txtApellido.setText("Apellido: "+apellido);
        txtNombre.setText("Nombre: "+nombre);
        txtUsuario.setText("Usuario: "+usuario);

        return itemView;
    }
}
