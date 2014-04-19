package com.llsfw.core.mapper.standard;

import com.llsfw.core.model.standard.TtJob;
import com.llsfw.core.model.standard.TtJobCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TtJobMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    int countByExample(TtJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    int deleteByExample(TtJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    int deleteByPrimaryKey(String jobCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    int insert(TtJob record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    int insertSelective(TtJob record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    List<TtJob> selectByExample(TtJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    TtJob selectByPrimaryKey(String jobCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    int updateByExampleSelective(@Param("record") TtJob record, @Param("example") TtJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    int updateByExample(@Param("record") TtJob record, @Param("example") TtJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    int updateByPrimaryKeySelective(TtJob record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    int updateByPrimaryKey(TtJob record);
}