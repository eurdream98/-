package com.section02.javaconfig;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.SqlSession;

public interface MenuMapper {

    /* 결과를 받아올 ResultMap 설정 */
    /* @Result는 각각 DTO객체의 필드명(= property 속성)과 대상 테이블(= column 속성)의 컬럼명을 매핑시킴
     * @Result의 속성 중 id가 true이면 PK임을 의미 */
    @Results(id="menuResultMap", value = {
            @Result(id = true, property = "code", column = "MENU_CODE"),
            @Result(property = "name", column = "MENU_NAME"),
            @Result(property = "price", column = "MENU_PRICE"),
            @Result(property = "categoryCode", column = "CATEGORY_CODE"),
            @Result(property = "orderableStatus", column = "ORDERABLE_STATUS"),
    })

    @Select("SELECT \n" +
            "       MENU_CODE\n" +
            "     , MENU_NAME\n" +
            "     , MENU_PRICE\n" +
            "     , CATEGORY_CODE\n" +
            "     , ORDERABLE_STATUS\n" +
            "  FROM TBL_MENU\n" +
            " WHERE ORDERABLE_STATUS = 'Y'\n" +
            " ORDER BY MENU_CODE")
    List<MenuDTO> selectAllMenu();

    @Select("SELECT \n" +
            "       MENU_CODE\n" +
            "     , MENU_NAME\n" +
            "     , MENU_PRICE\n" +
            "     , CATEGORY_CODE\n" +
            "     , ORDERABLE_STATUS\n" +
            "  FROM TBL_MENU\n" +
            " WHERE ORDERABLE_STATUS = 'Y'\n" +
            "   AND MENU_CODE = #{ code }")
    @ResultMap("menuResultMap")			//위에서 사용한 resultMap을 재사용
    MenuDTO selectMenuByCode(int code);

    @Insert("INSERT\n" +
            "  INTO TBL_MENU\n" +
            "(\n" +
            "  MENU_NAME\n" +
            ", MENU_PRICE\n" +
            ", CATEGORY_CODE\n" +
            ", ORDERABLE_STATUS\n" +
            ")\n" +
            "VALUES\n" +
            "(\n" +
            "  #{ name }\n" +
            ", #{ price }\n" +
            ", #{ categoryCode }\n" +
            ", 'Y'\n" +
            ")")
    int insertMenu(MenuDTO menu);

    @Update("UPDATE\n" +
            "        TBL_MENU\n" +
            "   SET MENU_NAME = #{ name }\n" +
            "     , MENU_PRICE = #{ price }\n" +
            "     , CATEGORY_CODE = #{ categoryCode }\n" +
            " WHERE MENU_CODE = #{ code }")
    int updateMenu(MenuDTO menu);

    @Delete("DELETE\n" +
            "  FROM TBL_MENU\n" +
            " WHERE MENU_CODE = #{ code }")
    int deleteMenu(int code);

}
