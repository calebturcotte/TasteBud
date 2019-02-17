package com.example.tastebud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.view.View;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {


    String [] SPINNERLIST={"INDIA", "AUSTRALIA", "SOUTH AFRICA", "NEW ZEALAND"};
    Integer count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        MaterialBetterSpinner betterSpinner=(MaterialBetterSpinner)findViewById(R.id.android_material_design_spinner);
        betterSpinner.setAdapter(arrayAdapter);

    }

    public void buttonOnClick(View v) {
            Button button = (Button) v;
        ((Button) v).setText(count.toString());
        count++;
    }


}
