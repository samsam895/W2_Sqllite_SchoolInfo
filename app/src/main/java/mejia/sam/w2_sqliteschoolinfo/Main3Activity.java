package mejia.sam.w2_sqliteschoolinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        final DataBaseHandler db = new DataBaseHandler(this);
        Button btn = (Button) findViewById(R.id.btnsave);
        final EditText et1 = (EditText) findViewById(R.id.namesv);
        final EditText et2 = (EditText) findViewById(R.id.adrsv);
        final EditText et3 = (EditText) findViewById(R.id.phosv);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inserting Contacts

                String nd1 = et1.getText().toString();
                String nd2 = et2.getText().toString();
                String nd3 = et3.getText().toString();
                db.addContent(new Content(nd1,nd2,nd3));


            }
        });


    }


}
