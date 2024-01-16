package ma.tdi2.gestioncontacts;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GestionBDContact extends SQLiteOpenHelper {



    private static final String DATABASE_NAME = "Contact.db";
    private static final String TABLE_Contact = "Contact";


    private static final String COL_1 = "id_Contact";
    private static final String COL_2 = "Nom";
    private static final String COL_3 = "MotDePass";
    private static final String COL_4 = "Email";
//constructeur win n3ml creation mt3 base
    public GestionBDContact( Context context) {
        super(context, DATABASE_NAME,null, 1);

//getWritableDatabase() y5dh esm base DATABASE_NAME ychoufha ken mwjouda kn mch mwjouda y3mlelha creation
        SQLiteDatabase db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_Contact = " CREATE TABLE " + TABLE_Contact +
                       "(" +
                        " id_Contact INTEGER PRIMARY KEY,"
                        +" Nom TEXT,"
                        + "MotDePass TEXT,"
                        +" Email TEXT"
                        +")";

        //db hiya base eli sn3neha fi constructeur
        // execSQL - connecter b3d t3ml executer l table contacts fi base
        db.execSQL(CREATE_TABLE_Contact);


    }


    //upgrade yjir ki n3ml changement fi table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion , int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Contact);// yfs5 version 9dima mt3 table
        onCreate(db); // w y3wd y3mlelha creation

    }


//insertion table contacts
    public void insertContact(Contact contact) {
        // 3mltha bch nt2kd mel base mwjouda ken mch mwjouda ysn3ha

        SQLiteDatabase db = getWritableDatabase();
// values objet systeme
        ContentValues values = new ContentValues();

        //n5dh valeur men objet contact w n7otou fi values
        values.put(COL_1 , contact.idContact);
        values.put(COL_2 , contact.Nom);
        values.put(COL_3 , contact.MotDePass);
        values.put(COL_4 , contact.Email);
 // n3abi fi table contact values
        db.insert( TABLE_Contact , null , values);

       // nskr bd
        db.close();

    }

    //update un Contact
    public void updateContact(Contact contact)
    {

        // create and/or open the database for writing

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_2 , contact.Nom);
        values.put(COL_3 , contact.MotDePass);
        values.put(COL_4 , contact.Email);
// condition de modification n9oul col1 n7ot fih idcontact
        //n3ml modification lel nom ... eli d5ltlou id mt3ou
     db.update(TABLE_Contact, values , COL_1 + " = ?",
             new String [] { String.valueOf(contact.idContact)});
     db.close();



    }

 //Delete a contact
    public void deleteContact(Contact contact )
    {
        // create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        //3mlt tableau de string 5tr ynjm ykoun plusieur condition
        db.delete(TABLE_Contact, "id_Contact=?", new String[]
                { String.valueOf(contact.idContact)});
        db.close();
    }

//polymorphisme deux method delete whda b id w whda b objet

    //Delete a contact
    public void deleteContact(int id )
    {
        // create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        //3mlt tableau de string 5tr ynjm ykoun plusieur condition
        db.delete(TABLE_Contact, "id_Contact=?", new String[]
                { String.valueOf(id)});
        db.close();
    }







// getAllContacts lire tous les contacts
    //nmchi l table contact n9ra les ligne eli fiha
//liste view njibou m table contact sql
    @SuppressLint("Range")
    public List<Contact> getAllContacts () {
        List<Contact> listContact = new ArrayList<Contact>();

        String Contacts_SELECT_QUERY = "SELECT * FROM " + TABLE_Contact;

        SQLiteDatabase db = getReadableDatabase(); // ouvrir BD en lecture
        Cursor cursor = db.rawQuery(Contacts_SELECT_QUERY, null);

        if(cursor.moveToFirst())
        {
            do {
                Contact cont = new Contact();
                cont.idContact = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_1)));
                cont.Nom = cursor.getString(cursor.getColumnIndex(COL_2));
                cont.MotDePass = cursor.getString(cursor.getColumnIndex(COL_3));
                cont.Email = cursor.getString(cursor.getColumnIndex(COL_4));

                listContact.add(cont);

            }  while (cursor.moveToNext());
    }
 return listContact;
  }

}



























// SQLiteDatabase declarer variable db de type SQLiteDatabase
// getWritableDatabase() ysn3 base b esm db
//oncreat  nsn3ou feha les tables contact
//onupgrade supprimer les tables w n3wdou nsn3ouhom yfs5 version 9dima w ysn3 version jdida ki tbda fama changement
//onupgrade lancer la suppression de la table b3d ndiw fonction on creat