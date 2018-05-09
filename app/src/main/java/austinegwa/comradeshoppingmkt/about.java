package austinegwa.comradeshoppingmkt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

public class about extends AppCompatActivity {

    TextView txt;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

       txt= findViewById(R.id.about);
       txt.setText( Html.fromHtml(getString(R.string.about)));

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, home.class);
        i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);
        }
    }
