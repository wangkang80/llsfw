package com.llsfw.core.model.standard;

import java.io.Serializable;
import java.util.Date;

public class TtAppLog implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_APP_LOG.LOGID
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    private String logid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_APP_LOG.CLASS_NAME
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    private String className;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_APP_LOG.METHOD
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    private String method;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_APP_LOG.CREATE_DATE
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_APP_LOG.LOGLEVEL
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    private String loglevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_APP_LOG.MSG
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    private String msg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TT_APP_LOG
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_APP_LOG.LOGID
     *
     * @return the value of TT_APP_LOG.LOGID
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    public String getLogid() {
        return logid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_APP_LOG.LOGID
     *
     * @param logid the value for TT_APP_LOG.LOGID
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    public void setLogid(String logid) {
        this.logid = logid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_APP_LOG.CLASS_NAME
     *
     * @return the value of TT_APP_LOG.CLASS_NAME
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    public String getClassName() {
        return className;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_APP_LOG.CLASS_NAME
     *
     * @param className the value for TT_APP_LOG.CLASS_NAME
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_APP_LOG.METHOD
     *
     * @return the value of TT_APP_LOG.METHOD
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    public String getMethod() {
        return method;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_APP_LOG.METHOD
     *
     * @param method the value for TT_APP_LOG.METHOD
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_APP_LOG.CREATE_DATE
     *
     * @return the value of TT_APP_LOG.CREATE_DATE
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_APP_LOG.CREATE_DATE
     *
     * @param createDate the value for TT_APP_LOG.CREATE_DATE
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_APP_LOG.LOGLEVEL
     *
     * @return the value of TT_APP_LOG.LOGLEVEL
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    public String getLoglevel() {
        return loglevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_APP_LOG.LOGLEVEL
     *
     * @param loglevel the value for TT_APP_LOG.LOGLEVEL
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    public void setLoglevel(String loglevel) {
        this.loglevel = loglevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_APP_LOG.MSG
     *
     * @return the value of TT_APP_LOG.MSG
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    public String getMsg() {
        return msg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_APP_LOG.MSG
     *
     * @param msg the value for TT_APP_LOG.MSG
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APP_LOG
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
        TtAppLog other = (TtAppLog) that;
        return (this.getLogid() == null ? other.getLogid() == null : this.getLogid().equals(other.getLogid()))
            && (this.getClassName() == null ? other.getClassName() == null : this.getClassName().equals(other.getClassName()))
            && (this.getMethod() == null ? other.getMethod() == null : this.getMethod().equals(other.getMethod()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getLoglevel() == null ? other.getLoglevel() == null : this.getLoglevel().equals(other.getLoglevel()))
            && (this.getMsg() == null ? other.getMsg() == null : this.getMsg().equals(other.getMsg()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APP_LOG
     *
     * @mbggenerated Fri May 02 18:25:13 CST 2014
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLogid() == null) ? 0 : getLogid().hashCode());
        result = prime * result + ((getClassName() == null) ? 0 : getClassName().hashCode());
        result = prime * result + ((getMethod() == null) ? 0 : getMethod().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getLoglevel() == null) ? 0 : getLoglevel().hashCode());
        result = prime * result + ((getMsg() == null) ? 0 : getMsg().hashCode());
        return result;
    }
}