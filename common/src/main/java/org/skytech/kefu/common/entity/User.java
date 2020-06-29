package org.skytech.kefu.common.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "uk_user")
@org.hibernate.annotations.Proxy(lazy = false)
public class User implements java.io.Serializable {

    @Id
    private String id;


    private String username;
    private String password;

    private String usertype; // 0 Admin User  : !0  Other User
    private String orgi;

    private String salt;        //密码加盐
}
