package askbuddy.indigitouslagos.com.askbuddy.functions;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import askbuddy.indigitouslagos.com.askbuddy.MainActivity;
import askbuddy.indigitouslagos.com.askbuddy.R;

/**
 * Created by Opeyemi on 06/11/2016.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.ViewHolder> {

    private List<Map> mapList;

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView questionTextView;
        public TextView commentTextView;
        public TextView authorTextView;
        public TextView dateTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(final View itemView, final Context context, final List<Map> mapList) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            questionTextView = (TextView) itemView.findViewById(R.id.card_item);
            commentTextView = (TextView) itemView.findViewById(R.id.comment_count);
            authorTextView = (TextView) itemView.findViewById(R.id.author);
            dateTextView = (TextView) itemView.findViewById(R.id.question_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) context).prepareBottomSheetCommentSection(context, mapList.get(getLayoutPosition()));
                    ((MainActivity) context).setBottomSheetHidden(false);
                }
            });
        }
    }

    // Pass in the contact array into the constructor
    public RecyclerViewAdapter(List<Map> mapList) {
        this.mapList = mapList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.card_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView, context, mapList);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HashMap mapList = (HashMap) this.mapList.get(position);

        // Set item views based on your views and data model
        TextView questionTextView = holder.questionTextView;
        questionTextView.setText((String) mapList.get("question"));
        TextView commentTextView = holder.commentTextView;
        commentTextView.setText("Comments: "+mapList.get("comment_count")+"");
        TextView questionAuthor = holder.authorTextView;
        questionAuthor.setText((String) mapList.get("author"));
        TextView questionDate = holder.dateTextView;
        questionDate.setText((String) mapList.get("date"));
    }

    @Override
    public int getItemCount() {
        return mapList.size();
    }
}
