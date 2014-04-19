package com.llsfw.core.model.standard;

import com.llsfw.core.model.standard.TtRoleFunction;
import com.llsfw.core.model.standard.TtRoleFunctionCriteria;
import com.llsfw.core.model.standard.TtRoleFunctionKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TtRoleFunctionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE_FUNCTION
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    int countByExample(TtRoleFunctionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE_FUNCTION
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    int deleteByExample(TtRoleFunctionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE_FUNCTION
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    int deleteByPrimaryKey(TtRoleFunctionKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE_FUNCTION
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    int insert(TtRoleFunction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE_FUNCTION
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    int insertSelective(TtRoleFunction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE_FUNCTION
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    List<TtRoleFunction> selectByExample(TtRoleFunctionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE_FUNCTION
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    TtRoleFunction selectByPrimaryKey(TtRoleFunctionKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE_FUNCTION
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    int updateByExampleSelective(@Param("record") TtRoleFunction record, @Param("example") TtRoleFunctionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE_FUNCTION
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    int updateByExample(@Param("record") TtRoleFunction record, @Param("example") TtRoleFunctionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE_FUNCTION
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    int updateByPrimaryKeySelective(TtRoleFunction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE_FUNCTION
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    int updateByPrimaryKey(TtRoleFunction record);
}