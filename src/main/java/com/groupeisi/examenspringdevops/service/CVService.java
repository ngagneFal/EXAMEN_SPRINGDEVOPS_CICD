package com.groupeisi.examenspringdevops.service;

import com.groupeisi.examenspringdevops.entity.CVEntity;
import com.groupeisi.examenspringdevops.entity.Role;
import com.groupeisi.examenspringdevops.repository.ICVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author MS GASSAMA
 */
@Service
public class CVService implements UserDetailsService {
    private ICVRepository icvRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public CVService(ICVRepository icvRepository) {
        this.icvRepository = icvRepository;
    }

    public CVEntity save(CVEntity cvEntity) {
        cvEntity.setPassword(passwordEncoder.encode(cvEntity.getPassword()));
        cvEntity.setRoles( Arrays.asList(new Role("ROLE_USER")));

        return icvRepository.save(cvEntity);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CVEntity cvEntity = icvRepository.findByEmail(username);
        if (cvEntity == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(cvEntity.getEmail(), cvEntity.getPassword(), mapRolesToAuthorities(cvEntity.getRoles()));
    }

    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection< Role > roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNom())).collect(Collectors.toList());
    }
    public  CVEntity edit(int id) {return icvRepository.findById(id).get();}

    public  CVEntity update(CVEntity cvEntity) {return icvRepository.save(cvEntity);}

    public CVEntity getCV(String email) {return icvRepository.findByEmail(email);}
}

