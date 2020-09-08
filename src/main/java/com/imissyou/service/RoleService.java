package com.imissyou.service;

import java.util.List;

public interface RoleService {

    List<String> findRolsByUserID(Long userId);

}
