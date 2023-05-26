package com.section01.xml;

import static com.common.Template.getSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.common.MenuDTO;
import com.common.SearchCriteria;

public class MenuService {

    private DynamicSqlMapper mapper;

    public void selectMenuByPrice(int price) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        /* hashmap의 key 혹은 dto의 getter를 이용하여 조건문의 값을 비교한다. */
        Map<String, Integer> map = new HashMap<>();
        map.put("price", price);

        List<MenuDTO> menuList = mapper.selectMenuByPrice(map);

        if(menuList != null && menuList.size() > 0) {
            for(MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }

        sqlSession.close();
    }

    public void searchMenu(SearchCriteria searchCriteria) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenu(searchCriteria);

        if(menuList != null && menuList.size() > 0) {
            for(MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }

        sqlSession.close();
    }

    public void searchMenuBySupCategory(SearchCriteria searchCriteria) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenuBySupCategory(searchCriteria);

        if(menuList != null && menuList.size() > 0) {
            for(MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }

        sqlSession.close();

    }

    public void searchMenuByRandomMenuCode(List<Integer> randomMeunuCodeList) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        Map<String, List<Integer>> criteria = new HashMap<>();
        criteria.put("randomMenuCodeList", randomMeunuCodeList);
        List<MenuDTO> menuList = mapper.searchMenuByRandomMenuCode(criteria);
        //List<MenuDTO> menuList = mapper.searchMenuByRandomMenuCode();

        if(menuList != null && menuList.size() > 0) {
            for(MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }

        sqlSession.close();

    }

    public void searchMenuByCodeOrSearchAll(SearchCriteria searchCriteria) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenuByCodeOrSearchAll(searchCriteria);

        if(menuList != null && menuList.size() > 0) {
            for(MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }

        sqlSession.close();

    }

    public void searchMenuByNameOrCategory(Map<String, Object> criteria) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenuByNameOrCategory(criteria);

        if(menuList != null && menuList.size() > 0) {
            for(MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }

        sqlSession.close();

    }

    public void modifyMenu(Map<String, Object> criteria) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        int result = mapper.modifyMenu(criteria);

        if(result > 0) {
            System.out.println("메뉴 정보 변경에 성공하셨습니다.");
            sqlSession.commit();
        } else {
            System.out.println("메뉴 정보 변경에 실패하셨습니다.");
            sqlSession.rollback();
        }

        sqlSession.close();

    }

}

