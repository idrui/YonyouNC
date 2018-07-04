package nc.vo.pu.pub.constant;

/**
 * 采购组的临时表枚举<br>
 * 为统一管理本组的临时表，请将使用到的临时到到片注册
 * 
 * @since 6.0
 * @version 2011-4-20 上午10:43:07
 * @author zhaoyha
 */
public enum PUTempTable {
  /* ================================ 物资需求申请单 ================================ */
  /** 维修计划关闭时时删除下游请购单，用于查询请购单时拼csourceid 的in sql */
  tmp_po_422X_01,
  /** 维修计划关闭时时删除下游请购单，用于查询请购单时拼csourcebid 的in sql */
  tmp_po_422X_02,
  /** 物资需求申请单回写累计消减主数量 */
  tmp_po_422X_03,
  /** 根据表体来源bid查询物资需求申请单临时表 */
  tmp_po_422X_04,
  /** makeGetSqlByHeadId*/
  tmp_po_422x_05,
  /** makeGetSqlByItemIds*/
  tmp_po_422x_06,
  /* ================================ 请购单 ================================ */
  /** 关闭请购单行，修改采购计划预占数后规则时，请购单表体ID临时表 */
  tmp_po_20_01,

  /** 生产计划订单转请购数量尾差倒挤 **/
  tmp_po_20_02,

  /** 总括订单回写请购单已生成总括订单字段 **/
  tmp_po_20_03,

  /** DelOldVersionRule - deleteOld **/
  tmp_po_20_04,

  /** QueryFor4b36BP - makeGetSqlByItemIds **/
  tmp_po_20_05,

  /** ReWrite20ForECBP - upDel **/
  tmp_po_20_06,

  /** QueryPrayAction - getSqlB **/
  tmp_po_20_07,

  /** QueryPrayBillImpl - queryViewByItemPK **/
  tmp_po_20_08,

  /** Delete20For30Action - getPraybillKeysSql **/
  tmp_po_20_09,

  /** QueryFor4b36BP - makeGetSqlByHeadId **/
  tmp_po_20_10,

  /** PrayNoOrderAlert预警类中拼装组织in sql的临时表 */
  tmp_po_20_11,

  /** PrayAudittedNotCreateOrderAlart预警类中拼装组织in sql的临时表 */
  tmp_po_20_12,

  /** PrayAudittedNotCreateOrderAlart预警类中拼装组织in sql的临时表2 */
  tmp_po_20_13,

  /** 总括订单回写请购单已生成总括订单字段2 **/
  tmp_po_20_14,
  
  /** 离散制造ECA查询请购单信息时，请购单接口实现类中新建的参数临时表 **/
  tmp_po_20_15,
  
  /** Delete20For4A08Action - queryViewQuerySql 拼来源id的 in sql的临时表 **/
  tmp_po_20_16,
  
  /** Delete20For4A08Action - queryViewQuerySql 拼来源bid的 in sql的临时表 **/
  tmp_po_20_17,

  /* ================================ 采购订单 ================================ */
  /** 采购订单删除是删除修订历史临时表 **/
  tmp_po_21_01,

  /** 采购计划控制检查，获取请购单视图VO时，用到的临时表 */
  tmp_po_21_02,

  /** 如果有修订历史，删除修订历史时，使用的临时表 */
  tmp_po_21_03,

  /** OrderQueryFor4CBP - queryOrderVOCoopFromSO */
  tmp_po_21_04,

  /** OrderQueryFor4CBP - queryOrderVOCoopToSO */
  tmp_po_21_05,

  /** 采购询价-订单无效数据重算时，使用的临时表 */
  tmp_po_21_06,

  /** 询最新价时使用的临时表 */
  tmp_po_21_07,

  /** 采购计划检查，获取请购单视图VO时使用的临时表 */
  tmp_po_21_08,

  /** OrderWBStoreArrvTolerRule - getCodeOverTolerWhenNoRef */
  tmp_po_21_09,

  /** OrderWBStoreArrvTolerRule - getCodeOverTolerWhenRef */
  tmp_po_21_10,

  /** 订单查询操作，订单ID的临时表 */
  tmp_po_21_11,

  /** 订单修订数量时,删除途状态表中数据时，使用的临时表 */
  tmp_po_21_12,

  /** 订单修订数量时,更新途状态表中数据时，使用的临时表 */
  tmp_po_21_13,

  /** 修订预算控制规则时，获取请购单视图VO时使用的临时表 */
  tmp_po_21_14,

  /** 修订订单中，对数量进行检查时的临时表 */
  tmp_po_21_15,

  /** 查询采购订单在途状态VO时使用的临时表 */
  tmp_po_21_16,

  /** 根据订单表体id查询订单付款计划id时，使用的临时表 */
  tmp_po_21_17,

  /** 根据合同主表PK，查询关联它的订单表体行PK时，使用的临时表 */
  tmp_po_21_18,

  /** 是否存在关联某一合同的订单时，使用的临时表 */
  tmp_po_21_19,

  /** 查询订单状态为审核通过的、供应链维护完成且发布的到货计划时，使用的临时表 */
  tmp_po_21_20,

  /** 为供应商查询到货计划时，使用的临时表 */
  tmp_po_21_21,

  /** 电子商务查询服务实现类中，订单ID临时表 */
  tmp_po_21_22,

  /** 电子商务查询服务实现类中，物料ID临时表 */
  tmp_po_21_23,

  /** 电子商务查询服务实现类中，查询到货计划时，订单ID临时表 */
  tmp_po_21_24,

  /** 为存货核算提供的查询接口的子查询中的临时表 */
  tmp_po_21_25,

  /** 采购订单给库存报表提供的服务时使用的临时表 */
  tmp_po_21_26,

  /** 取得供应商在时间范围内的分配配额数量时，使用的临时表 */
  tmp_po_21_27,

  /** 付款计划查询 */
  tmp_po_21_28,

  /** PayPlanNotAllDelRule - process */
  tmp_po_21_29,

  /** TranTypeChkRule - getHaveReceivePlanSet */
  tmp_po_21_30,

  /** OrderQueryExecForMMBP - queryExecForM52 */
  tmp_po_21_31,

  /** QueryMapListBidAsKey - queryMapListBidAsKey */
  tmp_po_21_32,

  /** OnwayStatusWriteBackRule - queryOnwayStatusVO */
  tmp_po_21_33,

  /** OrderWriteBackForScmf - udpateItemCenPuRuleB */
  tmp_po_21_34,

  /** ApDataAction - getInvoiceBIDsFromOrder */
  tmp_po_21_35,

  /** ApDataAction - getOrderPayMny */
  tmp_po_21_36,

  /** ConfirmUpdateBp - getOnwayVOs */
  tmp_po_21_37,

  /** OnwayDAO - getAccumNum */
  tmp_po_21_38,

  /** OnwayDAO - getNextStatusOnwayVO */
  tmp_po_21_39,

  /** OnwayUpdateBpMy - getOnwayVOs */
  tmp_po_21_40,

  /** OutputDeleteBp - getStatusOnwayVOs */
  tmp_po_21_41,

  /** OutputUpdateBp - getExistVO */
  tmp_po_21_42,

  /** OrderEditRecordQueryImpl - queryOrderPrice */
  tmp_po_21_43,

  /** OrderQueryFor45Impl - createQueryScheme */
  tmp_po_21_44,

  /** BillIdQueryFor36D1Impl - getBillIdsWhenCnt */
  tmp_po_21_45,

  /** BillIdQueryFor36D1Impl - getBillIdsWhenOrder */
  tmp_po_21_46,

  /** PoRPRowNoModel - getWherePart */
  tmp_po_21_47,

  /** OrderArrAlert预警类中拼装组织in sql的临时表 */
  tmp_po_21_48,

  /** OrderArrAlert预警类中拼装组织in sql的临时表2 */
  tmp_po_21_49,

  /** NoTradeVendAlert预警类中拼装组织in sql的临时表 */
  tmp_po_21_50,

  /** NoTradeVendAlert预警类中拼装组织in sql的临时表2 */
  tmp_po_21_51,

  /** PoRPNotArrvAlert预警类中拼装组织in sql的临时表 */
  tmp_po_21_52,

  /** PayPlanMatureAlert预警类中拼装组织in sql的临时表 */
  tmp_po_21_53,

  /** PayPlanMatureAlert预警类中拼装组织in sql的临时表2 */
  tmp_po_21_54,

  /** PoCloseBillAlert预警类中拼装组织in sql的临时表 */
  tmp_po_21_55,

  /** 寻最新价时，币种临时表 */
  tmp_po_21_56,

  /** 寻最新价时，财务组织临时表 */
  tmp_po_21_57,

  /** 寻最新价时，采购组织临时表 */
  tmp_po_21_58,

  /** 寻最新价时，供应商临时表 */
  tmp_po_21_59,
  /** 采购订单关闭预警表体主键临时表 */
  tmp_po_21_60,

  /** 采购订单查询折扣档案规则采购组织 */
  tmp_po_21_61,
  /** 采购订单查询折扣档案规则供应商 */
  tmp_po_21_62,
  /** 采购订单查询折扣档案规则折扣规则 */
  tmp_po_21_63,
  /** 给电子采购提供的根据pk查询表体时用到的临时表 */
  tmp_po_21_64,

  tmp_po_21_65,
  /** 离散制造ECA查询采购订单时，采购订单接口实现类中新建的参数临时表 */
  tmp_po_21_66,

  /* ================================ 到货单 ================================ */
  /** 到货单获取可入库数量临时表 */
  tmp_po_23_01,

  /** 根据检验结果入库的容差检查，子查询中的临时表 */
  tmp_po_23_02,

  /** 到货单回写紧急放行单的是否反馈质检结果的标志，之前质检结果反馈时用到临时表 */
  tmp_po_23_03,

  /** 到货单查询，处理可入库数量时用到的临时表 */
  tmp_po_23_04,

  /** 检查是否重复报检时，查询对应的检验结果明细用到的临时表 */
  tmp_po_23_05,

  /** 根据表头主键查询到货单表体临时表 */
  tmp_po_23_06,

  /** 根据订单主键查询该订单的退货单明细信息临时表 */
  tmp_po_23_07,

  /** 为电子商务提供的查询接口中，物料查询临时表 */
  tmp_po_23_08,

  /** 根据订单主键查询该订单的到货单明细信息临时表 */
  tmp_po_23_09,

  /** 判断订单是否有到货临时表 */
  tmp_po_23_10,

  /** 判断订单到货计划是否有到货临时表 */
  tmp_po_23_11,

  /** 根据采购订单表体id查询到货单临时表 */
  tmp_po_23_12,

  /** 可入库主数量 */
  tmp_po_23_13,

  /** 根据订单表体ID查询到货、退货汇总数量时用到的临时表 */
  tmp_po_23_15,

  /** WriteC005WhenResultFeedbackRule - queryOtherBbVO */
  tmp_po_23_16,

  /** Writeback23ForC003BP - writebackWhenUnApprove */
  tmp_po_23_17,

  /** ArrivePubQueryImpl - queryAggVOIncludeBBVOByBids */
  tmp_po_23_18,

  /* ================================ 价格结算单 ================================ */
  /** 查询订单行是否已经被价格结算单引用时，订单ID的临时表 */
  tmp_po_24_01,

  /* ================================ 采购发票 ================================ */
  /** 根据付款协议，查询各种起算（生效）日期时，拼接采购发票表体ID IN SQL的临时表 */
  tmp_po_25_01,

  /** 根据付款协议，查询各种起算（生效）日期时，拼接采购入表体ID IN SQL的临时表 */
  tmp_po_25_02,

  /** 查询货物发票附带的费用发票临时表 */
  tmp_po_25_03,

  /** 查询货物发票附带的自由状态费用发票临时表 */
  tmp_po_25_04,

  /** 根据订单号找发票表体id，源头单据号临时表 */
  tmp_po_25_05,

  /** 采购发票为结算查询BP时，物料临时表 */
  tmp_po_25_06,

  /** 为电子商务提供发票查询中，表头ID临时表 */
  tmp_po_25_07,

  /** 为电子商务提供发票查询中，物料ID临时表 */
  tmp_po_25_08,

  /** 为电子商务提供发票查询中，订单表头ID临时表 */
  tmp_po_25_09,

  /** 跨国报表，发票查询中到货库存组织的临时表 */
  tmp_po_25_10,

  /** 根据发票行id查询订单号时，发票明细ID临时表 */
  tmp_po_25_11,

  /** 根据订单表体ID查询发票时，订单表体ID临时表 */
  tmp_po_25_12,

  /** 查询发票最高价物料临时表 */
  tmp_po_25_13,

  /** 根据订单表体ID查询开票辅数量、开票原币金额时用到的临时表 */
  tmp_po_25_14,

  /* ================================ 采购结算单 ================================ */
  /** 查询费用暂估明细是使用的临时表 */
  tmp_po_27_01,

  /** 查询对应的费用分摊明细使用的临时表 */
  tmp_po_27_02,

  /** 根据虚拟发票VO查询结算单VO时，使用的临时表 */
  tmp_po_27_03,

  /** 检查是否存在结算未传成本时，使用的临时表 */
  tmp_po_27_04,

  /** 根据入库单/VMI的ID查询货物结算的结算单的货物行数组，暂估或确认成本前的结算时使用的临时表 */
  tmp_po_27_05,

  /** 结算单删除/取消传存货时检查时，结算单bid临时表 */
  tmp_po_27_06,

  /** 检测检验单已经被其他费用结算分摊过时，使用的临时表 */
  tmp_po_27_07,

  /** 查询给定的入库单和费用发票信息,判断入库单进行过哪些费用项的暂估应付时，使用的临时表 */
  tmp_po_27_08,

  /** 根据物料查询采购入库时的临时表 */
  tmp_po_27_09,

  /** 查询费用结算的结算关系时使用的临时表 */
  tmp_po_27_10,

  /** 查询货物发票结算和红蓝发票对冲的结算关系时使用的临时表 */
  tmp_po_27_11,

  /** 查询最新结算价时，使用的临时表 */
  tmp_po_27_12,

  /** 根据订单表体主键查询累计结算信息时，使用的临时表 */
  tmp_po_27_13,

  /** 根据订单主键查询累计结算数量和金额信息时，使用的临时表 */
  tmp_po_27_14,

  /* ================================ 库存财务 ================================ */
  /** 根据入库行id查询改行对应的物料时，使用的临时表 */
  tmp_po_4201_1,

  /* ================================ 消耗汇总财务 ================================ */
  /** 根据物料过滤消耗汇总的财务信息时，使用的临时表 */
  tmp_po_4202_1,

  /* ================================ 委外入财务 ================================ */
  /** 检查是否进行过费用结算时，使用的临时表 */
  tmp_po_4203_1,

  /** 根据采购订单ID查询待结算的采购入时，使用的临时表 */
  tmp_po_4203_2,

  /** 根据委外入BID查询待结算的委外入时，使用的临时表 */
  tmp_po_4203_3,

  /** 根据委外订单ID查询待结算的委外入时，使用的临时表 */
  tmp_po_4203_4,
  /** 根据入库行id查询改行对应的物料时，使用的临时表 */
  tmp_po_4203_5,

  /** 根据入库单行订单交易类型id查询交易类型code时，使用临时表 */
  tmp_po_4203_6,

  /* ================================ 期初暂估单 ================================ */
  /** 根据库存单据行ID查询待自动结算的VO时，使用的临时表 */
  tmp_po_4t_01,

  /** 根据订单行ID查询待自动结算的VO时，使用的临时表 */
  tmp_po_4t_02,

  /* ================================ 暂估 ================================ */
  /** 物理删除费用暂估记录的临时表 */
  tmp_po_est_01,

  /** 暂估VO查询表体时，使用的临时表 */
  tmp_po_est_02,

  /** 取消费用暂估的I9单据时，使用的临时表 */
  tmp_po_est_03,

  /** 自动暂估，取消暂估单据时，查询是否有暂估费用，使用的临时表 */
  tmp_po_est_04,

  /* ================================ 采购岗 ================================ */
  /** 采购岗计划岗物料、采购组织临时表 */
  tmp_po_pos_01,

  /** 采购岗计划岗库存组织临时表 */
  tmp_po_pos_02,

  /** 采购岗计划岗物料基本分类临时表 */
  tmp_po_pos_03,

  /** CheckUniqueRule - checkMaterialB */
  tmp_po_pos_04,

  /** QueryPlanMaterialAction - queryPlanByMaterial */
  tmp_po_pos_05,

  /** QueryPositionImpl - getPlanMaterialSqlByPkPlan */
  tmp_po_pos_06,

  /** CheckUniqueRule - getOtherUseClass */
  tmp_po_pos_07,

  /** QueryPlanMaterialAction - queryPlanByClass */
  tmp_po_pos_08,
  
  /** CheckUniqueRule - checkPk_classInOtherMaterial */
  tmp_po_pos_09,

  /* ================================ 岗位设置 ================================ */
  /** 根据物料类型获取岗位设置临时表 */
  tmp_po_pr_01,

  /** 获取岗位SQL临时表 */
  tmp_po_pr_02,

  /* ================================ 公共组件 ================================ */
  /** 委外入库单47提供给采购的服务适配中，判断上游单据是否已经生成过委外入的临时表 */
  tmp_po_pub_01,

  /** 返回超容差控制的物料编码时用到的临时表 */
  tmp_po_pub_02,

  /** 采购组尾差处理框架查询兄弟VO时来源BID临时表 **/
  tmp_po_pub_03,

  /** 采购组尾差处理框架查询兄弟VO时来源HID临时表 **/
  tmp_po_pub_04,

  /* ================================ 报表 ================================ */
  /** 订单执行明细查询BP中用到的临时表1 */
  tmp_po_rpt_01,

  /** 订单执行明细查询BP中用到的临时表2 */
  tmp_po_rpt_02,

  /** OrderReceivePlanRptBP - rpList */
  tmp_po_rpt_03,

  /** PayExecRptBP - getPayMny */
  tmp_po_rpt_04,

  /** PayExecRptBP - getQuerySql */
  tmp_po_rpt_05,
  /** PayExecRptBP - setCcurrencyid */
  tmp_po_rpt_06,

  /** 请购单明细查询中，从采购订单查询请购单表体ID临时表 */
  tmp_po_rpt_07,

  /** 请购单明细查询中，从销售订单查询请购单表体ID临时表 */
  tmp_po_rpt_08,

  /** 请购单从采购订单取数的临时表 */
  tmp_po_rpt_09,

  /** 请购单从销售订单取数的临时表 */
  tmp_po_rpt_10,

  /** 物料执行汇总，取请购单执行数临时表 */
  tmp_po_rpt_11,

  /** PurchaseinAsPrayBillBatchLinkQuery - createBidSql */
  tmp_po_rpt_12,
  /** PurchaseinBatchLinkQuery - createBidSql */
  tmp_po_rpt_13;
}
