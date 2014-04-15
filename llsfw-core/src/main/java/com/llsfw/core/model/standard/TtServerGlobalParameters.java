package com.llsfw.core.model.standard;

import java.io.Serializable;

public class TtServerGlobalParameters implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_CODE
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    private String parametersCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_VALUE
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    private String parametersValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_DESC
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    private String parametersDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_TYPE_CODE
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    private String parametersTypeCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_TYPE_NAME
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    private String parametersTypeName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_CODE
     *
     * @return the value of TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_CODE
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    public String getParametersCode() {
        return parametersCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_CODE
     *
     * @param parametersCode the value for TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_CODE
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    public void setParametersCode(String parametersCode) {
        this.parametersCode = parametersCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_VALUE
     *
     * @return the value of TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_VALUE
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    public String getParametersValue() {
        return parametersValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_VALUE
     *
     * @param parametersValue the value for TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_VALUE
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    public void setParametersValue(String parametersValue) {
        this.parametersValue = parametersValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_DESC
     *
     * @return the value of TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_DESC
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    public String getParametersDesc() {
        return parametersDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_DESC
     *
     * @param parametersDesc the value for TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_DESC
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    public void setParametersDesc(String parametersDesc) {
        this.parametersDesc = parametersDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_TYPE_CODE
     *
     * @return the value of TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_TYPE_CODE
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    public String getParametersTypeCode() {
        return parametersTypeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_TYPE_CODE
     *
     * @param parametersTypeCode the value for TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_TYPE_CODE
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    public void setParametersTypeCode(String parametersTypeCode) {
        this.parametersTypeCode = parametersTypeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_TYPE_NAME
     *
     * @return the value of TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_TYPE_NAME
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    public String getParametersTypeName() {
        return parametersTypeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_TYPE_NAME
     *
     * @param parametersTypeName the value for TT_SERVER_GLOBAL_PARAMETERS.PARAMETERS_TYPE_NAME
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    public void setParametersTypeName(String parametersTypeName) {
        this.parametersTypeName = parametersTypeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TtServerGlobalParameters other = (TtServerGlobalParameters) that;
        return (this.getParametersCode() == null ? other.getParametersCode() == null : this.getParametersCode().equals(other.getParametersCode()))
            && (this.getParametersValue() == null ? other.getParametersValue() == null : this.getParametersValue().equals(other.getParametersValue()))
            && (this.getParametersDesc() == null ? other.getParametersDesc() == null : this.getParametersDesc().equals(other.getParametersDesc()))
            && (this.getParametersTypeCode() == null ? other.getParametersTypeCode() == null : this.getParametersTypeCode().equals(other.getParametersTypeCode()))
            && (this.getParametersTypeName() == null ? other.getParametersTypeName() == null : this.getParametersTypeName().equals(other.getParametersTypeName()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getParametersCode() == null) ? 0 : getParametersCode().hashCode());
        result = prime * result + ((getParametersValue() == null) ? 0 : getParametersValue().hashCode());
        result = prime * result + ((getParametersDesc() == null) ? 0 : getParametersDesc().hashCode());
        result = prime * result + ((getParametersTypeCode() == null) ? 0 : getParametersTypeCode().hashCode());
        result = prime * result + ((getParametersTypeName() == null) ? 0 : getParametersTypeName().hashCode());
        return result;
    }
}