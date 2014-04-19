package com.llsfw.core.mapper.standard;

import com.llsfw.core.model.standard.TtUserOrgJob;
import com.llsfw.core.model.standard.TtUserOrgJobCriteria;
import com.llsfw.core.model.standard.TtUserOrgJobKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TtUserOrgJobMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_ORG_JOB
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    int countByExample(TtUserOrgJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_ORG_JOB
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    int deleteByExample(TtUserOrgJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_ORG_JOB
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    int deleteByPrimaryKey(TtUserOrgJobKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_ORG_JOB
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    int insert(TtUserOrgJob record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_ORG_JOB
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    int insertSelective(TtUserOrgJob record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_ORG_JOB
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    List<TtUserOrgJob> selectByExample(TtUserOrgJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_ORG_JOB
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    TtUserOrgJob selectByPrimaryKey(TtUserOrgJobKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_ORG_JOB
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    int updateByExampleSelective(@Param("record") TtUserOrgJob record, @Param("example") TtUserOrgJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_ORG_JOB
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    int updateByExample(@Param("record") TtUserOrgJob record, @Param("example") TtUserOrgJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_ORG_JOB
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    int updateByPrimaryKeySelective(TtUserOrgJob record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_ORG_JOB
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    int updateByPrimaryKey(TtUserOrgJob record);
}