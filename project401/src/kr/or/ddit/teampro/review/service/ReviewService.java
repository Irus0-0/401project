package kr.or.ddit.teampro.review.service;


import kr.or.ddit.teampro.review.dao.ReviewDao;
import kr.or.ddit.teampro.review.vo.ReviewVO;

import java.util.List;

public class ReviewService implements IReviewService {
    //singleton
    private static ReviewService instance = null;
    private ReviewService(){}
    public static ReviewService getInstance(){
        if(instance == null){
            instance = new ReviewService();
        }
        return instance;
    }
    @Override
    public int addReview(ReviewVO rv) {
        return ReviewDao.getInstance().addReview(rv);
    }

    @Override
    public int updateReview(ReviewVO rv) {
        return ReviewDao.getInstance().updateReview(rv);
    }

    @Override
    public int deleteReview(ReviewVO rv) {
        return ReviewDao.getInstance().deleteReview(rv);
    }

    @Override
    public ReviewVO getReview(ReviewVO rv) {
        return ReviewDao.getInstance().getReview(rv);
    }

    @Override
    public List<ReviewVO> getReviewList() {
        return ReviewDao.getInstance().getReviewList();
    }

    @Override
    public ReviewVO maxNum() {
        return ReviewDao.getInstance().maxNum();
    }
}
