package com.herokuapp.horopter.vinata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    ArrayList<String> arrayList = new ArrayList<String>();
    String init1 ="";
    String init2 = "";
    int sol=0;
    public void onClick1(View v)
    {
        TextView tv1 = (TextView) findViewById(R.id.textView);
        if(sol<0)
        {
            tv1.setText(Integer.toString(sol));
            arrayList.clear();
            arrayList.add(0, Integer.toString(0));
            arrayList.add(1,"-");
            arrayList.add(2, Integer.toString(-sol));
            init2=Integer.toString(sol);
            sol=0;
        }
        else if(sol!=0)
        {
            tv1.setText(Integer.toString(sol));
            arrayList.clear();
            arrayList.add(0, Integer.toString(sol));
            init2=Integer.toString(sol);
            sol=0;
        }
        Button b = (Button) v;
        init1 =  b.getText().toString();
        //Fault in Sameer's calci : Multiple digit contingency
        if(!(init1.contains("+")||init1.contains("-")||init1.contains("*")||init1.contains("/")||init1.contains("(")||init1.contains(")")||init1.contains("^")))
        {
            init2 = init2 + init1;
            if(arrayList.size()>0)
             {
                 arrayList.remove((arrayList.size()-1));
             }
            arrayList.add(init2);
        }
        else if(init1.contains("("))
        {
            if(arrayList.size()>0)
            {
                arrayList.remove((arrayList.size()-1));
            }
            arrayList.add(init1);
            arrayList.add(init2);
            init2 = ""; // make string null if multiple operators are pressed.
        }
        else if(init1.contains(")"))//reverse order for the sake of insertion
        {
            if(arrayList.size()>0)
            {
                arrayList.remove((arrayList.size()-1));
            }
            arrayList.add(init2);
            arrayList.add(init1);
            init2 = ""; // make string null if multiple operators are pressed.
        }
        else
        {
            if(arrayList.size()==0 && init1.contentEquals("-"))
            {
                arrayList.add(Integer.toString(0));
            }
            arrayList.add(init1);
            arrayList.add(init2);
            init2 = ""; // make string null if multiple operators are pressed.
        }
        tv1.setText(tv1.getText().toString()+init1);
        //tv1.setText(arrayList.toString()); // Debugger for lengthy string input.
    }
    public ArrayList<String> getPostOrder(ArrayList<String> inOrderList){

        ArrayList<String> result = new ArrayList<String>();
        Stack<String> stack = new Stack<String>();
        for (int i = 0; i < inOrderList.size(); i++) {
            if(Character.isDigit(inOrderList.get(i).charAt(0))){
                result.add(inOrderList.get(i));
            }else{
                switch (inOrderList.get(i).charAt(0)) {
                    case '(':
                        stack.push(inOrderList.get(i));
                        break;
                    case ')':
                        while (!stack.peek().equals("(")) {
                            result.add(stack.pop());
                        }
                        stack.pop();
                        break;
                    default:
                        while (!stack.isEmpty() && compare(stack.peek(), inOrderList.get(i))){
                            result.add(stack.pop());
                        }
                        stack.push(inOrderList.get(i));
                        break;
                }
            }
        }
        while(!stack.isEmpty()){
            result.add(stack.pop());
        }
        return result;
    }
    public Integer calculate(ArrayList<String> postOrder){
        Stack stack = new Stack();
        for (int i = 0; i < postOrder.size(); i++) {
            if(Character.isDigit(postOrder.get(i).charAt(0))){
                stack.push(Integer.parseInt(postOrder.get(i)));
            }else{
                Integer back = (Integer)stack.pop();
                Integer front = (Integer)stack.pop();
                Integer res = 0;
                switch (postOrder.get(i).charAt(0)) {
                    case '+':
                        res = front + back;
                        break;
                    case '-':
                        res = front - back;
                        break;
                    case '*':
                        res = front * back;
                        break;
                    case '/':
                        res = front / back;
                        break;
                    case '^':
                        res = (int) Math.pow(front,back);
                }
                stack.push(res);
            }
        }
        return (Integer)stack.pop();
    }
    public static boolean compare(String peek, String cur){
        if("^".equals(peek)&&("/".equals(cur) || "*".equals(cur) ||"+".equals(cur) ||"-".equals(cur)||"^".equals(peek)))
            return true;
        if("*".equals(peek) && ("/".equals(cur) || "*".equals(cur) ||"+".equals(cur) ||"-".equals(cur))){
            return true;
        }
        else if("/".equals(peek) && ("/".equals(cur) || "*".equals(cur) ||"+".equals(cur) ||"-".equals(cur))){
            return true;
        }
        else if("+".equals(peek) && ("+".equals(cur) || "-".equals(cur))){
            return true;
        }
        else if("-".equals(peek) && ("+".equals(cur) || "-".equals(cur))){
            return true;
        }
        return false;
    }
    public void onClick(View v)
    {
        TextView tv2 = (TextView) findViewById(R.id.textView2);
        //TextView tv1 = (TextView) findViewById(R.id.textView);
        ArrayList<String> result;
        result = getPostOrder(arrayList);
        sol = calculate(result);
        tv2.setText(Integer.toString(sol));
        arrayList.clear();
        arrayList.add(0, Integer.toString(sol));
        //tv1.setText(arrayList.toString());
    }
    public void clear(View v)
    {
        TextView tv1 = (TextView) findViewById(R.id.textView);
        TextView tv2 = (TextView) findViewById(R.id.textView2);
        init1 = "";
        init2 ="";
        tv1.setText("");
        tv2.setText("0");
        arrayList.clear();
        sol=0;
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
}
