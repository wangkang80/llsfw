package com.llsfw.core.mapper.standard;

import com.llsfw.core.model.standard.TtUserFunction;
import com.llsfw.core.model.standard.TtUserFunctionCriteria;
import com.llsfw.core.model.standard.TtUserFunctionKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TtUserFunctionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_FUNCTION
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    int countByExample(TtUserFunctionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_FUNCTION
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    int deleteByExample(TtUserFunctionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_FUNCTION
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    int deleteByPrimaryKey(TtUserFunctionKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_FUNCTION
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    int insert(TtUserFunction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_FUNCTION
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    int insertSelective(TtUserFunction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_FUNCTION
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    List<TtUserFunction> selectByExample(TtUserFunctionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_FUNCTION
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    TtUserFunction selectByPrimaryKey(TtUserFunctionKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_FUNCTION
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    int updateByExampleSelective(@Param("record") TtUserFunction record, @Param("example") TtUserFunctionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_FUNCTION
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    int updateByExample(@Param("record") TtUserFunction record, @Param("example") TtUserFunctionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_FUNCTION
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    int updateByPrimaryKeySelective(TtUserFunction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_FUNCTION
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    int updateByPrimaryKey(TtUserFunction record);
}