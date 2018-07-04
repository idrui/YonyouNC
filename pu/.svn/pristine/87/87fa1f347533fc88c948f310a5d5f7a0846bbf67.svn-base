package nc.bs.pu.m23.maintain.api;

import nc.bs.pu.m23.maintain.rule.ChkBackArWaitNumRule;
import nc.bs.pu.m23.maintain.rule.ChkBackFor23Rule;
import nc.bs.pu.m23.maintain.rule.ChkBillCodeUniqueRule;
import nc.bs.pu.m23.maintain.rule.ChkMrlInStockOrg;
import nc.bs.pu.m23.maintain.rule.DealFreeChkItemRule;
import nc.bs.pu.m23.maintain.rule.DealLocalMnyRule;
import nc.bs.pu.m23.maintain.rule.InsertAndDelBatchCodeBeforeRule;
import nc.bs.pu.m23.maintain.rule.ValiVOBeforSaveRule;
import nc.bs.pu.m23.maintain.rule.WriteBackFor23Rule;
import nc.bs.pu.m23.maintain.rule.insert.InsertAfterEventRule;
import nc.bs.pu.m23.maintain.rule.insert.InsertBatchCodeAfterRule;
import nc.bs.pu.m23.maintain.rule.insert.InsertBeforeEventRule;
import nc.bs.pu.m23.maintain.rule.insert.InsertBillCodeRule;
import nc.bs.pu.m23.maintain.rule.insert.InsertFillDataRule;
import nc.bs.pu.m23.maintain.rule.insert.InsertSendMsgRule;
import nc.bs.pu.m23.maintain.rule.insert.Write21WhenInsertRule;
import nc.bs.pu.m23.maintain.rule.insert.Write61WhenInsertRule;
import nc.bs.pu.m23.plugin.ArriveBPPlugInPoint;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.pubitf.bdlayer.msg.ScmBuziMsgResEnum;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.env.ArrivalUIToBSEnv;
import nc.vo.pu.m23.rule.ArriveATPUpdateRule;
import nc.vo.pu.m23.rule.api.PUArriveVOFillRuleBuilder;
import nc.vo.pu.m23.rule.api.check.ArriveVONullCheckRule;
import nc.vo.pu.m23.rule.api.check.ArriveVOValidator;
import nc.vo.pu.m23.rule.api.check.HeadNumAndMnySumFillRule;
import nc.vo.pu.m23.rule.api.fill.FillNplanAstNum;
import nc.vo.pu.m23.rule.api.fill.FillValuesByOrder;
import nc.vo.pu.m23.rule.api.process.NumAndPriceProcess;
import nc.vo.pu.pub.enumeration.EnumOperate;
import nc.vo.pu.pub.rule.NewestOrgVersionFillRule;
import nc.vo.pu.pub.rule.TrantypeNotNullCheckRule;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.check.billvalidate.BillVOsCheckRule;
import nc.vo.scmpub.rule.StockOrgEnableCheckRule;

/**
 * 
 * @description
 *		到货单API持久化保存BP
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *		到货单API持久化保存BP
 * @since 6.5
 * @version 2015-11-2 上午10:36:19
 * @author wandl
 */
public class ArriveInsertAPIBP {
	
	private ArrivalUIToBSEnv env;
	
	public ArriveInsertAPIBP(){
		this.env = null;
	}

  /**
   * 方法功能描述：完成新增保存到货单
   * <p>
   * <b>参数说明</b>
   * 
   * @param aggVO数组 <p>
   * @since 6.0
   * @author hanbin
   * @throws BusinessException 
   * @time 2010-1-13 上午10:18:08
   */
  public ArriveVO[] insertArrive(ArriveVO[] aggVO) throws BusinessException {
    CompareAroundProcesser<ArriveVO> processer =
        new CompareAroundProcesser<ArriveVO>(ArriveBPPlugInPoint.ArriveInsertAPIBP);
    
    ArriveVO[] vos = this.doBeforeInsert(aggVO);
    // 添加BP规则
    this.addBeforeRule(processer);
    this.addAfterRule(processer);
    processer.before(vos, null);
    BillInsert<ArriveVO> bo = new BillInsert<ArriveVO>();
    ArriveVO[] retvos = bo.insert(vos);
    processer.after(vos, null);
    return retvos;
  }

  private ArriveVO[] doBeforeInsert(ArriveVO[] aggVO) throws BusinessException{
  	
  	//API导入数据最小集合非空校验
  	BillVOsCheckRule check = new BillVOsCheckRule(new ArriveVOValidator());
  	check.check(aggVO);
  	
  	/*
  	 * 到货单导入数据值填充规则
  	 * 通过VO交换填充采购订单的单据信息到到货单
  	 */
    FillValuesByOrder fillvalue = new FillValuesByOrder();
    ArriveVO[] destVO = fillvalue.process(aggVO);
    /*
     * 到货单单据导入数据最小集合规则值VO交换之后填充规则
     */
		PUArriveVOFillRuleBuilder builder = new PUArriveVOFillRuleBuilder();
		builder.fillValue(destVO);
		//到货单来源于采购订单（委外订单）的数据非空校验
		IRule<ArriveVO> checkNull = new ArriveVONullCheckRule();
		checkNull.process(destVO);	
		return destVO;
  }
  
  private void addAfterRule(CompareAroundProcesser<ArriveVO> processer) {
    // 检查单据号的唯一性
    processer.addAfterRule(new ChkBillCodeUniqueRule());
    // 新增保存后批次号处理
    processer.addAfterRule(new InsertBatchCodeAfterRule());
    // 回写来源到货单的累计退货数量。
    processer.addAfterRule(new WriteBackFor23Rule(EnumOperate.ADD));
    /*
     * 回写采购订单的累计到货数量、途耗数量
     * API不做柔性控制，回写物料容差控制默认处理为严格控制
     */
    processer.addAfterRule(new Write21WhenInsertRule(this.env));
    /*
     * 回写委外订单的累计到货数量、途耗数量
     * API不做柔性控制，回写物料容差控制默认处理为严格控制
     */
    processer.addAfterRule(new Write61WhenInsertRule(this.env));
    // 退货单到货待检量检查
    processer.addAfterRule(new ChkBackArWaitNumRule());
    // 可用量变更后操作
    processer.addAfterRule(new ArriveATPUpdateRule(false));
    // 触发新增后事件
    processer.addAfterRule(new InsertAfterEventRule());
    // 发消息 到货通知请购员
    processer.addAfterRule(new InsertSendMsgRule(
        ScmBuziMsgResEnum.ARRIVAL_InformApplicant.code()));
  }

  private void addBeforeRule(CompareAroundProcesser<ArriveVO> processer) {
  	// 在基于到货单退货时，检查报检、检验报告
    //processer.addBeforeRule(new ChkCheckNumOnBackRule(this.env));
    // 检查交易类型是否为空
    processer.addBeforeFinalRule(new TrantypeNotNullCheckRule<ArriveVO>());
    // 计算主组织最新版
    processer.addBeforeFinalRule(new NewestOrgVersionFillRule<ArriveVO>(
        ArriveHeaderVO.PK_ARRIVEORDER));
    
    /*
     * 到货单导入根据数量做联动计算,vo交换的时候已经做过一次了，这里再做一次是防止用户输入的数量替换掉vo对照的数量
     */
    processer.addBeforeRule(new NumAndPriceProcess());
    //计算表头合计行
    processer.addBeforeRule(new HeadNumAndMnySumFillRule());
    /*
     * 联动计算后，将数量设置到应到数量上，在退货场景下，主数量取反，联动数量，应到应与数量保持一致。
     * 如果单据日期为空，填充当前业务日期
     */
    processer.addBeforeRule(new FillNplanAstNum());
    // 填补数据
    processer.addBeforeRule(new InsertFillDataRule());
    // 检查物料与库存组织是否匹配
    processer.addBeforeRule(new ChkMrlInStockOrg());
    // 保存前检查VO
    processer.addBeforeRule(new ValiVOBeforSaveRule());
    // 主组织停用检查
    processer.addBeforeRule(new StockOrgEnableCheckRule<ArriveVO>());
    // 处理本币金额
    processer.addBeforeRule(new DealLocalMnyRule());
    // 处理免检物料行
    processer.addBeforeRule(new DealFreeChkItemRule());
    // 新增保存前批次号处理
    processer.addBeforeRule(new InsertAndDelBatchCodeBeforeRule());
    // 单据号处理
    processer.addBeforeRule(new InsertBillCodeRule());
    // 基于到货单退货的相关检查。
    processer.addBeforeRule(new ChkBackFor23Rule());
    // 自由辅助属性校验
    processer.addBeforeRule(new UserDefSaveRule<ArriveVO>(new Class[] {
      ArriveHeaderVO.class, ArriveItemVO.class
    }));
    // 可用量变更前操作
    processer.addBeforeRule(new ArriveATPUpdateRule(true));
    // 触发新增前事件
    processer.addBeforeRule(new InsertBeforeEventRule());
  }
}
