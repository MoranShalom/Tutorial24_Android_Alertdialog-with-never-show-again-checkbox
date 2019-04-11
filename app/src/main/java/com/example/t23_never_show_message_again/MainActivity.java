package com.example.t23_never_show_message_again;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  create the dialog
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        // create a View to  inflate the layout we just created -  (resource file, we dont have a view group so we will leave it null - root= null
        View mView = getLayoutInflater().inflate(R.layout.dialog, null);
        CheckBox mCheckBox= mView.findViewById(R.id.checkBox);

        mBuilder.setTitle("What's Up");
        mBuilder.setMessage("1- Ok" +
                           " \n2- Good" +
                            " \n3- Very good " +
                           "\n4- Awesome.");
        mBuilder.setView(mView);

        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        mBuilder.setCancelable(false);
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    storeDialogStatus(true);
                }else{
                    storeDialogStatus(false);
                }
            }
        });

        if(getDialogStatus()){
            mDialog.hide();
        }else{
            mDialog.show();
        }
    }

    private void storeDialogStatus(boolean isChecked){
        SharedPreferences mSharedPreferences= getSharedPreferences("checkItem", MODE_PRIVATE);
        SharedPreferences.Editor mEditor= mSharedPreferences.edit();
        mEditor.putBoolean("item", isChecked);
        mEditor.apply();
    }

    private  boolean getDialogStatus(){
        SharedPreferences mSharedPreferences = getSharedPreferences("checkItem", MODE_PRIVATE);
        return mSharedPreferences.getBoolean("item", false);
    }

}
