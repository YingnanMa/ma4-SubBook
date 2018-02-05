package com.example.mayingnan.subbook;

import java.util.Date;

/**
 * Created by mayingnan on 2018/2/2.
 */

/**
 * Book Class
 *
 * Book Class is a class of getters and setters of name date charge and comment
 *
 * @author Yingnan Ma
 *
 * @version 1.0
 *
 *
 */

public class Book {
    private String name;
    private Date date;
    private double charge;
    private String comment;


    /**
     * set the name of the counter
     * @param new_name
     */
    public void setName(String new_name){
        this.name = new_name;
    }


    /**
     * set the date of the counter
     * @param new_date
     */
    public void setDate(Date new_date){
        this.date = new_date;
    }

    /**
     * set the charge of the counter
     * @param new_charge
     */
    public void setCharge(Double new_charge){
        this.charge = new_charge;
    }

    /**
     * set the comment of the counter
     * @param new_comment
     *
     */

    public void setComment(String new_comment){
        this.comment = new_comment;
    }

    /**
     * getter of the name
     * return the name
     * @return name
     */

    public String getName() {
        return name;
    }

    /**
     * getter of the date
     * return the date
     * @return date
     */

    public Date getDate() {
        return date;
    }

    /**
     * getter of the charge
     * return the charge
     * @return charge
     */

    public double getCharge() {
        return charge;
    }

    /**
     * getter of the comment
     * return the comment
     * @return comment
     */

    public String getComment() {
        return comment;
    }
}
