package com.example.test.text_quest;

import android.graphics.Typeface;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    int current;
    int end = 0;
    String title;
    String path = "";
    int kek = 0;
    int i=0;
    String[][] mess = new String[0][1];
    String[][] vibor = new String[0][1];
    int[][] vib = new int[0][1];
    Button button1;
            Button button2;
    Button button3;
    TextView titleg;
    TextView textg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Scanner sc = null;
            InputStream json = null;
            json = getAssets().open("quest.txt");
            //path = json.toString();
            sc = new Scanner(json);
            title = sc.nextLine();
            Log.d("hah", "onCreate: " + title);
            end = sc.nextInt();
            mess = new String[end][20];
            for (int i = 0; i < mess.length; i++) {

                for (int j = 0; j < mess[i].length; j++) {
                    String g = sc.nextLine();
                    if (!(g.equals("----"))) {
                        mess[i][j] = g;
                    } else {
                        break;
                    }
                }

            }
            sc.nextLine();
            sc.nextLine();
            vibor = new String[end][10];
            vib = new int[end][10];
            for (int i = 0; i < vibor.length; i++) {
                for (int j = 0; j < vibor[i].length; j++) {
                    String g = sc.nextLine();
                    if (!(g.equals("---"))) {
                        //String[] ss;
                        //ss = g.split(" ");
                        vibor[i][j] = g;
                        vib[i][j] = Integer.parseInt(sc.nextLine());

                    } else {
                        break;
                    }

                }
                setTitle(title+" - текстовый квест на Android");
            }
        } catch (IOException e) {
            Log.e("hah", path);
            e.printStackTrace();

        }
        Typeface face=Typeface.createFromAsset(getAssets(),
                "gothic.ttf");
        TextView igra = (TextView)findViewById(R.id.textView3);
        igra.setTypeface(face);
        igra.setText(title);
         button1 = (Button) findViewById(R.id.button);
          button2 = (Button) findViewById(R.id.button2);
          button3 = (Button) findViewById(R.id.button3);
          titleg = (TextView) findViewById(R.id.textView2);
          textg = (TextView) findViewById(R.id.textView);
        textg.setTypeface(face);
        titleg.setTypeface(face);
        button1.setTypeface(face);
        button2.setTypeface(face);
        button3.setTypeface(face);
        button1.setAllCaps(false); // для кнопок не AppCompat
        button2.setAllCaps(false); // для кнопок не AppCompat
        button3.setAllCaps(false); // для кнопок не AppCompat

        current = 0;
            if (i == current) {

                loadpacks();
                kek = i;

                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        kek = i;
                        titleg.setText("");
                        textg.setText("");

                        current = vib[kek][0];
                        i=current;

                        loadpacks();
                       // current = vib[kek][0];
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        kek = i;
                        if(vib[kek][1]!=0 ) {

                            titleg.setText("");
                            textg.setText("");
                            current = vib[kek][1];
                            i = current;

                            loadpacks();
                        }
                    }

                });
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        kek = i;
                        if(vib[kek][2]!=0) {
                            titleg.setText("");
                            textg.setText("");
                            current = vib[kek][2];
                            i = current;
                            loadpacks();
                        }
                    }
                });

                i = 0;


            }


    }

    private void loadpacks() {

        if (vibor[i][0] != null) {
            button1.setText(vibor[i][0]);
        }
        else
        {
            button1.setText("");
        }
        if (vibor[i][1] != null) {

            button2.setText(vibor[i][1]);
        }
        else
        {
            button2.setText("");
        }
        if (vibor[i][2] != null) {

            button3.setText(vibor[i][2]);
        }
        else
        {
            button3.setText("");
        }
        for (int j = 0; j < mess[i].length; j++) {
            if (mess[i][j] != null) {
                String w = (String) titleg.getText();
                w += (mess[i][j] + "\n");
                titleg.setText(w);
            }
        }
        for (int j = 0; j < vibor[i].length; j++) {
            if (vibor[i][j] != null) {
                String w = (String) textg.getText();
                w += ("" + (j + 1) + "." + vibor[i][j] + "\n");
                textg.setText(w);
            }
        }
    }

    private void choice(int d) {
        current = vib[kek][d];
    }

}
