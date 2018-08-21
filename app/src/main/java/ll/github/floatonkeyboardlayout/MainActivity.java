package ll.github.floatonkeyboardlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import github.ll.view.FloatOnKeyboardLayout;

/**
 * Created by Liliang
 * Email: 53127822@qq.com
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatOnKeyboardLayout floatOnKeyboardLayout = (FloatOnKeyboardLayout) findViewById(R.id.root_view);
        floatOnKeyboardLayout.setAnchor(findViewById(R.id.anchor));

        floatOnKeyboardLayout.setPopupListener(new FloatOnKeyboardLayout.OnKeyboardPopupListener() {
            @Override
            public void onKeyboardPopup(boolean isPop) {
                Toast.makeText(MainActivity.this, "popup: " + isPop, Toast.LENGTH_SHORT).show();
            }
        });

        floatOnKeyboardLayout.setMarginKeyboard(100);
    }
}
