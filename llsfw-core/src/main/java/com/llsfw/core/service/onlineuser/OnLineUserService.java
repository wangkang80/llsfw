/**
 * OnLineUserService.java
 * Created at 2013-12-18
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.service.onlineuser;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsfw.core.mapper.standard.TtOnlineUserMapper;
import com.llsfw.core.model.expand.LoginUser;
import com.llsfw.core.model.standard.TtOnlineUser;
import com.llsfw.core.model.standard.TtOnlineUserCriteria;
import com.llsfw.core.service.BaseService;

/**
 * <p>
 * ClassName: OnLineUserService
 * </p>
 * <p>
 * Description: 用户在线服务
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月18日
 * </p>
 */
@Service
public class OnLineUserService extends BaseService {
    /**
     * <p>
     * Field tolum: 在线用户
     * </p>
     */
    @Autowired
    private TtOnlineUserMapper tolum;

    /**
     * <p>
     * Description: 返回在线用户列表
     * </p>
     * 
     * @return 在线用户列表
     */
    public List<TtOnlineUser> getOnlineUserList() {
        TtOnlineUserCriteria tolu = null;
        tolu = new TtOnlineUserCriteria();
        tolu.setOrderByClause("LOGIN_DATE DESC");
        return this.tolum.selectByExample(tolu);
    }

    /**
     * <p>
     * Description: 用户登录(只有LoginUser类型才操作,其他的类型直接跳过)
     * </p>
     * 
     * @param ip ip地址
     * @param sessionObject session对象
     */
    public void userOnline(String ip, Object sessionObject) {
        //判断对象为LoginModule
        if (sessionObject instanceof LoginUser) {

            //先离线
            this.userOffline(ip, sessionObject);

            //新增在线用户
            LoginUser user = null;
            user = (LoginUser) sessionObject;
            TtOnlineUser tou = null;
            tou = new TtOnlineUser();
            tou.setLoginDate(new Date());
            tou.setLoginIp(ip);
            tou.setLoginName(user.getUser().getLoginName());
            this.tolum.insert(tou);
        }
    }

    /**
     * <p>
     * Description: 离线(只有LoginUser类型才操作,其他的类型直接跳过)
     * </p>
     * 
     * @param ip ip
     * @param sessionObject session
     */
    public void userOffline(String ip, Object sessionObject) {
        //判断对象为LoginModule
        if (sessionObject instanceof LoginUser) {
            if (null != ip) {
                LoginUser user = null;
                user = (LoginUser) sessionObject;

                //删除在线用户(同IP,同用户)
                TtOnlineUserCriteria touc = null;
                touc = new TtOnlineUserCriteria();
                touc.createCriteria().andLoginNameEqualTo(user.getUser().getLoginName()).andLoginIpEqualTo(ip);
                this.tolum.deleteByExample(touc);
            }
        }
    }
}
