package com.gwy.service.impl;

import com.gwy.bean.User;

public interface UserService {

    User getUserById(String id);

    User getUserByName(String  name);
}
