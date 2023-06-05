package com.khr.adminservice.service;

import com.khr.adminservice.dto.AdminDto;

import java.util.List;

public interface AdminService {
    List<AdminDto> getAll();

    AdminDto getById(Long id);

    AdminDto createAdmin(AdminDto createBody);

    AdminDto updateAdmin(Long id, AdminDto updateBody);

    void deleteAdmin(Long id);

    void unblockAccount(Long accountId);

}
