package com.sbm.module.common.authorization.base.usersettings.repository;

import com.sbm.module.common.authorization.base.usersettings.domain.TCUserSettings;
import com.sbm.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "usersettings")
public interface ITCUserSettingsRepository extends IDataRepository<TCUserSettings, Integer> {

	TCUserSettings findOneByCode(String code);

}
