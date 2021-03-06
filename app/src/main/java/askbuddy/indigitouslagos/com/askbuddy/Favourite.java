package askbuddy.indigitouslagos.com.askbuddy;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import askbuddy.indigitouslagos.com.askbuddy.functions.RecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Favourite extends Fragment {

    public Favourite() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setTitle("Favourites");

        FloatingActionButton questionFab = (FloatingActionButton) getActivity().findViewById(R.id.ask_a_question_fab);
        questionFab.setVisibility(View.GONE);

        RecyclerView questionFeeds = (RecyclerView) view.findViewById(R.id.favourite_questions);

        // Initialize questions
        List<Map> questions = new ArrayList<>();

        Map<String, String> comment = new HashMap<>();
        comment.put("comment_author", "Adam Monroe");
        comment.put("comment_description", "Lorem ipsum dolor sit amet, consectetur adipisicing elit.");
        comment.put("comment_date", "2 days ago");

        ArrayList<Map> comments = new ArrayList<>();
        comments.add(comment);
        comments.add(comment);
        comments.add(comment);
        comments.add(comment);
        comments.add(comment);

        HashMap<String, Object> question1 = new HashMap<>();
        question1.put("question", "Who am I?");
        question1.put("comment_count", "15");
        question1.put("author", "Robert");
        question1.put("date", "3 days ago");
        question1.put("comments", comments);
        questions.add(question1);

        HashMap<String, Object> question2 = new HashMap<>();
        question2.put("question", "Why am i poor?");
        question2.put("comment_count", "5");
        question2.put("author", "Jackson");
        question2.put("date", "3 days ago");
        question2.put("comments", comments);
        questions.add(question2);

        HashMap<String, Object> question3 = new HashMap<>();
        question3.put("question", "What is the meaning Life?");
        question3.put("comment_count", "15");
        question3.put("author", "Marc");
        question3.put("date", "3 days ago");
        question3.put("comments", comments);
        questions.add(question3);

        // Create adapter passing in the sample user data
        // int layout = R.id.question_feed;
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(questions);
        // Attach the adapter to the recyclerview to populate items
        questionFeeds.setAdapter(adapter);
        // Set layout manager to position the items
        questionFeeds.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

}
