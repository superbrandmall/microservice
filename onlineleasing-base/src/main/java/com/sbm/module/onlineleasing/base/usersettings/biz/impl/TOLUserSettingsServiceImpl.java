package com.sbm.module.onlineleasing.base.usersettings.biz.impl;

import com.sbm.module.onlineleasing.base.usersettings.biz.ITOLUserSettingsService;
import com.sbm.module.onlineleasing.base.usersettings.domain.TOLUserSettings;
import com.sbm.module.onlineleasing.base.usersettings.repository.ITOLUserSettingsRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TOLUserSettingsServiceImpl extends OLDataServiceImpl<TOLUserSettings, Integer> implements ITOLUserSettingsService {

	@Autowired
	private ITOLUserSettingsRepository repository;


}
