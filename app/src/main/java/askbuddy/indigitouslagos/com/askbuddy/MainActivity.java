package askbuddy.indigitouslagos.com.askbuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import askbuddy.indigitouslagos.com.askbuddy.functions.CommentViewAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    Toolbar toolbar = null;
    TextView questionTextView = null;
    TextView questionAuthorTextView = null;
    TextView questionDateTextView = null;
    TextView commentCountTextView = null;

    QuestionFeed questionFeed = null;
    Questions questions = null;
    MyQuestions myQuestions = null;
    Favourite favourite = null;
    BottomSheetBehavior bottomSheetBehavior = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        FloatingActionButton questionFab = (FloatingActionButton) findViewById(R.id.ask_a_question_fab);
        questionFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AskQuestion.class);
                startActivity(intent);
            }
        });

        QuestionFeed fragment = new QuestionFeed();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        View bottomSheet = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setPeekHeight(getWindow().getWindowManager().getDefaultDisplay().getHeight());
        bottomSheetBehavior.setSkipCollapsed(true);
        setBottomSheetHidden(true);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState == BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheetBehavior.setHideable(false);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // BottomSheet Question Details
        questionTextView = (TextView) findViewById(R.id.question);
        questionAuthorTextView =  (TextView) findViewById(R.id.question_author);
        questionDateTextView = (TextView) findViewById(R.id.qtn_date);
        commentCountTextView = (TextView) findViewById(R.id.cmt_count);
        ImageView closeBottomSheet = (ImageView) findViewById(R.id.close_details);
        closeBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBottomSheetHidden(true);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        MenuItem discipleshipCategory = navigationView.getMenu().getItem(4);
        // if profile is a mentor set true else false
        discipleshipCategory.setVisible(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN){
            setBottomSheetHidden(true);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        setBottomSheetHidden(true);

        if (id == R.id.nav_ask_question) {
            questionFeed = (questionFeed == null) ? new QuestionFeed() : questionFeed;
            fragmentTransaction.replace(R.id.fragment_container, questionFeed);
        } else if (id == R.id.nav_questions) {
            questions = (questions == null) ? new Questions() : questions;
            fragmentTransaction.replace(R.id.fragment_container, questions);
        } else if (id == R.id.nav_my_questions) {
            myQuestions = (myQuestions == null) ? new MyQuestions() : myQuestions;
            fragmentTransaction.replace(R.id.fragment_container, myQuestions);
        } else if (id == R.id.nav_favourites) {
            favourite = (favourite == null) ? new Favourite() : favourite;
            fragmentTransaction.replace(R.id.fragment_container, new Favourite());
        } else if (id == R.id.nav_disciples) {
            Toast.makeText(this, "Disciples Currently Unavailable", Toast.LENGTH_SHORT).show();
        }
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setBottomSheetHidden(boolean setHidden){
        if(setHidden){
            bottomSheetBehavior.setHideable(true);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        } else {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    public void prepareBottomSheetCommentSection(Context context, Map<String, Object> details) {
        questionTextView.setText((String) details.get("question"));
        questionAuthorTextView.setText((String) details.get("author"));
        questionDateTextView.setText((String) details.get("date"));
        List<Map> comments = (List<Map>) details.get("comments");
        String comment = (comments.size() > 1) ? "comments" : "comment";
        commentCountTextView.setText(details.get("comment_count")+" "+comment);

        RecyclerView commentList = (RecyclerView) findViewById(R.id.comments);
        CommentViewAdapter adapter = new CommentViewAdapter(comments);
        commentList.setAdapter(adapter);
        commentList.setLayoutManager(new LinearLayoutManager(context));
    }
}
