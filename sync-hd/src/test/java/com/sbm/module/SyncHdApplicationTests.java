package com.sbm.module;

import com.sbm.module.partner.hd.rest.brand.client.IHdBrandClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SyncHdApplicationTests {

	@Autowired
	private IHdBrandClient brandClient;

	@Test
	public void contextLoads() {

//		String tmp = "yourapp-name" + ":" + "yourapp-password";
//		String token = "Basic " + Base64.getEncoder().encodeToString(tmp.getBytes());
//
//		HdQueryFilter filter = new HdQueryFilter();
//		filter.setPage(0);
//		filter.setPageSize(2);
//		HdResult<HdResultBody<HdBrand>> result = brandClient.query(filter);
//
//		int a = 0;
//		int b = 0;
//		for (HdBrand brand : result.getBody().getRecords()) {
//			if (null != brand.getProperties()) {
//				a++;
//			} else {
//				b++;
//			}
//		}
//		System.out.println("a: " + a + " b: " + b);
	}
}
