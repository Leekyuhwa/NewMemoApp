package com.memo.studygroup.newmemoapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.memo.studygroup.newmemoapp.R;
import com.memo.studygroup.newmemoapp.database.DataBaseHandler;
import com.memo.studygroup.newmemoapp.intent.IntentHandler;
import com.memo.studygroup.newmemoapp.util.DateUtil;
import com.memo.studygroup.newmemoapp.vo.MemoVO;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MemoActivity extends AppCompatActivity {

    @InjectView(R.id.memo_edittext)
    protected EditText memoEditText;
    @InjectView(R.id.write_button)
    protected Button writeButton;
    @InjectView(R.id.save_button)
    protected Button saveButton;
    @InjectView(R.id.delete_button)
    protected Button deleteButton;

    private MemoVO intentTrasformMemoVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_item);
        ButterKnife.inject(this);
        processIntent(getIntent());
    }

    private void processIntent(Intent intent) {
        String intentMemo = intent.getStringExtra(IntentHandler.KEY_MEMO);

        if (intentMemo != null) {
            intentTrasformMemoVO = new MemoVO();
            intentTrasformMemoVO.setId(intent.getIntExtra(IntentHandler.KEY_ID, 0));
            intentTrasformMemoVO.setRegdate(intent.getStringExtra(IntentHandler.KEY_DATE));
            intentTrasformMemoVO.setMemo(intentMemo);
            initializationView(false);
        } else {
            initializationView(true);
        }
    }

    private void initializationView(Boolean viewCondition) {
        if(viewCondition) {
            writeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.GONE);
            saveButton.setVisibility(View.GONE);
        } else {
            writeButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.VISIBLE);
            saveButton.setVisibility(View.VISIBLE);
            memoEditText.setText(intentTrasformMemoVO.getMemo());
        }
    }

    @OnClick(R.id.write_button)
    public void writeButtonAction() {
        MemoVO memoVO = new MemoVO();
        memoVO.setMemo(memoEditText.getText().toString());
        memoVO.setRegdate(DateUtil.getToday());
        DataBaseHandler.getInstance(this).insert(memoVO);
        IntentHandler.meveToMemoListActivity(this);
    }

    @OnClick(R.id.save_button)
    public void saveButtonAction() {
        intentTrasformMemoVO.setMemo(memoEditText.getText().toString());
        intentTrasformMemoVO.setRegdate(DateUtil.getToday());
        DataBaseHandler.getInstance(this).update(intentTrasformMemoVO);
        IntentHandler.meveToMemoListActivity(this);
    }

    @OnClick(R.id.delete_button)
    public void deleteButtonAction() {
        DataBaseHandler.getInstance(this).delete(intentTrasformMemoVO);
        IntentHandler.meveToMemoListActivity(this);
    }
}
