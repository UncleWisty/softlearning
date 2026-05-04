package com.example.infraestructure.security.bootstrap;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.core.entities.auth.dto.PermissionEntity;
import com.example.core.entities.auth.dto.RoleEntity;
import com.example.core.entities.auth.dto.RoleEnum;
import com.example.core.entities.auth.dto.UserEntity;
import com.example.infraestructure.persistence.jpa.auth.JpaPermissionRepository;
import com.example.infraestructure.persistence.jpa.auth.JpaRoleRepository;
import com.example.infraestructure.persistence.jpa.auth.JpaUserRepository;

@Component
public class SecurityDataLoader implements CommandLineRunner {

    private final JpaUserRepository userRepository;
    private final JpaRoleRepository roleRepository;
    private final JpaPermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    public SecurityDataLoader(
            JpaUserRepository userRepository,
            JpaRoleRepository roleRepository,
            JpaPermissionRepository permissionRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) {
            return;
        }

        PermissionEntity create = permissionRepository.save(new PermissionEntity("CREATE"));
        PermissionEntity read = permissionRepository.save(new PermissionEntity("READ"));
        PermissionEntity update = permissionRepository.save(new PermissionEntity("UPDATE"));
        PermissionEntity delete = permissionRepository.save(new PermissionEntity("DELETE"));

        RoleEntity adminRole = roleRepository.save(new RoleEntity(
                RoleEnum.ADMIN,
                Set.of(create, read, update, delete)));

        RoleEntity userRole = roleRepository.save(new RoleEntity(
                RoleEnum.USER,
                Set.of(read)));

        RoleEntity productsManagerRole = roleRepository.save(new RoleEntity(
                RoleEnum.PRODUCTS_MANAGER,
                Set.of(create, read, update)));

        UserEntity jose = new UserEntity("jose", passwordEncoder.encode("1234"), true, true, true, true,
                Set.of(adminRole));
        UserEntity ruben = new UserEntity("ruben", passwordEncoder.encode("1234"), true, true, true, true,
                Set.of(userRole));
        UserEntity andrea = new UserEntity("andrea", passwordEncoder.encode("1234"), true, true, true, true,
                Set.of(userRole));
        UserEntity marta = new UserEntity("marta", passwordEncoder.encode("1234"), true, true, true, true,
                Set.of(productsManagerRole));

        userRepository.saveAll(List.of(jose, ruben, andrea, marta));
    }
}
