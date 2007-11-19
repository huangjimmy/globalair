/**
 * Created on Nov 04, 2007
 * Copyright: SEGroup.cs.tsinghua.edu.cn
 * All rights reserved.
 * 
 */
package net.seproject.ws.hotel;

/**
 * 酒店及客房情况
 * 
 * @author shufang
 * @version 1.0
 *
 */
public class HotelRoom {

	/**
	 * 酒店代号
	 * 
	 * <br/><br/>
	 * 唯一标识某个酒店
	 */
	private String id;

	/**
	 * 酒店名称
	 */
	private String hotel;

	/**
	 * 所在城市
	 */
	private String inCity;

	/**
	 * 客房类别
	 * 
	 * <br/><br/>
	 * 1: 高级套间<br/>
	 * 2: 商务间<br/>
	 * 3: 标准间<br/>
	 * <br/>
	 * 目前其他数值无效.
	 */
	private int category;

	/**
	 * 开始时间
	 * 
	 * <br/><br/>
	 * 格式: yyyy-MM-dd hh:mm
	 * <br/>
	 * 满足当前maxDiscount的折扣策略相关的开始时间
	 */
	private String startTime;

	/**
	 * 结束时间
	 * 
	 * <br/><br/>
	 * 格式: yyyy-MM-dd hh:mm
	 * <br/>
	 * 满足当前maxDiscount的折扣策略相关的结束时间
	 */
	private String endTime;

	/**
	 * 最优惠折扣
	 * 
	 * <br/><br/>
	 * 各种可用折扣策略中的最优惠折扣( 0 < x <= 1.0 )
	 * <br/>
	 * 这里的"折扣"是指打几折,例如折扣=0.85, 则指打85折.
	 */
	private float maxDiscount;

	/**
	 * 显示应用各种折扣策略的结果
	 * 
	 * <br/><br/>
	 * 提供人为可读的格式化列表,显示应用各种可用折扣策略的结果. 例如,<br/>
	 * <br/>
	 * 
	 * [策略序号] [折扣] (如果不可用,则显示"-", *标出最优惠的策略)<br/>
	 *    1       85%   <br/>
	 *    2       ---   <br/>
	 *    3       70% * <br/>
	 *    4       95%   <br/>
	 *    5       ---   <br/>
	 * <br/><br/>
	 * 
	 */
	private String detailedDiscount;

	/**
	 * 这类客房全价(单位:RMB元)
	 */
	private int rPrice;

	/**
	 * 这类客房剩余间数(单位:间)
	 */
	private int rAvailableRooms;

	/**
	 * 这类客房总的间数(单位:间)
	 */
	private int rTotalRooms;

}
