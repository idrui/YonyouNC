/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-14 下午09:07:37
 */
package nc.vo.pu.m21.entity;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.pubapp.pattern.model.tool.BillComposite;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单的视图VO
 * <li>保存的属性和订单VO一样,
 * <li>方便使用子表ID进行查询处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-1-14 下午09:07:37
 */
public class OrderViewVO extends AbstractDataView {

  private static final long serialVersionUID = 3619146191228968679L;

  /** 由视图VO得到订单VO **/
  public static OrderVO[] getOrderVO(AbstractDataView[] views) {
    if (ArrayUtils.isEmpty(views)) {
      return null;
    }
    List<OrderHeaderVO> headers = new ArrayList<OrderHeaderVO>();
    List<OrderItemVO> items = new ArrayList<OrderItemVO>();
    for (AbstractDataView view : views) {
      headers.add((OrderHeaderVO) view.getVO(OrderHeaderVO.class));
      items.add((OrderItemVO) view.getVO(OrderItemVO.class));
    }
    // return (OrderVO[]) AggVOUtil.createBillVO(headers
    // .toArray(new OrderHeaderVO[headers.size()]), items
    // .toArray(new OrderItemVO[items.size()]), OrderVO.class);

    BillComposite<OrderVO> bc = new BillComposite<OrderVO>(OrderVO.class);
    OrderVO tempVO = new OrderVO();
    bc.append(tempVO.getMetaData().getParent(),
        headers.toArray(new OrderHeaderVO[headers.size()]));
    bc.append(tempVO.getMetaData().getVOMeta(OrderItemVO.class),
        items.toArray(new OrderItemVO[items.size()]));
    return bc.composite();
  }

  /** 到货关闭 **/
  public UFBoolean getBarriveclose() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BARRIVECLOSE);
  }

  /** 借入转采购 getter 方法 */
  public UFBoolean getBborrowpur() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BBORROWPUR);
  }

  /** 是否直运采购 getter 方法 */
  public UFBoolean getBdirect() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BDIRECT);
  }

  /** 最终关闭 **/
  public UFBoolean getBfinalclose() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BFINALCLOSE);
  }

  /** 开票关闭 **/
  public UFBoolean getBinvoiceclose() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BINVOICECLOSE);
  }

  /** 付款关闭 **/
  public UFBoolean getBpayclose() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BPAYCLOSE);
  }

  /** 退货(库)基于原订单补货 **/
  public UFBoolean getBrefwhenreturn() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BREFWHENRETURN);
  }

  /** 退货 getter 方法 */
  public UFBoolean getBreturn() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BRETURN);
  }

  /** 入库关闭 **/
  public UFBoolean getBstockclose() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BSTOCKCLOSE);
  }

  /** 是否运输关闭 getter 方法 */
  public UFBoolean getBtransclosed() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BTRANSCLOSED);
  }

  /** 三角贸易 getter 方法 */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BTRIATRADEFLAG);
  }

  /** 合同头ID **/
  public String getCcontractid() {
    return (String) this.getAttributeValue(OrderItemVO.CCONTRACTID);
  }

  /** 合同行ID **/
  public String getCcontractrowid() {
    return (String) this.getAttributeValue(OrderItemVO.CCONTRACTROWID);
  }

  /** 本位币(结算财务组织) **/
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(OrderItemVO.CCURRENCYID);
  }

  /** 目的地地区 getter 方法 */
  public String getCdestiareaid() {
    return (String) this.getAttributeValue(OrderItemVO.CDESTIAREAID);
  }

  /** 目的地国 getter 方法 */
  public String getCdesticountryid() {
    return (String) this.getAttributeValue(OrderItemVO.CDESTICOUNTRYID);
  }

  /** 特征码 **/
  public String getCffileid() {
    return (String) this.getAttributeValue(OrderItemVO.CFFILEID);
  }

  /** 源头单据明细 getter 方法 */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(OrderItemVO.CFIRSTBID);
  }

  /** 源头单据类型 getter 方法 */
  public String getCfirsttypecode() {
    return (String) this.getAttributeValue(OrderItemVO.CFIRSTTYPECODE);
  }

  /** 原产地区 getter 方法 */
  public String getCorigareaid() {
    return (String) this.getAttributeValue(OrderItemVO.CORIGAREAID);
  }

  /** 原产国 getter 方法 */
  public String getCorigcountryid() {
    return (String) this.getAttributeValue(OrderItemVO.CORIGCOUNTRYID);
  }

  /** 原币币种 **/
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(OrderHeaderVO.CORIGCURRENCYID);
  }

  /** 生产厂商 getter 方法 */
  public String getCproductorid() {
    return (String) this.getAttributeValue(OrderItemVO.CPRODUCTORID);
  }

  /** 项目 getter 方法 */
  public String getCprojectid() {
    return (String) this.getAttributeValue(OrderItemVO.CPROJECTID);
  }

  /** 优质优价方案 getter 方法 */
  public String getCqpbaseschemeid() {
    return (String) this.getAttributeValue(OrderItemVO.CQPBASESCHEMEID);
  }

  /** 收货国家/地区 getter 方法 */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(OrderItemVO.CRECECOUNTRYID);
  }

  /** 行号 **/
  public String getCrowno() {
    return (String) this.getAttributeValue(OrderItemVO.CROWNO);
  }

  /** 发货国/地区 getter 方法 */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(OrderItemVO.CSENDCOUNTRYID);
  }

  /** 来源单据行ID **/
  public String getCsourcebid() {
    return (String) this.getAttributeValue(OrderItemVO.CSOURCEBID);
  }

  /** 来源单据 getter 方法 */
  public String getCsourceid() {
    return (String) this.getAttributeValue(OrderItemVO.CSOURCEID);
  }

  /** 来源单据类型 getter 方法 */
  public String getCsourcetypecode() {
    return (String) this.getAttributeValue(OrderItemVO.CSOURCETYPECODE);
  }

  /** 税码 getter 方法 */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(OrderItemVO.CTAXCODEID);
  }

  /** 报税国/地区 getter 方法 */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(OrderItemVO.CTAXCOUNTRYID);
  }

  /** 贸易术语 getter 方法 */
  public String getCtradewordid() {
    return (String) this.getAttributeValue(OrderHeaderVO.CTRADEWORDID);
  }

  /** 订单类型 getter 方法 */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(OrderHeaderVO.CTRANTYPEID);
  }

  /** 主单位 **/
  public String getCunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CUNITID);
  }

  /** 订单日期 **/
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(OrderItemVO.DBILLDATE);
  }

  /** 计划到货日期 **/
  public UFDate getDplanarrvdate() {
    return (UFDate) this.getAttributeValue(OrderItemVO.DPLANARRVDATE);
  }

  /** 购销类型 getter 方法 */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(OrderItemVO.FBUYSELLFLAG);
  }

  /** 单据状态 getter 方法 */
  public Integer getForderstatus() {
    return (Integer) this.getAttributeValue(OrderHeaderVO.FORDERSTATUS);
  }

  /** 扣税类别 **/
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(OrderItemVO.FTAXTYPEFLAG);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.pattern.model.entity.view.AbstractDataView#getMetaData()
   */
  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getBillViewMeta(OrderVO.class);
  }

  /** 累计已核销本币开票金额 **/
  public UFDouble getNacccancelinvmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCCANCELINVMNY);
  }

  /** 累计到货数量 **/
  public UFDouble getNaccumarrvnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMARRVNUM);
  }

  /** 累计运输主数量 getter 方法 */
  public UFDouble getNaccumdevnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMDEVNUM);
  }

  /** 累计本币开票金额 **/
  public UFDouble getNaccuminvoicemny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMINVOICEMNY);
  }

  /** 累计发票数量 **/
  public UFDouble getNaccuminvoicenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMINVOICENUM);
  }

  /** 累计拣货主数量 **/
  public UFDouble getNaccumpickupnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMPICKUPNUM);
  }

  /** 累计入库数量 **/
  public UFDouble getNaccumstorenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMSTORENUM);
  }

  /** 累计途耗数量 **/
  public UFDouble getNaccumwastnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMWASTNUM);
  }

  /** 累计退货数量 **/
  public UFDouble getNbackarrvnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NBACKARRVNUM);
  }

  /** 累计退库数量 **/
  public UFDouble getNbackstorenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NBACKSTORENUM);
  }

  /** 计成本金额 getter 方法 */
  public UFDouble getNcalcostmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCALCOSTMNY);
  }

  /** 计税金额 getter 方法 */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCALTAXMNY);
  }

  /** 可到货数量 getter 方法 */
  public UFDouble getNcanarrivenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCANARRIVENUM);
  }

  /** 可入库数量 getter 方法 */
  public UFDouble getNcaninnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCANINNUM);
  }

  /** 可开票数量 getter 方法 */
  public UFDouble getNcaninvoicenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCANINVOICENUM);
  }

  /** 费用累计开票金额 **/
  public UFDouble getNfeemny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NFEEMNY);
  }

  /** 主本币无税净价 **/
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNETPRICE);
  }

  /** 不可抵扣税额 getter 方法 */
  public UFDouble getNnosubtax() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNOSUBTAX);
  }

  /** 不可抵扣税率 getter 方法 */
  public UFDouble getNnosubtaxrate() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNOSUBTAXRATE);
  }

  /** 主数量 **/
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNUM);
  }

  /** 原币预付款限额 **/
  public UFDouble getNorgprepaylimit() {
    return (UFDouble) this.getAttributeValue(OrderHeaderVO.NORGPREPAYLIMIT);
  }

  /** 主无税单价 getter 方法 */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGPRICE);
  }

  /** 原币价税合计 **/
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGTAXMNY);
  }

  /** 主含税单价 getter 方法 */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGTAXPRICE);
  }

  /** OrderItemVO.nprice 主本币无税单价 */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NPRICE);
  }

  /**
   * 方法功能描述：累计预留数量
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author duy
   * @time 2010-6-17 下午07:34:27
   */
  public UFDouble getNsuprsnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NSUPRSNUM);
  }

  /** 本币价税合计 getter 方法 */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NTAXMNY);
  }

  /** OrderItemVO.NTAXPRICE 主本币含税单价 **/
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NTAXPRICE);
  }

  /** 税率 **/
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NTAXRATE);
  }

  /** 应付财务组织 **/
  public String getPk_apfinanceorg() {
    return (String) this.getAttributeValue(OrderItemVO.PK_APFINANCEORG);
  }

  /** 收货库存组织 **/
  public String getPk_arrvstoorg() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ARRVSTOORG);
  }

  /** 结算方式 **/
  public String getPk_balatype() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_BALATYPE);
  }

  /** 物料(VID) **/
  public String getPk_material() {
    return (String) this.getAttributeValue(OrderItemVO.PK_MATERIAL);
  }

  /** 采购订单表头_主键 **/
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORDER);
  }

  /** 采购订单行ID **/
  public String getPk_order_b() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORDER_B);
  }

  /** 采购组织 **/
  public String getPk_org() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORG);
  }

  /** 结算财务组织 **/
  public String getPk_psfinanceorg() {
    return (String) this.getAttributeValue(OrderItemVO.PK_PSFINANCEORG);
  }

  /** 收货仓库ID **/
  public String getPk_recvstordoc() {
    return (String) this.getAttributeValue(OrderItemVO.PK_RECVSTORDOC);
  }

  /** 供应商基本信息ID **/
  public String getPk_supplier() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_SUPPLIER);
  }

  /** 订单编号 getter 方法 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(OrderHeaderVO.VBILLCODE);
  }

  /** 合同号 **/
  public String getVcontractcode() {
    return (String) this.getAttributeValue(OrderItemVO.VCONTRACTCODE);
  }

  /** 自由辅助属性1 **/
  public String getVfree1() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE1);
  }

  /** 自由辅助属性10 **/
  public String getVfree10() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE10);
  }

  /** 自由辅助属性2 **/
  public String getVfree2() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE2);
  }

  /** 自由辅助属性3 **/
  public String getVfree3() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE3);
  }

  /** 自由辅助属性4 **/
  public String getVfree4() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE4);
  }

  /** 自由辅助属性5 **/
  public String getVfree5() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE5);
  }

  /** 自由辅助属性6 **/
  public String getVfree6() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE6);
  }

  /** 自由辅助属性7 **/
  public String getVfree7() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE7);
  }

  /** 自由辅助属性8 **/
  public String getVfree8() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE8);
  }

  /** 自由辅助属性9 **/
  public String getVfree9() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE9);
  }

  /** 订单编号 **/
  public String getVordercode() {
    return (String) this.getAttributeValue(OrderHeaderVO.VBILLCODE);
  }

  /** 订单类型（交易类型） **/
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(OrderHeaderVO.VTRANTYPECODE);
  }

  /** 到货关闭 **/
  public void setBarriveclose(UFBoolean barriveclose) {
    this.setAttributeValue(OrderItemVO.BARRIVECLOSE, barriveclose);
  }

  /** 借入转采购 setter 方法 */
  public void setBborrowpur(UFBoolean bborrowpur) {
    this.setAttributeValue(OrderItemVO.BBORROWPUR, bborrowpur);
  }

  /** 是否直运采购 setter 方法 */
  public void setBdirect(UFBoolean bdirect) {
    this.setAttributeValue(OrderHeaderVO.BDIRECT, bdirect);
  }

  /** 最终关闭 **/
  public void setBfinalclose(UFBoolean bfinalclose) {
    this.setAttributeValue(OrderHeaderVO.BFINALCLOSE, bfinalclose);
  }

  /** 开票关闭 **/
  public void setBinvoiceclose(UFBoolean binvoiceclose) {
    this.setAttributeValue(OrderItemVO.BINVOICECLOSE, binvoiceclose);
  }

  /** 付款关闭 **/
  public void setBpayclose(UFBoolean bpayclose) {
    this.setAttributeValue(OrderItemVO.BPAYCLOSE, bpayclose);
  }

  /** 退货(库)基于原订单补货 **/
  public void setBrefwhenreturn(UFBoolean brefwhenreturn) {
    this.setAttributeValue(OrderHeaderVO.BREFWHENRETURN, brefwhenreturn);
  }

  /** 退货 setter 方法 */
  public void setBreturn(UFBoolean breturn) {
    this.setAttributeValue(OrderHeaderVO.BRETURN, breturn);
  }

  /** 入库关闭 **/
  public void setBstockclose(UFBoolean bstockclose) {
    this.setAttributeValue(OrderItemVO.BSTOCKCLOSE, bstockclose);
  }

  /** 是否运输关闭 setter 方法 */
  public void setBtransclosed(UFBoolean btransclosed) {
    this.setAttributeValue(OrderItemVO.BTRANSCLOSED, btransclosed);
  }

  /** 三角贸易 setter 方法 */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(OrderItemVO.BTRIATRADEFLAG, btriatradeflag);
  }

  /** 合同头ID **/
  public void setCcontractid(String ccontractid) {
    this.setAttributeValue(OrderItemVO.CCONTRACTID, ccontractid);
  }

  /** 合同行ID **/
  public void setCcontractrowid(String ccontractrowid) {
    this.setAttributeValue(OrderItemVO.CCONTRACTROWID, ccontractrowid);
  }

  /** 本位币(结算财务组织) **/
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(OrderItemVO.CCURRENCYID, ccurrencyid);
  }

  /** 目的地地区 setter 方法 */
  public void setCdestiareaid(String cdestiareaid) {
    this.setAttributeValue(OrderItemVO.CDESTIAREAID, cdestiareaid);
  }

  /** 目的地国 setter 方法 */
  public void setCdesticountryid(String cdesticountryid) {
    this.setAttributeValue(OrderItemVO.CDESTICOUNTRYID, cdesticountryid);
  }

  /** 特征码 **/
  public void setCffileid(String cffileid) {
    this.setAttributeValue(OrderItemVO.CFFILEID, cffileid);
  }

  /** 源头单据 setter 方法 */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(OrderItemVO.CFIRSTBID, cfirstbid);
  }

  /** 源头单据类型 setter 方法 */
  public void setCfirsttypecode(String cfirsttypecode) {
    this.setAttributeValue(OrderItemVO.CFIRSTTYPECODE, cfirsttypecode);
  }

  /** 原产地区 setter 方法 */
  public void setCorigareaid(String corigareaid) {
    this.setAttributeValue(OrderItemVO.CORIGAREAID, corigareaid);
  }

  /** 原产国 setter 方法 */
  public void setCorigcountryid(String corigcountryid) {
    this.setAttributeValue(OrderItemVO.CORIGCOUNTRYID, corigcountryid);
  }

  /** 原币币种 **/
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(OrderHeaderVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /** 生产厂商 setter 方法 */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(OrderItemVO.CPRODUCTORID, cproductorid);
  }

  /** 项目 setter 方法 */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(OrderItemVO.CPROJECTID, cprojectid);
  }

  /** 优质优价方案 setter 方法 */
  public void setCqpbaseschemeid(String cqpbaseschemeid) {
    this.setAttributeValue(OrderItemVO.CQPBASESCHEMEID, cqpbaseschemeid);
  }

  /** 收货国家/地区 setter 方法 */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(OrderItemVO.CRECECOUNTRYID, crececountryid);
  }

  /** 行号 **/
  public void setCrowno(String crowno) {
    this.setAttributeValue(OrderItemVO.CROWNO, crowno);
  }

  /** 发货国/地区 setter 方法 */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(OrderItemVO.CSENDCOUNTRYID, csendcountryid);
  }

  /** 来源单据行ID **/
  public void setCsourcebid(String csourcebid) {
    this.setAttributeValue(OrderItemVO.CSOURCEBID, csourcebid);
  }

  /** 来源单据 setter 方法 */
  public void setCsourceid(String csourceid) {
    this.setAttributeValue(OrderItemVO.CSOURCEID, csourceid);
  }

  /** 来源单据类型 setter 方法 */
  public void setCsourcetypecode(String csourcetypecode) {
    this.setAttributeValue(OrderItemVO.CSOURCETYPECODE, csourcetypecode);
  }

  /** 税码 setter 方法 */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(OrderItemVO.CTAXCODEID, ctaxcodeid);
  }

  /** 报税国/地区 setter 方法 */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(OrderItemVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  /** 贸易术语 setter 方法 */
  public void setCtradewordid(String ctradewordid) {
    this.setAttributeValue(OrderHeaderVO.CTRADEWORDID, ctradewordid);
  }

  /** 主单位 **/
  public void setCunitid(String cunitid) {
    this.setAttributeValue(OrderItemVO.CUNITID, cunitid);
  }

  /** 订单日期 **/
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(OrderItemVO.DBILLDATE, dbilldate);
  }

  /** 计划到货日期 **/
  public void setDplanarrvdate(UFDate dplanarrvdate) {
    this.setAttributeValue(OrderItemVO.DPLANARRVDATE, dplanarrvdate);
  }

  /** 购销类型 setter 方法 */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(OrderItemVO.FBUYSELLFLAG, fbuysellflag);
  }

  /** 单据状态 setter 方法 */
  public void setForderstatus(Integer forderstatus) {
    this.setAttributeValue(OrderHeaderVO.FORDERSTATUS, forderstatus);
  }

  /** 扣税类别 **/
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(OrderItemVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /** 累计已核销本币开票金额 **/
  public void setNacccancelinvmny(UFDouble nacccancelinvmny) {
    this.setAttributeValue(OrderItemVO.NACCCANCELINVMNY, nacccancelinvmny);
  }

  /** 累计到货数量 **/
  public void setNaccumarrvnum(UFDouble naccumarrvnum) {
    this.setAttributeValue(OrderItemVO.NACCUMARRVNUM, naccumarrvnum);
  }

  /** 累计运输主数量 setter 方法 */
  public void setNaccumdevnum(UFDouble naccumdevnum) {
    this.setAttributeValue(OrderItemVO.NACCUMDEVNUM, naccumdevnum);
  }

  /** 累计本币开票金额 **/
  public void setNaccuminvoicemny(UFDouble naccuminvoicemny) {
    this.setAttributeValue(OrderItemVO.NACCUMINVOICEMNY, naccuminvoicemny);
  }

  /** 累计发票数量 **/
  public void setNaccuminvoicenum(UFDouble naccuminvoicenum) {
    this.setAttributeValue(OrderItemVO.NACCUMINVOICENUM, naccuminvoicenum);
  }

  /** 累计拣货主数量 **/
  public void setNaccumpickupnum(UFDouble naccumpickupnum) {
    this.setAttributeValue(OrderItemVO.NACCUMPICKUPNUM, naccumpickupnum);
  }

  /** 累计入库数量 **/
  public void setNaccumstorenum(UFDouble naccumstorenum) {
    this.setAttributeValue(OrderItemVO.NACCUMSTORENUM, naccumstorenum);
  }

  /** 累计途耗数量 **/
  public void setNaccumwastnum(UFDouble naccumwastnum) {
    this.setAttributeValue(OrderItemVO.NACCUMWASTNUM, naccumwastnum);
  }

  /** 累计退货数量 **/
  public void setNbackarrvnum(UFDouble nbackarrvnum) {
    this.setAttributeValue(OrderItemVO.NBACKARRVNUM, nbackarrvnum);
  }

  /** 累计退库数量 **/
  public void setNbackstorenum(UFDouble nbackstorenum) {
    this.setAttributeValue(OrderItemVO.NBACKSTORENUM, nbackstorenum);
  }

  /** 计成本金额 setter 方法 */
  public void setNcalcostmny(UFDouble ncalcostmny) {
    this.setAttributeValue(OrderItemVO.NCALCOSTMNY, ncalcostmny);
  }

  /** 计税金额 setter 方法 */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(OrderItemVO.NCALTAXMNY, ncaltaxmny);
  }

  /** 可到货数量 setter 方法 */
  public void setNcanarrivenum(UFDouble ncanarrivenum) {
    this.setAttributeValue(OrderItemVO.NCANARRIVENUM, ncanarrivenum);
  }

  /** 可入库数量 setter 方法 */
  public void setNcaninnum(UFDouble ncaninnum) {
    this.setAttributeValue(OrderItemVO.NCANINNUM, ncaninnum);
  }

  /** 可开票数量 setter 方法 */
  public void setNcaninvoicenum(UFDouble ncaninvoicenum) {
    this.setAttributeValue(OrderItemVO.NCANINVOICENUM, ncaninvoicenum);
  }

  /** 费用累计开票金额 **/
  public void setNfeemny(UFDouble nfeemny) {
    this.setAttributeValue(OrderItemVO.NFEEMNY, nfeemny);
  }

  /** 主本币无税净价 **/
  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(OrderItemVO.NNETPRICE, nnetprice);
  }

  /** 不可抵扣税额 setter 方法 */
  public void setNnosubtax(UFDouble nnosubtax) {
    this.setAttributeValue(OrderItemVO.NNOSUBTAX, nnosubtax);
  }

  /** 不可抵扣税率 setter 方法 */
  public void setNnosubtaxrate(UFDouble nnosubtaxrate) {
    this.setAttributeValue(OrderItemVO.NNOSUBTAXRATE, nnosubtaxrate);
  }

  /** 主数量 **/
  public void setNnum(UFDouble nmainnum) {
    this.setAttributeValue(OrderItemVO.NNUM, nmainnum);
  }

  /** 原币预付款限额 **/
  public void setNorgprepaylimit(UFDouble norgprepaylimit) {
    this.setAttributeValue(OrderHeaderVO.NORGPREPAYLIMIT, norgprepaylimit);
  }

  /** 主无税单价 setter 方法 */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(OrderItemVO.NORIGPRICE, norigprice);
  }

  /** 原币价税合计 **/
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(OrderItemVO.NORIGTAXMNY, norigtaxmny);
  }

  /** 主含税单价 setter 方法 */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(OrderItemVO.NORIGTAXPRICE, norigtaxprice);
  }

  /**
   * 方法功能描述：累计预留数量
   * <p>
   * <b>参数说明</b>
   * 
   * @param nsuprsnum
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-6-17 下午07:34:47
   */
  public void setNsuprsnum(UFDouble nsuprsnum) {
    this.setAttributeValue(OrderItemVO.NSUPRSNUM, nsuprsnum);
  }

  /** 本币价税合计 setter 方法 */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(OrderItemVO.NTAXMNY, ntaxmny);
  }

  /** 税率 **/
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(OrderItemVO.NTAXRATE, ntaxrate);
  }

  /** 应付财务组织 **/
  public void setPk_apfinanceorg(String pk_apfinanceorg) {
    this.setAttributeValue(OrderItemVO.PK_APFINANCEORG, pk_apfinanceorg);
  }

  /** 收货库存组织 **/
  public void setPk_arrvstoorg(String pk_arrvstoorg) {
    this.setAttributeValue(OrderItemVO.PK_ARRVSTOORG, pk_arrvstoorg);
  }

  /** 结算方式 **/
  public void setPk_balatype(String pk_balatype) {
    this.setAttributeValue(OrderHeaderVO.PK_BALATYPE, pk_balatype);
  }

  /** 物料(VID) **/
  public void setPk_material(String pk_material) {
    this.setAttributeValue(OrderItemVO.PK_MATERIAL, pk_material);
  }

  /** 采购订单表头_主键 **/
  public void setPk_order(String pk_order) {
    this.setAttributeValue(OrderItemVO.PK_ORDER, pk_order);
  }

  /** 采购订单行ID **/
  public void setPk_order_b(String pk_order_b) {
    this.setAttributeValue(OrderItemVO.PK_ORDER_B, pk_order_b);
  }

  /** 采购组织 **/
  public void setPk_org(String pk_org) {
    this.setAttributeValue(OrderItemVO.PK_ORG, pk_org);
  }

  /** 结算财务组织 **/
  public void setPk_psfinanceorg(String pk_psfinanceorg) {
    this.setAttributeValue(OrderItemVO.PK_PSFINANCEORG, pk_psfinanceorg);
  }

  /** 收货仓库ID **/
  public void setPk_recvstordoc(String pk_recvstordoc) {
    this.setAttributeValue(OrderItemVO.PK_RECVSTORDOC, pk_recvstordoc);
  }

  /** 供应商基本信息ID **/
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(OrderHeaderVO.PK_SUPPLIER, pk_supplier);
  }

  /** 订单编号 setter 方法 */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(OrderHeaderVO.VBILLCODE, vbillcode);
  }

  /** 合同号 **/
  public void setVcontractcode(String vcontractcode) {
    this.setAttributeValue(OrderItemVO.VCONTRACTCODE, vcontractcode);
  }

  /** 自由辅助属性1 **/
  public void setVfree1(String vfree1) {
    this.setAttributeValue(OrderItemVO.VFREE1, vfree1);
  }

  /** 自由辅助属性10 **/
  public void setVfree10(String vfree10) {
    this.setAttributeValue(OrderItemVO.VFREE10, vfree10);
  }

  /** 自由辅助属性2 **/
  public void setVfree2(String vfree2) {
    this.setAttributeValue(OrderItemVO.VFREE2, vfree2);
  }

  /** 自由辅助属性3 **/
  public void setVfree3(String vfree3) {
    this.setAttributeValue(OrderItemVO.VFREE3, vfree3);
  }

  /** 自由辅助属性4 **/
  public void setVfree4(String vfree4) {
    this.setAttributeValue(OrderItemVO.VFREE4, vfree4);
  }

  /** 自由辅助属性5 **/
  public void setVfree5(String vfree5) {
    this.setAttributeValue(OrderItemVO.VFREE5, vfree5);
  }

  /** 自由辅助属性6 **/
  public void setVfree6(String vfree6) {
    this.setAttributeValue(OrderItemVO.VFREE6, vfree6);
  }

  /** 自由辅助属性7 **/
  public void setVfree7(String vfree7) {
    this.setAttributeValue(OrderItemVO.VFREE7, vfree7);
  }

  /** 自由辅助属性8 **/
  public void setVfree8(String vfree8) {
    this.setAttributeValue(OrderItemVO.VFREE8, vfree8);
  }

  /** 自由辅助属性9 **/
  public void setVfree9(String vfree9) {
    this.setAttributeValue(OrderItemVO.VFREE9, vfree9);
  }

  /** 订单编号 **/
  public void setVordercode(String vordercode) {
    this.setAttributeValue(OrderHeaderVO.VBILLCODE, vordercode);
  }

  /** 订单类型（交易类型） **/
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(OrderHeaderVO.VTRANTYPECODE, vtrantypecode);
  }

}
