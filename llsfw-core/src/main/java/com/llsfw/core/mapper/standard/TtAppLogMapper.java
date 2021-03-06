package com.llsfw.core.mapper.standard;

import com.llsfw.core.model.standard.TtAppLog;
import com.llsfw.core.model.standard.TtAppLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TtAppLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APP_LOG
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int countByExample(TtAppLogCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APP_LOG
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int deleteByExample(TtAppLogCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APP_LOG
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int deleteByPrimaryKey(String logid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APP_LOG
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int insert(TtAppLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APP_LOG
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int insertSelective(TtAppLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APP_LOG
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    List<TtAppLog> selectByExample(TtAppLogCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APP_LOG
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    TtAppLog selectByPrimaryKey(String logid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APP_LOG
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int updateByExampleSelective(@Param("record") TtAppLog record, @Param("example") TtAppLogCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APP_LOG
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int updateByExample(@Param("record") TtAppLog record, @Param("example") TtAppLogCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APP_LOG
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int updateByPrimaryKeySelective(TtAppLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APP_LOG
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int updateByPrimaryKey(TtAppLog record);
}