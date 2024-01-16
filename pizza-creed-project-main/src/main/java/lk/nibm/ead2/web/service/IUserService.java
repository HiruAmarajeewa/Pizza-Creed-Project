package lk.nibm.ead2.web.service;

import lk.nibm.ead2.web.model.User;

public interface IUserService {
    User findByUsername(String username);

    User save(User user);
}
