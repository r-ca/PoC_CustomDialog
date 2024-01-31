package one.nem.trident.poc_customdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ボタンを押したときにテストダイアログを表示する
        findViewById(R.id.button).setOnClickListener(v -> {
            showTestDialog();
        });
    }

    /**
     * テストダイアログを表示する
     */
    private void showTestDialog() {
        TestDialog testDialog = new TestDialog();
        testDialog.setTestData(getStubData(5));
        testDialog.setListener((position, item) -> {
            Toast.makeText(this, "Clicked: " + item + " (position: " + position + ")", Toast.LENGTH_SHORT).show();
        });

        testDialog.show(getSupportFragmentManager(), "TestDialog");
    }

    /**
     * テストデータを作成する
     * @param size データのサイズ
     * @return テストデータ(StringのArrayList)
     */
    private ArrayList<String> getStubData(int size) {
        ArrayList<String> stubData = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            stubData.add("Item " + i);
        }
        return stubData;
    }
}