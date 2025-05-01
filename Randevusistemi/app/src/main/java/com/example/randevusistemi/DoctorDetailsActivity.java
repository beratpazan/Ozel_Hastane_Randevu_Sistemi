package com.example.randevusistemi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1=
            {
            {"Doktor Adı : Ajit Saste", "Hastane Adresi: Pimpri", "Tecrübe: 5yrs", "TEL No:9898989898", "608"},
            {"Doktor Adı : Prasad Pawar", "HHastane Adresi: Nigdi", "Tecrübe: 15yrs", "TEL No:7898989898", "900"},
            {"Doktor Adı : Swapnil Kale", "Hastane Adresi: Pune", "Tecrübe: 8yrs", "TEL No:8898989898", "300"},
            {"Doktor Adı : Deepak Deshmukh", "Hastane Adresi: Chinchwad", "Tecrübe: 6yrs", "TEL No:9898000000", "500"},
            {"Doktor Adı : Ashok Panda", "Hastane Adresi: Katraj", "Tecrübe: 7yrs", "TEL No:7798989898", "800"}
    };

        private String[][] doctor_details2= {

                {"Doktor Adı : Neelam Patil", "Hastane Adresi: Pimpri", "Tecrübe: 5yrs", "MTEL No:9898989898", "600"},
                {"Doktor Adı : Swati Pawar", "Hastane Adresi: Nigdi", "Tecrübe: 15yrs", "TEL No:7898989898", "900"},
                {"Doktor Adı : Neeraja Kale", "Hastane Adresi: Pune", "Tecrübe: 8yrs", "TEL No:8898989898", "300"},
                {"Doktor Adı : Mayuri Deshmukh", "Hastane Adresi: Chinchwad", "Tecrübe: 6yrs", "TEL No:9898000000", "500"},
                {"Doktor Adı : Minakshi Panda", "Hastane Adresi: Katraj", "Tecrübe: 7yrs", "TEL No:7798989898", "800"}
        };

    private String[][] doctor_details3= {

            {"Doktor Adı : Seema Patil", "Hastane Adresi: Pimpri", "Tecrübe: 4yrs", "TEL No:9898989898", "200"},
            {"Doktor Adı : Pnkaj Parab", "Hastane Adresi: Nigdi", "Tecrübe: 5yrs", "TEL No:7898989898", "300"},
            {"Doktor Adı : Monish Jain", "Hastane Adresi: Pune", "ETecrübe: 7yrs", "TEL No:8898989898", "300"},
            {"Doktor Adı : Vishal Deshmukh", "Hastane Adresi: Chinchwad", "Tecrübe: 6yrs", "TEL No:9898000000", "580"},
            {"Doktor Adı : Shrikant Panda", "Hastane Adresi: Katraj", "Tecrübe: 7yrs", "TEL No:7798989898", "600"}
    };

    private String[][] doctor_details4= {

            {"Doktor Adı : Amol Gawade", "Hastane Adresi: Pimpri", "Tecrübe: 5yrs", "TEL No:9898989898", "600"},
            {"Doktor Adı : Prasad Pawar", "Hastane Adresi: Nigdi", "Tecrübe: 15yrs", "TEL No:7898989898", "980"},
            {"Doktor Adı : Nilesh Kale", "Hastane Adresi: Pune", "Tecrübe: 8yrs", "TEL No:8898989898", "300"},
            {"Doktor Adı : Deepak Deshpande", "Hastane Adresi: Chinchwad", "Tecrübe: 6yrs", "TEL No:9898800000", "500"},
            {"Doktor Adı : Ashok Singh", "Hastane Adresi: Katraj", "Tecrübe: 7yrs", "TEL No:7798989898", "800"}
    };
    private String[][] doctor_details5= {

            {"Doktor Adı : Nilesh Borate", "Hastane Adresi: Pimpri", "Tecrübe: 5yrs", "TEL No: 9898989898", "1600"},
            {"Doktor Adı : Pamkaj Pawar", "Hastane Adresi: Nigdi", "Tecrübe: 15yrs", "TEL No:7898989898", "1900"},
            {"Doktor Adı : Swapnil Lele", "Hastane Adresi: Pune", "Tecrübe: 8yrs", "TEL No:8898989898", "1300"},
            {"Doktor Adı : Deepak Kumar", "Hastane Adresi: Chinchwad", "Tecrübe: 6yrs", "TEL No:9898000008", "1500"},
            {"Doktor Adı : Ankul Panda", "HHastane Adresi: Katrai", "Tecrübe: 7yrs", "TEL No:7798989898", "1800"}
    };



    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    ArrayList list;
    HashMap<String,String>item;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_details);

        tv=findViewById(R.id.textViewODTitle);
        btn=findViewById(R.id.buttonBMBack);

        Intent it = getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Aile Hekimi")==0)
            doctor_details=doctor_details1;
        else
        if(title.compareTo("Diyetisyen")==0)
            doctor_details=doctor_details2;
        else
        if(title.compareTo("Diş Hekimi")==0)
            doctor_details=doctor_details3;
        else
        if(title.compareTo("Cerrah")==0)
            doctor_details=doctor_details4;
        else
            doctor_details=doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });
        list = new ArrayList();
        for(int i=0;i<doctor_details.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Ücretler:"+doctor_details[i][4]+"/-");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                );
        ListView lst=findViewById(R.id.listViewBM);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,View view,int i,long l) {
                Intent it= new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });

    }
}