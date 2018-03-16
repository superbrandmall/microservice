package com.sbm.module.onlineleasing.base.usersettings.repository;

import com.sbm.module.onlineleasing.base.usersettings.domain.TOLUserSettings;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "usersettings")
public interface ITOLUserSettingsRepository extends IOLDataRepository<TOLUserSettings, Integer> {


}
