package com.section01.xmlconfig;

import static com.section01.xmlconfig.Template.getSqlSession;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class MenuService {

    /* Service는 sqlSession을 생성하여 DAO를 통해 쿼리를 수행하도록 하고, 결과값을 반환함 */
    /* 마찬가지로 sqlSession은 사용 후 반드시 반납해야 함*/

    /* Service에서 공통 사용할 객체 선언 */
    private final MenuDAO menuDAO;

    /*  Service 객체 생성 시 사용할 객체도 생성 */
    public MenuService() {
        menuDAO = new MenuDAO();
    }

    public List<MenuDTO> selectAllMenu() {

        SqlSession sqlSession = getSqlSession();

        List<MenuDTO> menuList = menuDAO.selectAllMenu(sqlSession);

        sqlSession.close();

        return menuList;

    }

    public MenuDTO selectMenuByCode(int code) {

        SqlSession sqlSession = getSqlSession();

        MenuDTO menu = menuDAO.selectMenuByCode(sqlSession, code);

        sqlSession.close();

        return menu;
    }

    public boolean registMenu(MenuDTO menu) {

        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.insertMenu(sqlSession, menu);

        if(result > 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }

        sqlSession.close();

        return result > 0? true: false;

    }

    public boolean modifyMenu(MenuDTO menu) {

        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.updateMenu(sqlSession, menu);

        if(result > 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }

        sqlSession.close();

        return result > 0? true: false;

    }

    public boolean deleteMenu(int code) {

        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.deleteMenu(sqlSession, code);

        if(result > 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }

        sqlSession.close();

        return result > 0? true: false;

    }

}