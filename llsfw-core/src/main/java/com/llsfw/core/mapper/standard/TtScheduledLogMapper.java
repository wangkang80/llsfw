package com.llsfw.core.mapper.standard;

import com.llsfw.core.model.standard.TtScheduledLog;
import com.llsfw.core.model.standard.TtScheduledLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TtScheduledLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    int countByExample(TtScheduledLogCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    int deleteByExample(TtScheduledLogCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    int deleteByPrimaryKey(String logid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    int insert(TtScheduledLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    int insertSelective(TtScheduledLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    List<TtScheduledLog> selectByExample(TtScheduledLogCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    TtScheduledLog selectByPrimaryKey(String logid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    int updateByExampleSelective(@Param("record") TtScheduledLog record, @Param("example") TtScheduledLogCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    int updateByExample(@Param("record") TtScheduledLog record, @Param("example") TtScheduledLogCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    int updateByPrimaryKeySelective(TtScheduledLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    int updateByPrimaryKey(TtScheduledLog record);
}