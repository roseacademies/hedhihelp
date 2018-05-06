package com.example.sarahpagnani.sdesignapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sarahpagnani.sdesignapp.ClinicResources.Clinic;

import com.example.sarahpagnani.sdesignapp.R;

/*
 * Created by SarekSoteloJimenez on 5/5/18.
 */

public class ClinicView extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clinic_info);

        Clinic selected = (Clinic) getIntent().getSerializableExtra("SelectedClinic");

        TextView mT1 = (TextView) findViewById(R.id.clinic_name);
        mT1.setText(selected.getName());

        TextView mT2 = (TextView) findViewById(R.id.clinic_address);
        mT2.setText(selected.getAddress());

        TextView mT3 = (TextView) findViewById(R.id.clinic_phone);
        mT3.setText(selected.getLatlong());

    }
}
