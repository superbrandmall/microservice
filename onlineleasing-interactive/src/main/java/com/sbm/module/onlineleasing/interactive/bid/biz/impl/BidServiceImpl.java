package com.sbm.module.onlineleasing.interactive.bid.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.constant.CommonConstant;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.onlineleasing.base.bid.biz.ITOLBidService;
import com.sbm.module.onlineleasing.base.bid.constant.BidConstant;
import com.sbm.module.onlineleasing.base.bid.domain.TOLBid;
import com.sbm.module.onlineleasing.base.bidcontract.biz.ITOLBidContractService;
import com.sbm.module.onlineleasing.base.bidcontract.domain.TOLBidContract;
import com.sbm.module.onlineleasing.base.shop.biz.ITOLShopService;
import com.sbm.module.onlineleasing.base.shop.constant.ShopConstant;
import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.onlineleasing.file.upload.client.IUploadProcessClient;
import com.sbm.module.onlineleasing.file.upload.constant.UploadConstant;
import com.sbm.module.onlineleasing.file.upload.domain.UploadDetail;
import com.sbm.module.onlineleasing.interactive.bid.biz.IBidService;
import com.sbm.module.onlineleasing.interactive.bid.domain.ApproveResult;
import com.sbm.module.onlineleasing.interactive.bid.domain.BidResult;
import com.sbm.module.onlineleasing.interactive.bid.domain.EffectResult;
import com.sbm.module.onlineleasing.interactive.bid.exception.BidCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class BidServiceImpl extends CommonServiceImpl implements IBidService {

	@Autowired
	private ITOLBidService service;
	@Autowired
	private ITOLBidContractService contractService;
	@Autowired
	private ITOLShopService shopService;

	@Autowired
	private IUploadProcessClient uploadProcessClient;

	@Override
	@Transactional
	public void approve(BidResult<ApproveResult> vo) {
		vo.getBidResultReceives().forEach(e -> {
			TOLBid po = checkIfNullThrowException(service.findOneByBillNumber(e.getBid().getBillNumber()),
					new BusinessException(BidCode.C11203, new Object[]{e.getBid().getBillNumber()}));
			// 审批通过
			if (BidConstant.APPROVE.equals(e.getBid().getIsApprove())) {
				po.setIsApprove(e.getBid().getIsApprove());
				// 合同过期日期
				po.setExpireDate(checkIfNullThrowException(e.getBid().getExpireDate(), new BusinessException(BidCode.C11200, new Object[]{e.getBid().getBillNumber()})));
				// 更新账单
				updateContract(po.getCode(), e.getBidContract().getDepositBillNumber(), e.getBidContract().getFileId());
			}
			// 审批不通过
			else if (BidConstant.REJECT.equals(e.getBid().getIsApprove())) {
				po.setIsApprove(e.getBid().getIsApprove());
			}
			// 其他
			else {
				throw new BusinessException(BidCode.C11201, new Object[]{e.getBid().getBillNumber(), e.getBid().getIsApprove()});
			}
			// 审批结束
			po.setProcessState(BidConstant.END_OF_APPROVAL);
			// 更新记录
			service.save(po);
			// TODO 其他操作
		});
	}


	private void updateContract(String bidCode, String depositBillNumber, String fileId) {
		TOLBidContract bidContract = contractService.findOneByCode(bidCode);
		bidContract.setDepositBillNumber(depositBillNumber);
		JsonContainer<String> result = uploadProcessClient.saveUploadDetail(new UploadDetail(
				CommonConstant.SYSTEM, fileId, UploadConstant.CONTAINER_NAME_DEFAULT,
				bidCode, UploadConstant.PDF, UploadConstant.DEPOSIT_BILL));
		bidContract.setDepositBillPdf(checkJsonContainer(result));
		contractService.save(bidContract);
	}

	@Override
	@Transactional
	public void effect(BidResult<EffectResult> vo) {
		vo.getBidResultReceives().forEach(e -> {
			TOLBid po = checkIfNullThrowException(service.findOneByBillNumber(e.getBid().getBillNumber()),
					new BusinessException(BidCode.C11203, new Object[]{e.getBid().getBillNumber()}));
			// 生效
			if (BidConstant.EFFECTIVE.equals(e.getBid().getIsEffect())) {
				po.setIsEffect(e.getBid().getIsEffect());
				// 更新铺位
				updateShop(po.getShopCode(), ShopConstant.SHOP_STATE_0, po.getEndDate(), po.getBrandCode());
			}
			// 不生效
			else if (BidConstant.INVALID.equals(e.getBid().getIsEffect())) {
				po.setIsEffect(e.getBid().getIsEffect());
			}
			// 其他
			else {
				throw new BusinessException(BidCode.C11202, new Object[]{e.getBid().getBillNumber(), e.getBid().getIsEffect()});
			}
			// 更新记录
			service.save(po);
			// TODO 其他操作
		});
	}

	private void updateShop(String shopCode, Integer shopState, Date contractExpireDate, String brandCode) {
		TOLShop po = shopService.findOneByCode(shopCode);
		// 铺位状态
		po.setShopState(shopState);
		// 更新最后一份合同到期日为本合同到期日
		po.setContractExpireDate(contractExpireDate);
		// 商铺品牌
		po.setBrandCode(brandCode);
		shopService.save(po);
	}

}
