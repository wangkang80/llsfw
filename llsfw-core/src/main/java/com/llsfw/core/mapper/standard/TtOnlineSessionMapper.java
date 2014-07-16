package com.llsfw.core.mapper.standard;

import com.llsfw.core.model.standard.TtOnlineSession;
import com.llsfw.core.model.standard.TtOnlineSessionCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TtOnlineSessionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int countByExample(TtOnlineSessionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int deleteByExample(TtOnlineSessionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int deleteByPrimaryKey(String sessionId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int insert(TtOnlineSession record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int insertSelective(TtOnlineSession record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    List<TtOnlineSession> selectByExample(TtOnlineSessionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    TtOnlineSession selectByPrimaryKey(String sessionId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int updateByExampleSelective(@Param("record") TtOnlineSession record, @Param("example") TtOnlineSessionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int updateByExample(@Param("record") TtOnlineSession record, @Param("example") TtOnlineSessionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int updateByPrimaryKeySelective(TtOnlineSession record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int updateByPrimaryKey(TtOnlineSession record);
}