package com.llsfw.core.mapper.standard;

import com.llsfw.core.model.standard.TtServerGlobalParameters;
import com.llsfw.core.model.standard.TtServerGlobalParametersCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TtServerGlobalParametersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    int countByExample(TtServerGlobalParametersCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    int deleteByExample(TtServerGlobalParametersCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    int deleteByPrimaryKey(String parametersCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    int insert(TtServerGlobalParameters record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    int insertSelective(TtServerGlobalParameters record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    List<TtServerGlobalParameters> selectByExample(TtServerGlobalParametersCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    TtServerGlobalParameters selectByPrimaryKey(String parametersCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    int updateByExampleSelective(@Param("record") TtServerGlobalParameters record, @Param("example") TtServerGlobalParametersCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    int updateByExample(@Param("record") TtServerGlobalParameters record, @Param("example") TtServerGlobalParametersCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    int updateByPrimaryKeySelective(TtServerGlobalParameters record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    int updateByPrimaryKey(TtServerGlobalParameters record);
}