package org.ifsim.vairline.operator.controller;

import java.util.Collection;
import java.util.Map;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  
@RequestMapping("/sessions")  
public class SessionController {  
    @Autowired  
    private SessionDAO sessionDAO;  
    
    @RequestMapping()  
    public String list(Map<String,Object> view) {  
        Collection<Session> sessions =  sessionDAO.getActiveSessions();  
        view.put("sessions", sessions);  
        view.put("sesessionCount", sessions.size());  
        return "session/info";  
    }  
}  
