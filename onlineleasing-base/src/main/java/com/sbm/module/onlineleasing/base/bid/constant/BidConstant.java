package com.sbm.module.onlineleasing.base.bid.constant;

public class BidConstant {

	// 状态

	/**
	 * 1-已保存
	 * 保存/修改时使用这此状态
	 */
	public static final Integer CREATED = 1;
	/**
	 * 2-预览合同生成
	 * 保存之后发起异步事件，调用接口生成预览合同，生成成功后使用此状态
	 */
	public static final Integer PREVIEW_CONTRACT_CREATED = 2;

	/**
	 * 3-预览合同生成失败
	 * 保存之后发起异步事件，调用接口生成预览合同，生成失败后使用此状态
	 */
	public static final Integer PREVIEW_CONTRACT_FAILURE = 3;

	/**
	 * 4-等待提交至海鼎
	 */
	public static final Integer WAITING = 4;

	/**
	 * 5-提交成功
	 * 提交至海鼎成功，用此状态
	 */
	public static final Integer SUCCESS = 5;

	/**
	 * 6-提交失败
	 * 提交至海鼎失败，用此状态
	 */
	public static final Integer FAILURE = 6;

	/**
	 * 7-暂不提交至海鼎
	 * 用户点击提交，判断是否非标，非标的出价需要判断是否有审批结束，审批结果为通过，是否生效为未生效的合同(待付保证金和邮寄合同的出价)，
	 * 如果有则需等待提交，用此状态
	 */
	public static final Integer PAUSE = 7;

	/**
	 * 9-审批结束
	 * 这条出价审批结束，收到返回（出价通过/驳回）结果，用此状态
	 */
	public static final Integer END_OF_APPROVAL = 9;

	/***************************************************************/
	// 标准/非标准合同

	/**
	 * 1-标准合同
	 */
	public static final Integer STANDARD = 1;

	/**
	 * 2-非标准合同
	 */
	public static final Integer NONSTANDARD = 2;

	/***************************************************************/
	// 审批结果

	/**
	 * 0-未审批
	 */
	public static final Integer NOT_APPROVED = 0;

	/**
	 * 1-审批通过
	 */
	public static final Integer APPROVE = 1;

	/**
	 * 2-审批不通过
	 */
	public static final Integer REJECT = 2;

	/***************************************************************/
	// 是否生效

	/**
	 * 0-未生效
	 * 尚未获得返回结果的出价
	 */
	public static final Integer INEFFECTIVE = 0;

	/**
	 * 1-生效
	 * 交完保证金，收到纸质合同后，海鼎返回结果
	 */
	public static final Integer EFFECTIVE = 1;

	/**
	 * 2-不生效
	 * 1、别的合同出价生效，剩余的出价失效
	 * 2、出价过期失效
	 */
	public static final Integer INVALID = 2;

}
