package com.sbm.module.onlineleasing.file.init;

import com.sbm.module.common.init.InitAfterLoad;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class UploadInit implements InitAfterLoad {

	@Value("${spring.http.multipart.location}")
	private String location;

	@Override
	public void init() {
		File dir = new File(location);
		if (!dir.exists() || !dir.isDirectory()) {
			dir.mkdirs();
		}
	}
}
