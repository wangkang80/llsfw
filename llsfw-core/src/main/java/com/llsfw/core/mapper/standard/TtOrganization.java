package com.llsfw.core.mapper.standard;

import java.io.Serializable;
import java.util.Date;

public class TtOrganization implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_ORGANIZATION.ORG_CODE
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    private String orgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_ORGANIZATION.ORG_NAME
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    private String orgName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_ORGANIZATION.PARENT_ORG_CODE
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    private String parentOrgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_ORGANIZATION.ORG_SORT
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    private String orgSort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_ORGANIZATION.ORT_STATUS
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    private String ortStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_ORGANIZATION.CREATE_BY
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    private Integer createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_ORGANIZATION.CREATE_DATE
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_ORGANIZATION.UPDATE_BY
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_ORGANIZATION.UPDATE_DATE
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TT_ORGANIZATION
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_ORGANIZATION.ORG_CODE
     *
     * @return the value of TT_ORGANIZATION.ORG_CODE
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_ORGANIZATION.ORG_CODE
     *
     * @param orgCode the value for TT_ORGANIZATION.ORG_CODE
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_ORGANIZATION.ORG_NAME
     *
     * @return the value of TT_ORGANIZATION.ORG_NAME
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_ORGANIZATION.ORG_NAME
     *
     * @param orgName the value for TT_ORGANIZATION.ORG_NAME
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_ORGANIZATION.PARENT_ORG_CODE
     *
     * @return the value of TT_ORGANIZATION.PARENT_ORG_CODE
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public String getParentOrgCode() {
        return parentOrgCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_ORGANIZATION.PARENT_ORG_CODE
     *
     * @param parentOrgCode the value for TT_ORGANIZATION.PARENT_ORG_CODE
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public void setParentOrgCode(String parentOrgCode) {
        this.parentOrgCode = parentOrgCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_ORGANIZATION.ORG_SORT
     *
     * @return the value of TT_ORGANIZATION.ORG_SORT
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public String getOrgSort() {
        return orgSort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_ORGANIZATION.ORG_SORT
     *
     * @param orgSort the value for TT_ORGANIZATION.ORG_SORT
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public void setOrgSort(String orgSort) {
        this.orgSort = orgSort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_ORGANIZATION.ORT_STATUS
     *
     * @return the value of TT_ORGANIZATION.ORT_STATUS
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public String getOrtStatus() {
        return ortStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_ORGANIZATION.ORT_STATUS
     *
     * @param ortStatus the value for TT_ORGANIZATION.ORT_STATUS
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public void setOrtStatus(String ortStatus) {
        this.ortStatus = ortStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_ORGANIZATION.CREATE_BY
     *
     * @return the value of TT_ORGANIZATION.CREATE_BY
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_ORGANIZATION.CREATE_BY
     *
     * @param createBy the value for TT_ORGANIZATION.CREATE_BY
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_ORGANIZATION.CREATE_DATE
     *
     * @return the value of TT_ORGANIZATION.CREATE_DATE
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_ORGANIZATION.CREATE_DATE
     *
     * @param createDate the value for TT_ORGANIZATION.CREATE_DATE
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_ORGANIZATION.UPDATE_BY
     *
     * @return the value of TT_ORGANIZATION.UPDATE_BY
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_ORGANIZATION.UPDATE_BY
     *
     * @param updateBy the value for TT_ORGANIZATION.UPDATE_BY
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_ORGANIZATION.UPDATE_DATE
     *
     * @return the value of TT_ORGANIZATION.UPDATE_DATE
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_ORGANIZATION.UPDATE_DATE
     *
     * @param updateDate the value for TT_ORGANIZATION.UPDATE_DATE
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ORGANIZATION
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
        TtOrganization other = (TtOrganization) that;
        return (this.getOrgCode() == null ? other.getOrgCode() == null : this.getOrgCode().equals(other.getOrgCode()))
            && (this.getOrgName() == null ? other.getOrgName() == null : this.getOrgName().equals(other.getOrgName()))
            && (this.getParentOrgCode() == null ? other.getParentOrgCode() == null : this.getParentOrgCode().equals(other.getParentOrgCode()))
            && (this.getOrgSort() == null ? other.getOrgSort() == null : this.getOrgSort().equals(other.getOrgSort()))
            && (this.getOrtStatus() == null ? other.getOrtStatus() == null : this.getOrtStatus().equals(other.getOrtStatus()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ORGANIZATION
     *
     * @mbggenerated Sat Apr 19 12:59:00 CST 2014
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrgCode() == null) ? 0 : getOrgCode().hashCode());
        result = prime * result + ((getOrgName() == null) ? 0 : getOrgName().hashCode());
        result = prime * result + ((getParentOrgCode() == null) ? 0 : getParentOrgCode().hashCode());
        result = prime * result + ((getOrgSort() == null) ? 0 : getOrgSort().hashCode());
        result = prime * result + ((getOrtStatus() == null) ? 0 : getOrtStatus().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        return result;
    }
}