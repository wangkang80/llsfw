package com.llsfw.core.model.standard;

import java.io.Serializable;
import java.util.Date;

public class TtUserFunction extends TtUserFunctionKey implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_USER_FUNCTION.PURVIEW_DATE
     *
     * @mbggenerated Mon May 05 17:09:26 CST 2014
     */
    private Long purviewDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_USER_FUNCTION.CREATE_BY
     *
     * @mbggenerated Mon May 05 17:09:26 CST 2014
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_USER_FUNCTION.CREATE_DATE
     *
     * @mbggenerated Mon May 05 17:09:26 CST 2014
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TT_USER_FUNCTION
     *
     * @mbggenerated Mon May 05 17:09:26 CST 2014
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_USER_FUNCTION.PURVIEW_DATE
     *
     * @return the value of TT_USER_FUNCTION.PURVIEW_DATE
     *
     * @mbggenerated Mon May 05 17:09:26 CST 2014
     */
    public Long getPurviewDate() {
        return purviewDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_USER_FUNCTION.PURVIEW_DATE
     *
     * @param purviewDate the value for TT_USER_FUNCTION.PURVIEW_DATE
     *
     * @mbggenerated Mon May 05 17:09:26 CST 2014
     */
    public void setPurviewDate(Long purviewDate) {
        this.purviewDate = purviewDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_USER_FUNCTION.CREATE_BY
     *
     * @return the value of TT_USER_FUNCTION.CREATE_BY
     *
     * @mbggenerated Mon May 05 17:09:26 CST 2014
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_USER_FUNCTION.CREATE_BY
     *
     * @param createBy the value for TT_USER_FUNCTION.CREATE_BY
     *
     * @mbggenerated Mon May 05 17:09:26 CST 2014
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_USER_FUNCTION.CREATE_DATE
     *
     * @return the value of TT_USER_FUNCTION.CREATE_DATE
     *
     * @mbggenerated Mon May 05 17:09:26 CST 2014
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_USER_FUNCTION.CREATE_DATE
     *
     * @param createDate the value for TT_USER_FUNCTION.CREATE_DATE
     *
     * @mbggenerated Mon May 05 17:09:26 CST 2014
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_FUNCTION
     *
     * @mbggenerated Mon May 05 17:09:26 CST 2014
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
        TtUserFunction other = (TtUserFunction) that;
        return (this.getLoginName() == null ? other.getLoginName() == null : this.getLoginName().equals(other.getLoginName()))
            && (this.getFunctionCode() == null ? other.getFunctionCode() == null : this.getFunctionCode().equals(other.getFunctionCode()))
            && (this.getPurviewCode() == null ? other.getPurviewCode() == null : this.getPurviewCode().equals(other.getPurviewCode()))
            && (this.getPurviewDate() == null ? other.getPurviewDate() == null : this.getPurviewDate().equals(other.getPurviewDate()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_FUNCTION
     *
     * @mbggenerated Mon May 05 17:09:26 CST 2014
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLoginName() == null) ? 0 : getLoginName().hashCode());
        result = prime * result + ((getFunctionCode() == null) ? 0 : getFunctionCode().hashCode());
        result = prime * result + ((getPurviewCode() == null) ? 0 : getPurviewCode().hashCode());
        result = prime * result + ((getPurviewDate() == null) ? 0 : getPurviewDate().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        return result;
    }
}