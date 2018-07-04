/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-2 下午05:09:27
 */
package nc.vo.pu.m21.rule;

import nc.impl.pubapp.env.BSContext;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置补货订单的值
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-2 下午05:09:27
 */
public class ReplenishDefaultValue {

  private IKeyValue keyValue;

  public ReplenishDefaultValue(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：设置默认值
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-6-5 下午04:15:38
   */
  public void setDefaultValue() {
    // 是否退货
    this.keyValue.setHeadValue(OrderHeaderVO.BRETURN, UFBoolean.FALSE);
    // 修订人
    this.keyValue.setHeadValue(OrderHeaderVO.CREVISEPSN, null);
    // 修订时间
    this.keyValue.setHeadValue(OrderHeaderVO.TREVISIONTIME, null);
    // 修改人
    this.keyValue.setHeadValue(OrderHeaderVO.MODIFIER, null);
    // 修改时间
    this.keyValue.setHeadValue(OrderHeaderVO.MODIFIEDTIME, null);
    // 制单人
    this.keyValue.setHeadValue(OrderHeaderVO.BILLMAKER, BSContext.getInstance()
        .getUserID());
    // 创建时间
    this.keyValue.setHeadValue(OrderHeaderVO.CREATIONTIME, null);
    // 审核人
    this.keyValue.setHeadValue(OrderHeaderVO.APPROVER, null);
    // 审批时间
    this.keyValue.setHeadValue(OrderHeaderVO.TAUDITTIME, null);
    // 订单编号
    this.keyValue.setHeadValue(OrderHeaderVO.VBILLCODE, null);
    // 订单日期
    this.keyValue.setHeadValue(OrderHeaderVO.DBILLDATE, AppContext
        .getInstance().getBusiDate());
    // 补货标志
    this.keyValue.setHeadValue(OrderHeaderVO.BISREPLENISH, UFBoolean.TRUE);
    // 订单状态
    this.keyValue.setHeadValue(OrderHeaderVO.FORDERSTATUS,
        POEnumBillStatus.FREE.value());
    // 是否最新版本
    this.keyValue.setHeadValue(OrderHeaderVO.BISLATEST, UFBoolean.TRUE);
    // 版本信息
    this.keyValue.setHeadValue(OrderHeaderVO.NVERSION, Integer.valueOf(1));
    // 原币预付款限额
    this.keyValue.setHeadValue(OrderHeaderVO.NORGPREPAYLIMIT, null);

    // 对方订单号
    this.keyValue.setHeadValue(OrderHeaderVO.VCOOPORDERCODE, null);
    // 由销售订单协同生成
    this.keyValue.setHeadValue(OrderHeaderVO.BSOCOOPTOME, null);

    this.keyValue.setHeadValue(OrderHeaderVO.IRESPSTATUS, null);
    this.keyValue.setHeadValue(OrderHeaderVO.VREASON, null);
    this.keyValue.setHeadValue(OrderHeaderVO.BPUBLISH, UFBoolean.FALSE);
    this.keyValue.setHeadValue(OrderHeaderVO.PK_PUBPSN, null);
    this.keyValue.setHeadValue(OrderHeaderVO.TPUBTIME, null);
    this.keyValue.setHeadValue(OrderHeaderVO.PK_RESPPSN, null);
    this.keyValue.setHeadValue(OrderHeaderVO.TRESPTIME, null);

    this.keyValue.setHeadValue(OrderHeaderVO.TS, null);
    this.keyValue.setHeadValue(OrderHeaderVO.BFINALCLOSE, UFBoolean.FALSE);
    int count = this.keyValue.getItemCount();
    for (int i = 0; i < count; ++i) {
      this.keyValue.setBodyValue(i, OrderItemVO.CROWNO, null);
      // 激活状态
      this.keyValue.setBodyValue(i, OrderItemVO.FISACTIVE,
          EnumActive.ACTIVE.value());
      // 修正日期
      this.keyValue.setBodyValue(i, OrderItemVO.DCORRECTDATE, null);
      // 存在到货计划
      this.keyValue.setBodyValue(i, OrderItemVO.BRECEIVEPLAN, null);
      // 去掉订单行状态
      // 到货关闭
      this.keyValue.setBodyValue(i, OrderItemVO.BARRIVECLOSE, UFBoolean.FALSE);
      // 入库关闭
      this.keyValue.setBodyValue(i, OrderItemVO.BSTOCKCLOSE, UFBoolean.FALSE);
      // 开票关闭
      this.keyValue.setBodyValue(i, OrderItemVO.BINVOICECLOSE, UFBoolean.FALSE);
      // 付款关闭
      this.keyValue.setBodyValue(i, OrderItemVO.BPAYCLOSE, UFBoolean.FALSE);
      this.keyValue.setBodyValue(i, OrderItemVO.FISACTIVE,
          EnumActive.ACTIVE.value());
      // 对方订单号
      this.keyValue.setBodyValue(i, OrderItemVO.VVENDORORDERCODE, null);
      // 对方订单行号
      this.keyValue.setBodyValue(i, OrderItemVO.VVENDORORDERROW, null);
      // 确认数量
      this.keyValue.setBodyValue(i, OrderItemVO.NCONFIRMNUM, null);
      // 确认日期
      this.keyValue.setBodyValue(i, OrderItemVO.DCONFIRMDATE, null);
      // 计划到货日期，为重新设置计划到货日期做准备
      this.keyValue.setBodyValue(i, OrderItemVO.DPLANARRVDATE, null);
      // 累计到货主数量
      this.keyValue.setBodyValue(i, OrderItemVO.NACCUMARRVNUM, null);
      // 累计运输主数量
      this.keyValue.setBodyValue(i, OrderItemVO.NACCUMDEVNUM, null);
      // 累计开票主数量
      this.keyValue.setBodyValue(i, OrderItemVO.NACCUMINVOICENUM, null);
      // 累计到货计划主数量
      this.keyValue.setBodyValue(i, OrderItemVO.NACCUMRPNUM, null);
      // 累计入库主数量
      this.keyValue.setBodyValue(i, OrderItemVO.NACCUMSTORENUM, null);
      // 累计途耗主数量
      this.keyValue.setBodyValue(i, OrderItemVO.NACCUMWASTNUM, null);
      // 累计退货主数量
      this.keyValue.setBodyValue(i, OrderItemVO.NBACKARRVNUM, null);
      // 累计退库主数量
      this.keyValue.setBodyValue(i, OrderItemVO.NBACKSTORENUM, null);
      // 累计本币开票金额
      this.keyValue.setBodyValue(i, OrderItemVO.NACCUMINVOICEMNY, null);
      // 累计已核销本币开票金额
      this.keyValue.setBodyValue(i, OrderItemVO.NACCCANCELINVMNY, null);
      // 累计拣货主数量
      this.keyValue.setBodyValue(i, OrderItemVO.NACCUMPICKUPNUM, null);
      // 费用累计开票金额
      this.keyValue.setBodyValue(i, OrderItemVO.NFEEMNY, null);

      this.keyValue.setBodyValue(i, OrderItemVO.TS, null);

      this.keyValue.setBodyValue(i, OrderItemVO.NORIGORDERNUM, null);
      this.keyValue.setBodyValue(i, OrderItemVO.NORIGORDERPRICE, null);
      this.keyValue.setBodyValue(i, OrderItemVO.DORIGPLANARRVDATE, null);
      this.keyValue.setBodyValue(i, OrderItemVO.ISTORESTATUS, null);

      this.keyValue.setBodyValue(i, OrderItemVO.CECBILLBID, null);
      this.keyValue.setBodyValue(i, OrderItemVO.CECBILLID, null);
      this.keyValue.setBodyValue(i, OrderItemVO.CECTYPECODE, null);
      this.keyValue.setBodyValue(i, OrderItemVO.VECBILLCODE, null);
      this.keyValue.setBodyValue(i, OrderItemVO.NSENDPLANNUM, null);
      this.keyValue.setBodyValue(i, OrderItemVO.PK_SCHEDULE, null);
      this.keyValue.setBodyValue(i, OrderItemVO.PK_SCHEDULE_B, null);

    }
  }

  /**
   * 方法功能描述：设置订单VO数据为正
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-6-3 上午09:53:17
   */
  public void setPositiveOrder() {
    int count = this.keyValue.getItemCount();

    for (int i = 0; i < count; ++i) {
      // 单品折扣率
      UFDouble nitemdiscountrate =
          (UFDouble) this.keyValue.getBodyValue(i,
              OrderItemVO.NITEMDISCOUNTRATE);
      this.keyValue.setBodyValue(i, OrderItemVO.NITEMDISCOUNTRATE, MathTool
          .nvl(nitemdiscountrate).abs());

      // 折本汇率
      UFDouble nexchangerate =
          (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NEXCHANGERATE);
      this.keyValue.setBodyValue(i, OrderItemVO.NEXCHANGERATE,
          MathTool.nvl(nexchangerate).abs());

      // 本币无税金额
      UFDouble nmny =
          (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NMNY);
      this.keyValue.setBodyValue(i, OrderItemVO.NMNY, MathTool.nvl(nmny).abs());

      // 主单位本币无税净价
      UFDouble nnetprice =
          (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NNETPRICE);
      this.keyValue.setBodyValue(i, OrderItemVO.NNETPRICE,
          MathTool.nvl(nnetprice).abs());

      // 原币无税金额
      UFDouble norigmny =
          (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NORIGMNY);
      this.keyValue.setBodyValue(i, OrderItemVO.NORIGMNY, MathTool
          .nvl(norigmny).abs());

      // 主单位原币无税净价
      UFDouble norignetprice =
          (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NORIGNETPRICE);
      this.keyValue.setBodyValue(i, OrderItemVO.NORIGNETPRICE,
          MathTool.nvl(norignetprice).abs());

      // 主单位原币无税单价
      UFDouble norigprice =
          (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NORIGPRICE);
      this.keyValue.setBodyValue(i, OrderItemVO.NORIGPRICE,
          MathTool.nvl(norigprice).abs());

      // // 原币税额
      // UFDouble norigtax =
      // (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NORIGTAX);
      // this.keyValue.setBodyValue(i, OrderItemVO.NORIGTAX, MathTool
      // .nvl(norigtax).abs());

      // 原币价税合计
      UFDouble norigtaxmny =
          (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NORIGTAXMNY);
      this.keyValue.setBodyValue(i, OrderItemVO.NORIGTAXMNY,
          MathTool.nvl(norigtaxmny).abs());

      // 主单位原币含税净价
      UFDouble norigtaxnetprice =
          (UFDouble) this.keyValue
              .getBodyValue(i, OrderItemVO.NORIGTAXNETPRICE);
      this.keyValue.setBodyValue(i, OrderItemVO.NORIGTAXNETPRICE,
          MathTool.nvl(norigtaxnetprice).abs());

      // 主单位原币含税单价
      UFDouble norigtaxprice =
          (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NORIGTAXPRICE);
      this.keyValue.setBodyValue(i, OrderItemVO.NORIGTAXPRICE,
          MathTool.nvl(norigtaxprice).abs());

      // 报价本币无税净价
      UFDouble nqtnetprice =
          (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NQTNETPRICE);
      this.keyValue.setBodyValue(i, OrderItemVO.NQTNETPRICE,
          MathTool.nvl(nqtnetprice).abs());

      // 报价原币无税净价
      UFDouble nqtorignetprice =
          (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NQTORIGNETPRICE);
      this.keyValue.setBodyValue(i, OrderItemVO.NQTORIGNETPRICE,
          MathTool.nvl(nqtorignetprice).abs());

      // 报价原币无税单价
      UFDouble nqtorigprice =
          (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NQTORIGPRICE);
      this.keyValue.setBodyValue(i, OrderItemVO.NQTORIGPRICE,
          MathTool.nvl(nqtorigprice).abs());

      // 报价原币含税净价
      UFDouble nqtorigtaxnetprc =
          (UFDouble) this.keyValue
              .getBodyValue(i, OrderItemVO.NQTORIGTAXNETPRC);
      this.keyValue.setBodyValue(i, OrderItemVO.NQTORIGTAXNETPRC,
          MathTool.nvl(nqtorigtaxnetprc).abs());

      // 报价原币含税单价
      UFDouble nqtorigtaxprice =
          (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NQTORIGTAXPRICE);
      this.keyValue.setBodyValue(i, OrderItemVO.NQTORIGTAXPRICE,
          MathTool.nvl(nqtorigtaxprice).abs());

      // 报价本币含税净价
      UFDouble nqttaxnetprice =
          (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NQTTAXNETPRICE);
      this.keyValue.setBodyValue(i, OrderItemVO.NQTTAXNETPRICE,
          MathTool.nvl(nqttaxnetprice).abs());

      // 本币税额
      UFDouble ntax =
          (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NTAX);
      this.keyValue.setBodyValue(i, OrderItemVO.NTAX, MathTool.nvl(ntax).abs());

      // 本币价税合计
      UFDouble ntaxmny =
          (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NTAXMNY);
      this.keyValue.setBodyValue(i, OrderItemVO.NTAXMNY, MathTool.nvl(ntaxmny)
          .abs());

      // 主单位本币含税净价
      UFDouble ntaxnetprice =
          (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NTAXNETPRICE);
      this.keyValue.setBodyValue(i, OrderItemVO.NTAXNETPRICE,
          MathTool.nvl(ntaxnetprice).abs());

      // 税率
      UFDouble ntaxrate =
          (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NTAXRATE);
      this.keyValue.setBodyValue(i, OrderItemVO.NTAXRATE, MathTool
          .nvl(ntaxrate).abs());
      // 本币含税单价
      UFDouble nqttaxprice =
          (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NQTTAXPRICE);
      this.keyValue.setBodyValue(i, OrderItemVO.NQTTAXPRICE,
          MathTool.nvl(nqttaxprice).abs());
      // 本币无税单价
      UFDouble nqtprice =
          (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NQTPRICE);
      this.keyValue.setBodyValue(i, OrderItemVO.NQTPRICE, MathTool
          .nvl(nqtprice).abs());
      // 主本币含税单价
      UFDouble ntaxprice =
          (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NTAXPRICE);
      this.keyValue.setBodyValue(i, OrderItemVO.NTAXPRICE,
          MathTool.nvl(ntaxprice).abs());
      // 主本币无税单价
      UFDouble nprice =
          (UFDouble) this.keyValue.getBodyValue(i, OrderItemVO.NPRICE);
      this.keyValue.setBodyValue(i, OrderItemVO.NPRICE, MathTool.nvl(nprice)
          .abs());
    }

  }

  public void setVOInfoByStore(int[] rows, PurchaseInVO[] purchaseinVOs) {
    BillIndex index = new BillIndex(purchaseinVOs);
    IVOMeta meta =
        purchaseinVOs[0].getMetaData().getVOMeta(PurchaseInBodyVO.class);
    for (int row : rows) {
      String bid =
          (String) this.keyValue.getBodyValue(row, OrderItemVO.CSOURCEBID);
      PurchaseInBodyVO purchaseInBodyVO =
          (PurchaseInBodyVO) index.get(meta, bid);
      // 默认的补货数量=ABS(退库数量+累计补货数量)
      this.keyValue.setBodyValue(row, OrderItemVO.NNUM,
          purchaseInBodyVO.getNcanreplnum());

      String pkOrderB =
          (String) this.keyValue.getBodyValue(row, OrderItemVO.PK_ORDER_B);
      if (!StringUtil.isEmptyWithTrim(pkOrderB)) {
        this.keyValue.setBodyValue(row, OrderItemVO.CSOURCEID,
            this.keyValue.getBodyValue(row, OrderItemVO.PK_ORDER));
        this.keyValue.setBodyValue(row, OrderItemVO.CSOURCEBID,
            this.keyValue.getBodyValue(row, OrderItemVO.PK_ORDER_B));
        this.keyValue.setBodyValue(row, OrderItemVO.CSOURCETYPECODE,
            POBillType.Order.getCode());
      }

      this.keyValue.setBodyValue(row, OrderItemVO.PK_ORDER, null);
      this.keyValue.setBodyValue(row, OrderItemVO.PK_ORDER_B, null);
    }
    this.keyValue.setHeadValue(OrderHeaderVO.PK_ORDER, null);
  }
}
