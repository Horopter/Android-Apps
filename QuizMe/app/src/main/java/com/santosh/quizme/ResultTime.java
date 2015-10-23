package com.santosh.quizme;

/**
 * Created by PUSKAR on 9/22/2015.
 */
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultTime extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        RatingBar bar=(RatingBar)findViewById(R.id.ratings);
        bar.setNumStars(5);
        bar.setStepSize(0.5f);
        TextView t=(TextView)findViewById(R.id.Results);
        Bundle b = getIntent().getExtras();
        int score= b.getInt("score");
        bar.setRating(score);
        switch (score)
        {
            case 1:
                t.setText("Poor Score!");
                break;
            case 2:
                t.setText("Meh Score!");
                break;
            case 3:
                t.setText("Average Score!");
                break;
            case 4:t.setText("Just miss..");
                break;
            case 5:t.setText("We are proud of you.");
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_result, menu);
        return true;
    }
}
