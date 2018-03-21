package com.sbm.module.common.authorization.base.usersettings.biz;

import com.sbm.module.common.authorization.base.usersettings.domain.TCUserSettings;
import com.sbm.module.common.data.biz.IDataService;

public interface ITCUserSettingsService extends IDataService<TCUserSettings, Integer> {

	TCUserSettings findOneByCode(String code);

	TCUserSettings findOneByIdCard(String idCard);

}
