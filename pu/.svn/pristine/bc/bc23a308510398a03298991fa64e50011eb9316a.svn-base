package nc.vo.pu.tbb;

import java.io.Serializable;
import java.util.ArrayList;

import nc.bs.framework.server.util.NewObjectService;
import nc.itf.tb.control.IBusiSysExecDataProvider;
import nc.itf.tb.control.IBusiSysReg;
import nc.itf.tb.control.IDateType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.tb.control.ControlBillType;
import nc.vo.tb.control.ControlObjectType;
import nc.vo.tb.control.DocTypeEnum;

/**
 * 预算接口nc.itf.tb.control.IBusiSysReg的VO实现类
 * 供预算进行实际公式和控制方案设置时进行选择
 * 注册在ntb_id_sysreg表中
 * 
 * @since 6.0
 * @version 2010-12-28 下午04:15:34
 * @author wuxla
 */

public class PuBillBusiSysReg implements IBusiSysReg, IDateType, Serializable {

  /**
   * 审核日期
   */
  public static final String DAUDITDATT = "taudittime";

  /**
   * 单据日期
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * 无税金额
   */
  public static final String NMNY = "nmny";

  /**
   * 数量
   */
  public static final String NNUM = "nnum";

  /**
   * 含税金额
   */
  public static final String NTAXMNY = "ntaxmny";

  /**
   * 发生
   */
  public static final String OCCUR = "occur";

  private static final long serialVersionUID = -8418830220908866456L;

  private ArrayList<ControlBillType> billType;

  private ArrayList<ControlObjectType> objectType;

  private ArrayList<ControlObjectType> prayobjectType;

  public PuBillBusiSysReg() {
    this.initBillType();
    this.initObjectType();
  }

  @Override
  public ArrayList<ControlBillType> getBillType() {
    return this.billType;
  }

  @Override
  public String getBusiSysDesc() {
    return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
        "04004000-0097")/* @res "NC采购管理" */;
  }

  @Override
  public String getBusiSysID() {
    return "PO";
  }

  @Override
  public String[] getBusiType() {
    return null;
  }

  @Override
  public String[] getBusiTypeDesc() {
    return null;
  }

  @Override
  public String[] getControlableDirections() {
    return null;
  }

  @Override
  public String[] getControlableDirectionsDesc() {
    return null;
  }

  @Override
  public ArrayList<ControlObjectType> getControlableObjects() {
    return this.objectType;
  }

  @Override
  public ArrayList<ControlObjectType> getControlableObjects(String billtype) {
    if (POBillType.PrayBill.getCode().equals(billtype)) {
      return this.prayobjectType;
    }
    return this.getControlableObjects();
  }

  @Override
  public String[] getDataType() {
    return new String[] {
      PuBillBusiSysReg.DBILLDATE, PuBillBusiSysReg.DAUDITDATT
    };
  }

  @Override
  public String[] getDataTypeDesc() {
    return new String[] {
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
          "UC000-0000799")/* @res "单据日期" */,
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
          "04004000-0098")
    /* @res "审核日期" */
    };
  }

  @Override
  public String[] getDateType(String billtype) {
    return this.getDataType();
  }

  @Override
  public IBusiSysExecDataProvider getExecDataProvider() {
    return (IBusiSysExecDataProvider) NewObjectService.newInstance("pu",
        "nc.pubimpl.pu.tbb.PuExecDataProvider");
  }

  @Override
  public String getMainPkOrg() {
    return null;
  }

  @Override
  public boolean isBudgetControling() {
    return false;
  }

  @Override
  public boolean isSupportMutiSelect() {
    return false;
  }

  @Override
  public boolean isUseAccountDate(String billtype) {
    return false;
  }

  private void initBillType() {
    this.billType = new ArrayList<ControlBillType>();

    ControlBillType prayBillType = new ControlBillType();
    prayBillType.setPk_billType(POBillType.PrayBill.getCode());
    // prayBillType.setPk_billType("1002Z81000000000DC38");//
    // 单据类型在表bd_billtype中的主键
    prayBillType.setBillType_code(POBillType.PrayBill.getCode());
    prayBillType.setBillType_name(POBillType.PrayBill.getName());
    prayBillType.setDoctype(DocTypeEnum.Bill_type.name());
    prayBillType.setRunBillType(false);// 是否执行单据
    prayBillType.setReadyBillType(true);// 是否预占单据
    prayBillType.setUseUfind(true);
    prayBillType.setUseControl(false);
    ArrayList<String> prayBill_orgs = new ArrayList<String>();
    prayBill_orgs.add(DocConst.TYPE_STOCK);// 库存组织
    prayBillType.setPk_orgs(prayBill_orgs);// 该单据预算控制的主组织
    this.billType.add(prayBillType);

    ControlBillType orderBillType = new ControlBillType();
    orderBillType.setPk_billType(POBillType.Order.getCode());
    // orderBillType.setPk_billType("1002Z81000000000DC44");//
    // 单据类型在表bd_billtype中的主键
    orderBillType.setBillType_code(POBillType.Order.getCode());
    orderBillType.setBillType_name(POBillType.Order.getName());
    orderBillType.setDoctype(DocTypeEnum.Bill_type.name());
    orderBillType.setRunBillType(true);// 是否执行单据
    orderBillType.setReadyBillType(true);// 是否预占单据
    orderBillType.setUseUfind(true);
    orderBillType.setUseControl(false);
    ArrayList<String> orderBill_orgs = new ArrayList<String>();
    orderBill_orgs.add(DocConst.TYPE_PURCHASE);// 采购组织
    orderBill_orgs.add(DocConst.TYPE_FINANCE);// 结算组织
    orderBill_orgs.add(DocConst.TYPE_STOCK);// 库存组织
    orderBillType.setPk_orgs(orderBill_orgs);// 该单据预算控制的主组织
    this.billType.add(orderBillType);

    ControlBillType invoiceBillType = new ControlBillType();
    invoiceBillType.setPk_billType(POBillType.Invoice.getCode());
    // invoiceBillType.setPk_billType("1002Z81000000000DC42");//
    // 单据类型在表bd_billtype中的主键
    invoiceBillType.setBillType_code(POBillType.Invoice.getCode());
    invoiceBillType.setBillType_name(POBillType.Invoice.getName());
    invoiceBillType.setDoctype(DocTypeEnum.Bill_type.name());
    invoiceBillType.setRunBillType(true);// 是否执行单据
    invoiceBillType.setReadyBillType(false);// 是否预占单据
    invoiceBillType.setUseUfind(true);
    invoiceBillType.setUseControl(false);
    ArrayList<String> invoiceBill_orgs = new ArrayList<String>();
    invoiceBill_orgs.add(DocConst.TYPE_FINANCE);// 财务组织
    invoiceBill_orgs.add(DocConst.TYPE_PURCHASE);// 采购组织
    invoiceBillType.setPk_orgs(invoiceBill_orgs);// 该单据预算控制的主组织
    this.billType.add(invoiceBillType);
  }

  private void initObjectType() {
    this.objectType = new ArrayList<ControlObjectType>();
    this.prayobjectType = new ArrayList<ControlObjectType>();
    ControlObjectType[] cotMember = new ControlObjectType[3];
    // 请购单使用
    ControlObjectType[] cotMember0 = new ControlObjectType[1];
    cotMember[0] =
        new ControlObjectType(PuBillBusiSysReg.NNUM, PuBillBusiSysReg.NNUM,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0002282")/* @res "数量" */);
    cotMember0[0] = cotMember[0];
    cotMember[1] =
        new ControlObjectType(PuBillBusiSysReg.NMNY, PuBillBusiSysReg.NMNY,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0002307")/* @res "无税金额" */);
    cotMember[2] =
        new ControlObjectType(PuBillBusiSysReg.NTAXMNY,
            PuBillBusiSysReg.NTAXMNY, nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("common", "UC000-0001162")/*
                                                                      * @res
                                                                      * "含税金额"
                                                                      */);

    ControlObjectType occurObject =
        new ControlObjectType(PuBillBusiSysReg.OCCUR, PuBillBusiSysReg.OCCUR,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
                "04004000-0099")/* @res "发生" */);
    // 请购单使用
    ControlObjectType occurObject0 =
        new ControlObjectType(PuBillBusiSysReg.OCCUR, PuBillBusiSysReg.OCCUR,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
                "04004000-0099")/* @res "发生" */);
    occurObject.setCotMember(cotMember);
    occurObject0.setCotMember(cotMember0);
    this.objectType.add(occurObject);
    this.prayobjectType.add(occurObject0);
  }
}
