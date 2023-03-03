package util;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * SQL Session 객체를 제공하는 팩토리 클래스
 */
public class MyBatisUtil {

    private static SqlSessionFactory sqlSessionFactory; // 객체변수 선언

    static {
        try {
            // 1-1. xml문서 읽어오기
            Charset charset = Charset.forName("UTF-8"); // 설정파일의 한글처리용
            Resources.setCharset(charset);

            Reader rd = Resources.getResourceAsReader("config/mybatis-config.xml");

            // 1-2. 위에서 읽어온 Reader 객체를 잉숑하여 실제 작업을 진행할 객체 생성하기

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);

            rd.close(); // Reader 닫기

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    } // static END

    /**
     * SqlSession 객체를 제공하는 팩토리 메서드
     * @return SqlSession 객체
     */
    public static SqlSession getInstance() {
        return sqlSessionFactory.openSession();
    }

    /**
     * SqlSession 객체를 제공하는 팩토리 메서드
     * @param autoCommit True 이면 오토커밋 사용
     * @return SqlSession 객체
     */
    public static SqlSession getInstance(boolean autoCommit) {
        return sqlSessionFactory.openSession(autoCommit);
    }


}
