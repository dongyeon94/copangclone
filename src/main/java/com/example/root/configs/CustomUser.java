package com.example.root.configs;

import com.example.root.dao.entity.UserEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;


@Getter @Setter
@Data
public class CustomUser implements UserDetails {

    private Long        id;
    private String      email;
    private String      password;
    private String      username;
    private String      address;
    private String      phoneNumber;
    private Collection<? extends GrantedAuthority> authorities;

    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    public CustomUser(UserEntity userEntity) {
        this.id             = userEntity.getId();
        this.email          = userEntity.getEmail();
        this.password       = userEntity.getPassword();
        this.username       = userEntity.getNickName();
        this.address        = userEntity.getAddress();
        this.phoneNumber    = userEntity.getPhoneNumber();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
