package mejia.sam.w2_sqliteschoolinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 11/22/2016.
 */

public class DataBaseHandler  extends  SQLiteOpenHelper{

        // All Static variables
        // Data version

        private static final int DATABASE_VERSION = 2;

        // DataBase Name
        private static final String DATABASE_NAME = "contactsManager";

        //Contacts table name
        private static final String TABLE_CONTACTS = "contacts";

        //Contacts Table Columns names
        private static final String KEY_ID = "id";
        private static final String KEY_NAME = "name";
        private static final String KEY_ADRESS = "adress";
        private static final String KEY_PHONE = "phone";

        public DataBaseHandler(Context context){
            super (context, DATABASE_NAME, null, DATABASE_VERSION);
        }


//    Creating tables

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," +KEY_ADRESS + " TEXT,"
                    + KEY_PHONE + " TEXT" + ")";
            db.execSQL(CREATE_CONTACTS_TABLE);
        }

//    Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);


    }


    // Adding new contact
    public void addContent (Content content) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, content.getName()); // Contact Name
        values.put(KEY_ADRESS, content.getAdress()); // Contact Name
        values.put(KEY_PHONE, content.getPhone()); // Contact Phone Number

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public Content getContent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Content content = null;
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_ADRESS,KEY_PHONE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if(cursor.moveToNext()){
             content = new Content(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }

        // return contact
        return content;

    }

    // Getting All Contacts
    public List<Content> getAllContacts() {
        List<Content> contactList = new ArrayList<Content>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Content content= new Content();
                content.setId(Integer.parseInt(cursor.getString(0)));
                content.setName(cursor.getString(1));
                content.setAdress(cursor.getString(2));
                content.setPhone(cursor.getString(3));

                // Adding contact to list
                contactList.add(content);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Getting contacts Count
    public int getContentCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();

    }

    // Updating single contact
    public int updateContent(Content content) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, content.getName());
        values.put(KEY_ADRESS, content.getAdress());
        values.put(KEY_PHONE, content.getPhone());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(content.getId()) });

    }

    // Deleting single contact
    public void deleteContent(Content content) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(content.getId()) });
        db.close();

    }










}
