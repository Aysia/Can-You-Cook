package com.example.android.canyoucook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main);
    }

    /** This method calls to the checkAnswers method and creates a Toast **/
    public void submitAnswers(View view) {
        int countCorrect = checkAnswers();
        String myGrade = printResults(countCorrect);
        Toast.makeText(getApplicationContext(),
                myGrade, Toast.LENGTH_LONG).show();
    }

    /**
     *  This method gets the name the user entered
     */
    private String getName() {
        EditText yourNameText = (EditText) findViewById(R.id.your_name_view);
        String yourName = yourNameText.getText().toString();
        return yourName;
    }

    /**
     * This method gets the users favorite dish
     */
    private String getDish() {
        EditText yourDishText = (EditText) findViewById(R.id.favorite_dish_view);
        String yourDish = yourDishText.getText().toString();
        return yourDish;
    }

    /**
     * Verify the users answers to the correct answers. It will verify the correct answers
     * and add 1 point for each correct answer. Question 6 is multiple choice, here the method
     * will determine if the wrong answer (answer_6b) is checked and will deduct 1 point for wrong answer.
     * 
     * @return correctCount - number of answers correct
     */
    private int checkAnswers() {
        int correctCount = 0;

        String result = "";
        // assign id of gradable answers to variables
        RadioButton radio1 = (RadioButton) findViewById(R.id.answer_1d); //Braising
        RadioButton radio2 = (RadioButton) findViewById(R.id.answer_2b); //Frying
        RadioButton radio3 = (RadioButton) findViewById(R.id.answer_3c); //165 degrees
        RadioButton radio4 = (RadioButton) findViewById(R.id.answer_4b); //Boil for minimal amount of time
        RadioButton radio5 = (RadioButton) findViewById(R.id.answer_5d); //Cook til firl to bite
        CheckBox radio6a = (CheckBox) findViewById(R.id.answer_6a); //multiple choice correct
        CheckBox radio6b = (CheckBox) findViewById(R.id.answer_6b); //wrong answer
        CheckBox radio6c = (CheckBox) findViewById(R.id.answer_6c); //multiple choice correct
        RadioButton radio7 = (RadioButton) findViewById(R.id.answer_7b); //read the recipe
        RadioButton radio8 = (RadioButton) findViewById(R.id.answer_8a); //A Cook on high hea
        RadioButton radio9 = (RadioButton) findViewById(R.id.answer_9c); //Oil

        // Run through each answer above and assign 1 or deduct 1 point for selection
        if (radio1.isChecked()) {
            correctCount = correctCount + 1;
        }
        if (radio2.isChecked()) {
            correctCount = correctCount + 1;
        }
        if (radio3.isChecked()) {
            correctCount = correctCount + 1;
        }
        if (radio4.isChecked()) {
            correctCount = correctCount + 1;
        }
        if (radio5.isChecked()) {
            correctCount = correctCount + 1;
        }
        if (radio6a.isChecked()) {
            correctCount = correctCount + 1;
        }
        // Wrong answer - deduct 1 point
        if (radio6b.isChecked()){
            correctCount = correctCount -1;
        }
        if (radio6c.isChecked()) {
            correctCount = correctCount + 1;
        }
        if (radio7.isChecked()) {
            correctCount = correctCount + 1;
        }
        if (radio8.isChecked()) {
            correctCount = correctCount + 1;
        }
        if (radio9.isChecked()) {
            correctCount = correctCount + 1;
        }

        return correctCount;

    }

    /**
     * Takes the correctCount result from checkAnswers and outputs dialog based on the number
     * @param countCorrect the result of checkAnswers()
     * @return grade returns the dialog to display based on count
     */
    private String printResults( int countCorrect ) {
        // determine points for each answer (2 points for multiple choice)
        int points = Math.round(100 / 10);

        String yourName = getName();
        String yourDish = getDish();

        /**
         * assign a percentage grade based on number correct multiplied by points per answer
         */
        int percent = countCorrect * points;

        // initial grade string does not change despite grade.
        String grade = yourName + " you got " + percent + "% correct!";

        // provide a funny way to display result based on correct answers

        if (countCorrect < 11) {
            if (percent >= 90 && percent < 100) {
                grade += " You're a budding chef, I see tons of plated ";
                grade += yourDish + " in your future!!";
            } else if (percent <= 80 && percent > 70) {
                grade += " If you continue to practice making " + yourDish;
                grade += " You'll be a Chef in no time!";
            } else if (percent <= 70 && percent > 60) {
                grade += " You should study up on cooking techniques and soon you will";
                grade += " be cooking " + yourDish + " like a pro!";
            } else {
                grade += "You got " + percent + "% Correct. How about some takeout tonight?";
            }
        } else {
            grade += " You're a Superstar Chef and can probably make "+ yourDish + " blindfolded!";
        }
        return grade;
    }
}
