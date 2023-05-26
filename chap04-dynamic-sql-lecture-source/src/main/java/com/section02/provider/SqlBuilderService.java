package com.section02.provider;

import static com.common.Template.getSqlSession;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.common.MenuDTO;

public class SqlBuilderService {

    private SqlBuilderMapper mapper;

    public void registMenu(MenuDTO menu) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(SqlBuilderMapper.class);

        int result = mapper.registMenu(menu);

        if(result > 0) {
            System.out.println("신규 메뉴 등록에 성공하셨습니다.");
            sqlSession.commit();
        } else {
            System.out.println("신규 메뉴 등록에 실패하셨습니다.");
            sqlSession.rollback();
        }

        sqlSession.close();
    }

    public void modifyMenu(MenuDTO menu) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(SqlBuilderMapper.class);

        int result = mapper.modifyMenu(menu);

        if(result > 0) {
            System.out.println("메뉴 정보 수정에 성공하셨습니다.");
            sqlSession.commit();
        } else {
            System.out.println("메뉴 정보 수정에 실패하셨습니다.");
            sqlSession.rollback();
        }

        sqlSession.close();
    }

    public void deleteMenu(int code) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(SqlBuilderMapper.class);

        int result = mapper.deleteMenu(code);

        if(result > 0) {
            System.out.println("메뉴 삭제에 성공하셨습니다.");
            sqlSession.commit();
        } else {
            System.out.println("메뉴 삭제에 실패하셨습니다.");
            sqlSession.rollback();
        }

        sqlSession.close();
    }

}

