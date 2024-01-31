package one.nem.trident.poc_customdialog;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.TestRecyclerViewViewHolder>{

    public interface TestRecyclerViewAdapterListener {
        void onItemClicked(int position, String item);
    }

    // Listener
    private TestRecyclerViewAdapterListener listener;

    private ArrayList<String> testData;

    public TestRecyclerViewAdapter() {
    }

    public void setTestData(ArrayList<String> testData) {
        this.testData = testData;
    }

    public void setListener(TestRecyclerViewAdapterListener listener) {
        this.listener = listener;
    }
    @NonNull
    @Override
    public TestRecyclerViewAdapter.TestRecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_dialog_test, parent, false);
        return new TestRecyclerViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestRecyclerViewAdapter.TestRecyclerViewViewHolder holder, int position) {
        if (testData != null) {
            String item = testData.get(position);
            Log.d("TestRecyclerViewAdapter", "onBindViewHolder: " + item);
            if (holder.itemTextView == null) {
                Log.d("TestRecyclerViewAdapter", "onBindViewHolder: itemTextView is null");
            } else {
                Log.d("TestRecyclerViewAdapter", "onBindViewHolder: itemTextView is not null");
            }
            holder.itemTextView.setText(item);
            holder.itemTextView.setOnClickListener(v -> {
                if(listener != null) {
                    listener.onItemClicked(position, item);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return testData == null ? 0 : testData.size();
    }

    public class TestRecyclerViewViewHolder extends RecyclerView.ViewHolder {

        TextView itemTextView;

        public TestRecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);

            itemTextView = itemView.findViewById(R.id.item_text_view);
        }
    }
}
