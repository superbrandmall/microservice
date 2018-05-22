package com.sbm.module.common.authorization.api.user.biz;


import com.sbm.module.common.authorization.api.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {

	void save(User vo);

	String encryptPassword(String password);

	Page<User> findAll(Pageable pageable);

	User findOneByCode(String code);

	User findOneByUsername(String username);

	void updateLastLogin(String code);

	void updateName(String code, String name);

	void updateNameAndIdCard(String code, String name, String idCard, Integer idCardType);

	void updatePassword(String code, String password);
}
