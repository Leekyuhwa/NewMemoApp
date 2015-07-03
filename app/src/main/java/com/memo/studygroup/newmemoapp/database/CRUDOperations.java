package com.memo.studygroup.newmemoapp.database;

import com.memo.studygroup.newmemoapp.vo.MemoVO;

import java.util.List;

/**
 * Created by coupang on 2015. 6. 25..
 */
public interface CRUDOperations {
    public void insert(MemoVO memoVO);
    public void update(MemoVO memoVO);
    public void delete(MemoVO memoVo);
    public MemoVO read(int id);
    public List<MemoVO> readAll();
}
