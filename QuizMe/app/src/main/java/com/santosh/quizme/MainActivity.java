package com.santosh.quizme;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    int pos;
    List<Question> ListQuestion;
    int score=0;
    int qid=0;
    Question headQ;
    TextView question;
    RadioButton op1, op2, op3, op4;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBhandler db=new DBhandler(this);
        ListQuestion=db.getAllQuestions();
        headQ=ListQuestion.get(qid);
        question=(TextView)findViewById(R.id.question);
        question.setTypeface(null, Typeface.BOLD_ITALIC);
        op1=(RadioButton)findViewById(R.id.op1);
        op1.setTypeface(null, Typeface.BOLD_ITALIC);
        op2=(RadioButton)findViewById(R.id.op2);
        op2.setTypeface(null, Typeface.BOLD_ITALIC);
        op3=(RadioButton)findViewById(R.id.op3);
        op3.setTypeface(null, Typeface.BOLD_ITALIC);
        op4=(RadioButton)findViewById(R.id.op4);
        op4.setTypeface(null, Typeface.BOLD_ITALIC);
        submit=(Button)findViewById(R.id.submit);
        setQuestionView();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp = (RadioGroup) findViewById(R.id.query);
                RadioButton answer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());
                if (headQ.getCorrectoption().equals(answer.getText())) {
                    score++;
                }
                if (qid < 5) {
                    headQ = ListQuestion.get(qid);
                    setQuestionView();
                } else {
                    Intent intent = new Intent(MainActivity.this, ResultTime.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score);
                    intent.putExtras(b);
                    startActivity(intent);
                    finish();
                }
                grp.clearCheck();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setQuestionView()
    {
        question.setText(headQ.getQuestion());
        op1.setText(headQ.getOption1());
        op2.setText(headQ.getOption2());
        op3.setText(headQ.getOption3());
        op4.setText(headQ.getOption4());
        qid++;
    }
}
