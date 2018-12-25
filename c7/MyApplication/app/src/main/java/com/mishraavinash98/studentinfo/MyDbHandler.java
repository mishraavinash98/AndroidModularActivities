package com.mishraavinash98.studentinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDbHandler extends SQLiteOpenHelper {

    Context context;
    SQLiteDatabase db;

    MyDbHandler(Context context){
        //DBCreation
        super(context,"studentdb",null,1);
        this.context=context;
        db=this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        //table creation
        String sql="create table student(rno integer primary key,name text)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void addRecord(int rno,String name){

        ContentValues cv=new ContentValues();
        cv.put("rno",rno);
        cv.put("name",name);

        long rid=db.insert("student",null,cv);

        if(rid<0){
            Toast.makeText(context, "Insert issue", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Record added", Toast.LENGTH_SHORT).show();
    }
    public String viewRecord(){

        StringBuffer sb=new StringBuffer();
        Cursor c=db.query("student",null,null,null,null,null,null);

        if(c.getCount()>0)
        {
            c.moveToFirst();
            do{
                String rno=c.getString(c.getColumnIndex("rno"));
                String name=c.getString(c.getColumnIndex("name"));

                sb.append("Roll No="+rno+" Name="+name+"\n");

            }while (c.moveToNext());
        }
        return sb.toString();

    }

    public void updateRecord(int rno,String name){

        ContentValues cv=new ContentValues();
        cv.put("rno",rno);
        cv.put("name",name);

        long rid=db.update("student",cv,"rno="+rno,null);

        if(rid<0){
            Toast.makeText(context, "Update issue", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Record updated", Toast.LENGTH_SHORT).show();
    }

    public void deleteRecord(int rno){

        long rid=db.delete("student","rno="+rno,null);

        if(rid<0){
            Toast.makeText(context, "Delete issue", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Record deleted ", Toast.LENGTH_SHORT).show();
    }

}
