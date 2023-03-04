package kr.or.ddit.teampro.master.dao;

import kr.or.ddit.teampro.master.vo.MasterVO;

import java.util.List;

public interface IMasterDao {
    public int insertMaster(MasterVO mv);
    public int updateMaster(MasterVO mv);
    public int deleteMaster(MasterVO mv);
    public MasterVO display(MasterVO mv);
    public List<MasterVO> displayAll();
    public boolean isExist(MasterVO mv);

}
