package com.sbm.module;

import com.alibaba.fastjson.JSON;
import com.sbm.module.onlineleasing.base.mall.biz.ITOLMallService;
import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SyncHdApplicationTests {

	@Autowired
	private ITOLMallService service;

	@Test
	public void contextLoads() {
//		TOLMall po = service.findOneByCode("OLMALL171212000010");
//		System.out.println(JSON.toJSONString(po));
//		po.setGrossFloorArea(new BigDecimal(777));
//		service.save(po);
	}
}
