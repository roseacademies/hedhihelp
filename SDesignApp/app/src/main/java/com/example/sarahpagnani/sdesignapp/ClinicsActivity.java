package com.example.sarahpagnani.sdesignapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sarahpagnani.sdesignapp.ClinicResources.Clinic;

/*
 * Created by SarekSoteloJimenez on 5/5/18.
 */

public class ClinicsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {



    private Clinic[] clinics = new Clinic[10];
    private String[] clinicNames = new String[10];

    private String[] test = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinics);

        initClinics();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_clinics,R.id.textview, clinicNames);
        //ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_clinics, test);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    public void initClinics(){
        clinics[0] = new Clinic("AAR Healthcare Williamson Outpatient Centre",
                "2nd Floor Williamson House,  4th Ngong Avenue, Nairobi",
                "0709701000 (0-20), 0730655000 Ext 5052/3/1",
                "Hi");
        clinics[1] = new Clinic("AAR Healthcare Sarit Outpatient Centre",
                "4th Floor Sarit Centre, Westlands, Nairobi",
                "0202416720",
                "Hi");
        clinics[2] = new Clinic("AAR Healthcare City Centre Outpatient Centre",
                "6th Floor ICEA Building, Kenyatta Avenue, Nairobi",
                "020 – 3318 924/31, 0202215762",
                "Hi");
        clinics[3] = new Clinic("AAR Healthcare Roysambu Outpatient Centre",
                "Royal Plaza, Off Kamiti Road",
                "020 – 3318 924/31, 0202215762",
                "Hi");
        clinics[4] = new Clinic("AAR Healthcare Greenhouse Outpatient Centre",
                "Greenhouse, 1st Floor Adams Arcade, Ngong Road",
                "0706 595 786",
                "Hi");
        clinics[5] = new Clinic("AAR Healthcare Karen/Lang'ata Outpatient Centre",
                "Great Jubilee Centre, Ground Floor, Off Lang’ata Road",
                "020 – 263 1852",
                "Hi");
        clinics[6] = new Clinic("AAR Healthcare Mountain Mall Outpatient Centre",
                "1st Floor Mountain Mall, Thika Super Highway",
                "0771 399 302",
                "Hi");
        clinics[7] = new Clinic("AAR Healthcare Embakasi Outpatient Centre",
                "Wanandege Sacco Plaza, Opposite Embakasi Police Station",
                "0733 888 259",
                "Hi");
        clinics[8] = new Clinic("AAR Healthcare Mombasa Outpatient Centre",
                "Pereira Building, Machakos Street, Off Moi Avenue",
                "041 – 222 9045",
                "Hi");
        clinics[9] = new Clinic("AAR Healthcare Kisumu Outpatient Centre",
                "1st Floor, Al Imran Plaza Oginga Odinga Road, Kisumu",
                "057 – 2024892",
                "Hi");

        for (int i = 0; i < 10; i++){
            clinicNames[i] = clinics[i].getName();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
        Intent intent = new Intent(this, ClinicView.class);
        intent.putExtra("SelectedClinic", clinics[position]);
        startActivity(intent);
    }


}
