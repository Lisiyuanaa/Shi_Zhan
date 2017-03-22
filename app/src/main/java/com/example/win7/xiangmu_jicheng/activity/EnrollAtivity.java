package com.example.win7.xiangmu_jicheng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.win7.xiangmu_jicheng.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

/**
 * Created by oooooo on 2017/3/22.
 * 注册列表
 * 注册用户名,密码是否合法
 */

public class EnrollAtivity extends AppCompatActivity implements View.OnClickListener {
    private EditText account;//账号
    private EditText password;//密码
    private EditText passworda;//密码
    private TextView retur;//返回
    private Button enroll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enroll_activity);
        initView();
    }

    private void initView() {
        account = (EditText) findViewById(R.id.enroll_zhang_edit_text);
        password = (EditText) findViewById(R.id.enroll_mi_edit_text);
        passworda = (EditText) findViewById(R.id.enroll_zaimi_edit_text);
        retur = (TextView) findViewById(R.id.enroll_fan_text_view);
        enroll = (Button) findViewById(R.id.enroll_que_btn_text);
        retur.setOnClickListener(this);
        enroll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enroll_fan_text_view:
                Intent intent = new Intent(EnrollAtivity.this, RegisterActivit.class);
                startActivity(intent);
                break;
            case R.id.enroll_que_btn_text:
                que();
                empty();
                break;
        }
    }

    private void empty() {
        account.setText("");
        password.setText("");
        passworda.setText("");
    }

    private void que() {
        String enrol = account.getText().toString();
        String enro = password.getText().toString();
        String enr = passworda.getText().toString();
        int reCode = estimate(enrol, enro, enr);
        switch (reCode) {
            case 0:
                try {
                    EMClient.getInstance().createAccount(enrol, enro);

                } catch (HyphenateException e) {
                    Toast.makeText(EnrollAtivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                Toast.makeText(EnrollAtivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                break;
        }

    }


    //校验账号密码
    private int estimate(String enrol, String enro, String enr) {
        int reCode = 0;
        if (TextUtils.isEmpty(enrol)) {
            reCode = 1;
        }
        if (TextUtils.isEmpty(enro)) {
            reCode = 2;
        }
        if (TextUtils.isEmpty(enr)) {
            reCode = 3;
        }
        return reCode;
    }
}
