package id.co.mii.serverapp.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AppUserDetail implements UserDetails {

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        user
                .getRoles()
                .forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()) // ROLE USER
                    );
                    role
                            .getPrivileges()
                            .forEach(privilege -> {
                                authorities.add(new SimpleGrantedAuthority(privilege.getName().toUpperCase()) // CREATE_USER
                                );
                            });
                });
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getIsAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getIsAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getIsCredentialNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.getIsEnabled();
    }

}
