package sairakunov.meder.transportlocation;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class DbBackend extends DbObject {

    public DbBackend(Context context) {
        super(context);
    }

    public String[] getIndexes(String number){
        String query = "SELECT indexes FROM marshrut WHERE nomer = " + number;
        Cursor cursor = this.getDbConnection().rawQuery(query, null);
        ArrayList<String> terms = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                String word = cursor.getString(cursor.getColumnIndexOrThrow("indexes"));
                terms.add(word);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        String[] list = new String[terms.size()];
        return terms.toArray(list);
    }

    public double[] getRoutes(String index){
        double[] data = new double[4];
        String query = "SELECT * FROM marshrut WHERE indexes = " + index + " ORDER BY created DESC LIMIT 2";
        Cursor cursor = this.getDbConnection().rawQuery(query, null);
        if(cursor.moveToFirst()){
            data[0] = cursor.getDouble(cursor.getColumnIndexOrThrow("lat"));
            data[1] = cursor.getDouble(cursor.getColumnIndexOrThrow("lon"));
            if (cursor.moveToNext()){
                data[2] = cursor.getDouble(cursor.getColumnIndexOrThrow("lat"));
                data[3] = cursor.getDouble(cursor.getColumnIndexOrThrow("lon"));
            }
        }
        cursor.close();
        return data;
    }
}