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



    private Clinic[] clinics = new Clinic[32];
    private String[] clinicNames = new String[32];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinics);

        initClinics();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_clinics,R.id.textview, clinicNames);

        ListView listView = findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    public void initClinics(){
        clinics[0] = new Clinic("CURE Children’s Hospital of Uganda",
                "97 – 105 Bugwere Road 903, Mbale Mbale",
                "045-4435273, 045-4340645",
                "https://www.google.com/maps/place/CURE+Children's+Hospital+of+Uganda/@1.0743847,34.1694529,17z/data=!3m1!4b1!4m5!3m4!1s0x1778b60cdc7e2661:0x63b4a175ae00e53d!8m2!3d1.0743847!4d34.1716416");
        clinics[1] = new Clinic("Kololo Hospital",
                "16 Kawalya Kaggwa Close Kololo 71997, Kampala Kampala",
                "041-4342435, 031-2264702",
                "https://www.google.com/maps/place/Kololo+Hospital/@0.3340316,32.5868098,17z/data=!3m1!4b1!4m5!3m4!1s0x177dbba3cefe7fbd:0xc81bd9581aa35dee!8m2!3d0.3340316!4d32.5889985");
        clinics[2] = new Clinic("Karoli Lwanga Hospital Nyakibale",
                "Nyakibale Road 31, Rukungiri Rukungiri",
                "0486-442020, 0486-442555",
                "https://www.google.com/maps/place/Karoli+Lwanga+Hospital+Nyakibale/@-0.7875469,29.9242043,17z/data=!3m1!4b1!4m5!3m4!1s0x19deb880928059a1:0x9b6723dfd90a8312!8m2!3d-0.7875469!4d29.926393");
        clinics[3] = new Clinic("Norvik Hospital & Research Centre ltd.",
                "13 Bombo Road Kampala Kampala",
                "041-4346772/3/4",
                "https://www.google.com/maps/place/Norvik+Hospital/@0.3232501,32.5722907,17z/data=!4m8!1m2!2m1!1sNorvik+Hospital+%26+Research+Centre+ltd.!3m4!1s0x177dbb7a64a420f7:0xe54c172c785ad8d4!8m2!3d0.3232501!4d32.5744794");
        clinics[4] = new Clinic("International Hospital – Kampala(IHK)",
                "4686 Namuwongo 8177, Kampala Kampala",
                "041-4340531, 031-2200400/505",
                "https://www.google.com/maps/place/International+Hospital+Kampala/@0.3047931,32.6090335,17z/data=!3m1!4b1!4m5!3m4!1s0x177dbc13d8dfbc29:0x22822ee37951b2a4!8m2!3d0.3047931!4d32.6112222");
        clinics[5] = new Clinic("The Surgery (Dr. Stockley)",
                "2 John Babiiha Avenue (Former Acacia) Kololo 24100,Kampala Kampala",
                "041-4256003, 077-2756003, 075-2756004",
                "https://www.google.com/maps/place/The+Surgery+Uganda/@0.341169,32.5996833,17z/data=!3m1!4b1!4m5!3m4!1s0x177dbbb706377ddf:0x58e028a79f5f676!8m2!3d0.341169!4d32.601872");
        clinics[6] = new Clinic("Kapchorwa Hospital",
                "16,Kapchorwa Kapchorwa",
                "045-4451016",
                "https://www.google.com/maps/place/Kapchorwa+Hospital/@1.4029105,34.4421387,18z/data=!4m8!1m2!2m1!1sKapchorwa+Hospital!3m4!1s0x177883135283517d:0x9f1dfb7f99e2998c!8m2!3d1.4029105!4d34.4421387");
        clinics[7] = new Clinic("Kadic Hospital",
                "Bukoto 7470,Kampala Kampala",
                "077-2777811, 071-1777811, 077-2777913",
                "https://www.google.com/maps/place/Kadic+Clinic/@0.3272009,32.5612557,17z/data=!3m1!4b1!4m5!3m4!1s0x177dbb696fb6a99f:0xb917df262441ad99!8m2!3d0.3272009!4d32.5634444");
        clinics[8] = new Clinic("Gulu Independent Hospital",
                "34/36 Air Field Road 23,Gulu Gulu",
                "0471-432279, 041-4251690",
                "https://www.google.com/maps/place/Gulu+Independent+Hospital/@2.7768699,32.2898444,13z/data=!4m8!1m2!2m1!1sGulu+Hospital!3m4!1s0x1771a642269a627f:0xcb6aba25240c9390!8m2!3d2.776873!4d32.2898446");
        clinics[9] = new Clinic("Rubaga Hospital",
                "Rubaga Road Rubaga Hill 14130,Kampala Kampala",
                "041-4270203/4, 041-4273692/3/4, 031-2264244/5/6/7",
                "https://www.google.com/maps/place/Lubaga+Hospital/@0.3042221,32.5503379,17z/data=!3m1!4b1!4m5!3m4!1s0x177dbcb95a57c9af:0x56656c38af282afb!8m2!3d0.3042221!4d32.5525266");
        clinics[10] = new Clinic("Makerere University Health Services",
                "Makerere Hill Road Makerere – Kavule 7062,Kampala Kampala",
                "041-4542922, 041-4541578",
                "https://www.google.com/maps/place/Lung+Institute,+Makerere+University+College+of+Health+Sciences/@0.3390058,32.5753352,17.8z/data=!4m8!1m2!2m1!1sMakerere+University+Health+Services!3m4!1s0x177dbb05f7881adb:0x928fcb50271aeea2!8m2!3d0.3397705!4d32.576156");
        clinics[11] = new Clinic("Nakasero Blood Bank",
                "Nakasero Hill Road 1772, Kampala Kampala",
                "041-4257155, 041-4259010, 041-4259191",
                "https://www.google.com/maps/place/Uganda+Red+Cross+Nakasero+Blood+Bank/@0.323666,32.577327,17z/data=!3m1!4b1!4m5!3m4!1s0x177dbb79d3533c49:0xbc799c77a73d7b92!8m2!3d0.323666!4d32.5795157");
        clinics[12] = new Clinic("Paragon Hospital Kampala Ltd.",
                "6A/7A Luthuli 5th Close Bugolobi 21387,Kampala Kampala",
                "041-4220024, 041-4220028, 071-7770000",
                "https://www.google.com/maps/place/Paragon+Hospital+Kampala+Limited/@0.3083932,32.6249575,17z/data=!3m1!4b1!4m5!3m4!1s0x177dbc02a522f881:0xf24df82d35b691e!8m2!3d0.3083932!4d32.6271462");
        clinics[13] = new Clinic("Kibuli Muslim Hospital",
                "Mbogo Road Kiburi Hill 24548,Kampala Kampala",
                "041-4236476/7, 041-4235234",
                "https://www.google.com/maps/place/Kibuli+Muslim+Hospital/@0.3094773,32.5932294,17z/data=!3m1!4b1!4m5!3m4!1s0x177dbc705dc0451d:0xe8b1a69b3d739a9!8m2!3d0.3094773!4d32.5954181");
        clinics[14] = new Clinic("Women’s Hospital International & Fertility Centre",
                "Bukoto – Kisaasi Road Kampala Kampala",
                "041-4541361",
                "https://www.google.com/maps/place/Women's+Hospital+International+and+Fertility+Centre/@0.350636,32.5968293,17z/data=!3m1!4b1!4m5!3m4!1s0x177dba4c0c48ec03:0x3258401eee5c6dc3!8m2!3d0.350636!4d32.599018");
        clinics[15] = new Clinic("Butabika Hospital (National Mental Referral Hospital)",
                "Luzira 7017, Kampala Kampala",
                "041-4504376",
                "https://www.google.com/maps/place/Butabika+National+Referral+Mental+Hospital/@0.3149573,32.6561083,17z/data=!3m1!4b1!4m5!3m4!1s0x177dbeccdb657ac7:0x3dd2c05b132ce1b2!8m2!3d0.3149573!4d32.658297");
        clinics[16] = new Clinic("St. Raphael of St. Francis Nsambya Hospital",
                "Nsambya Road Nsambya Hill 7146, Kampala Kampala",
                "041-4267012/3, 041-4266998",
                "https://www.google.com/maps/place/St.+Francis+Hospital+-+Nsambya/@0.3017248,32.5839101,17z/data=!3m1!4b1!4m5!3m4!1s0x177dbc60e36f8dbb:0x6a7282c2e45d68e9!8m2!3d0.3017248!4d32.5860988");
        clinics[17] = new Clinic("Case Medical Centre",
                "69/71 Buganda Road Case Medical Centre 4547, Kampala Kampala",
                "041-4250362, 031-2261123",
                "https://www.google.com/maps/place/Case+Hospital/@0.3246552,32.5728891,17z/data=!3m1!4b1!4m5!3m4!1s0x177dbb709451390f:0xf025da6fe7cb3667!8m2!3d0.3246552!4d32.5750778");
        clinics[18] = new Clinic("Bulamu Medical Clinic",
                "Edward Ave, Masaka, Uganda",
                "+256 758 155347",
                "https://www.google.com/maps/place/Bulamu+Medical+Clinic/@-0.3457797,31.7358676,17z/data=!3m1!4b1!4m5!3m4!1s0x19d78edc6aa6ddbf:0xc2477297732d0c25!8m2!3d-0.3457797!4d31.7380563");
        clinics[19] = new Clinic("Police Health Center III",
                "Masaka Pollice Barracks, Masaka P.O.Box 10, Masaka, Uganda",
                "+256 703 337301",
                "https://www.google.com/maps/place/Police+Health+Center+III/@-0.3443506,31.7322385,17z/data=!3m1!4b1!4m5!3m4!1s0x19d78edb054985cd:0x8700762db41b7692!8m2!3d-0.3443506!4d31.7344272");
        clinics[20] = new Clinic("Kitovu Health Care Complex",
                "Located at Kitovu hill, within Masaka Municipality, P.O. BOX 524",
                "+256 41 4285481",
                "https://www.google.com/maps/place/Kitovu+Hospital/@-0.3442488,31.755113,17z/data=!3m1!4b1!4m5!3m4!1s0x19d78efa4685e92f:0x6959a7a70b48be92!8m2!3d-0.3442488!4d31.7573017");
        clinics[21] = new Clinic("Kitovu Hospital",
                "EKitovu Road, P.O.Box 413",
                "+256 41 4285481",
                "https://www.google.com/maps/place/Kitovu+Hospital/@-0.3442488,31.755113,17z/data=!3m1!4b1!4m5!3m4!1s0x19d78efa4685e92f:0x6959a7a70b48be92!8m2!3d-0.3442488!4d31.7573017");
        clinics[22] = new Clinic("Masaka Regional Referral Hospital",
                "Alex Ssebowa, Masaka, Uganda",
                "+256 772422819",
                "https://www.google.com/maps/place/Masaka+Regional+Referral+Hospital/@-0.3293044,31.7337434,17z/data=!3m1!4b1!4m5!3m4!1s0x19d78ebb9db30b6f:0x3735f21041acd7db!8m2!3d-0.3293044!4d31.7359321");
        clinics[23] = new Clinic("Villa Maria Hospital",
                "P.O.Box 32, Masaka, Masaka, Uganda",
                "+256 758 155347",
                "https://www.google.com/maps/place/");
        clinics[24] = new Clinic("Masaka Vet Pharmacy",
                "Located Plot 911, Buddu Road, P.O. Box 921, Masaka, Uganda",
                "+256 (0)772 593 399",
                "https://www.google.com/maps/place/");
        clinics[25] = new Clinic("Ranana Enterprises Limited Pharmacy",
                "P.O. Box 210,Masaka, Uganda",
                "+256 (0)772 955 286",
                "https://www.google.com/maps/place/");
        clinics[26] = new Clinic("Ngonge Pharmaceuticals Limited",
                "P.O. Box 449, Masaka, Uganda",
                "+256 (0)481 421 078",
                "https://www.google.com/maps/place/");
        clinics[27] = new Clinic("Mukwano Medical Services Limited",
                "P.O. Box 55，Masaka, Uganda",
                "+256 (0)772 699 906",
                "https://www.google.com/maps/place/");
        clinics[28] = new Clinic("Kisa Pharmacy Limited",
                "Located on Plot 3, Edward Avenue, Masaka,P.O. Box 55，Masaka, Uganda",
                "+256 (0)772 512 6545",
                "https://www.google.com/maps/place/");
        clinics[29] = new Clinic("Equus Enterprises Pharmacy",
                "P.O. Box 139，Masaka, Uganda",
                "+256 (0)772 521 924",
                "https://www.google.com/maps/place/");
        clinics[30] = new Clinic("Byansi Services Limited Pharmacy",
                "9 BroadWay Rd, Masaka, Uganda",
                "+256 (0)481 421 433",
                "https://www.google.com/maps/place/Byansi+Services+Ltd.+(OPD+Clinic+%26+Pharmacy)/@-0.3411667,31.7355802,17z/data=!3m1!4b1!4m5!3m4!1s0x19d78ec37253a109:0x87013a859493f6b2!8m2!3d-0.3411667!4d31.7377689");
        clinics[31] = new Clinic("Arrow Pharmacy 2000 Limited",
                "P.O. Box 1461，Masaka, Uganda",
                "+256 (0)752 844 878",
                "https://www.google.com/maps/place/");


        for (int i = 0; i < 32; i++){
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