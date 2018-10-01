package org.roseacademies.hedhihelp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.roseacademies.hedhihelp.ClinicResources.Clinic;

/*
 * Created by SarekSoteloJimenez on 5/5/18.
 */

public class ClinicsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {



    private Clinic[] clinics = new Clinic[11];
    private String[] clinicNames = new String[11];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinics);

        initClinics();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_clinics,R.id.textview, clinicNames);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    public void initClinics(){
        clinics[0] = new Clinic("AAR Healthcare Williamson Outpatient Centre",
                "2nd Floor Williamson House,  4th Ngong Avenue, Nairobi",
                "0709701000 (0-20), 0730655000 Ext 5052/3/1",
                "https://www.google.com/maps/search/2nd+Floor+Williamson+House,+4th+Ngong+Avenue,+Nairobi/");
        clinics[1] = new Clinic("AAR Healthcare Sarit Outpatient Centre",
                "4th Floor Sarit Centre, Westlands, Nairobi",
                "0202416720",
                "https://www.google.com/maps/search/AAR+Healthcare+Sarit+Center+4th+Floor+Nairobi+Kenya");
        clinics[2] = new Clinic("AAR Healthcare City Centre Outpatient Centre",
                "6th Floor ICEA Building, Kenyatta Avenue, Nairobi",
                "020 – 3318 924/31, 0202215762",
                "https://www.google.com/maps/search/AAR+Healthcare+6th+Floor+ICEA+Building,+Kenyatta+Avenue,+Nairobi/");
        clinics[3] = new Clinic("AAR Healthcare Roysambu Outpatient Centre",
                "Royal Plaza, Off Kamiti Road",
                "020 – 3318 924/31, 0202215762",
                "https://www.google.com/maps/search/AAR+Healthcare,+1st+Floor+Royal+Plaza+Building,+Off+Kamiti+Road+Roysambu+Kasarani,+Nairobi,+Kenya");
        clinics[4] = new Clinic("AAR Healthcare Greenhouse Outpatient Centre",
                "Greenhouse, 1st Floor Adams Arcade, Ngong Road",
                "0706 595 786",
                "https://www.google.com/maps/search/AAR+Healthcare,+1st+Floor+Green+House+Adams,+Ngong+Rd,+Nairobi,+Kenya/");
        clinics[5] = new Clinic("AAR Healthcare Karen/Lang'ata Outpatient Centre",
                "Great Jubilee Centre, Ground Floor, Off Lang’ata Road",
                "020 – 263 1852",
                "https://www.google.com/maps/search/AAR+Healthcare+Karen%2FLangata+Outpatient+Centre,+Langata+Road,+Opposite+Catholic+University/");
        clinics[6] = new Clinic("AAR Healthcare Mountain Mall Outpatient Centre",
                "1st Floor Mountain Mall, Thika Super Highway",
                "0771 399 302",
                "https://www.google.com/maps/place/AAR+Healthcare+Mountain+Mall+Outpatient+Centre/@-1.2328074,36.8716584,17z/data=!4m12!1m6!3m5!1s0x182f15dde48fcc9b:0xf5b52df8ba86b95!2sAAR+Healthcare+Mountain+Mall+Outpatient+Centre!8m2!3d-1.2328074!4d36.8738471!3m4!1s0x182f15dde48fcc9b:0xf5b52df8ba86b95!8m2!3d-1.2328074!4d36.8738471");
        clinics[7] = new Clinic("AAR Healthcare Embakasi Outpatient Centre",
                "Wanandege Sacco Plaza, Opposite Embakasi Police Station",
                "0733 888 259",
                "https://www.google.com/maps/place/AAR+Healthcare/@-1.3097474,36.9118445,17.5z/data=!4m8!1m2!2m1!1sAAR+Healthcare+!3m4!1s0x0:0x580f90de9e58ded9!8m2!3d-1.3094933!4d36.9123727");
        clinics[8] = new Clinic("AAR Healthcare Mombasa Outpatient Centre",
                "Pereira Building, Machakos Street, Off Moi Avenue",
                "041 – 222 9045",
                "https://www.google.com/maps/search/AAR+Healthcare+Mombasa+Outpatient+Centre/");
        clinics[9] = new Clinic("AAR Healthcare Kisumu Outpatient Centre",
                "1st Floor, Al Imran Plaza Oginga Odinga Road, Kisumu",
                "057 – 2024892",
                "https://www.google.com/maps/place/AAR+Healthcare+Kisumu+Outpatient+Centre/@-0.1023255,34.7500273,17z/data=!4m8!1m2!2m1!1sAAR+Healthcare+1st+Floor,+Al+Imran+Plaza+Oginga+Odinga+Road,+Kisumu!3m4!1s0x0:0x48bd23109b329d7d!8m2!3d-0.1000832!4d34.7510882");
        clinics[10] = new Clinic("","","","");

        for (int i = 0; i < 11; i++){
            clinicNames[i] = clinics[i].getName();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
        Intent intent = new Intent(this, ClinicView.class);
        intent.putExtra("SelectedClinic", clinics[position]);
        startActivity(intent);
    }


}
