package com.example.appdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_BET = "USER_BET";
    public static final String COLUMN_BET_PUBLIC = "BET_PUBLIC";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    //called the first time a db is accessed
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + USER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, " + COLUMN_USER_BET + " INT, " + COLUMN_BET_PUBLIC + " BOOLEAN)";

        db.execSQL(createTableStatement);

    }


    //called with every db version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne (UserModel userModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_NAME, userModel.getName());
        cv.put(COLUMN_USER_BET, userModel.getBet());
        cv.put(COLUMN_BET_PUBLIC, userModel.getIsPublic());

        long insert = db.insert(USER_TABLE, null, cv);
        if (insert == -1){
            return false;
        }
        else {
            return true;
        }


    }

    public boolean deleteOne(UserModel userModel) {
        //find usermodel in db. if found delete it and return true
        //if not found,  return false

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + USER_TABLE + " WHERE " + COLUMN_ID + " = " + userModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        }

        else {
            return false;
        }


    }

    public List<UserModel> getAll() {

        List<UserModel> returnList = new ArrayList<>();

        //get data from db

        String queryString = "SELECT * FROM " + USER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            //loop through cursor and create new user objects, put them in returnList

            do {

                int userID = cursor.getInt(0);
                String userName = cursor.getString(1);
                int userBet = cursor.getInt(2);
                boolean userPublic = cursor.getInt(3) ==1 ? true: false;

                UserModel newBet = new UserModel(userID, userName, userBet, userPublic);

                returnList.add(newBet);

            }
            while (cursor.moveToNext());

        }

        else {
            //failure
        }

        //close cursor and db
        cursor.close();
        db.close();

        return returnList;
    }

}
