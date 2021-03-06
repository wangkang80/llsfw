package com.llsfw.core.mapper.standard;

import com.llsfw.core.model.standard.TtOrganization;
import com.llsfw.core.model.standard.TtOrganizationCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TtOrganizationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ORGANIZATION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int countByExample(TtOrganizationCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ORGANIZATION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int deleteByExample(TtOrganizationCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ORGANIZATION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int deleteByPrimaryKey(String orgCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ORGANIZATION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int insert(TtOrganization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ORGANIZATION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int insertSelective(TtOrganization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ORGANIZATION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    List<TtOrganization> selectByExample(TtOrganizationCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ORGANIZATION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    TtOrganization selectByPrimaryKey(String orgCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ORGANIZATION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int updateByExampleSelective(@Param("record") TtOrganization record, @Param("example") TtOrganizationCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ORGANIZATION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int updateByExample(@Param("record") TtOrganization record, @Param("example") TtOrganizationCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ORGANIZATION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int updateByPrimaryKeySelective(TtOrganization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ORGANIZATION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int updateByPrimaryKey(TtOrganization record);
}