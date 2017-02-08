package com.example.xiaoshuai.annotationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.annotation.core.BindClick;
import com.annotation.core.BindView;
import com.annotation.utils.InjectUtils;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.button)
    private Button mButton;
    @BindView(R.id.textview)
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //必须放在setContentView后面
        InjectUtils.inject(this);

        mTextView.setText("通过代码设置的文字");
    }
    @BindClick(R.id.button)
    private void onButtonClick(){
        Toast.makeText(this,"Button被点击了",Toast.LENGTH_SHORT).show();
    }
    @BindClick(R.id.textview)
    private void onTextViewClick(){
        Toast.makeText(this,"TextView被点击了",Toast.LENGTH_SHORT).show();
    }
}
