package askbuddy.indigitouslagos.com.askbuddy.functions;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import askbuddy.indigitouslagos.com.askbuddy.R;

/**
 * Created by Opeyemi on 10/12/2016.
 */

public class CommentViewAdapter extends RecyclerView.Adapter <CommentViewAdapter.ViewHolder> {

    private List<Map> mapList;

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView commentTextView;
        public TextView authorTextView;
        public TextView dateTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            commentTextView = (TextView) itemView.findViewById(R.id.comment_description);
            authorTextView = (TextView) itemView.findViewById(R.id.comment_author);
            dateTextView = (TextView) itemView.findViewById(R.id.comment_date);
        }
    }

    // Pass in the contact array into the constructor
    public CommentViewAdapter(List<Map> mapList) {
        this.mapList = mapList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.comments_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HashMap mapList = (HashMap) this.mapList.get(position);

        // Set item views based on your views and data model
        TextView authorTextView = holder.authorTextView;
        authorTextView.setText((String) mapList.get("comment_author"));
        TextView commentTextView = holder.commentTextView;
        commentTextView.setText((String) mapList.get("comment_description"));
        TextView dateTextView = holder.dateTextView;
        dateTextView.setText((String) mapList.get("comment_date"));
    }

    @Override
    public int getItemCount() {
        return mapList.size();
    }
}
