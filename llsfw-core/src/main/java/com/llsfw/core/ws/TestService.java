package com.llsfw.core.ws;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.llsfw.core.model.standard.TtApplicationUser;

@WebService
public interface TestService {

    public List<TtApplicationUser> getUserList();

    public String getName(@WebParam(name = "name") String name);

}
