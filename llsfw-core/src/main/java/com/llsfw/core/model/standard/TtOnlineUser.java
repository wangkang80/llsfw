package com.llsfw.core.model.standard;

import java.io.Serializable;
import java.util.Date;

public class TtOnlineUser implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_ONLINE_USER.LOGIN_NAME
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    private String loginName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_ONLINE_USER.LOGIN_IP
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    private String loginIp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_ONLINE_USER.LOGIN_DATE
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    private Date loginDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TT_ONLINE_USER
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_ONLINE_USER.LOGIN_NAME
     *
     * @return the value of TT_ONLINE_USER.LOGIN_NAME
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_ONLINE_USER.LOGIN_NAME
     *
     * @param loginName the value for TT_ONLINE_USER.LOGIN_NAME
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_ONLINE_USER.LOGIN_IP
     *
     * @return the value of TT_ONLINE_USER.LOGIN_IP
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_ONLINE_USER.LOGIN_IP
     *
     * @param loginIp the value for TT_ONLINE_USER.LOGIN_IP
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_ONLINE_USER.LOGIN_DATE
     *
     * @return the value of TT_ONLINE_USER.LOGIN_DATE
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    public Date getLoginDate() {
        return loginDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_ONLINE_USER.LOGIN_DATE
     *
     * @param loginDate the value for TT_ONLINE_USER.LOGIN_DATE
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_USER
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
        TtOnlineUser other = (TtOnlineUser) that;
        return (this.getLoginName() == null ? other.getLoginName() == null : this.getLoginName().equals(other.getLoginName()))
            && (this.getLoginIp() == null ? other.getLoginIp() == null : this.getLoginIp().equals(other.getLoginIp()))
            && (this.getLoginDate() == null ? other.getLoginDate() == null : this.getLoginDate().equals(other.getLoginDate()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_USER
     *
     * @mbggenerated Fri Mar 21 15:52:29 CST 2014
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLoginName() == null) ? 0 : getLoginName().hashCode());
        result = prime * result + ((getLoginIp() == null) ? 0 : getLoginIp().hashCode());
        result = prime * result + ((getLoginDate() == null) ? 0 : getLoginDate().hashCode());
        return result;
    }
}