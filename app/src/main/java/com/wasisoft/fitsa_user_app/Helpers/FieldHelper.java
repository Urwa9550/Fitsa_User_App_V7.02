package com.wasisoft.fitsa_user_app.Helpers;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

public class FieldHelper {
// it will chec fr me all the edittext fields
    public static boolean fieldempty(EditText... fields){

        boolean yesAllEmpty = true;

        for(EditText field : fields){
            if(!TextUtils.isEmpty(field.getText().toString())){
                yesAllEmpty = false;
            }else {
                yesAllEmpty = true;
                field.setError("Field is required!");
                break;
            }
        }

        return yesAllEmpty;
    }

    public static void displayToast(Context context, String msg){
        Toast.makeText(context,msg, Toast.LENGTH_LONG).show();
    }

    public static void moveTo(Context fromActivity, Class toActivity){
        Intent intent = new Intent(fromActivity, toActivity);
        fromActivity.startActivity(intent);
    }

}
