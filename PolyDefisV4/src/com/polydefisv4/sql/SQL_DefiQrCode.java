package com.polydefisv4.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQL_DefiQrCode extends SQLiteOpenHelper {

	private static final String TABLE_QRCODE = "D_QRCODE";
	private static final String COL_ID_DEFI = "IdDefi";
	private static final String COL_QRCODE = "QrCode";

	private static final String CREATE_BDD = 
			"CREATE TABLE " + TABLE_QRCODE + " (" + 
					COL_ID_DEFI + " INT PRIMARY KEY, " + 
					COL_QRCODE + " TEXT NOT NULL);";
 
	public SQL_DefiQrCode(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
		//on cr�e la table � partir de la requ�te �crite dans la variable CREATE_BDD
		db.execSQL(CREATE_BDD);
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//On peut faire ce qu'on veut ici moi j'ai d�cid� de supprimer la table et de la recr�er
		//comme �a lorsque je change la version les id repartent de 0
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QRCODE + ";");
		onCreate(db);
	}
	
	
}
