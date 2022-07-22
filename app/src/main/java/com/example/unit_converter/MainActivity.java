package com.example.unit_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {
    EditText editText1;
    String display="";
    int cursorPos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1=(EditText)findViewById(R.id.value);
        editText1.setShowSoftInputOnFocus(false);

    }

    private void updateValue(String val)
    {
        display=editText1.getText().toString();
        cursorPos=editText1.getSelectionStart();
        editText1.setSelection(0);
        String leftStr=display.substring(0,cursorPos);
        String rightStr=display.substring(cursorPos);
        editText1.setText(String.format("%s%s%s",leftStr,val,rightStr));
        editText1.setSelection(cursorPos+1);
    }
    public void button_pressed(View view) {
        Button b=(Button) view;
        updateValue(b.getText().toString());
    }

    public void all_clear(View view) {
          display="";
          editText1.setText("");
          editText1.setSelection(0);
    }

     public void clear(View view) {

        SpannableStringBuilder selection= (SpannableStringBuilder) editText1.getText();
        int currentPos=editText1.getSelectionEnd();
        int length=selection.length();

        if( cursorPos != 0 && length != 0)
        {
            Log.d("Result","If Block Execute");
            selection.replace(currentPos-1,currentPos,"");
            editText1.setSelection(currentPos-1);
        }
   }


    public void equals(View view) {

        String expString= editText1.getText().toString();

        expString = expString.replaceAll("÷","/");
        expString = expString.replaceAll("x","*");
        expString = expString.replaceAll("–","-");

        Expression expression=new Expression(expString);

        Double d= expression.calculate();
        Log.d("Result",""+d);
        String result=""+d;
        editText1.setText(result);
        editText1.setSelection(result.length());



    }
}