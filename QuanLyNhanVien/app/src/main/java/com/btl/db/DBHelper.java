package com.btl.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.btl.model.Account;
import com.btl.model.ChamCong;
import com.btl.model.Employee;
import com.btl.model.Luong;
import com.btl.model.PhongBan;

import java.util.ArrayList;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =31;
    private static final String DATABASE_NAME = "EmployeeManager";
    private static final String TABLE_Employee = "employee";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRES = "address";
    private static final String KEY_GT = "gioitinh";
    private static final String KEY_PHONE = "phone_number";
    private static final String KEY_CHUCVU = "chucvu";
    private static final String KEY_PHONGBAN = "id_pb";
    private static final String KEY_BACLUONG = "id_bl";

    private static final String TABLE_BACLUONG = "BacLuong";
    private static final String KEY_ID_BACLUONG="idbacluong";
    private static final String KEY_LCB_BACLUONG="LCB";
    private static final String KEY_HSL_BACLUONG="HSL";
    private static final String KEY_PHUCAP="PC";

    private static final String TABLE_PHONGBAN = "PhongBan";
    private static final String KEY_ID_PHONGBAN="idphongban";
    private static final String KEY_TEN_PHONGBAN="tenpb";
    private static final String KEY_DIADIEM_PHONGBAN="diadiem";

    private static final String TABLE_ACCOUNT = "ACCOUNT";
    private static final String KEY_ID_ACCOUNT="idnv";
    private static final String KEY_TEN_ACCOUNT="user";
    private static final String KEY_PASS="password";

    private static final String TABLE_CHAMCONG = "chamcong";
    private static final String KEY_ID_CHAMCONG="id_cc";
    private static final String KEY_NHANVIEN="idnv";
    private static final String KEY_THOIGIAN="thoigian";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_Employee_TABLE = "CREATE TABLE " + TABLE_Employee +"(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME +" TEXT ," + KEY_GT + " TEXT," + KEY_ADDRES+ " TEXT," + KEY_PHONE+ " TEXT, " + KEY_CHUCVU + " TEXT ,"
                + KEY_PHONGBAN +" INTEGER, " + KEY_BACLUONG + " INTEGER," +
                " FOREIGN KEY( " + KEY_PHONGBAN + " ) REFERENCES " + TABLE_PHONGBAN + " ( " + KEY_ID_PHONGBAN + "), " +
                " FOREIGN KEY( " + KEY_BACLUONG + " ) REFERENCES " + TABLE_BACLUONG + " ( " + KEY_ID_BACLUONG + "))";

        String create_accCount="CREATE TABLE " + TABLE_ACCOUNT + "(" + KEY_ID_ACCOUNT + " INTEGER PRIMARY KEY  AUTOINCREMENT," + KEY_TEN_ACCOUNT+ " TEXT ," + KEY_PASS+ " TEXT, FOREIGN KEY(" + KEY_ID_ACCOUNT+ ") REFERENCES "+ TABLE_Employee + "(" + KEY_ID + ") )";

        String CREATE_BACLUONG_TABLE ="CREATE TABLE " + TABLE_BACLUONG + "(" +KEY_ID_BACLUONG +" INTEGER PRIMARY KEY  AUTOINCREMENT, "+KEY_LCB_BACLUONG +" FLOAT, "
                +KEY_HSL_BACLUONG +" FLOAT, "+KEY_PHUCAP+" FLOAT)";

        String CREATE_PHONGBAN_TABLE="CREATE TABLE "+ TABLE_PHONGBAN + "(" +KEY_ID_PHONGBAN +" INTEGER PRIMARY KEY  AUTOINCREMENT, "+KEY_TEN_PHONGBAN +" TEXT, "
                +KEY_DIADIEM_PHONGBAN +" TEXT)";

        String CREATE_CHAMCONG_TABLE = "CREATE TABLE " + TABLE_CHAMCONG + "( "+ KEY_ID_CHAMCONG + " INTEGER PRIMARY KEY  AUTOINCREMENT," + KEY_NHANVIEN + " INTEGER," + KEY_THOIGIAN + " TEXT ,FOREIGN KEY(" + KEY_NHANVIEN+ ") REFERENCES "+ TABLE_Employee + "(" + KEY_ID + "))";

        db.execSQL(create_accCount);
        db.execSQL(CREATE_PHONGBAN_TABLE);
        db.execSQL(CREATE_BACLUONG_TABLE);
        db.execSQL(CREATE_Employee_TABLE);
        db.execSQL(CREATE_CHAMCONG_TABLE);

    }
    @Override
    public void onConfigure(SQLiteDatabase db){
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CHAMCONG);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_Employee);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BACLUONG);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PHONGBAN);

        onCreate(db);
    }
    public ArrayList<Employee> getAllEmployee() {

        ArrayList<Employee> EmployeeList = new ArrayList<Employee>();
        String selectQuery = "SELECT  * FROM " + TABLE_Employee;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Employee e=new Employee();
                e.setId(Integer.parseInt(cursor.getString(0)));
                e.setName(cursor.getString(1));
                e.setGioiTinh(cursor.getString(2));
                e.setAddr(cursor.getString(3));
                e.setSdt(cursor.getString(4));
                e.setChucVu(cursor.getString(5));
                e.setIDPB(Integer.parseInt(cursor.getString(6)));
                e.setId_bacluong(Integer.parseInt(cursor.getString(7)));

                EmployeeList.add(e);

            } while (cursor.moveToNext());
        }

        return EmployeeList;
    }
    public ArrayList<Employee> getEmployeeNotAC() {

        ArrayList<Employee> EmployeeList = new ArrayList<Employee>();
        String selectQuery = "SELECT  * FROM " + TABLE_Employee + " where id not in(select idnv FROM ACCOUNT)" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Employee e=new Employee();
                e.setId(Integer.parseInt(cursor.getString(0)));
                e.setName(cursor.getString(1));
                e.setGioiTinh(cursor.getString(2));
                e.setAddr(cursor.getString(3));
                e.setSdt(cursor.getString(4));
                e.setChucVu(cursor.getString(5));
                e.setIDPB(Integer.parseInt(cursor.getString(6)));

                EmployeeList.add(e);
            } while (cursor.moveToNext());
        }

        return EmployeeList;
    }
    public ArrayList<Employee> getAllEmployee(String s) {

        ArrayList<Employee> EmployeeList = new ArrayList<Employee>();
        String selectQuery = "SELECT * FROM " + TABLE_Employee + " WHERE "
                + KEY_NAME + " LIKE '%" + s + "%' OR "+KEY_ADDRES+" LIKE '%"+s+"%' OR "+KEY_CHUCVU+" LIKE '%"+s+"%' OR "+
                KEY_PHONE +" LIKE '%"+s+"%' OR "+KEY_GT +" LIKE '%"+s+"'" +
                " OR "+KEY_PHONGBAN +" LIKE '%"+s+"' OR "+KEY_BACLUONG +" LIKE '%"+s+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Employee e=new Employee();
                e.setId(Integer.parseInt(cursor.getString(0)));
                e.setName(cursor.getString(1));
                e.setGioiTinh(cursor.getString(2));
                e.setAddr(cursor.getString(3));
                e.setSdt(cursor.getString(4));
                e.setChucVu(cursor.getString(5));

                EmployeeList.add(e);
            } while (cursor.moveToNext());
        }

        return EmployeeList;
    }
    public void addEmployee(Employee employee) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, employee.getName());
        values.put(KEY_GT, employee.getGioiTinh());
        values.put(KEY_ADDRES, employee.getAddr());
        values.put(KEY_PHONE,employee.getSdt());
        values.put(KEY_CHUCVU,employee.getChucVu());
        values.put(KEY_PHONGBAN, employee.getIDPB());
        values.put(KEY_BACLUONG, employee.getId_bacluong());

        db.insert(TABLE_Employee, null, values);
        db.close();
    }
    public int deleteEmployee(int ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
           db.delete(TABLE_Employee, KEY_ID + " = ?",new String[]{String.valueOf(ID)});
        }
        catch (Exception e){
            return 0;
        }
        db.close();
        return 1;



    }
    public void deleteAccount(int ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("ACCOUNT",  " idnv = ?",new String[]{String.valueOf(ID)});
        db.close();
    }
    public  void updateEmployee(Employee employee){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_NAME,employee.getName());
        contentValues.put(KEY_GT,employee.getGioiTinh());
        contentValues.put(KEY_ADDRES,employee.getAddr());
        contentValues.put(KEY_PHONE,employee.getSdt());
        contentValues.put(KEY_CHUCVU,employee.getChucVu());
        contentValues.put(KEY_PHONGBAN, employee.getIDPB());
        contentValues.put(KEY_BACLUONG, employee.getId_bacluong());

        db.update(TABLE_Employee,contentValues,"id = ?", new String[]{String.valueOf(employee.getId())});

    }
    public boolean isExistsAcCount(Account acCount){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql=String.format("SELECT COUNT(*) FROM ACCOUNT WHERE user ='%s' and password ='%s'",acCount.getUser(),acCount.getPassword());
        Cursor cursor=db.rawQuery(sql,null);
        int count=0;
        if(cursor.moveToNext()){
             count=cursor.getInt(0);
        }
        if(count>0){
            return true;
        }
        return false;

    }
    public boolean isExistsAcCount(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql=String.format("SELECT COUNT(*) FROM ACCOUNT WHERE user ='%s' ",user);
        Cursor cursor=db.rawQuery(sql,null);
        int count=0;
        if(cursor.moveToNext()){
            count=cursor.getInt(0);
        }
        if(count>0){
            return true;
        }
        return false;

    }
    public boolean isManager(Account acCount){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql="SELECT chucvu FROM Employee WHERE id =(select idnv FROM ACCOUNT WHERE user = '"+acCount.getUser()+"')";
        Cursor cursor=db.rawQuery(sql,null);
        String chucvu="";
        if(cursor.moveToNext()){
            chucvu=cursor.getString(0);
        }
        if(chucvu.equals("Quản lý")){
            return true;
        }
        return false;

    }
    public void addAcCount(Account acCount){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("idnv",acCount.getIdnv());
        contentValues.put("user",acCount.getUser());
        contentValues.put("password",acCount.getPassword());
        db.insert("ACCOUNT",null,contentValues);
        db.close();
    }
    public  void updateAccount(Account pb){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_TEN_ACCOUNT, pb.getUser());
        contentValues.put(KEY_PASS, pb.getPassword());

        db.update(TABLE_ACCOUNT,contentValues,KEY_ID_ACCOUNT+ " = ?", new String[]{String.valueOf(pb.getIdnv())});

    }
    public ArrayList<Account> getAllAcCount() {

        ArrayList<Account> accounts = new ArrayList<Account>();
        String selectQuery = "SELECT  * FROM ACCOUNT" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Account acCount=new Account();
                acCount.setIdnv(Integer.parseInt(cursor.getString(0)));
                acCount.setUser(cursor.getString(1));
                acCount.setPassword(cursor.getString(2));
                accounts.add(acCount);
            } while (cursor.moveToNext());
        }

        return accounts;
    }
    public ArrayList<Account> getAcCounttouser(String username, String password) {

        ArrayList<Account> accounts = new ArrayList<Account>();
        String sql=String.format("SELECT * FROM ACCOUNT WHERE user ='%s' and password ='%s'",username,password);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Account acCount=new Account();
                acCount.setIdnv(Integer.parseInt(cursor.getString(0)));
                acCount.setUser(cursor.getString(1));
                acCount.setPassword(cursor.getString(2));
                accounts.add(acCount);
            } while (cursor.moveToNext());
        }

        return accounts;
    }
    public Employee getEmployee(int idnv) {
        String selectQuery = "SELECT * FROM Employee where id ="+idnv ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Employee e=new Employee();

        if (cursor.moveToFirst()) {
            do {

                e.setId(Integer.parseInt(cursor.getString(0)));
                e.setName(cursor.getString(1));
                e.setGioiTinh(cursor.getString(2));
                e.setAddr(cursor.getString(3));
                e.setSdt(cursor.getString(4));
                e.setChucVu(cursor.getString(5));

            } while (cursor.moveToNext());
        }

        return e;
    }



    public ArrayList<PhongBan>getAllPhongBan(){
        ArrayList<PhongBan> pb = new ArrayList<>();
        //cau lenh query
        String selectQuery = "SELECT  * FROM "+TABLE_PHONGBAN;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                PhongBan phongBan =new PhongBan();
                phongBan.setIdPB(Integer.parseInt(cursor.getString(0)));
                phongBan.setTenPB(cursor.getString(1));
                phongBan.setDiaDiem(cursor.getString(2));
                pb.add(phongBan);
            } while (cursor.moveToNext());
        }
        return pb;
    }
    public ArrayList<PhongBan>getPhongBanName(int $id){
        ArrayList<PhongBan> pb = new ArrayList<>();
        //cau lenh query
        String selectQuery = "SELECT  * FROM "+TABLE_PHONGBAN +" WHERE " + KEY_ID_PHONGBAN+ " = " + $id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                PhongBan phongBan =new PhongBan();
                phongBan.setIdPB(Integer.parseInt(cursor.getString(0)));
                phongBan.setTenPB(cursor.getString(1));
                phongBan.setDiaDiem(cursor.getString(2));
                pb.add(phongBan);
            } while (cursor.moveToNext());
        }
        return pb;
    }

    public void addPhongBan(PhongBan phongBan){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_TEN_PHONGBAN,phongBan.getTenPB());
        contentValues.put(KEY_DIADIEM_PHONGBAN,phongBan.getDiaDiem());
        db.insert(TABLE_PHONGBAN,null,contentValues);
        db.close();
    }

    public PhongBan getPhongBan(int id_pb){
        String selectQuery = "SELECT * FROM PhongBan where idphongban ="+id_pb ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        PhongBan phongBan=new PhongBan();

        if (cursor.moveToFirst()) {
            do {
                phongBan.setIdPB(Integer.parseInt(cursor.getString(0)));
                phongBan.setTenPB(cursor.getString(1));
                phongBan.setDiaDiem(cursor.getString(2));
            } while (cursor.moveToNext());
        }

        return phongBan;
    }

    public int deletePhongBan(int idPB) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete("PhongBan",  " idphongban = ?",new String[]{String.valueOf(idPB)});
        }
        catch (Exception e){
            return 0;
        }
        db.close();
        return 1;

    }
    public  void updatePhongBan(PhongBan pb){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_TEN_PHONGBAN,pb.getTenPB());
        contentValues.put(KEY_DIADIEM_PHONGBAN,pb.getDiaDiem());

        db.update(TABLE_PHONGBAN,contentValues,"idphongban = ?", new String[]{String.valueOf(pb.getIdPB())});

    }

    public ArrayList<Luong> getAllBacLuong(){
        ArrayList<Luong> Luongs = new ArrayList<Luong>();
        String selectQuery = "SELECT  * FROM "+TABLE_BACLUONG ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Luong luong=new Luong();
                luong.setIdBacLuong(Integer.parseInt(cursor.getString(0)));
                luong.setLuongCoBan(Float.parseFloat(cursor.getString(1)));
                luong.setHeSoLuong(Float.parseFloat(cursor.getString(2)));
                luong.setHeSoPhuCap(Float.parseFloat(cursor.getString(3)));
                Luongs.add(luong);
            } while (cursor.moveToNext());
        }
        return Luongs;
    }
    public void addBacLuong(Luong luong){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LCB_BACLUONG, luong.getLuongCoBan());
        values.put(KEY_HSL_BACLUONG, luong.getHeSoLuong());
        values.put(KEY_PHUCAP,luong.getHeSoPhuCap());
        db.insert(TABLE_BACLUONG, null, values);
        db.close();
    }
    public void updateBacLuong(Luong luong){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LCB_BACLUONG, luong.getLuongCoBan());
        values.put(KEY_HSL_BACLUONG, luong.getHeSoLuong());
        values.put(KEY_PHUCAP,luong.getHeSoPhuCap());
        db.update(TABLE_BACLUONG, values, "idbacluong = ?",new String[]{String.valueOf(luong.getIdBacLuong())});
        db.close();
    }
    public int deleteBacLuong(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        try {
            db.delete(TABLE_BACLUONG,  " idbacluong = ?",new String[]{String.valueOf(id)});
            db.close();
        }
        catch (Exception e){
            return 0;
        }
        db.close();
        return 1;

    }

    public void addChamcong(ChamCong chamcong) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NHANVIEN, chamcong.getIDNV());
        values.put(KEY_THOIGIAN, chamcong.getDate());
        db.insert(TABLE_CHAMCONG, null, values);
        db.close();
    }
    public ArrayList<ChamCong> getallchamcongtoid(int id, String month) {
        Date d=new Date();
        month="04";
        int year=d.getYear()+1900;
        ArrayList<ChamCong> chamCongs = new ArrayList<ChamCong>();
        String sql="SELECT * FROM " + TABLE_CHAMCONG+ " WHERE "+ KEY_NHANVIEN +" ='"+id+"'"  +
                "and strftime('%m-%Y','now')='"+month+"-"+year+"' ORDER BY thoigian DESC " ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                ChamCong chamcong = new ChamCong();
                chamcong.setIDCC(Integer.parseInt(cursor.getString(0)));
                chamcong.setIDNV(Integer.parseInt(cursor.getString(1)));
                chamcong.setDate(cursor.getString(2));
                chamCongs.add(chamcong);
            } while (cursor.moveToNext());
        }

        return chamCongs;
    }
    public boolean isNotDate(String date, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql=String.format("SELECT COUNT(*) FROM " + TABLE_CHAMCONG+ " WHERE "+ KEY_THOIGIAN +" ='%s' AND " + KEY_NHANVIEN +" = %d",date, id);
        Cursor cursor=db.rawQuery(sql,null);
        int count=0;
        if(cursor.moveToNext()){
            count=cursor.getInt(Integer.parseInt("0"));
        }
        if(count>0){
            return false;
        }
        return true;

    }
    public int getallsongaycong(int id,String month) {
        Date d=new Date();
        int year=d.getYear()+1900;
        String sql="SELECT COUNT(*)  FROM " + TABLE_CHAMCONG+ " WHERE "+ KEY_NHANVIEN +" ="+id+" and strftime('%m-%Y',thoigian)='"+month+"-"+year+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        int count=0;
        if(cursor.moveToNext()){
            count=cursor.getInt(Integer.parseInt("0"));
        }

        return count;
    }
    public Luong getLuong(int id){
        String sql="SELECT * FROM "+TABLE_BACLUONG +" WHERE "+ KEY_ID_BACLUONG +" = "+id ;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        Luong luong=new Luong();
        if(cursor.moveToNext()){
            luong.setIdBacLuong(cursor.getInt(0));
            luong.setLuongCoBan(cursor.getInt(1));
            luong.setHeSoLuong(cursor.getInt(2));
            luong.setHeSoPhuCap(cursor.getInt(3));
        }
        return luong ;
    }
}
