package com.example.database.service.IMPL;

import com.example.database.Model.dto.LoginDTO;
import com.example.database.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServieImpl  implements LoginService {
    @Override
    public String login(String username ,String passwword ){
      if(username.equals("admin") && passwword.equals("admin")){
          return "admin";
      }
        return "false";
    }
}
