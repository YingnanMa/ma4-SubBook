package com.example.mayingnan.subbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * EditsubActivity
 *
 * @author Yingnan Ma
 *
 * @version 1.0
 *
 * EditsubActivity is to display the edit&view UI.
 * The edit&view UI contains the name, date, charge and comment that will show the original information.
 * It can be edited by users.
 * It also contains save and delete button.
 */

public class EditsubActivity extends AppCompatActivity {
    private TextView edit_name;
    private TextView edit_date;
    private TextView edit_charge;
    private TextView edit_comment;
    private  Button edit_save;
    private  Button edit_delete;
    private Booklist bl = new Booklist();
    private Book view_book;
    private Book target_book;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editsub);

        context = getApplicationContext();
        bl.loadFromFile(context);
        final Intent old_intent = getIntent();

        /**
         * get the index of the book that user wants to view
         *
         * then use the index to get the view_book that the user wants to view
         */

        int view_index = Integer.parseInt(old_intent.getExtras().get("info").toString());
        view_book=bl.getList().get(view_index);

        /**
         * get the name from the booklist
         * find the view of edit_name
         * set text on name
         *
         */

        String temp_name=view_book.getName();
        edit_name = (TextView) findViewById(R.id.edit_name);
        edit_name.setText(temp_name);

        /**
         * get the date from the booklist
         * format the date type into string
         * find the view of edit_date
         * set text on date
         *
         */

        Date temp_date1=view_book.getDate();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String temp_date = df.format(temp_date1);
        edit_date = (TextView) findViewById(R.id.edit_date);
        edit_date.setText(temp_date);

        /**
         * get the charge from the booklist
         * find the view of edit_charge
         * format double type into string
         * set text on charge
         *
         */
        Double temp_charge=view_book.getCharge();
        edit_charge = (TextView) findViewById(R.id.edit_charge);
        edit_charge.setText(Double.toString(temp_charge));

        /**
         * get the comment from the booklist
         * find the view of edit_comment
         * set text on comment
         *
         */
        String temp_comment=view_book.getComment();
        edit_comment = (TextView) findViewById(R.id.edit_comment);
        edit_comment.setText(temp_comment);

        /**
         * find the view of save and delete button
         */
        edit_save = (Button) findViewById(R.id.edit_save);
        edit_delete = (Button) findViewById(R.id.edit_delete);

        /**
         * edit_save click
         *
         * when click the save button in edit UI, intent from EditsubActivity to MainActivity.
         *
         * from the old_intent get the book_id,use id to get the target book that edited.
         *
         * get text of the information after editing
         *
         * set the new information in target book
         *
         * save the booklist in file
         */

        edit_save.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                if (check_empty(edit_name.getText().toString(), edit_date.getText().toString(), edit_charge.getText().toString())) {
                    if (check_date_format("yyyy-MM-dd",edit_date.getText().toString())) {

                        Intent i = new Intent(EditsubActivity.this, MainActivity.class);

                        //get id of the target book that wants to edit, then use id to get the target book
                        int book_id = Integer.parseInt(old_intent.getExtras().get("info").toString());
                        target_book = bl.getList().get(book_id);

                        /**
                         * set new name to target book
                         */
                        target_book.setName(edit_name.getText().toString());

                        /**
                         * format the date
                         *
                         * set new date to target book
                         *
                         */
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date new_date = new Date();
                        try {
                            new_date = format.parse(edit_date.getText().toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        target_book.setDate(new_date);

                        /**
                         * set new comment to target book
                         */
                        target_book.setComment(edit_comment.getText().toString());

                        /**
                         * format the charge
                         *
                         * set new charge to target book
                         *
                         */
                        target_book.setCharge(Double.parseDouble(edit_charge.getText().toString()));

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

        /**
         * edit_delete click
         *
         * when click the delete button in edit UI, intent from EditsubActivity to MainActivity.
         *
         * from the old_intent get the book_id,use id to detelte the target book from bl.
         *
         * save the booklist in file
         *
         */

        edit_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditsubActivity.this, MainActivity.class);
                //extract inputs
                bl.loadFromFile(context);
                int book_id = Integer.parseInt(old_intent.getExtras().get("info").toString());
                bl.delete(book_id);

                bl.saveInFile(context);

                startActivity(i);

            }


        });

    }

    /**
     * check_empty
     * check the length of name, date, charge
     * to make sure name date and charge are not empty
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
     * check if the date format is correct
     * @param format
     * @param value
     * @return
     *
     * source:https://stackoverflow.com/questions/20231539/java-check-the-date-format-of-current-string-is-according-to-required-format-or
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
