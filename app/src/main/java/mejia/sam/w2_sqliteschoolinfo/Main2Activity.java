package mejia.sam.w2_sqliteschoolinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final DataBaseHandler db = new DataBaseHandler(this);
        Button btn = (Button) findViewById(R.id.btnserch);
        Button btnup = (Button) findViewById(R.id.btnupdate);
        Button btnde = (Button) findViewById(R.id.btndelete);
        final TextView tv = (TextView) findViewById(R.id.tv);
        final TextView tv1 = (TextView) findViewById(R.id.tv1);
        final TextView tv2 = (TextView) findViewById(R.id.tv2);
        final TextView tv3 = (TextView) findViewById(R.id.tv3);
        final EditText et = (EditText) findViewById(R.id.et);
        final EditText etna = (EditText) findViewById(R.id.namesv);
        final EditText etad = (EditText) findViewById(R.id.adrsv);
        final EditText etph = (EditText) findViewById(R.id.phosv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!et.getText().toString().equals("")) {
                    Integer n = Integer.valueOf(et.getText().toString());
                    Content content = db.getContent(n);
                    if (content != null) {
                        tv.setText("" + content.getId());
                        tv1.setText("" + content.getName());
                        tv2.setText("" + content.getAdress());
                        tv3.setText("" + content.getPhone());
                        etna.setText(tv1.getText().toString());
                        etad.setText(tv2.getText().toString());
                        etph.setText(tv3.getText().toString());
                    } else {
                        Toast.makeText(Main2Activity.this, "There's not data", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tv.getText().toString().equals("")){
                    int tb =  Integer.parseInt(tv.getText().toString());
                    String sna = etna.getText().toString();
                    String sad = etad.getText().toString();
                    String sph = etph.getText().toString();

                    Content content = new Content(tb,sna,sad,sph);
                    db.updateContent(content);
                }

            }
        });



        btnde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tv.getText().toString().equals("")) {
                    int tb = Integer.parseInt(tv.getText().toString());
                    Content content = new Content(tb);
                    db.deleteContent(content);
                }
            }
        });


        /**
         * CRUD Operations
         * */
        // Inserting Contacts
//        Log.d("Insert: ", "Inserting ..");
//        db.addContent(new Content("Ravi", "9100000000"));
//        db.addContent(new Content("Srinivas", "9199999999"));
//        db.addContent(new Content("Tommy", "9522222222"));
//        db.addContent(new Content("Karthik", "9533333333"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Content> contacts = db.getAllContacts();
        for (Content cn : contacts) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + ", Adress: " + cn.getAdress() + ",Phone: " + cn.getPhone();
            // Writing Contacts to log

            Log.d("Name: ", log);
        }

    }





}

