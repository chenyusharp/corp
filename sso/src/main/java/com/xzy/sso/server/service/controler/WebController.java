package com.xzy.sso.server.service.controler;

import com.xzy.sso.core.user.XzySsoUser;
import com.xzy.sso.server.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Date: 2023/7/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Controller
public class WebController {

    @Autowired
    private UserService userService;


    @PostMapping("/")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {

        XzySsoUser xzySsoUser = new XzySsoUser();
        return null;


    }


}