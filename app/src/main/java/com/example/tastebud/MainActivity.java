package com.example.tastebud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class MainActivity extends AppCompatActivity {


    String [] SPINNERLIST={"INDIAN", "CHINESE", "KOREAN", "ITALIAN"};
    String [] SPINNERLIST2={"RATING", "PRICE", "DISTANCE"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        MaterialBetterSpinner betterSpinner=findViewById(R.id.android_material_design_spinner);
        betterSpinner.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, SPINNERLIST2);
        MaterialBetterSpinner betterSpinner2=findViewById(R.id.android_material_design_spinner2);
        betterSpinner2.setAdapter(arrayAdapter2);

    }



    public void sendMessage(View view) {
        Intent intent = new Intent(this, Map.class);
        Bundle extras = new Bundle();

        //Data 1
        EditText editText =  findViewById(R.id.android_material_design_spinner);
        String message = editText.getText().toString();
        EditText editText2 =  findViewById(R.id.android_material_design_spinner2);
        String message2 = editText2.getText().toString();

        extras.putString("m1", message);
        extras.putString("m2", message2);

        intent.putExtras(extras);


        startActivity(intent);

    }


}
