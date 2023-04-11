package com.example.lbc_thread_cal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public EditText ed1;
    public EditText ed2;
    public Button btn1;
    public TextView a1,a2,a3,a4,b1,b2,b3,b4,c1,c2,c3,c4;
    public int auto,manual,day,num;
    public String temp;
    public boolean digitsOnly,cancal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化
        init();
        //計算
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //檢查2個輸入是否合理
                temp = ed1.getText().toString();
                cancal = true;
                if(temp.isEmpty())
                {
                    digitsOnly = false;
                }
                else
                {
                    digitsOnly = TextUtils.isDigitsOnly(temp);

                }

                if(digitsOnly)
                {
                    num = Integer.parseInt(temp);
                }
                else
                {
                    cancal = false;
                }
                temp = ed2.getText().toString();
                digitsOnly = TextUtils.isDigitsOnly(temp);
                if(temp.isEmpty())
                {
                    digitsOnly = false;
                }
                else
                {
                    digitsOnly = TextUtils.isDigitsOnly(temp);

                }
                if(digitsOnly)
                {
                    day = Integer.parseInt(temp);
                }
                else
                {
                    cancal = false;
                }
                if(cancal == false)
                {
                    Toast.makeText(getApplicationContext(), "請檢查輸入，只接受正整數", Toast.LENGTH_LONG).show();
                }
                //基礎加倍便當數 手:3*2顆便當/加倍，掃: 3*4顆便當/加倍
                int dmanual = day*3*2,dauto = day*3*4;
                //基礎加倍燈泡數 手:6*3顆燈泡/加倍，掃: 9*3顆燈泡/加倍
                int dTmanual = day*3*6,dTauto = day*3*9;
                //非加倍單場燈泡數 手:一場3顆燈泡/沒加倍，掃: 一場5顆燈泡/沒加倍
                //每天平均多打一場
                int Nmanual = day*3,Nauto = day*5;
                //天數
                int count = 0,total = 0;

                //計算1 純手
                //起始=基礎手動加倍燈泡數
                int tnum = dTmanual;
                while(tnum<num)
                {
                    ++count;
                    //總和加上每天平均多打一場
                    tnum += Nmanual;
                }
                //花費便當 = 基礎加倍便當數+天數*每天平均多打場數*手動花費
                total = dmanual+day*count*2;
                a1.setText(Integer.toString(count)+"場");
                b1.setText(Integer.toString(total)+"顆");
                c1.setText(Integer.toString(tnum));


                //計算2 純掃
                //起始=基礎掃蕩加倍燈泡數
                count = 0;
                tnum = dTauto;
                while(tnum<num)
                {
                    ++count;
                    //總和加上每天平均多掃一場
                    tnum += Nauto;
                }
                //花費便當 = 基礎加倍掃蕩便當數+天數*每天平均多打場數*掃蕩花費
                total = dauto+day*count*4;
                a2.setText(Integer.toString(count)+"場");
                b2.setText(Integer.toString(total)+"顆");
                c2.setText(Integer.toString(tnum));


                //計算3 先手後掃
                //起始=基礎手動加倍燈泡數
                count = 0;
                tnum = dTmanual;
                while(tnum<num)
                {
                    ++count;
                    //總和加上每天平均多掃一場
                    tnum += Nauto;
                }
                //花費便當 = 基礎手動加倍便當數+天數*每天平均多打場數*掃蕩花費
                total = dmanual+day*count*4;
                a3.setText(Integer.toString(count)+"場");
                b3.setText(Integer.toString(total)+"顆");
                c3.setText(Integer.toString(tnum));


                //計算4 先掃後手
                //起始=基礎掃蕩加倍燈泡數
                count = 0;
                tnum = dTauto;
                while(tnum<num)
                {
                    ++count;
                    //總和加上每天平均多手動一場
                    tnum += Nmanual;
                }
                //花費便當 = 基礎加倍掃蕩便當數+天數*每天平均多打場數*手動花費
                total = dauto+day*count*2;
                a4.setText(Integer.toString(count)+"場");
                b4.setText(Integer.toString(total)+"顆");
                c4.setText(Integer.toString(tnum));
            }
        });
    }
    public void init()
    {
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        btn1 = findViewById(R.id.btn1);
        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        a4 = findViewById(R.id.a4);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        c4 = findViewById(R.id.c4);
        ed2.setHint("輸入天數");
        ed1.setHint("輸入數量");
        auto = 4;
        manual = 2;
    }
}