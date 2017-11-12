package com.example.calculator;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity {

    TextView tv;
    int[] buttons;
    float result;
    float result1;
    float result2;

    Button buttonC;
    Button buttonAdd;
    Button buttonMinus;
    Button buttonMulti;
    Button buttonDivide;
    Button buttonEqual;
    Button buttonBack;
    Button buttonPoint;
    Button buttonBrackets1;
    Button buttonBrackets2;

    String str1;
    String str2;
    int flag=0;
    Button temp;
    public Button getBtnForId(int rID){
        Button btn = (Button)this.findViewById(rID);
        return btn;
     }

    public void initButton(){
        tv = (TextView)this.findViewById(R.id.tv);
        str1 = String.valueOf(tv.getText());
        str2 = "";
        buttonC = getBtnForId(R.id.ButtonC);
        buttonAdd = getBtnForId(R.id.ButtonAdd);
        buttonMinus= getBtnForId(R.id.ButtonMinus);
        buttonMulti = getBtnForId(R.id.ButtonMulti);
        buttonDivide = getBtnForId(R.id.ButtonDivide);
        buttonEqual = getBtnForId(R.id.ButtonEqual);
        buttonBack=getBtnForId(R.id.ButtonBack);
        buttonPoint=getBtnForId(R.id.ButtonPoint);
        buttonBrackets1=getBtnForId(R.id.ButtonBrackets1);
        buttonBrackets2=getBtnForId(R.id.ButtonBrackets2);
        buttons = new int[]{
                R.id.Button0,R.id.Button1,R.id.Button2,
                R.id.Button3, R.id.Button4,R.id.Button5,
                R.id.Button6, R.id.Button7,R.id.Button8,R.id.Button9
        };
    }
    //按钮监听
    public void buttonListener(Button button,final int id){
      button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String str = tv.getText().toString().trim();
            result1= Float.parseFloat(str);
            tv.setText("");
            flag = id;
        }
    });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);
        initButton();
        //清空（C按钮）
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1=" ";
                str2=" ";
                tv.setText("0");
                result=0;
                result1=0;
                result2=0;
                flag=0;
            }
        });
        //数字输入

        for(int i=0;i<10;i++){
         
        temp=getBtnForId(buttons[i]);
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (flag != 0) {
                    str1 = "";
             //   }
                    str1 = tv.getText().toString().trim();
                    if (str1.equals("0")){
                        str1 = "";
                    }
                str1 = str1 + String.valueOf(((Button)v).getText());
                tv.setText(str1);
            }
        });
        }
         buttonPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1=str1+".";
                tv.setText(str1);
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1=tv.getText().toString().trim();
                str1=str1.substring(0,str1.length()-1);
                tv.setText(str1);
            }
        });

        buttonListener(buttonAdd,1);
        buttonListener(buttonMinus,2);
        buttonListener(buttonMulti,3);
        buttonListener(buttonDivide,4);
        buttonListener(buttonBack,5);
       // buttonListener(buttonPoint,6);
    buttonEqual.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        result2 = Float.parseFloat(str1);
        if (flag == 1) {
            result = result1 + result2;
        }
        if (flag == 2) {
            result = result1 - result2;
        }
        if (flag == 3) {
            result = result1 * result2;
        }
        if (flag == 4) {
            if (result2 != 0) {
                result = result1 / result2;
            } else {
                Toast.makeText(CalculatorActivity.this, "除数不能为0！", Toast.LENGTH_SHORT).show();
            }
        } else if (flag == 0) {
            result = result1;
        }
        String str = (result + "").trim();
        if (result1 == 0 && flag == 4) {
            str = "错误";
        }
        tv.setText(str);
        Toast.makeText(CalculatorActivity.this, "结果是：" + result, Toast.LENGTH_SHORT).show();

    }
    });
    }
    }

