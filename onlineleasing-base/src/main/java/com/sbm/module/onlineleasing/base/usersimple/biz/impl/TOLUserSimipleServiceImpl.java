package com.sbm.module.onlineleasing.base.usersimple.biz.impl;


import com.sbm.module.onlineleasing.base.usersimple.biz.ITOLUserSimpleService;
import com.sbm.module.onlineleasing.base.usersimple.domain.TOLUserSimple;
import com.sbm.module.onlineleasing.base.usersimple.repository.ITOLUserSimpleRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TOLUserSimipleServiceImpl extends OLDataServiceImpl<TOLUserSimple, Integer> implements ITOLUserSimpleService {

	@Autowired
	private ITOLUserSimpleRepository repository;


}
