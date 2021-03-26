package com.city2farmer;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;
import android.util.Log;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class HappyfarmDB extends SQLiteOpenHelper {
//    =======================7====================
    public String sCreateTableCommand;
    private static final String COLUMN_ = "VitC";
    Context context;
    SQLiteDatabase db;
    private Resources mResources;
    public static final String DB_TABLE07 = "nutrition";
    public static final String COLUMN_NAME = "Plant";
    public static final String COLUMN_ITEM = "Calories";
    public static final String COLUMN_Content = "K";
    public static final String COLUMN_Description = "VitA";
    private static final String SQL_CREATE_BUGS_TABLE = "CREATE TABLE " + DB_TABLE07 + " (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_NAME + " TEXT  NOT NULL, " +
            COLUMN_ITEM + " INTEGER NOT NULL, " +
            COLUMN_Content + " INTEGER NOT NULL, " +
            COLUMN_Description + " INTEGER NOT NULL, " +
            COLUMN_ + " FLOAT NOT NULL " + " );";
    //    =======================7=========================
    // 資料庫名稱=========12================
    private static final String TAG = HappyfarmDB.class.getSimpleName();
    private static final String DB_File = "happyfarm.db";
    private static final String DB_TABLE12 = "HA1200"; // 資料庫物件，固定的欄位變數
    public static final int VERSION = 7;
    private static final String crTBsql12 = " CREATE TABLE " + DB_TABLE12 + " (l0101 INTEGER PRIMARY KEY, l0102 TEXT NOT NULL, l0103 TEXT, l0104 TEXT, l0105 TEXT, l0106 TEXT, l0107 TEXT, l0108 TEXT); ";
    private static SQLiteDatabase database;
    private String totle = "全區";
    //==================12===========================
//========================5=================================
    private static final String DB_TABLE05 = "ha0500";    // 資料庫物件，固定的欄位變數

    private static final String crTBsql05 = " CREATE TABLE " + DB_TABLE05 + " (n0101 INTEGER PRIMARY KEY, n0102 TEXT NOT NULL, n0103 TEXT, n0104 TEXT, n0105 TEXT,n0106 TEXT); ";
//=======================5==================================
//=======================6==================================
private static final String DB_TABLE06 = "HA0600"; // 資料表名稱
    private static final String crTBsql06 = "CREATE TABLE " + DB_TABLE06 + " ( "
            + "id INTEGER PRIMARY KEY," +"SE001 TEXT NOT NULL , "
            + "CroNam TEXT NOT NULL,"
            + "PN001 TEXT,"
            + "PN002 TEXT,"
            + "PU001 TEXT,"
            + "PU002 TEXT,"
            + "ST001 TEXT,"
            + "ST002 TEXT);";// 資料庫物件，固定的欄位變數
//=======================6==================================
//===========================13===============================
    private static final String DB_TABLE13 = "HA1301";    // 資料庫物件，固定的欄位變數

    private static final String crTBsql13 = " CREATE TABLE " + DB_TABLE13 + " (r0201 INTEGER PRIMARY KEY, r0202 TEXT , r0203 TEXT, r0204 TEXT,r0205 TEXT); ";
//=========================13====================================
//========================14=================================
    private static final String DB_TABLE14 = "HA1400";    // 資料庫物件，固定的欄位變數

    private static final String crTBsql14 = "CREATE     TABLE   " + DB_TABLE14 + " (y0101 INTEGER PRIMARY KEY, y0102 TEXT NOT NULL, y0103 TEXT, y0104 TEXT, y0105 TEXT, y0106 TEXT); ";
//=======================14_===================================

    public HappyfarmDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
        super(context, DB_File, null, VERSION);
        sCreateTableCommand = "";

        mResources = context.getResources();
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //================使用別人的APP================================
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        //======================================================

        db.execSQL(crTBsql05);
        db.execSQL(crTBsql06);
        db.execSQL(SQL_CREATE_BUGS_TABLE);
        db.execSQL(crTBsql13);
        db.execSQL(crTBsql12);
        db.execSQL(crTBsql14);

//        ===============07==================

//        final String SQL_CREATE_BUGS_TABLE = "CREATE TABLE " + DB_TABLE07 + " (" +
//                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
//                COLUMN_NAME + " TEXT  NOT NULL, " +
//                COLUMN_ITEM + " INTEGER NOT NULL, " +
//                COLUMN_Content + " INTEGER NOT NULL, " +
//                COLUMN_Description + " INTEGER NOT NULL, " +
//                COLUMN_ + " FLOAT NOT NULL " + " );";

        //db.execSQL(SQL_CREATE_BUGS_TABLE);

        Log.d(TAG, "Database Created Successfully");

        try {
            readDataToDb(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        ==============07_====================

    }

    //=====================07==========================
    private void readDataToDb(SQLiteDatabase db) {
        final String MNU_NAME = "Plant";
        final String MNU_ITEM = "Calories";
        final String MNU_CONTENT = "K";
        final String MNU_DESCRIPTION = "VitA";
        final String MNU_ = "VitC";

        try {
            String jsonDataString = readJsonDataFromFile();
            JSONArray menuItemsJsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < menuItemsJsonArray.length(); ++i) {

                String name;
                String item;
                String content;
                String description;
                String VitC;

                JSONObject menuItemObject = menuItemsJsonArray.getJSONObject(i);

                name = menuItemObject.getString(MNU_NAME);
                item = menuItemObject.getString(MNU_ITEM);
                content = menuItemObject.getString(MNU_CONTENT);
                description = menuItemObject.getString(MNU_DESCRIPTION);
                VitC = menuItemObject.getString(MNU_);

                ContentValues menuValues = new ContentValues();

                menuValues.put(COLUMN_NAME, name);
                menuValues.put(COLUMN_ITEM, item);
                menuValues.put(COLUMN_Content, content);
                menuValues.put(COLUMN_Description, description);
                menuValues.put(COLUMN_, VitC);

                db.insert(DB_TABLE07, null, menuValues);
                Log.d(TAG, "Inserted Successfully " + menuValues);
            }

        } catch (JSONException | IOException e) {
            Log.e(TAG, e.getMessage(), e);
            e.printStackTrace();
        }
    }

    private String readJsonDataFromFile() throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {
            String jsonDataString = null;
            inputStream = mResources.openRawResource(R.raw.nutrition_0928);

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));
            while ((jsonDataString = bufferedReader.readLine()) != null) {
                builder.append(jsonDataString);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }
//    public ArrayList<String> getRecSet() {
//        SQLiteDatabase db = getReadableDatabase();
//        String sql = "SELECT * FROM " + DB_TABLE07;
//        Cursor recSet = db.rawQuery(sql, null);
//        ArrayList<String> recAry = new ArrayList<String>();
//
//        //----------------------------
//        Log.d(TAG, "recSet=" + recSet);
//        int columnCount = recSet.getColumnCount();
//        while (recSet.moveToNext()) {
//            String fldSet = "";
//            for (int i = 0; i < columnCount; i++)
//                fldSet += recSet.getString(i) + "#";
//            recAry.add(fldSet);
//        }
//        //------------------------
//        recSet.close();
//        //db.close();
//
//        Log.d(TAG, "recAry=" + recAry);
//        return recAry;
//    }

    public String[] getOneRec(int position) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE07 +" WHERE `ID`="+position+";";
        Cursor nw = db.rawQuery(sql, null);
        int columnCount = nw.getColumnCount();
        String[] nwAry =new String[columnCount];
        while (nw.moveToNext()) {

            for (int i = 0; i < columnCount; i++){
                String yy = nw.getString(0);
                nwAry[i]=nw.getString(i);
            }
        }
        nw.close();
        return nwAry;
    };
//    =====================07_===========================

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        ddl 語法
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE05);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE06);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE07);
        //onCreate(db);
        db.execSQL("  DROP  TABLE  IF  EXISTS  " + DB_TABLE12);
        db.execSQL("  DROP  TABLE  IF  EXISTS  " + DB_TABLE13);
        db.execSQL("  DROP  TABLE  IF  EXISTS  " + DB_TABLE14);
        onCreate(db);
    }

    // 需要資料庫的元件呼叫這個方法，這個方法在一般的應用都不需要修改
//    ====================12==============================
    public static SQLiteDatabase getDatabase(Context context) {
        if (database == null || !database.isOpen()) {
            database = new HappyfarmDB(context, DB_File, null, VERSION)
                    .getWritableDatabase();
        }
        return database;
    }

    public int RecCount() {

        SQLiteDatabase db = getWritableDatabase();
        //        這串語法是記算選擇的table有幾筆資料
        String sql = "SELECT * FROM " + DB_TABLE12;
        Cursor recSet = db.rawQuery(sql, null);
        return recSet.getCount();
    }

    //    縣市
    public List<String> getAll_l0103() {
        List<String> labels = new ArrayList<String>();

        String selectQuery = "SELECT  * FROM " + DB_TABLE12;

        SQLiteDatabase db = this.getReadableDatabase();  //讀
        Cursor cursor = db.rawQuery(selectQuery, null);

        String s = ""; //跟labels比對用

        if (cursor.moveToFirst()) {
            do {
//                如果重複就跳出
                if (!labels.contains(s)) {
                    labels.add(cursor.getString(2));
                }
                s = cursor.getString(2);
            } while (cursor.moveToNext());
        }
//
//        cursor.close();
//        db.close();

        return labels;
    }

    //    縣市
    public List<String> getAll_l0104(String company) {
        List<String> labels = new ArrayList<String>();
//        用HashSet去判斷裡面的所有資料有沒有重複
        Set<String> set = new HashSet<String>();

        String selectQuery = "SELECT  * FROM " + DB_TABLE12 + " WHERE l0103 = '" + company + "' ";

        SQLiteDatabase db = this.getReadableDatabase();  //讀
        Cursor cursor = db.rawQuery(selectQuery, null);

        labels.add(totle);
        if (cursor.moveToFirst()) {
            do {
////                用以下的方法不行判斷亂排的資料有沒有重複
//               if(!labels.contains(s)){
//                    labels.add(cursor.getString(3));
//               }
//               s = cursor.getString(3);

//                要用HashSet去看全部的資料有沒有重複
                set.add(cursor.getString(3));
            } while (cursor.moveToNext());
        }
        labels.addAll(set);

//        cursor.close();
//        db.close();

        return labels;
    }

    //    公司名稱,地址,電話
    public ArrayList<Map<String, Object>> getAll_l0105(String counties, String asdt) {

        List<String> labels = new ArrayList<String>();
        String selectQuery = "";
        if(asdt == totle){
            selectQuery = "SELECT  * FROM " + DB_TABLE12 + " WHERE l0103 = '" + counties + "' ";
        }else {
            selectQuery = "SELECT  * FROM " + DB_TABLE12 + " WHERE l0104 = '" + asdt + "' AND l0103 = '" + counties + "' ";
        }
        SQLiteDatabase db = this.getReadableDatabase();  //讀
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<Map<String, Object>> mLsit = new ArrayList<Map<String, Object>>();
//        for(int i=0;i<arrayshop.length;i++) {
//            Map<String, Object> item = new HashMap<String, Object>();
//            item.put("shopname", arrayshop[i][0]);
//            item.put("address", arrayshop[i][1]);
//            item.put("telphone", arrayshop[i][2]);
//            mLsit.add(item);

        if (cursor.moveToFirst()) {
            do {
                Map<String, Object> item = new HashMap<String, Object>();
                item.put("shopname", cursor.getString(1));
                item.put("address", cursor.getString(4));
                item.put("telphone", cursor.getString(5));
                mLsit.add(item);
            } while (cursor.moveToNext());
        }

//        cursor.close();
//        db.close();

        return mLsit;
    }
//    ==========================12_=============================


    //    =====================5===============================
    public Long insertRec05(String b_n0102, String b_n0103, String b_n0104, String b_n0105) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues rec = new ContentValues();
        //rec.put("n0101", b_n0101);
        rec.put("n0102", b_n0102);
        rec.put("n0103", b_n0103);
        rec.put("n0104", b_n0104);
        rec.put("n0105", b_n0105);
        long rowID = db.insert(DB_TABLE05, null, rec);
        db.close();
        return rowID;
    }

    public Integer RecCount05() {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE05;
        //選擇的TABLE有幾筆
        Cursor recSet = db.rawQuery(sql, null);
        return recSet.getCount();
    }

    //============測試清空資料庫==================
    public int clearRec05() {
        SQLiteDatabase db = getWritableDatabase(); //寫入資料庫
        String sql = "SELECT * FROM " + DB_TABLE05;   //帶入資料表資料
        Cursor recSet = db.rawQuery(sql, null); //查詢資料
        //------------------------------------------------
        if (recSet.getCount() != 0) {
//			String whereClause = "id < 0";
            int rowsAffected = db.delete(DB_TABLE05, "1", null); //1代表全部
            // From the documentation of SQLiteDatabase delete method:
            // To remove all rows and get a count pass "1" as the whereClause.
            //db.close();
            return rowsAffected;    //傳回值
        } else {
           //db.close();
            return -1;  //-1傳回空值
        }
    }

    //==============修改筆記=============================
    public int updateRec05(String b_n0101, String b_n0102, String b_n0103, String b_n0104, String b_n0105) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = " SELECT * FROM " + DB_TABLE05;
        Cursor recSet = db.rawQuery(sql, null);
        if (recSet.getCount() != 0) {
            ContentValues rec = new ContentValues();
            rec.put("n0102", b_n0102);
            rec.put("n0103", b_n0103);
            rec.put("n0104", b_n0104);
            rec.put("n0105", b_n0105);
            String whereClause = " n0101= '" + b_n0101 + "'"; //帶入ID
            int rowsAffected05 = db.update(DB_TABLE05, rec, whereClause, null);    //whereClause只更改當筆資料
            recSet.close();
            //db.close();
            return rowsAffected05;
        } else {
            recSet.close();
            //db.close();
            return -1;
        }
    }

    public ArrayList<String> getRecSet05() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE05;
        Cursor recSet05 = db.rawQuery(sql, null);
        ArrayList<String> recAry05 = new ArrayList<String>();
        //----------------------------
        Log.d(TAG, "recSet05=" + recSet05);
        int columnCount = recSet05.getColumnCount();

        while (recSet05.moveToNext()) {
            String fldSet05 = "";
            for (int i = 0; i < columnCount; i++)
                fldSet05 += recSet05.getString(i) + "#";
            recAry05.add(fldSet05);
        }
        //------------------------
        recSet05.close();
        //db.close();
        Log.d(TAG, "recAry05=" + recAry05);
        return recAry05;
    }
//================deleteRec確定刪除===========================
    public int deleteRec05(String b_n0101) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE05;
        Cursor recSet05 = db.rawQuery(sql, null);

        if (recSet05.getCount() != 0) {
            String whereClause = " n0101= '" + b_n0101 + "'";
            int rowsAffected = db.delete(DB_TABLE05, whereClause, null); //
            // From the documentation of SQLiteDatabase delete method:
            // To remove all rows and get a count pass "1" as the whereClause.
            recSet05.close();
            //db.close();
            return rowsAffected;
        } else {
            recSet05.close();
            //db.close();
            return -1;
        }
    }
    //==============新增================
//    ContentValues values
    public long insertRec05_m(ContentValues rec) {
        SQLiteDatabase db = getWritableDatabase();
        long rowID05 = db.insert(DB_TABLE05, null, rec);
        db.close();
        return rowID05;
    }
//    ==================5==============================

    //====================6===============================
    //    ContentValues values
    public long insertRec_m06 (ContentValues rec){
        SQLiteDatabase db = getWritableDatabase();
        long rowID06 = db.insert(DB_TABLE06, null, rec);
        db.close();
        return rowID06;
    }
    public int RecCount06() {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE06;
        Cursor recSet06 = db.rawQuery(sql, null);
        return recSet06.getCount();
    }

    public ArrayList<String> getRecSet06() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE06;
        Cursor recSet06 = db.rawQuery(sql, null);
        ArrayList<String> recAry06 = new ArrayList<String>();

        //----------------------------
        Log.d(TAG, "recSet06=" + recSet06);
        int columnCount = recSet06.getColumnCount();

        while (recSet06.moveToNext()) {
            String fldSet = "";
            for (int i = 0; i < columnCount; i++)
                fldSet += recSet06.getString(i) + "#";
            recAry06.add(fldSet);
        }
        //------------------------
        recSet06.close();
        db.close();

        Log.d(TAG, "recAry06=" + recAry06);
        return recAry06;
    }


//    public void createTB06() {
//        // 批次新增
//        int maxrec = 40;
//        SQLiteDatabase db = getWritableDatabase();
//        for (int i = 0; i < maxrec; i++) {
//            ContentValues newRow = new ContentValues();
//            newRow.put("CroNam", "作物" + u_chinayear(i));
//            newRow.put("PN001", "昆蟲" + u_chinano((int) (Math.random() * 4 + 1)) + "號");
//            newRow.put("PU001", "https://i.imgur.com/eHpPvXD.jpg ");
//            newRow.put("ST001", "處理方法" + (1+ i) );
//            newRow.put("PN002", "病害" + u_chinano((int) (Math.random() * 4 + 1)) + "號");
//            newRow.put("PU002", "https://i.imgur.com/jfRBzDC.jpg ");
//            newRow.put("ST002", "處理方法" + (1+ i));
//            db.insert(DB_TABLE06, null, newRow);
//        }
//        //db.close();
//    }

//    private String u_chinano(int input_i) {
//        String c_number = "";
//        String china_no[] = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
//        c_number = china_no[input_i % 10];
//
//        return c_number;
//    }
//
//    private String u_chinayear(int input_i) {
//        String c_number = "";
//        String china_no[] = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
//        c_number = china_no[input_i % 10];
//
//        return c_number;
//    }

    public int clearRec06() {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE06;
        Cursor recSet06 = db.rawQuery(sql, null);
//        //-----------
//        Cursor c1=db.execSQL("");
//        Cursor c2=db.rawQuery();
//        Cursor c3=db.insert();
//        Cursor c4=db.update(, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , );
//        Cursor c5=db.delete();
//        //------------------------------------------------
        if (recSet06.getCount() != 0) {
//			String whereClause = "id < 0";
            int rowsAffected = db.delete(DB_TABLE06, "1", null); //
            // From the documentation of SQLiteDatabase delete method:
            // To remove all rows and get a count pass "1" as the whereClause.
            //db.close();
            return rowsAffected;
        } else {
            //db.close();
            return -1;
        }
    }

    public int updateRec06 (String b_id, String b_crop, String b_pes1, String b_url1, String
            b_sol1, String b_pes2, String b_url2, String b_sol2){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE06;
        Cursor recSet06 = db.rawQuery(sql, null);
        if (recSet06.getCount() != 0) {
            ContentValues rec = new ContentValues();
            //rec.put("id", b_id);
            rec.put("CroNam", b_crop);
            rec.put("PN001", b_pes1);
            rec.put("PU001", b_url1);
            rec.put("ST001", b_sol1);
            rec.put("PN002", b_pes2);
            rec.put("PU002", b_url2);
            rec.put("ST002", b_sol2);
            String whereClause = "id='" + b_id + "'";

            int rowsAffected06 = db.update(DB_TABLE06, rec, whereClause, null);
            recSet06.close();
            db.close();
            return rowsAffected06;
        } else {
            db.close();
            return -1;
        }
    }

    public int deleteRec06 (String b_id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE06;
        Cursor recSet06 = db.rawQuery(sql, null);
        if (recSet06.getCount() != 0) {
            String whereClause = "id='" + b_id + "'";
            int rowsAffected06 = db.delete(DB_TABLE06, whereClause, null); //
            // From the documentation of SQLiteDatabase delete method:
            // To remove all rows and get a count pass "1" as the whereClause.
            recSet06.close();
            db.close();
            return rowsAffected06;
        } else {
            recSet06.close();
            db.close();
            return -1;
        }
    }

    public List<String> getCroNam(String b_season) {
        SQLiteDatabase db = getReadableDatabase();  //讀
        List<String> labels = new ArrayList<String>();
//        用HashSet去判斷裡面的所有資料有沒有重複
        Set<String> set = new HashSet<String>();

        String sql = "SELECT  * FROM " + DB_TABLE06 + " WHERE SE001 = '" + b_season + "' ";
        int aa=0;
        Cursor cursor = db.rawQuery(sql, null);
        int bb=0;

        if (cursor.moveToFirst()) {
            do {
                set.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        labels.add("選擇作物");
        labels.addAll(set);

        cursor.close();
        db.close();

        return labels;
    }

    public ArrayList<String> getpest_info(String b_lSeason, String b_lCrop) {
        SQLiteDatabase db = getReadableDatabase();  //讀
//        用HashSet去判斷裡面的所有資料有沒有重複
        String sql = "SELECT * FROM " + DB_TABLE06 +
                " WHERE SE001 = '" +b_lSeason+ "' AND CroNam = '" +b_lCrop+"' ";

        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<String> labels = new ArrayList<String>();
        //-----------------------------------------------
        int columnCount = cursor.getColumnCount();
        while (cursor.moveToNext()) {
            String fldSet = "";
            for (int i = 3; i < columnCount; i++)
                fldSet += cursor.getString(i) + "#";
            labels.add(fldSet);
        }

        cursor.close();
        db.close();

        return labels;
    }
    //====================6================================
    //====================13===============================
    public int clearRec13() { //清空\
        SQLiteDatabase db = getWritableDatabase(); //寫入資料庫
        String sql = "SELECT * FROM " + DB_TABLE13;   //帶入資料表資料
        Cursor recSet = db.rawQuery(sql, null); //查詢資料
        //------------------------------------------------
        if (recSet.getCount() != 0) {
//			String whereClause = "id < 0";
            int rowsAffected = db.delete(DB_TABLE13, "1", null); //1代表全部
            // From the documentation of SQLiteDatabase delete method:
            // To remove all rows and get a count pass "1" as the whereClause.
            //db.close();
            return rowsAffected;    //傳回值
        } else {
            //db.close();
            return -1;  //-1傳回空值
        }
    }

    public Long insertRec13(String b_r0202, String b_r0203, String b_r0204, String b_r0205) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues rec = new ContentValues();

        rec.put("r0202", b_r0202);  //googleId
        rec.put("r0203", b_r0203); //state=1
        rec.put("r0204", b_r0204);  //email
        rec.put("r0205", b_r0205);  //PhotoUrl
        long rowID = db.insert(DB_TABLE13, null, rec);
        //db.close();
        return rowID;
    }
    //===============13=======================================



    //===================14=====================================
    public void db14(int b_y0101, String b_y0102, String b_y0103, String b_y0104) {
        SQLiteDatabase db =getWritableDatabase();
        String sql = "SELECT * FROM" +DB_TABLE14;
        ContentValues r14 = new ContentValues();
        r14.put("y0101",b_y0101);
        r14.put("y0102",b_y0102);
        r14.put("y0103",b_y0103);
        r14.put("y0104",b_y0104);
        db.insert(DB_TABLE14,null,r14);
    }

    public List<String> redb(int i, int x) {
        List<String> randb14 = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + DB_TABLE14;
        SQLiteDatabase db = this.getReadableDatabase();  //讀
        Cursor cursor = db.rawQuery(selectQuery, null);
        String a = "";
        if (cursor.moveToPosition(i)) {
            if(!randb14.contains(a)){
                randb14.add(cursor.getString(x));
            }
        }
        cursor.close();
        db.close();
        return randb14;
    }
//=======================14_=================================
//==================登入=================================
public boolean status() {//收尋email
    SQLiteDatabase db = getReadableDatabase();
    boolean fldSet = false;
    String sql = "SELECT  r0203  FROM " + DB_TABLE13 ;

    Cursor recSet = db.rawQuery(sql, null);

    int columnCount = recSet.getColumnCount();

    if (recSet.getCount() != 0) {
        recSet.moveToFirst();
        if(recSet.getString(0).equals("1")){
            fldSet=true;
        }
    }
    recSet.close();
    db.close();
    return fldSet;
}

    public String Find(String str) {
        SQLiteDatabase db = getReadableDatabase();
        String fldSet = null;
        String sql = "SELECT "+str+"  FROM " + DB_TABLE13 ;


        Cursor recSet = db.rawQuery(sql, null);

        int columnCount = recSet.getColumnCount();

        if (recSet.getCount() != 0) {
            recSet.moveToFirst();
            fldSet = recSet.getString(0);
        }
        recSet.close();
        db.close();
        return fldSet;
    }


}
