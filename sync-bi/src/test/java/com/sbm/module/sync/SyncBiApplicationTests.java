package com.sbm.module.sync;

import com.alibaba.fastjson.JSON;
import com.sbm.module.sync.bi.api.bi.biz.IBiService;
import com.sbm.module.sync.bi.api.bi.domain.Bi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SyncBiApplicationTests {

	@Autowired
	private IBiService service;

	@Test
	public void contextLoads() {
		//service.refresh();
	}
}
