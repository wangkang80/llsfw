package com.llsfw.core.mapper.standard;

import com.llsfw.core.model.standard.TtApplicationUser;
import com.llsfw.core.model.standard.TtApplicationUserCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TtApplicationUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Sat Apr 19 13:01:24 CST 2014
     */
    int countByExample(TtApplicationUserCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Sat Apr 19 13:01:24 CST 2014
     */
    int deleteByExample(TtApplicationUserCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Sat Apr 19 13:01:24 CST 2014
     */
    int deleteByPrimaryKey(String loginName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Sat Apr 19 13:01:24 CST 2014
     */
    int insert(TtApplicationUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Sat Apr 19 13:01:24 CST 2014
     */
    int insertSelective(TtApplicationUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Sat Apr 19 13:01:24 CST 2014
     */
    List<TtApplicationUser> selectByExample(TtApplicationUserCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Sat Apr 19 13:01:24 CST 2014
     */
    TtApplicationUser selectByPrimaryKey(String loginName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Sat Apr 19 13:01:24 CST 2014
     */
    int updateByExampleSelective(@Param("record") TtApplicationUser record, @Param("example") TtApplicationUserCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Sat Apr 19 13:01:24 CST 2014
     */
    int updateByExample(@Param("record") TtApplicationUser record, @Param("example") TtApplicationUserCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Sat Apr 19 13:01:24 CST 2014
     */
    int updateByPrimaryKeySelective(TtApplicationUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Sat Apr 19 13:01:24 CST 2014
     */
    int updateByPrimaryKey(TtApplicationUser record);
}