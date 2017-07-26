package com.lenach.totalbooker.security;

import com.lenach.totalbooker.data.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by o.chubukina on 13/06/2017.
 */
public class UserAccount extends com.lenach.totalbooker.data.User implements UserDetails {

    private static final long serialVersionUID = 5639683223516504866L;
    public UserAccount (User user) {
        if (user != null) {
            this.setId(user.getId());
            this.setUsername(user.getUsername());
            this.setPassword(user.getPassword());
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_USER");
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
