package com.sbm.module.common.authorization.api.user.biz;


import com.sbm.module.common.authorization.api.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {

	void save(User vo);

	Page<User> findAll(Pageable pageable);

	User findOneByCode(String code);

	User findOneByUsername(String username);

	void updateLastLogin(String code);

	void updateNameAndIdCard(String code, String name, String idCard, Integer idCardType);

	void updatePassword(String code, String password);
}
