package com.rielmao.mycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_FORMULA = "KEY_FORMULA";
    public static final String KEY_RESULT = "KEY_RESULT";
    TextView formula;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        formula = (TextView) findViewById(R.id.formula_area);
        result = (TextView) findViewById(R.id.result_area);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_about:{
                Intent intent = new Intent(getApplication(),AboutActivity.class);
                startActivity(intent);
            }
            break;
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_FORMULA,formula.getText().toString());
        outState.putString(KEY_RESULT,result.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String strFormula = (String) savedInstanceState.get(KEY_FORMULA);
        String strResult = (String) savedInstanceState.get(KEY_RESULT);
        formula.setText(strFormula);
        result.setText(strResult);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.button_0:
            case R.id.button_1:
            case R.id.button_2:
            case R.id.button_3:
            case R.id.button_4:
            case R.id.button_5:
            case R.id.button_6:
            case R.id.button_7:
            case R.id.button_8:
            case R.id.button_9:
            case R.id.button_add:
            case R.id.button_sub:
            case R.id.button_mul:
            case R.id.button_div:
            case R.id.button_dot:{
                Button btn = (Button) view;
                String strAdded = btn.getText().toString();
                String strContent = formula.getText().toString();
                String strNew = strContent + strAdded;
                formula.setText(strNew);
            }
                break;
            case R.id.button_c: {
                formula.setText("");
                result.setText("");
            }
                break;
            case R.id.button_del:{
                String strContent = formula.getText().toString();
                if (strContent.length()>0){
                    formula.setText(strContent.substring(0,strContent.length()-1));
                }

            }
            break;
            case R.id.button_equ:{
                Symbols s = new Symbols();
                try {
                    double res = s.eval(formula.getText().toString());
                    result.setText(String.valueOf(res));
                    formula.setText("");
                } catch (SyntaxException e) {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
                break;
        }
    }
}
