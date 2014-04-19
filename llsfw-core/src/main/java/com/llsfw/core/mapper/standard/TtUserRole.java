package com.llsfw.core.mapper.standard;

import java.io.Serializable;
import java.util.Date;

public class TtUserRole extends TtUserRoleKey implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_USER_ROLE.CREATE_BY
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_USER_ROLE.CREATE_DATE
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TT_USER_ROLE
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_USER_ROLE.CREATE_BY
     *
     * @return the value of TT_USER_ROLE.CREATE_BY
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_USER_ROLE.CREATE_BY
     *
     * @param createBy the value for TT_USER_ROLE.CREATE_BY
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_USER_ROLE.CREATE_DATE
     *
     * @return the value of TT_USER_ROLE.CREATE_DATE
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_USER_ROLE.CREATE_DATE
     *
     * @param createDate the value for TT_USER_ROLE.CREATE_DATE
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_ROLE
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
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
        TtUserRole other = (TtUserRole) that;
        return (this.getLoginName() == null ? other.getLoginName() == null : this.getLoginName().equals(other.getLoginName()))
            && (this.getRoleCode() == null ? other.getRoleCode() == null : this.getRoleCode().equals(other.getRoleCode()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_ROLE
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLoginName() == null) ? 0 : getLoginName().hashCode());
        result = prime * result + ((getRoleCode() == null) ? 0 : getRoleCode().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        return result;
    }
}