package com.llsfw.core.mapper.standard;

import com.llsfw.core.model.standard.TtUserJob;
import com.llsfw.core.model.standard.TtUserJobCriteria;
import com.llsfw.core.model.standard.TtUserJobKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TtUserJobMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_JOB
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    int countByExample(TtUserJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_JOB
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    int deleteByExample(TtUserJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_JOB
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    int deleteByPrimaryKey(TtUserJobKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_JOB
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    int insert(TtUserJob record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_JOB
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    int insertSelective(TtUserJob record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_JOB
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    List<TtUserJob> selectByExample(TtUserJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_JOB
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    TtUserJob selectByPrimaryKey(TtUserJobKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_JOB
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    int updateByExampleSelective(@Param("record") TtUserJob record, @Param("example") TtUserJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_JOB
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    int updateByExample(@Param("record") TtUserJob record, @Param("example") TtUserJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_JOB
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    int updateByPrimaryKeySelective(TtUserJob record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_JOB
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    int updateByPrimaryKey(TtUserJob record);
}