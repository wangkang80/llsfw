package com.llsfw.core.mapper.standard;

import com.llsfw.core.model.standard.TtRole;
import com.llsfw.core.model.standard.TtRoleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TtRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE
     *
     * @mbggenerated Sun Apr 20 00:31:30 CST 2014
     */
    int countByExample(TtRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE
     *
     * @mbggenerated Sun Apr 20 00:31:30 CST 2014
     */
    int deleteByExample(TtRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE
     *
     * @mbggenerated Sun Apr 20 00:31:30 CST 2014
     */
    int deleteByPrimaryKey(String roleCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE
     *
     * @mbggenerated Sun Apr 20 00:31:30 CST 2014
     */
    int insert(TtRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE
     *
     * @mbggenerated Sun Apr 20 00:31:30 CST 2014
     */
    int insertSelective(TtRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE
     *
     * @mbggenerated Sun Apr 20 00:31:30 CST 2014
     */
    List<TtRole> selectByExample(TtRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE
     *
     * @mbggenerated Sun Apr 20 00:31:30 CST 2014
     */
    TtRole selectByPrimaryKey(String roleCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE
     *
     * @mbggenerated Sun Apr 20 00:31:30 CST 2014
     */
    int updateByExampleSelective(@Param("record") TtRole record, @Param("example") TtRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE
     *
     * @mbggenerated Sun Apr 20 00:31:30 CST 2014
     */
    int updateByExample(@Param("record") TtRole record, @Param("example") TtRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE
     *
     * @mbggenerated Sun Apr 20 00:31:30 CST 2014
     */
    int updateByPrimaryKeySelective(TtRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ROLE
     *
     * @mbggenerated Sun Apr 20 00:31:30 CST 2014
     */
    int updateByPrimaryKey(TtRole record);
}