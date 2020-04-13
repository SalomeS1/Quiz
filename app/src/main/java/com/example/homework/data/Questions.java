package com.example.homework.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Questions {

    public ArrayList<String> questions;
    public ArrayList<ArrayList<String>> answers;
    public int length;
    public int options;

    public Questions()
    {
        length = 4;
        options = 4;
        answers = new ArrayList<ArrayList<String>>();
        questions = new ArrayList<String>();
        for(int i = 0; i < length; i++)
        {
            String example = " Question " + Integer.toString(i + 1) + " ? ";
            questions.add(example);
            ArrayList<String> ans = new ArrayList<String>();
            for(int j = 0; j < options; j++)
            {
                if(j == 0)
                    ans.add("Correct");
                else
                    ans.add("Incorrect");
            }
            answers.add(ans);
        }
    }

}
