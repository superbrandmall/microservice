package com.sbm.module.onlineleasingfile;

import com.alibaba.fastjson.JSON;
import com.sbm.module.onlineleasing.file.media.caller.BurlapServiceCaller;
import com.sbm.module.onlineleasing.file.media.service.IFileProcessService;
import com.sbm.module.onlineleasing.file.media.service.MediaSFileInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class OnlineleasingFileApplicationTests {

	//@Test
	public void contextLoads() throws Exception {
		IFileProcessService service = BurlapServiceCaller.getFileProcessService();
		MediaSFileInfo info = service.getMediaSFileInfo("ab6884caa3a9e0de56aa2a32c315819d32da134dd47850d1ba2f90b90fe16339b38624078f777ed3");
		System.out.println(JSON.toJSONString(info));
	}

}
