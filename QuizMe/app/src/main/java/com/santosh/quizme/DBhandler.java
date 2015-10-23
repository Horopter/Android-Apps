package com.santosh.quizme;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "quizme";
    private static final String TABLE_QUERY = "query";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_QUESTION = "question";
    private static final String COLUMN_CORRECT_OPTION = "correctoption"; 
    private static final String COLUMN_OPTION1= "option1"; 
    private static final String COLUMN_OPTION2= "option2"; 
    private static final String COLUMN_OPTION3= "option3"; 
    private static final String COLUMN_OPTION4= "option4";
    private SQLiteDatabase database;
    public DBhandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        database=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUERY + " ( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  COLUMN_CORRECT_OPTION+ " TEXT, "+
                COLUMN_OPTION1 +" TEXT, "  +COLUMN_OPTION2 +" TEXT, "+COLUMN_OPTION3+" TEXT, "+COLUMN_OPTION4+ 
                " TEXT, " + COLUMN_QUESTION + " TEXT);";
        db.execSQL(sql);
        addQuestions();
    }
    private void addQuestions()
    {
        Question q1=new Question("Under an agreement with which of the following countries did Subhas" +
                " Chandra Bose organize the Indian soldiers, taken as prisoners by the Axis Powers," +
                " into the Azad Hind Fauj?", "Japan", "Italy","China","Japan","Germany");
        this.addQuestion(q1);
        Question q2=new Question("To which professions earlier leaders who struggled for freedom of" +
                " India mainly belonged?","All of the above"," Teachers","Journalists","Lawyers","All of the above");
        this.addQuestion(q2);
        Question q3=new Question("Tripitakas are sacred books of","Buddhists","Buddhists","Jains","Hindus","None of the above");
        this.addQuestion(q3);
        Question q4=new Question("The territory of Porus who offered strong resistance to Alexander" +
                " was situated between the rivers of"," Jhelum and Chenab","Sutlej and Beas"," Ravi " +
                "and Chenab","Ganga and Yamuna","Jhelum and Chenab");
        this.addQuestion(q4);
        Question q5=new Question("The treaty of Srirangapatna was signed between Tipu Sultan and",
                "Cornwallis","Robert Clive","Cornwallis","Dalhousie"," Warren Hastings");
        this.addQuestion(q5);
        Question q6=new Question("Under Akbar, the Mir Bakshi was required to look after",
                "military affairs","the state treasury","military affairs","the royal household","the land revenue system");
        this.addQuestion(q6);
        Question q7=new Question("To conquer and annex Peshawar and Punjab, Mahmud of Ghazni defeated"
                ,"Hindushahis","Karkotakas","Ghurids","Hindushahis","Arabs");
        this.addQuestion(q7);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUERY);
        onCreate(db);
    }
    // Adding new question
    public void addQuestion(Question queries) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION, queries.getQuestion());
        values.put(COLUMN_CORRECT_OPTION, queries.getCorrectoption());
        values.put(COLUMN_OPTION1, queries.getOption1());
        values.put(COLUMN_OPTION2, queries.getOption2());
        values.put(COLUMN_OPTION3, queries.getOption3());
        values.put(COLUMN_OPTION4, queries.getOption4());
        database.insert(TABLE_QUERY, null, values);
    }
    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        String selectQuery = "SELECT * FROM " + TABLE_QUERY + " ORDER BY RANDOM() LIMIT 5 ; " ;
        database=this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Question queries = new Question();
                queries.setQuestion(cursor.getString(1));
                queries.setCorrectoption(cursor.getString(2));
                queries.setOption1(cursor.getString(3));
                queries.setOption2(cursor.getString(4));
                queries.setOption3(cursor.getString(5));
                queries.setOption4(cursor.getString(6));
                quesList.add(queries);
            } while (cursor.moveToNext());
        }
        database.close();
        return quesList;
    }
}