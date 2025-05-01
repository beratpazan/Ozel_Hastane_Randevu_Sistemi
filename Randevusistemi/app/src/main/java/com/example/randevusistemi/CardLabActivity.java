package com.example.randevusistemi;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CardLabActivity extends AppCompatActivity {
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    TextView tvTotal;
    ListView lst;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, timeButton, btnCheckout, btnBack;
    private String[][] packages={};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_card_lab);

        dateButton = findViewById(R.id.buttonCartDate);
        timeButton = findViewById(R.id.buttonCartTime); // DÜZELT: Aynı ID kullanılmasın
        btnCheckout = findViewById(R.id.buttonBMGoToCart);
        btnBack = findViewById(R.id.buttonBMBack);
        tvTotal =findViewById(R.id.textViewCartTotalCost);
        ListView lst=findViewById(R.id.listViewBM);

        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username= sharedPreferences.getString("Kullanıcı Adı","").toString();

        Database db=new Database(getApplicationContext(),"Randevu Sistemi",null,1);

        float totalAmount= 0;
        ArrayList dbData= db.getCartData(username,"lab");
        Toast.makeText(getApplicationContext(),""+dbData,Toast.LENGTH_SHORT).show();

        packages =new String[dbData.size()][];
        for(int i=0;i<packages.length;i++) {
            packages[i] = new String[5];
        }

        for(int i=0;i<dbData.size();i++){
            String arrData=dbData.get(i).toString();
            String[] strdata=arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0]=strdata[0];
            packages[i][4]="Maliyet:"+strdata[1]+"/-";
            totalAmount=totalAmount+Float.parseFloat(strdata[1]);
        }
        tvTotal.setText("Toplam Maliyet:"+totalAmount);

        list = new ArrayList();
        for(int i=0;i<packages. length; i++){
            item = new HashMap<String, String>();
            item.put( "line1", packages[i][0]);
            item.put( "line2", packages[i][1]);
            item.put( "Line3", packages[i][2]);
            item.put( "Line4", packages[i][3]);
            item.put( "Line5", packages[i][4]);
            list.add( item );
        }
        sa = new SimpleAdapter( this,list,
                R.layout.multi_lines,
                new String[] { "Line1","line2", "line3", "line4", "Line5" },
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lst.setAdapter(sa);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CardLabActivity.this, LabTestActivity.class));
            }
        });
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(CardLabActivity.this,LabTestBookActivity.class);
                it.putExtra("fiyat",tvTotal.getText());
                it.putExtra("tarih",dateButton.getText());
                it.putExtra("saat",timeButton.getText());
                startActivity(it);
            }
        });


        // Date ve Time picker’ları başlat
        initDatePicker();
        initTimePicker();

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                dateButton.setText(day + "/" + month + "/" + year);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(CardLabActivity.this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000); // Yarın ve sonrası seçilsin
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                timeButton.setText(hour + ":" + String.format("%02d", minute));
            }
        };

        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR_OF_DAY);
        int mins = cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(CardLabActivity.this, style, timeSetListener, hrs, mins, true);
    }
}
