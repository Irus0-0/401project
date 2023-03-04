package kr.or.ddit.teampro.master.service;

import kr.or.ddit.teampro.master.vo.MasterVO;

import java.util.List;

public interface IMasterService {
    public int insertMaster(MasterVO mv);
    public int updateMaster(MasterVO mv);
    public int deleteMaster(MasterVO mv);
    public MasterVO selectMaster(MasterVO mv);
    public List<MasterVO> selectMasterList(MasterVO mv);
    public MasterVO getVo ();
    public MasterVO setVo (MasterVO mv);
}
