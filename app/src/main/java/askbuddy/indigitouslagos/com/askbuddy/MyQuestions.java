package askbuddy.indigitouslagos.com.askbuddy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyQuestions extends Fragment {


    public MyQuestions() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_questions, container, false);
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setTitle("My Questions");

        RecyclerView questionFeeds = (RecyclerView) view.findViewById(R.id.my_questions);

        // Initialize questions
        List<Map> questions = new ArrayList<Map>();

        HashMap<String, String> question1 = new HashMap<>();
        question1.put("question", "Why do i Experence Pain?");
        question1.put("comment_count", "15");
        questions.add(question1);

        HashMap<String, String> question2 = new HashMap<>();
        question2.put("question", "Who is Jesus?");
        question2.put("comment_count", "5");
        questions.add(question2);

        HashMap<String, String> question3 = new HashMap<>();
        question3.put("question", "Why is my marriage not working?");
        question3.put("comment_count", "5");
        questions.add(question3);

        // Create adapter passing in the sample user data
        int layout = R.id.question_feed;
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(view.getContext(), questions);
        // Attach the adapter to the recyclerview to populate items
        questionFeeds.setAdapter(adapter);
        // Set layout manager to position the items
        questionFeeds.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

}
