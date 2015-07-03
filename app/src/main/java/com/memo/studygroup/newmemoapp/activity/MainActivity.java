package com.memo.studygroup.newmemoapp.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.memo.studygroup.newmemoapp.R;
import com.memo.studygroup.newmemoapp.adapter.MemoListAdapter;
import com.memo.studygroup.newmemoapp.database.DataBaseHandler;
import com.memo.studygroup.newmemoapp.intent.IntentHandler;
import com.memo.studygroup.newmemoapp.listener.HidingScrollListener;
import com.memo.studygroup.newmemoapp.vo.MemoVO;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.memo_list_view)
    protected RecyclerView memoListView;
    @InjectView(R.id.write_button)
    protected FloatingActionButton writeButton;
    @InjectView(R.id.toolbar)
    protected Toolbar toolbar;

    private DataBaseHandler dbHandler;
    private RecyclerView.LayoutManager memoListLayoutManager;
    private MemoListAdapter memoListAdapter;

    private List<MemoVO> memoVOList;

    public MainActivity() {
        this.dbHandler = DataBaseHandler.getInstance(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initializationData();
        initializationToolbar();
        initializationRecyclerView();

    }

    private void initializationData() {
        List<MemoVO> dbMemoList = dbHandler.readAll();
        this.memoVOList = dbMemoList;
    }

    private void initializationRecyclerView() {
        memoListLayoutManager = new LinearLayoutManager(this);
        memoListView.setLayoutManager(memoListLayoutManager);
        if(memoVOList.size() != 0) {
            memoListAdapter = new MemoListAdapter(this, memoVOList);
            memoListView.setAdapter(memoListAdapter);
        }
        memoListView.setOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                hideViews();
            }

            @Override
            public void onShow() {
                showViews();
            }
        });
    }

    private void initializationToolbar() {
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.app_name));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    private void hideViews() {
        toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));

        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) writeButton.getLayoutParams();
        int fabBottomMargin = lp.bottomMargin;
        writeButton.animate().translationY(writeButton.getHeight() + fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
    }

    private void showViews() {
        toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        writeButton.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }

    @OnClick(R.id.write_button)
    public void writeButtonAction() {
        IntentHandler.moveToMemoActivity(this);
    }

}
