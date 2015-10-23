package com.santosh.quizme;

/**
 * Created by PUSKAR on 9/19/2015.
 */
public class Question {
    private int _id;
    private String question;
    private String option1, option2, option3,option4,correctoption;

    public Question()
    {
        correctoption="";
        option1="";
        option2="";
        option3="";
        option4="";
        question="";
    }

    public Question(String correctoption, String option1, String option2, String option3, String option4, String question)
    {
        this.correctoption = correctoption;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.question = question;
    }

    public String getQuery()
    {
        String query = getQuestion() + "llm" + getOption1() + "llm" + getOption2() + "llm" +
                getOption3() + "llm" + getOption4() + "llm" + getCorrectoption();
        return query;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCorrectoption() {
        return correctoption;
    }

    public void setCorrectoption(String correctoption) {
        this.correctoption = correctoption;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
