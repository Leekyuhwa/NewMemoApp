package com.memo.studygroup.newmemoapp.adapter.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.memo.studygroup.newmemoapp.R;

public class MemoHolder extends RecyclerView.ViewHolder {

    public TextView idTextView;
    public TextView memoTextView;
    public TextView regDateTextView;

    public CardView itemLayout;

    public View.OnClickListener onClickListener;


    public MemoHolder(View itemView) {
        super(itemView);
        idTextView = (TextView) itemView.findViewById(R.id.item_id_textView);
        memoTextView = (TextView) itemView.findViewById(R.id.item_memo_textView);
        regDateTextView = (TextView) itemView.findViewById(R.id.item_regdate_textView);
        itemLayout = (CardView) itemView.findViewById(R.id.item_layout);
    }

}
