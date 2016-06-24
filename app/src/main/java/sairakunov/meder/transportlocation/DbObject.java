package sairakunov.meder.transportlocation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DbObject {
    private SQLiteDatabase db;

    public DbObject(Context context) {
        DictionaryDatabase dbHelper = new DictionaryDatabase(context);
        this.db = dbHelper.getWritableDatabase();
    }

    public SQLiteDatabase getDbConnection() {return this.db;}

//    public void closeDbConnection() {
//        if (this.db != null) {
//            this.db.close();
//        }
//    }

}
