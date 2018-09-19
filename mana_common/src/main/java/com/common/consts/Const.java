package com.common.consts;

/**
 * 全局静态资源：
 * 
 */
public class Const {

	/**
	 * 子服务系统编码定义
	 */
	public static final String SUB_SYSTEM_CODE_SUSPECT = "serUrl.suspect";// 人员子系统
	public static final String SUB_SYSTEM_CODE_CASE = "serUrl.case";// 案情子系统
	public static final String SUB_SYSTEM_CODE_ALARM = "serUrl.alarm";// 警情子系统
	public static final String SUB_SYSTEM_CODE_QB = "serUrl.intelligence";// 情报子系统
	public static final String SUB_SYSTEM_CODE_INTEGRAL = "serUrl.integral";// 积分统计
	public static final String SUB_SYSTEM_CODE_SYS = "serUrl.sys";// 系统配置
	public static final String SUB_SYSTEM_CODE_TICKET = "serUrl.ticket";// 技战工具
	public static final String SUB_SYSTEM_CODE_TASKSTU = "serUrl.taskStu";// 任务研判

	/**
	 * 涉案人员类别
	 */
	public static final String CASE_SUSPECT_TYPE_XY = "1000";// 嫌疑人
	public static final String CASE_SUSPECT_TYPE_SH = "1001";
	/**
	 * 分权分域类别-单位粒度
	 */
	public static final String DECE_SIGNS_TYPE_UNIT = "1";//
	/**
	 * 分权分域类别-用户粒度
	 */
	public static final String DECE_SIGNS_TYPE_USER = "2";//
	/**
	 * 重点人员类别
	 */
	public static final String SUSPECT_TYPE_QT = "4205";// 其他
	public static final String SUSPECT_TYPE_XM = "4204";// 刑满释放
	public static final String SUSPECT_TYPE_BGK = "4203";// 监视
	public static final String SUSPECT_TYPE_ZY = "4200";// 在押
	public static final String SUSPECT_TYPE_QB = "4202";// 取保
	public static final String SUSPECT_TYPE_ZT = "4201";// 在逃

	/**
	 * 重点人员关注标识
	 */
	public static final Integer SUSPECT_IS_INTL_YES = Integer.valueOf(1);// 已关注
	public static final Integer SUSPECT_IS_INTL_NO = Integer.valueOf(0);// 未关注

	/**
	 * 积分部分静态资源
	 */
	public static final String INTEGRAL_FZ = "30";
	public static final String INTEGRAL_SUSPECT_BL_ZD = "3010";
	public static final String INTEGRAL_SUSPECT_BL_BGK = "3011";
	public static final String INTEGRAL_SUSPECT_BL_ZT = "3012";
	// 默认分值
	public static final String INTEGRAL_SUSPECT_ALARM = "3020";
	public static final String INTEGRAL_SUSPECT_CASE = "3021";
	public static final String INTEGRAL_SUSPECT_QB = "3022";

	// 单位积分权重
	/**
	 * 单位积分权重-警情
	 */
	public static final String INTEGRAL_UNIT_ALARM = "3002";
	/**
	 * 单位积分权重-案件
	 */
	public static final String INTEGRAL_UNIT_CASE = "3001";
	/**
	 * 单位积分权重-情报
	 */
	public static final String INTEGRAL_UNIT_QB = "3003";
	/**
	 * 单位积分权重-重点人员
	 */
	public static final String INTEGRAL_UNIT_SUSPECT = "3004";
	/**
	 * 案件串并方式
	 */
	public static final String CASE_SERIES_CATEGORY = "案件类别";
	public static final String CASE_SERIES_SUSPICION = "嫌疑人身份证号";
	public static final String CASE_SERIES_VICTIM = "受害人身份证号";

	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_MENULIST = "menuList"; // 当前菜单

	/**
	 * 上传配置文件位置
	 */
	public static final String UPLOAD_CONFIG = "upload.properties";
	/**
	 * 没有权限返回的URL
	 */
	public static final String NO_AUTHORIZED_URL = "/system/noAuthorized";// 没有权限返回的URL
	/**
	 * 没有权限返回中文说明
	 */
	public static final String NO_AUTHORIZED_MSG = "当前角色没有权限";//
	/**
	 * 返回值 没有权限 100
	 */
	public static final int NO_AUTHORIZED = 100;
	/**
	 * 返回值 成功(1)
	 */
	public static final int SUCCEED = 1;
	/**
	 * 返回值 失败(0)
	 */
	public static final int FAIL = 0;
	/**
	 * 菜单类型 (1)
	 */
	public static final String RESOURCES_TYPE_MENU = "1";
	/**
	 * 功能类型(2)
	 */
	public static final String RESOURCES_TYPE_FUNCTION = "2";
	/**
	 * 按钮类型(3)
	 */
	public static final String RESOURCES_TYPE_BUTTON = "3";
	/**
	 * 保存成功
	 */
	public static final String SAVE_SUCCEED = "保存成功";
	/**
	 * 保存失败
	 */
	public static final String SAVE_FAIL = "保存失败";
	/**
	 * 删除成功
	 */
	public static final String DEL_SUCCEED = "删除成功";
	/**
	 * 删除失败
	 */
	public static final String DEL_FAIL = "删除失败";
	/**
	 * 修改成功
	 */
	public static final String UPDATE_SUCCEED = "修改成功";
	/**
	 * 修改失败
	 */
	public static final String UPDATE_FAIL = "修改失败";
	/**
	 * 数据获取成功
	 */
	public static final String DATA_SUCCEED = "数据获取成功";
	/**
	 * 数据获取失败
	 */
	public static final String DATA_FAIL = "数据获取失败";

	// 消息列表接口定义 静态变量
	/**
	 * 情报下发通知
	 */
	public static final String XIAOXI_QINGBAO_XIAFA = "3100";
	/**
	 * 情报接收通知
	 */
	public static final String XIAOXI_QINGBAO_JIESHOU = "3101";
	/**
	 * 情报接收审核通知
	 */
	public static final String XIAOXI_QINGBAO_JIESHOUSHENHE = "3102";
	/**
	 * 情报上报通知
	 */
	public static final String XIAOXI_QINGBAO_SHANGBAO = "3103";
	/**
	 * 情报处理通知
	 */
	public static final String XIAOXI_QINGBAO_CHULI = "3104";
	/**
	 * 预警生成通知
	 */
	public static final String XIAOXI_QINGBAO_YUJING = "3105";
	/**
	 * 1:新增举报
	 */
	public static final String XIAOXI_JUBAO_XINZENG = "3106";
	/**
	 * 2:审核举报
	 */
	public static final String XIAOXI_JUBAO_SHENHE = "3107";

	/**
	 * 3:下发举报
	 */
	public static final String XIAOXI_JUBAO_XIAFA = "3108";

	/**
	 * 4:签收举报
	 */
	public static final String XIAOXI_JUBAO_QIANSHOU = "3109";

	/**
	 * 5:研判举报
	 */
	public static final String XIAOXI_JUBAO_YANPAN = "3110";
	/**
	 * 6:查结反馈
	 */
	public static final String XIAOXI_JUBAO_CHAJIE = "3111";

}
