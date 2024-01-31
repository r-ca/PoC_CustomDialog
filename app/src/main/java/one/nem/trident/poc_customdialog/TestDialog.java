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

    public interface TestDialogListener {
        void onItemClicked(int position, String item);
    }

    private TestDialogListener listener;

    private ArrayList<String> testData;

    public void setListener(TestDialogListener listener) {
        this.listener = listener;
    }

    public void setTestData(ArrayList<String> testData) {
        this.testData = testData;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setTitle("Test Dialog");
        builder.setMessage("This is a test dialog");

        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_dialog_test, null);

        RecyclerView recyclerView = view.findViewById(R.id.test_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        TestRecyclerViewAdapter adapter = new TestRecyclerViewAdapter();

        adapter.setListener((position, item) -> {
            if (listener != null) {
                listener.onItemClicked(position, item);
            }
        });

        getActivity().runOnUiThread(() -> {
            adapter.setTestData(this.testData);
            adapter.notifyDataSetChanged();
        });

        recyclerView.setAdapter(adapter);

        builder.setView(view);

        builder.setPositiveButton("OK", (dialog, which) -> {
            Toast.makeText(getActivity(), "OK", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
        });

        return builder.create();
    }
}
