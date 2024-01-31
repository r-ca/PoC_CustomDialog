package one.nem.trident.poc_customdialog;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Adapter
public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.TestRecyclerViewViewHolder>{

    // Listener
    public interface TestRecyclerViewAdapterListener {
        void onItemClicked(int position, String item);
    }

    // Fields
    private TestRecyclerViewAdapterListener listener;

    private ArrayList<String> testData;

    public TestRecyclerViewAdapter() {
        // 空のコンストラクタ
    }

    // Setters (Privateなフィールドにデータをセットするためのメソッド)
    public void setTestData(ArrayList<String> testData) {
        this.testData = testData;
    }

    public void setListener(TestRecyclerViewAdapterListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public TestRecyclerViewAdapter.TestRecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // レイアウトファイルの読み込み
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_dialog_test, parent, false);
        return new TestRecyclerViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestRecyclerViewAdapter.TestRecyclerViewViewHolder holder, int position) {
        if (testData != null) { // データがnullでない場合
            String item = testData.get(position); // データを取得
            Log.d("TestRecyclerViewAdapter", "onBindViewHolder: " + item); // データをログに出力
            holder.itemTextView.setText(item); // データをTextViewにセット
            holder.itemTextView.setOnClickListener(v -> { // リスナーをセット
                if(listener != null) { // リスナーがnullでない場合
                    listener.onItemClicked(position, item); // リスナーを呼び出す
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return testData == null ? 0 : testData.size();
        /*
        データがNullの場合は0を返す

        このサンプルは非同期でデータを追加できるようにしてあるので
        Dialogが生成された瞬間(データがNullの状態)でgetItemCount()が呼ばれることがある
        データのセットをコンストラクタで行う場合は(基本的に)考慮しなくてok
         */

    }

    // ViewHolder
    public class TestRecyclerViewViewHolder extends RecyclerView.ViewHolder {

        TextView itemTextView;

        public TestRecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);

            // Viewの取得
            itemTextView = itemView.findViewById(R.id.item_text_view);
        }
    }
}
