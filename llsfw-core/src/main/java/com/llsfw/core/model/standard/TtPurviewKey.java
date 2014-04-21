package com.llsfw.core.model.standard;

import java.io.Serializable;

public class TtPurviewKey implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_PURVIEW.FUNCTION_CODE
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    private String functionCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_PURVIEW.PURVIEW_CODE
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    private String purviewCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TT_PURVIEW
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_PURVIEW.FUNCTION_CODE
     *
     * @return the value of TT_PURVIEW.FUNCTION_CODE
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    public String getFunctionCode() {
        return functionCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_PURVIEW.FUNCTION_CODE
     *
     * @param functionCode the value for TT_PURVIEW.FUNCTION_CODE
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_PURVIEW.PURVIEW_CODE
     *
     * @return the value of TT_PURVIEW.PURVIEW_CODE
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    public String getPurviewCode() {
        return purviewCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_PURVIEW.PURVIEW_CODE
     *
     * @param purviewCode the value for TT_PURVIEW.PURVIEW_CODE
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    public void setPurviewCode(String purviewCode) {
        this.purviewCode = purviewCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_PURVIEW
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
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
        TtPurviewKey other = (TtPurviewKey) that;
        return (this.getFunctionCode() == null ? other.getFunctionCode() == null : this.getFunctionCode().equals(other.getFunctionCode()))
            && (this.getPurviewCode() == null ? other.getPurviewCode() == null : this.getPurviewCode().equals(other.getPurviewCode()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_PURVIEW
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFunctionCode() == null) ? 0 : getFunctionCode().hashCode());
        result = prime * result + ((getPurviewCode() == null) ? 0 : getPurviewCode().hashCode());
        return result;
    }
}