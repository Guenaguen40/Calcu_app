package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView result_view,solution_view;
    MaterialButton c_button,delete_button;
    MaterialButton brackets_r_button,brackets_l_button,point_button;
    MaterialButton multiply_button,minus_button,add_button,divide_button,equal_button;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_view = findViewById(R.id.result_view);
        solution_view = findViewById(R.id.solution_view);

        assignId(c_button,R.id.c_button);
        assignId(delete_button,R.id.delete_button);
        assignId(brackets_l_button,R.id.brackets_l_button);
        assignId(brackets_r_button,R.id.brackets_r_button);
        assignId(point_button,R.id.point_button);
        assignId(multiply_button,R.id.multiply_button);
        assignId(minus_button,R.id.minus_button);
        assignId(add_button,R.id.add_button);
        assignId(divide_button,R.id.divide_button);
        assignId(equal_button,R.id.equal_button);
        assignId(button0,R.id.button0);
        assignId(button1,R.id.button1);
        assignId(button2,R.id.button2);
        assignId(button3,R.id.button3);
        assignId(button4,R.id.button4);
        assignId(button5,R.id.button5);
        assignId(button6,R.id.button6);
        assignId(button7,R.id.button7);
        assignId(button8,R.id.button8);
        assignId(button9,R.id.button9);


    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution_view.getText().toString();

        if (buttonText.equals("AC")) {
            solution_view.setText("");
            result_view.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            solution_view.setText(result_view.getText());
            return;
        }
        if (buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }

        solution_view.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);
        if(!finalResult.equals("Err")) {
        result_view.setText(finalResult);
        }
        }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data, "Javascript",1,null).toString();
            if (finalResult.endsWith((".0"))) {
                finalResult = finalResult.replace(".0", "");
            }
                    return finalResult;
        } catch (Exception e){
            return "Err";
        }
    }
}