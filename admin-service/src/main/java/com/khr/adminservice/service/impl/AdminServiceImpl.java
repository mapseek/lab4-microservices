package com.khr.adminservice.service.impl;

import com.khr.adminservice.repository.AccountRepository;
import com.khr.adminservice.domain.Admin;
import com.khr.adminservice.dto.AccountDto;
import com.khr.adminservice.dto.AdminDto;
import com.khr.adminservice.repository.AdminRepository;
import com.khr.adminservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AccountRepository accountRepository;

    @Override
    public List<AdminDto> getAll() {
        return adminRepository.findAll().stream().map(Admin::toDto).collect(Collectors.toList());
    }

    @Override
    public AdminDto getById(Long id) {
        return adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin is not found")).toDto();
    }

    @Override
    public AdminDto createAdmin(AdminDto createBody) {
        Admin admin = createBody.toDomain();
        Admin stored = adminRepository.save(admin);
        return stored.toDto();
    }

    @Override
    public AdminDto updateAdmin(Long id, AdminDto updateBody) {
        Admin persisted = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin is not found"));

        String username = updateBody.getUsername();
        if (username != null) persisted.setUsername(username);

        String password = updateBody.getPassword();
        if(password != null) persisted.setPassword(password);

        Admin stored = adminRepository.save(persisted);

        return stored.toDto();
    }

    @Override
    public void deleteAdmin(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin is not found"));
        adminRepository.delete(admin);
    }

    @Override
    public void unblockAccount(Long accountId) {
        AccountDto account = accountRepository.findById(accountId).orElseThrow(()-> new RuntimeException("account is not found"));
        if (account.getBlocked() == false) throw new RuntimeException("Account is not blocked");

        account.setBlocked(true);
        AccountDto stored = accountRepository.save(account);
    }
}
