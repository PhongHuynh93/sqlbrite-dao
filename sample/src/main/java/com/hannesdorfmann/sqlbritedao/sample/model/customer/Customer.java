package com.hannesdorfmann.sqlbritedao.sample.model.customer;

import com.hannesdorfmann.sqlbrite.objectmapper.annotation.Column;
import com.hannesdorfmann.sqlbrite.objectmapper.annotation.ObjectMappable;
import com.hannesdorfmann.sqlbritedao.sample.model.Person;

/**
 * @author Hannes Dorfmann
 * todo a class which we want to create mapper from
 */
@ObjectMappable
public class Customer extends Person {

    public static final String TABLE_NAME = "Customer";
    /**
     * todo the desired fields with @Column with the table column name (String) as parameter
     */
    public static final String COL_FIRSTNAME = "firstname";
    public static final String COL_LASTNAME = "lastname";
    public static final String COL_ADULT = "adult";

    //
    // Fields mapped to database columns
    //
    @Column(COL_FIRSTNAME)
    String firstname;
    @Column(COL_LASTNAME)
    private String mLastname;

    private boolean adult;

    public Customer() {
    }

    public Customer(long id, String firstname, String lastname, boolean adult) {
        this.id = id;
        this.firstname = firstname;
        this.mLastname = lastname;
        this.adult = adult;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return mLastname;
    }

    public boolean isAdult() {
        return adult;
    }

    //
    // Fields mapped to database columns
    //
    @Column(COL_ADULT)
    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setmLastname(String mLastname) {
        this.mLastname = mLastname;
    }
}
