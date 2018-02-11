package com.sbm.module;

import com.alibaba.fastjson.JSON;
import com.sbm.module.partner.hd.rest.base.domain.*;
import com.sbm.module.partner.hd.rest.brand.client.IHdBrandClient;
import com.sbm.module.partner.hd.rest.brand.domain.HdBrand;
import com.sbm.module.partner.hd.rest.contract.client.IHdContractClient;
import com.sbm.module.partner.hd.rest.contract.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SyncHdApplicationTests {

	@Autowired
	private IHdBrandClient brandClient;
	@Autowired
	private IHdContractClient client;

//	@Test
	public void brand() {
		HdQueryFilter filter = new HdQueryFilter();
		filter.setPageSize(10000);
		HdResult<HdResultBody<HdBrand>> result = brandClient.query(filter);
		System.out.println(JSON.toJSONString(result));
	}


	//@Test
	public void contextLoads() {
		Date begin = null;
		Date end = null;
		try {
			begin = new SimpleDateFormat("yyyy-MM-dd").parse("2018-02-08");
			end = new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-08");
		} catch (Exception e) {

		}

		HdContract vo = new HdContract();
		// 模板名称
		vo.setTemplate("正柜租赁-取高");
		// ol合同id
		vo.setOlContractId("test666666");
		// 合同类型
		vo.setContractCategory("正柜");
		// 合作方式 计租方式
		vo.setCoopMode("取高");
		// 结算方式
		vo.setAccType("租赁");
		// 法务异议
		vo.setLegal_objection("法务异议123");
		// 有效期天数
		vo.setEffective_Days(15);
		// 装修免租期
		vo.setFree_days(15);
		// 商户
		vo.setTenant(new HdUCN("8a028c966020793a016030826b8d30d0", "00000015", "上海完美珠宝商贸有限公司"));
		// 品牌
		vo.setBrand(new HdUCN("8a028c966069b2680160782c36721f32", "00001555", "格力"));
		// 业态
		vo.setBizType(new HdBizType("8a028c966020793a01603005482b0dac", "00050203", "家用", "0000000500020003"));
		// 合同开始时间
		vo.setBeginDate(begin);
		// 合同结束时间
		vo.setEndDate(end);
		// 收银方式
		vo.setPosMode("unuse");
		// 铺位
		vo.setPosition(new HdUCN("8a028c966137dc70016140a874f418a0", "B03FL001", "2-301"));

		// 月固定条款
		HdMonthFixedTerm monthFixedTerm;
		// 销售固定比例提成条款
		HdNormalSaleRateTerm normalSaleRateTerm;
		// 账款按科目取最大值条款
		HdMaxSubjectTerm maxSubjectTerm;
		// 预存款条款
		HdDepositTerm depositTerm;
		// 免租条款
		HdFreeAccountTerm freeAccountTerm;

		// 固定租金
		monthFixedTerm = new HdMonthFixedTerm("固定租金", "月固定条款");
		monthFixedTerm.getDetails().add(new HdDateRangeDetail(begin, end, new BigDecimal("666666")));
		vo.getMonthFixeds().add(monthFixedTerm);

		// 提成租金
		normalSaleRateTerm = new HdNormalSaleRateTerm("提成租金", "销售固定比例提成条款");
		normalSaleRateTerm.getDetails().add(new HdDateRangeDetail(begin, end, new BigDecimal("10")));
		vo.getNormalSaleRates().add(normalSaleRateTerm);

		// 固租与提成取高
		maxSubjectTerm = new HdMaxSubjectTerm("固租与提成取高", "销售固定比例提成条款");
		maxSubjectTerm.getDetails().add(new HdDateRangeDetail(begin, end, null));
		vo.getMaxSubjects().add(maxSubjectTerm);

		// 物业管理费
		monthFixedTerm = new HdMonthFixedTerm("物业管理费", "月固定条款");
		monthFixedTerm.getDetails().add(new HdDateRangeDetail(begin, end, new BigDecimal("50")));
		vo.getMonthFixeds().add(monthFixedTerm);

		// 推广费（比例）
		normalSaleRateTerm = new HdNormalSaleRateTerm("推广费（比例）", "销售固定比例提成条款");
		normalSaleRateTerm.getDetails().add(new HdDateRangeDetail(begin, end, new BigDecimal("1")));
		vo.getNormalSaleRates().add(normalSaleRateTerm);

		// POS租用费
		monthFixedTerm = new HdMonthFixedTerm("POS租用费", "月固定条款");
		monthFixedTerm.getDetails().add(new HdDateRangeDetail(begin, end, new BigDecimal("1")));
		vo.getMonthFixeds().add(monthFixedTerm);

		// 收银员费用
		monthFixedTerm = new HdMonthFixedTerm("收银员费用", "月固定条款");
		monthFixedTerm.getDetails().add(new HdDateRangeDetail(begin, end, new BigDecimal("1")));
		vo.getMonthFixeds().add(monthFixedTerm);

		// GAG初装费 TODO

		// 装修保证金
		depositTerm = new HdDepositTerm("装修保证金", "预存款条款");
		depositTerm.getDetails().add(new HdDepositTermDetail(new BigDecimal("1")));
		vo.getDepositTerms().add(depositTerm);

		// 租赁意向金
		depositTerm = new HdDepositTerm("租赁意向金", "预存款条款");
		depositTerm.getDetails().add(new HdDepositTermDetail(new BigDecimal("1")));
		vo.getDepositTerms().add(depositTerm);

		// GAG押金
		depositTerm = new HdDepositTerm("GAG押金", "预存款条款");
		depositTerm.getDetails().add(new HdDepositTermDetail(new BigDecimal("1")));
		vo.getDepositTerms().add(depositTerm);

		// 租赁保证金
		depositTerm = new HdDepositTerm("租赁保证金", "预存款条款");
		depositTerm.getDetails().add(new HdDepositTermDetail(new BigDecimal("1")));
		vo.getDepositTerms().add(depositTerm);

		// 公共事业费押金
		depositTerm = new HdDepositTerm("公共事业费押金", "预存款条款");
		depositTerm.getDetails().add(new HdDepositTermDetail(new BigDecimal("240000")));
		vo.getDepositTerms().add(depositTerm);

		// 装修免租期
		freeAccountTerm = new HdFreeAccountTerm("装修免租期", "免租条款");
		vo.getFreeAccountTerms().add(freeAccountTerm);

		// 约定营业日
		vo.getEnteryTerm().setOpenDate(begin);


		// 预览
//		HdResult<String> result = client.preview(vo);

		// 非标
		List<HdContract> vos = new ArrayList<>();
		vos.add(vo);

		System.out.println(JSON.toJSONString(vos));

		HdResult<HdContractNonStandardResult> result = client.nonstandardSubmit(vos);

		System.out.println(JSON.toJSONString(result));
	}
}
