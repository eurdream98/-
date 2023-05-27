package com.section01.xml;

import java.util.List;
import java.util.Map;

import com.common.MenuDTO;
import com.common.SearchCriteria;

public interface DynamicSqlMapper {

    List<MenuDTO> selectMenuByPrice(Map<String, Integer> map);

    List<MenuDTO> searchMenu(SearchCriteria searchCriteria);

    List<MenuDTO> searchMenuBySupCategory(SearchCriteria searchCriteria);

    List<MenuDTO> searchMenuByRandomMenuCode(Map<String, List<Integer>> criteria);
    /* mapper xml 파일에서 foreach 태그 사용 시 static 필드에 직접 접근하는 경우 아래처럼 parameter 넘겨주지 않아도 됨 */
    //List<MenuDTO> searchMenuByRandomMenuCode();

    List<MenuDTO> searchMenuByCodeOrSearchAll(SearchCriteria searchCriteria);

    List<MenuDTO> searchMenuByNameOrCategory(Map<String, Object> criteria);

    int modifyMenu(Map<String, Object> criteria);

}
