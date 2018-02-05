package com.example.mayingnan.subbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * my_adapter
 *
 * Created by mayingnan on 2018/2/2.
 *
 * @author Yingnan Ma
 *
 * @version 1.0
 *
 * entends BaseAdapter.
 *
 * my_adapter is used when show the book in oldlist.
 * In list_item.xml I settle the book only show name date and charge in the list.
 *
 * source:https://www.youtube.com/watch?v=QyUTMem1fJs
 *
 */

public class my_adapter extends BaseAdapter{
    private Context context;
    static LayoutInflater inflater = null;
    ArrayList<Book> list;


    /**
     *
     *Constructs my_adapter using the given message
     *
     * @param i_context
     * @param i_list
     *
     */
    public my_adapter(Context i_context, ArrayList<Book> i_list ){
        this.context = i_context;
        this.list = i_list;
    }



    /**
     * getCount
     *
     * getCount in interface Adapter
     *
     * @return list.size
     */

    @Override
    public int getCount() {
        return list.size();
    }

    /**
     *getItem
     *
     *getItem in interface Adapter
     *
     * @param i
     *
     * @return getItemId(i)
     *
     */
    @Override
    public Object getItem(int i) {
        return getItemId( i );
    }

    /**
     *getItemId
     *
     *getItemId in interface Adapter
     *
     * @param i
     *
     * @return i
     */

    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     *getview
     *
     *getView in interface Adapter
     *
     * @param i
     * @param view
     * @param viewGroup
     *
     * @return row
     */

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        if(row == null){
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item, null);
        }

        /**
         * find view of item_name,item_date and item_charge
         */
        TextView item_name = row.findViewById(R.id.item_name);
        TextView item_date = row.findViewById(R.id.item_date);
        TextView item_charge = row.findViewById(R.id.item_charge);

        /**
         * set item name
         */
        item_name.setText(list.get(i).getName().toString());


        /**
         * set item date with format
         */
        Date temp= list.get(i).getDate();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String temp_date = df.format(temp);
        item_date.setText(temp_date);

        /**
         * set item charge
         */
        item_charge.setText(Double.toString(list.get(i).getCharge()));







        return row;
    }
}

