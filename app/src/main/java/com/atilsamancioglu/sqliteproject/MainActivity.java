package com.atilsamancioglu.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //Musicians databasesi oluşturuldu yada varsa açıldı diyebiliriz
            SQLiteDatabase database = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
            //musicians tablosu yoksa oluşturuldu id primeriy key , name varchar ve age int olarak açıldı
            //PRIMARY KEY girerken INT olmaz INTEGER olması şart
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY, name VARCHAR, age INT)");

            database.execSQL("INSERT INTO musicians (name, age) VALUES ('James',50)");
            database.execSQL("INSERT INTO musicians (name, age) VALUES ('Lars',60)");
            database.execSQL("INSERT INTO musicians (name, age) VALUES ('Kirk',55)");

            //database.execSQL("UPDATE musicians SET age = 61 WHERE name = 'Lars'");
            //database.execSQL("UPDATE musicians SET name = 'Kirk Hammett' WHERE id = 3");

            //database.execSQL("DELETE FROM musicians WHERE id = 2");


            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name = 'James'",null);
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE 'K%'",null);
            //select için böle bir sınıfı kullanıyoruz allatki gibi
            Cursor cursor = database.rawQuery("SELECT * FROM musicians",null);
            //cursor sorgusunun name adlı kolonunun indexini alır
            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int idIx = cursor.getColumnIndex("id");
            //cursor sorgusunun sonuna kadar okuması demek
            while (cursor.moveToNext()) {
                //cursor sorgusunun o indesinin datasını okumasını sağlar
                System.out.println("Name: " + cursor.getString(nameIx));
                System.out.println("Age: " + cursor.getInt(ageIx));
                System.out.println("Id: " + cursor.getInt(idIx));
            }
            //sorgu kapatma sanıırım
            cursor.close();
            //oluşturulan veritabanı vs appi silerek temizledik yada delete diyede yapabilirdik


        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
