package com.example.exa_apps2_prac_2_a_froyo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

//ADAPTADOR PARA LA LISTA DE ARCHIVOS
public class NotesAdapter extends ArrayAdapter<NotesClass> {

    Context context;
    int resource;
    ArrayList<NotesClass> notes;

    public NotesAdapter( Context context, int resource,  ArrayList<NotesClass> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.notes = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView txtNote;
        if (convertView == null){
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            convertView = layoutInflater.inflate(resource, parent, false);
        }
        txtNote = convertView.findViewById(R.id.txtNote);
        txtNote.setText(notes.get(position).getNote());
        return convertView;
    }
}
