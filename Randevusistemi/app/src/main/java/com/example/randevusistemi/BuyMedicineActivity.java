package com.example.randevusistemi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {
    // Paket bilgileri (isim, açıklama, fiyat gibi)
    private String[][] packages = {
            {"Uprise-D3 1000IU Capsule", "", "", "", "50"},
            {"HealthVit Chromium Picolinate 200mcg Capsule", "", "", "", "305"},
            {"Vitamin B Complex Capsules", "", "", "", "448"},
            {"Inlife Vitamin E Wheat Germ Oil Capsule", "", "", "", "539"},
            {"Dolo 650 Tablet", "", "", "", "30"},
            {"Crocin 650 Advance Tablet", "", "", "", "50"},
            {"Strepsils Medicated Lozenges for Sore Throat", "", "", "", "40"},
            {"Tata 1mg Calcium + Vitamin D3", "", "", "", "30"},
            {"Feronia -XT Tablet", "", "", "", "130"},
    };

    // Paket açıklamaları (her paketin ne işe yaradığını anlatır)
    private String[] package_details = {
            "Kemiklerin ve dişlerin güçlü kalmasını sağlar.\n" +
                    "Yorgunluk/stres ve kas ağrılarının azaltılmasına yardımcı olur.\n" +
                    "Bağışıklığı güçlendirir ve enfeksiyonlara karşı direnci artırır.",

            "Krom, insülinin düzenlenmesinde önemli rol oynayan temel bir iz mineraldir.",

            "B vitamini eksikliğine karşı rahatlama sağlar.\n" +
                    "Kırmızı kan hücrelerinin oluşumuna yardımcı olur.\n" +
                    "Sağlıklı sinir sistemini destekler.",

            "Cilt sağlığını ve genel sağlığı destekler.\n" +
                    "Cilt lekelerini ve pigmentasyonu azaltmaya yardımcı olur.\n" +
                    "Cildi zararlı UVA ve UVB ışınlarına karşı korur.",

            "Dolo 650 Tablet, belirli kimyasal habercilerin salınımını engelleyerek ağrı ve ateşi hafifletir.",

            "Ateşi düşürmeye ve yüksek vücut sıcaklığını azaltmaya yardımcı olur.\n" +
                    "Kalp hastalığı veya yüksek tansiyon sorunu olan kişiler için uygundur.",

            "Boğaz enfeksiyonu belirtilerini hafifletir ve iyileşme sürecini rahatlatır.\n" +
                    "Boğaz ağrısı sırasında sıcak ve rahatlatıcı bir his sağlar.",

            "Kalsiyum eksikliği, Raşitizm ve Osteoporoz riskini azaltır.\n" +
                    "Eklem hareketliliği ve esnekliği artırır.",

            "Kronik kan kaybı veya düşük demir alımı sonucu oluşan demir eksikliğini azaltmaya yardımcı olur."
    };

    HashMap<String,String>item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack,btnGoToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_medicine);

        lst=findViewById(R.id.listViewBM);
        btnGoToCart=findViewById(R.id.buttonBMGoToCart);

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });

        list = new ArrayList();
        for(int i=0;i<packages. length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("Line2", packages[i][1]);
            item.put("Line3", packages[i][2]);
            item.put("Line4", packages[i][3]);
            item.put("line5", "Total Cost:" + packages[i][4] + "/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[] { "Line1","line2", "Line3", "Line4", "Line5" },
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                it.putExtra("text1", packages[i][0]);
                it.putExtra("text2", package_details[i]);
                it.putExtra("text3", packages[i][4]);
                startActivity(it);
            }
        });

    }
}