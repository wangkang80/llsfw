package com.llsfw.core.mapper.standard;

import com.llsfw.core.model.standard.TtOnlineUser;
import com.llsfw.core.model.standard.TtOnlineUserCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TtOnlineUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_USER
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    int countByExample(TtOnlineUserCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_USER
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    int deleteByExample(TtOnlineUserCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_USER
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    int insert(TtOnlineUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_USER
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    int insertSelective(TtOnlineUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_USER
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    List<TtOnlineUser> selectByExample(TtOnlineUserCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_USER
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    int updateByExampleSelective(@Param("record") TtOnlineUser record, @Param("example") TtOnlineUserCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_USER
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    int updateByExample(@Param("record") TtOnlineUser record, @Param("example") TtOnlineUserCriteria example);
}