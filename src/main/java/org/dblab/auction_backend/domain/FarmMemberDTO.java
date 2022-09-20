package org.dblab.auction_backend.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Collections;

import lombok.Data;

@Data
public class FarmMemberDTO implements UserDetails {
    
    private int farm_id;
    private String f_passwd;
    private String f_email;
    private int f_phonenum;
    private String f_name;
    private String f_RRN;
    private String f_BRN;
    private int f_zipcode;
    private String f_location;
    private String f_farm_name;
    private int f_num;
    private String f_explanation;
    private String f_major_crop;
    private String f_profile_img;
    private String f_img;
    private String f_bank;
    private String f_bank_name;
    private int f_bank_num; 
    private String f_bank_img;
    private String authority;
    private String token;
    private MultipartFile new_bank_img;
    private MultipartFile new_farm_img;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.authority));
    }
    @Override
    public String getPassword() {
        return this.f_passwd;
    }
    @Override
    public String getUsername() {
        return this.f_email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
