package com.example.mayingnan.subbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * AddsubActivity
 *
 * @author Yingnan Ma
 *
 * @version 1.0
 *
 * AddsubActivity is to display the add UI.
 * The add UI contains the name, date, charge and comment that added by user and the  button save.
 *
 */

public class AddsubActivity extends AppCompatActivity {
    private TextView add_name;
    private TextView add_date;
    private TextView add_charge;
    private TextView add_comment;
    private  Button add_save;
    private Booklist bl = new Booklist();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsub);
        context=getApplicationContext();

        /**
         * find view of name date charge comment and save button.
         */
        add_name = (TextView) findViewById(R.id.add_name);
        add_date = (TextView) findViewById(R.id.add_date);
        add_charge = (TextView) findViewById(R.id.add_charge);
        add_comment = (TextView) findViewById(R.id.add_comment);
        add_save = (Button) findViewById(R.id.add_save);

        /*
         *add_save click
         *
         * when click the save button in add UI, intent from AddsubActivity to MainActivity.
         *
         * create a newbook and put the information that typed by users to newbook.
         *
         * then put the newbook in booklist and save in file.
         *
         */
        add_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (check_empty(add_name.getText().toString(),add_date.getText().toString(),add_charge.getText().toString())) {
                        if (check_date_format("yyyy-MM-dd",add_date.getText().toString())) {


                            Intent i = new Intent(AddsubActivity.this, MainActivity.class);

                            /**
                             * extract inputs
                             */

                            bl.loadFromFile(context);
                            Book new_book = new Book();


                            /**
                             * set name in new_book
                             */
                            new_book.setName(add_name.getText().toString());


                            /**
                             * format the date and set date in new_book
                             */
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            Date new_date = new Date();
                            try {
                                new_date = format.parse(add_date.getText().toString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            new_book.setDate(new_date);


                            /**
                             * set comment in new_book
                             */
                            new_book.setComment(add_comment.getText().toString());


                            /**
                             * set charge in new_book
                             */
                            new_book.setCharge(Double.parseDouble(add_charge.getText().toString()));


                            /**
                             * add book to booklist and sabe booklist in file
                             */
                            bl.add(new_book);
                            bl.saveInFile(context);

                            startActivity(i);
                        }else{
                            Toast toast = Toast.makeText(context,"Enter date in 'yyyy-MM-dd'",Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }else{
                        Toast toast = Toast.makeText(context,"Enter name, date and charge",Toast.LENGTH_LONG);
                        toast.show();
                    }

                }
        });
    }

    /**
     * check_empty check the length of the name date and charge
     * so that these three information can not be make empty
     * @param name
     * @param date
     * @param charge
     * @return
     */
    private boolean check_empty(String name,String date, String charge){
        if(name.length()==0 || date.length()==0 || charge.length()==0){
            return false;
        }
        return true;
    }

    /**
     * check_date_format
     *
     * check if the date format is correct
     *
     * @param format
     * @param value
     * @return
     */
    private boolean check_date_format(String format, String value){
        Date d = null;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            d = sdf.parse(value);
            if (!value.equals(sdf.format(d))){
                d = null;
            }
        } catch (ParseException ex){
            ex.printStackTrace();
        }
        return d != null;
    }

}
