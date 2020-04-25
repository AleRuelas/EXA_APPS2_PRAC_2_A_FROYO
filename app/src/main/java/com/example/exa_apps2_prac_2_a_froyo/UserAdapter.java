package com.example.exa_apps2_prac_2_a_froyo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class UserAdapter extends ArrayAdapter<UserClass> {
    Context context;
    int resource;
    UserClass[] users;


    public UserAdapter( Context context, int resource,  UserClass[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.users = objects;
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        TextView txtUsuario, txtApellido, txtNombre;

        if (convertView == null){
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            convertView = layoutInflater.inflate(resource, parent, false);
        }
        txtUsuario = convertView.findViewById(R.id.txtUsuario);
        txtApellido = convertView.findViewById(R.id.txtApellido);
        txtNombre = convertView.findViewById(R.id.txtNombre);

        txtUsuario.setText(users[position].getUsuario());
        txtApellido.setText(users[position].getApellido());
        txtNombre.setText(users[position].getNombre());

        return convertView;
    }
}
