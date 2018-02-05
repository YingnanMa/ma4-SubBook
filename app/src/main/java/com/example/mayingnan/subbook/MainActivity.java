package com.example.mayingnan.subbook;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;





/**
 * MainActivity
 *
 * @author Yingnan Ma
 *
 * @version 1.0
 *
 * MainActivity is to display the main UI.
 * The main UI contains the title with the total charge, oldlist of books and floatingactionbutton to add book.
 *
 */



public class MainActivity extends AppCompatActivity {
    private TextView title;
    private ListView oldlist;
    private my_adapter adapter;
    private Booklist bl = new Booklist();
    private Context context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bl = new Booklist();
        context=getApplicationContext();
        bl.loadFromFile(context);



        /*
         * rename the title and edit the total charge
         *
         * source of formating the string:https://dzone.com/articles/java-string-format-examples
         */

        title = (TextView) findViewById(R.id.title);
        title.setText(String.format("ma4-SubBook\ntotal_charge: %s", bl.getTotal_charge()));


        /*
         *oldlist click
         *
         * when click, intent from MainActivity to EditsubActivity.
         *
         * source:https://developer.android.com/reference/android/widget/AdapterView.OnItemClickListener.html
         *
         */

        oldlist = (ListView) findViewById(R.id.oldlist);
        oldlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long r_id) {
                Intent info1 = new Intent(MainActivity.this, EditsubActivity.class);
                info1.putExtra("info", index);
                startActivity(info1);
            }
        });

        /*
         *add button click
         *
         * when click, intent from MainActivity to AddsubActivity.
         *
         */
        FloatingActionButton floatingbutton = (FloatingActionButton) findViewById(R.id.floatingbutton);
        floatingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent info2 = new Intent(MainActivity.this, AddsubActivity.class);
                startActivity(info2);

            }
        });

    }


    /*
     *set Adapter
     *
     */

    @Override
    protected void onStart(){
        super.onStart();
        adapter = new my_adapter(this, bl.getList());
        oldlist.setAdapter(adapter);

        }






    }

