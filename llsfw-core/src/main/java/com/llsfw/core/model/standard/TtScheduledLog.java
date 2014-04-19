package com.llsfw.core.model.standard;

import java.io.Serializable;
import java.util.Date;

public class TtScheduledLog implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_LOG.LOGID
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    private String logid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_LOG.CREATE_DATE
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_SCHEDULED_LOG.MSG
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    private String msg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_LOG.LOGID
     *
     * @return the value of TT_SCHEDULED_LOG.LOGID
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    public String getLogid() {
        return logid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_LOG.LOGID
     *
     * @param logid the value for TT_SCHEDULED_LOG.LOGID
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    public void setLogid(String logid) {
        this.logid = logid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_LOG.CREATE_DATE
     *
     * @return the value of TT_SCHEDULED_LOG.CREATE_DATE
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_LOG.CREATE_DATE
     *
     * @param createDate the value for TT_SCHEDULED_LOG.CREATE_DATE
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_SCHEDULED_LOG.MSG
     *
     * @return the value of TT_SCHEDULED_LOG.MSG
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    public String getMsg() {
        return msg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_SCHEDULED_LOG.MSG
     *
     * @param msg the value for TT_SCHEDULED_LOG.MSG
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
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
        TtScheduledLog other = (TtScheduledLog) that;
        return (this.getLogid() == null ? other.getLogid() == null : this.getLogid().equals(other.getLogid()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getMsg() == null ? other.getMsg() == null : this.getMsg().equals(other.getMsg()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Sat Apr 19 14:53:29 CST 2014
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLogid() == null) ? 0 : getLogid().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getMsg() == null) ? 0 : getMsg().hashCode());
        return result;
    }
}