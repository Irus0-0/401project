package kr.or.ddit.teampro.master.dao;

import kr.or.ddit.teampro.master.vo.MasterVO;

import java.util.List;

public class MasterDao implements IMasterDao{
    @Override
    public int insertMaster(MasterVO mv) {

    }

    @Override
    public int updateMaster(MasterVO mv) {
        return 0;
    }

    @Override
    public int deleteMaster(MasterVO mv) {
        return 0;
    }

    @Override
    public MasterVO display(MasterVO mv) {
        return null;
    }

    @Override
    public List<MasterVO> displayAll(MasterVO mv) {
        return null;
    }

    @Override
    public boolean isExist(MasterVO mv) {
        return false;
    }
}
