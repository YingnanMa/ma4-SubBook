package com.example.mayingnan.subbook;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;




/**
 * Created by mayingnan on 2018/2/2.
 */

/**
 * Booklist
 *
 * @author Yingnan Ma
 *
 * @version 1.0
 *
 * Booklist is to put books and have some functions: getTotal_charge, add, delete, saveInfile, loadFromfile
 *
 */

public class Booklist {
    private double total_charge;
    private ArrayList<Book> list = new ArrayList<Book>();
    private static final String FILENAME = "ma4_subbook.sav";


    /**
     * getTotal_charge is to get all the charges in the current list
     * @return total_charge
     */

    public double getTotal_charge() {

        this.total_charge = 0.0;
        for(int i = 0; i<list.size(); i++){
            this.total_charge += list.get(i).getCharge();
        }
        return total_charge;
    }


    /**
     * ArrayList<Book> getList
     * @return list
     */
    public ArrayList<Book> getList() {
        return list;
    }

    /**
     * add
     * add new book to the list
     * @param new_book
     */

    public void add(Book new_book){
        this.list.add(new_book);



    }

    /**
     * delete
     * delete the book from the list using index
     * @param i
     */


    public void delete(int i){
        this.list.remove(i);



    }


    /**
     * saveInFile
     * save In File using gson
     * @param context
     */
    public void saveInFile(Context context) {// basicaly copy from lonely tweeter
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,
                    0);
            OutputStreamWriter writer= new OutputStreamWriter(fos);
            Gson gson =new Gson();
            gson.toJson(this.list, writer);
            writer.flush();
            fos.close();
        }

        catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * loadFromFile
     * load from file using gson
     * @param context
     */
    public void  loadFromFile(Context context) {// basicaly copy from lonely tweeter

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Book>>() {}.getType();
            //take from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html
            list =gson.fromJson(in, listType);



        } catch (FileNotFoundException e) {
            list = new ArrayList<Book>();

        }


    }


}
