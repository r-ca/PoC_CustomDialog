package one.nem.trident.poc_customdialog;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class TestDialog extends DialogFragment {

    // Listener (別ファイルで定義してもok)
    public interface TestDialogListener {
        void onItemClicked(int position, String item);
    }

    // Fields
    private TestDialogListener listener;

    private ArrayList<String> testData;

    // Setters (Privateなフィールドにデータをセットするためのメソッド)
    public void setListener(TestDialogListener listener) {
        this.listener = listener;
    }

    public void setTestData(ArrayList<String> testData) {
        this.testData = testData;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // ダイアログの作成
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setTitle("Test Dialog"); // タイトル
        builder.setMessage("This is a test dialog"); // メッセージ

        // ダイアログの中身の作成 --------------------------------------------------

        // レイアウトファイルの読み込み
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_dialog_test, null);

        // RecyclerViewの取得
        RecyclerView recyclerView = view.findViewById(R.id.test_recycler_view);

        // LayoutManagerの設定
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Adapterの設定
        TestRecyclerViewAdapter adapter = new TestRecyclerViewAdapter();

        adapter.setListener((position, item) -> { // リスナーの設定
            if (listener != null) {
                listener.onItemClicked(position, item);
            }
        });

        getActivity().runOnUiThread(() -> { // UIスレッドで実行
            adapter.setTestData(this.testData); // データのセット
            adapter.notifyDataSetChanged(); // データの更新
        });

        recyclerView.setAdapter(adapter); // Adapterのセット

        // ----------------------------------------------------------------------

        builder.setView(view); // ダイアログにViewをセット

        // ボタンの設定
        builder.setPositiveButton("OK", (dialog, which) -> { // Positive（肯定的）ボタン
            Toast.makeText(getActivity(), "OK", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> { // Negative（否定的）ボタン
            Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
        });

        // ダイアログの作成/返却
        return builder.create();
    }
}
