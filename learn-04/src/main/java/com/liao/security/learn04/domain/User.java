package com.liao.security.learn04.domain;

import java.util.Date;
import lombok.Data;

/**
  * @author liao
  * @since 2020/9/25 16:57
  */    
@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private Date createDatetime;

    private Integer orgId;

    private Byte enable;
}