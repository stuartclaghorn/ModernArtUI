package modernartui.labs.course.modernartui;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
// import android.widget.Toast;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.*;
import java.util.ArrayList;

public class MuseumUIActivity extends AppCompatActivity {
    private Dialog mDialog;
    private ArrayList<Integer> startColors = new ArrayList<Integer>();
    TextView textBox1, textBox2, textBox3, textBox5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_ui);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Museum UI");
        setSupportActionBar(toolbar);

        textBox1 = (TextView) findViewById(R.id.textView1);
        textBox2 = (TextView) findViewById(R.id.textView2);
        textBox3 = (TextView) findViewById(R.id.textView3);
        textBox5 = (TextView) findViewById(R.id.textView5);

        startColors.add(getResources().getColor(android.R.color.holo_orange_light));
        startColors.add(getResources().getColor(android.R.color.holo_purple));
        startColors.add(getResources().getColor(android.R.color.holo_green_light));
        startColors.add(getResources().getColor(android.R.color.holo_red_light));

        SeekBar colorControl = (SeekBar) findViewById(R.id.color_bar);

        colorControl.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                // incremental change in tracking movement
                progressChanged = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // When tracking begins
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // tracking movement stops
                // Toast.makeText(MuseumUIActivity.this,"seek bar progress:"+progressChanged,
                //        Toast.LENGTH_SHORT).show();
                int colorValue = startColors.get(Math.abs(progressChanged % 4));
                textBox1.setBackgroundColor(colorValue);
                colorValue = startColors.get(Math.abs((progressChanged + 1) % 4));
                textBox2.setBackgroundColor(colorValue);
                colorValue = startColors.get(Math.abs((progressChanged + 2) % 4));
                textBox3.setBackgroundColor(colorValue);
                colorValue = startColors.get(Math.abs((progressChanged + 3) % 4));
                textBox5.setBackgroundColor(colorValue);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_museum_ui, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.about_menu_item:
                // Show a Toast Message
                // Toast.makeText(getApplicationContext(),
                        // "This action provided by the More Information Item", Toast.LENGTH_SHORT)
                        // .show();

                // custom dialog
                mDialog = new Dialog(this);
                mDialog.setContentView(R.layout.about_dialog);
                mDialog.setTitle("More Information");

                Button dialogButtonCancel = (Button) mDialog.findViewById(R.id.dialogButtonCancel);
                // if button is clicked, close the custom dialog
                dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });

                Button dialogButtonOK = (Button) mDialog.findViewById(R.id.dialogButtonOK);
                // if MOMA button is clicked go to MOMA web page
                dialogButtonOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = "https://www.moma.org/explore/mobile";
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                        mDialog.dismiss();
                    }
                });
                mDialog.show();
                
                // return value true indicates that the menu click has been handled
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
