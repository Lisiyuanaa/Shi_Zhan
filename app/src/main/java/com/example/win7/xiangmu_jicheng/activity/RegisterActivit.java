package com.example.win7.xiangmu_jicheng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.win7.xiangmu_jicheng.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

/**
 * Created by oooooo on 2017/3/22.
 * 登录页面
 * 登录 描述：判断账号密码是否一致
 * 注册 描述：跳转注册页面
 */

public class RegisterActivit extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {
    private EditText account;//账号
    private EditText password;//密码
    private Button register;//登录
    private Button registe;//注册

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeractivit);
        initView();
    }

    private void initView() {
        account = (EditText) findViewById(R.id.register_zhang_edit_text);
        password = (EditText) findViewById(R.id.register_mi_edit_text);
        register = (Button) findViewById(R.id.register_deng_btn_text);
        registe = (Button) findViewById(R.id.register_zhu_btn_text);
        registe.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.register_deng_btn_text:
                result();
                break;
            case R.id.register_zhu_btn_text:
                Intent intent = new Intent(RegisterActivit.this, EnrollAtivity.class);
                startActivity(intent);
                break;

        }
    }

    //根据校验的结果判断
    private void result() {
        String resg = account.getText().toString();
        String res = password.getText().toString();
        int reCode = estimate(resg, res);
        switch (reCode) {
            case 0:
                resgister(resg, res);
                break;
        }

    }


    //登录成功时调用
    private void resgister(String resg, String res) {
        EMClient.getInstance().login(resg, res, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Intent intent = new Intent(RegisterActivit.this, EnrollAtivity.class);
                startActivity(intent);
                finish();
                Log.e("main", "登录聊天服务器成功！");
                Toast.makeText(RegisterActivit.this, "登录成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.e("main", "登录聊天服务器失败！");
                Toast.makeText(RegisterActivit.this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //校验账号密码
    private int estimate(String resg, String res) {
        int reCode = 0;
        if (TextUtils.isEmpty(resg)) {
            reCode = 1;
        }
        if (TextUtils.isEmpty(res)) {
            reCode = 2;
        }
        return reCode;

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_GO) {
            result();
        }
        return false;
    }
}
