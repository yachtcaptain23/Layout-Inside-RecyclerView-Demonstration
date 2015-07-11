package us.albertwang.recyclerviewexample;

import us.albertwang.recyclerviewexample.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.Random;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class TodoList extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;
    public String TAG = "Albert";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate for TodoList");
        mContext = getApplicationContext();

        final View contentView = findViewById(R.id.my_recycler_view);

        String myDataset[] = new String[5];
        myDataset[0] = "Albert";
        myDataset[1] = "Betsy";
        myDataset[2] = "Charlie";
        myDataset[3] = "Delta";
        myDataset[4] = "Echo";


        setContentView(R.layout.activity_todo_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Log.d(TAG, "TodoList.onCreate @ setLayoutManager");

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
        Button myRemover = (Button) findViewById(R.id.button);
        myRemover.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        Log.d(TAG, "Finished onCreate for LinearLayoutManager");
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private String[] mDataset;
        private ViewHolder mViewHolder;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTextViewTask;
            public TextView mTextViewComment;
            public TextView mTextViewCountdown;
            public TextView mTextViewDate;
            public ViewHolder(View v) {
                super(v);
                Log.d(TAG, "Called super from ViewHolder constructor for layout");
                mTextViewTask = (TextView) v.findViewById(R.id.topleft_todo_row);
                mTextViewDate = (TextView) v.findViewById(R.id.topright_todo_row);
                mTextViewComment = (TextView) v.findViewById(R.id.bottomleft_todo_row);
                mTextViewCountdown = (TextView) v.findViewById(R.id.bottomright_todo_row);
            }
            public ViewHolder(TextView task, TextView date, TextView comment, TextView countdown) {
                super(task);

                Log.d(TAG, "Called super from ViewHolder constructor");
                mTextViewTask = task;
                mTextViewDate = date;
                mTextViewComment = comment;
                mTextViewCountdown = countdown;

            }

            public ViewHolder(TextView task) {
                super(task);

                Log.d(TAG, "Called super from ViewHolder constructor");
                mTextViewTask = task;
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(String[] myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {

            View todo_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
            /* TextView due_view = (TextView) v.findViewById(R.id.topright_todo_row);
            TextView comment_view = (TextView) v.findViewById(R.id.bottomleft_todo_row);
            TextView duration_view = (TextView) v.findViewById(R.id.bottomright_todo_row); */

            // set the view's size, margins, paddings and layout parameters
            // mViewHolder = new ViewHolder(todo_view, due_view, comment_view, duration_view);
            mViewHolder = new ViewHolder(todo_view);
            Log.d(TAG, "Finished onCreate");
            return mViewHolder;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            Log.d(TAG, "start changing view");
            holder.mTextViewTask.setText(mDataset[position]);

            holder.mTextViewDate.setText("" + (new Random()).nextInt(10));
            holder.mTextViewComment.setText("Why you no work?");
            Log.d(TAG, "changing view");

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.length;
        }

        public void RemoveTop() {
            // How to remove?
        }
    }
}
