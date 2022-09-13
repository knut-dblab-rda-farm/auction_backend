package org.dblab.auction_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginObj {

    private String checkUser;
    private String email;
    private String password;

}
