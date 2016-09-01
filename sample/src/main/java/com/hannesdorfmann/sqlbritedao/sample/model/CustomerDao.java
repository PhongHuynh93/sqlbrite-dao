    package com.hannesdorfmann.sqlbritedao.sample.model;

    import android.content.ContentValues;
    import android.database.sqlite.SQLiteDatabase;

    import com.hannesdorfmann.sqlbrite.dao.Dao;
    import com.hannesdorfmann.sqlbritedao.sample.model.customer.Customer;
    import com.hannesdorfmann.sqlbritedao.sample.model.customer.CustomerMapper;

    import java.util.List;

    import rx.Observable;

    /**
     * @author Hannes Dorfmann
     */
    
    /**
     * todo create DAO db
     * define methods to manipulate or query your database table.
     */
    public class CustomerDao extends Dao {

        /**
         * todo implement createTable to create table
         *
         * @param database
         */
        @Override
        public void createTable(SQLiteDatabase database) {
            /**
             * todo create table with 4 column
             */
            CREATE_TABLE(
                    Customer.TABLE_NAME,
                    Customer.COL_ID + " INTEGER PRIMARY KEY NOT NULL",
                    Customer.COL_FIRSTNAME + " TEXT",
                    Customer.COL_LASTNAME + " TEXT",
                    Customer.COL_ADULT + " BOOLEAN"
            ).execute(database);
        }

        /**
         * todo must have implement this method, upgrade the db
         *
         * @param db         the database
         * @param oldVersion old database version
         * @param newVersion new database version
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        public Observable<List<Customer>> getCustomers() {
            /**
             * todo query the db to get the cursor
             * translate cursor by mapToList (CustomerMapper.MAPPER is auto created)
             */
            return query(
                    SELECT(
                            Customer.COL_ID,
                            Customer.COL_FIRSTNAME,
                            Customer.COL_LASTNAME,
                            Customer.COL_ADULT
                    ).FROM(Customer.TABLE_NAME))
                    .run()
                    .mapToList(CustomerMapper.MAPPER);
        }

        /**
         * build a content provider to insert to db.
         * Default if we dont use sqlbrite dao, we can have declare constant which column is add.
         *
         * but simpler if we use sqlbrite dao, so the field in class is the column
         * {@link Customer}
         * todo contentValues is auto create method
         * @param id
         * @param firstname
         * @param lastname
         * @param adult
         *
         * @return
         */
        public Observable<Long> insert(int id, String firstname, String lastname, boolean adult) {
            ContentValues values = CustomerMapper.contentValues()
                    .id(id)
                    .firstname(firstname)
                    .lastname(lastname)
                    .adult(adult)
                    .build();

            return insert(Customer.TABLE_NAME, values);
        }
    }