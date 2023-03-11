package kr.or.ddit.teampro.review.dao;


import kr.or.ddit.teampro.review.vo.ReviewVO;

import java.util.List;

public interface IReviewDao {
    public int addReview(ReviewVO rv);
    public int updateReview(ReviewVO rv);
    public int deleteReview(ReviewVO rv);
    public ReviewVO getReview(ReviewVO rv);
    public List<ReviewVO> getReviewList();
    public ReviewVO maxNum();

}
