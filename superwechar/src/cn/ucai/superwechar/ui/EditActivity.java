package cn.ucai.superwechar.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cn.ucai.superwechar.R;

public class EditActivity extends BaseActivity {
    private EditText editText;


    @Override
    protected void onCreate(Bundle arg0) {
        setContentView(R.layout.em_activity_edit);
        super.onCreate(arg0);
        showLeftBack();
        setListener();
        editText = (EditText) findViewById(R.id.edittext);
        String title = getIntent().getStringExtra("title");
        String data = getIntent().getStringExtra("data");
        if (title != null)
            ((TextView) findViewById(R.id.tv_title)).setText(title);
        if (data != null)
            editText.setText(data);
        editText.setSelection(editText.length());

    }

    private void setListener() {
        titleBar.getRightLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(v);
            }
        });
    }


    public void save(View view) {
        setResult(RESULT_OK, new Intent().putExtra("data", editText.getText().toString()));
        finish();
    }

    public void back(View view) {
        finish();
    }
}
