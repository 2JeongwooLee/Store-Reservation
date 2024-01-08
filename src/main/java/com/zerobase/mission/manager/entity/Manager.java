package com.zerobase.mission.manager.entity;

import com.zerobase.mission.common.type.MemberType;
import com.zerobase.mission.common.entity.BaseEntity;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Audited
@AuditOverride(forClass = BaseEntity.class)
public class Manager extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @Column
    @NotBlank
    private String name;

    @Email
    @Column(unique = true)
    private String email;

    @Column
    @NotBlank
    private String password;

    @Column
    @NotBlank
    private String phoneNumber;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_MANAGER"));
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
