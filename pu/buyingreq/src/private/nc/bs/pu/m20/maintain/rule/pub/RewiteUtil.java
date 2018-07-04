/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-10 下午08:56:54
 */
package nc.bs.pu.m20.maintain.rule.pub;

import java.util.HashMap;
import java.util.Map;

import nc.bs.pu.m20.rewrite.source.RewiteM4F;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.am.constant.BillTypeConst_4B;
import nc.vo.pu.m20.enumeration.EnumPraySource;
import nc.vo.scmpub.res.billtype.ETBillType;
import nc.vo.scmpub.res.billtype.INVPBillType;
import nc.vo.scmpub.res.billtype.MMBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>放置增删改回写规则对应的单据类型和取得回写实现类。
 * <li>加入新的规则时：
 * <li>1.实现对应的回写接口IRewite。
 * <li>2.将新的单据类型对应的回写接口加入到getRewite方法中。
 * <li>3.在增删改中需要调用回写接口的map中放入来源和单据类型对应关系。
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-10 下午08:56:54
 */
public class RewiteUtil {
  private static RewiteUtil instance = new RewiteUtil();

  // 删除时需要回写的单据
  private Map<Integer, String> delRule = new HashMap<Integer, String>();

  // 新增时需要回写的单据
  private Map<Integer, String> inRule = new HashMap<Integer, String>();

  // 修改时需要回写的单据
  private Map<Integer, String> upRule = new HashMap<Integer, String>();

  private RewiteUtil() {
    this.initInrule();
    this.initUprule();
    this.initDelrule();
  }

  public static RewiteUtil getInstance() {
    return RewiteUtil.instance;
  }

  public Map<Integer, String> getDelRule() {
    return this.delRule;
  }

  public Map<Integer, String> getInRule() {
    return this.inRule;
  }

  /**
   * 方法功能描述：根据单据类型取得回写接口实现类。
   * <p>
   * <b>参数说明</b>
   * 
   * @param billtype
   *          单据类型
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-11 上午08:43:34
   */
  public IRewite getRewite(String billtype) {
    // 委外订单
    if (SOBillType.Order.getCode().equals(billtype)) {
      return new RewiteM30();
    }
    // 调拨订单
    if (TOBillType.TransOrder.getCode().equals(billtype)) {
      return new RewiteM5x();
    }
    // 计划订单
    if (MMBillType.PlanOrder.getCode().equals(billtype)) {
      return new RewiteM55B4();
    }
    // 生产订单
    if (MMBillType.ProduceOrder.getCode().equals(billtype)) {
      return new RewiteM55A2();
    }
    // 离散生产订单
    if (MMBillType.LsProduceOrder.getCode().equals(billtype)) {
      return new RewiteM55C2();
    }
    // 生产订单（库存计划）
    if (INVPBillType.PoOrder.getCode().equals(billtype)) {
      return new RewiteM4F();
    }
    // 物资需求申请单
    if (POBillType.MRBill.getCode().equals(billtype)) {
      return new Rewite422X();
    }
    // 资产卡片只进行并发控制
    String workorderid = this.getWorkorderTypeId();
    if (workorderid != null && workorderid.equals(billtype)) {
      return new Rewite4B36();
    }
    // 出口合同
    if (ETBillType.CONTRACT.getCode().equals(billtype)) {
      return new RewiteM5720();
    }
    return null;
  }

  public Map<Integer, String> getUpRule() {
    return this.upRule;
  }

  private String getWorkorderTypeId() {
    Map<String, String> map =
        PfServiceScmUtil.getTrantypeidByCode(new String[] {
          BillTypeConst_4B.WORKORDER
        });
    return map != null ? map.get(BillTypeConst_4B.WORKORDER) : null;
  }

  private void initDelrule() {
    // 删除时需要回写的单据
    this.delRule.put(EnumPraySource.SO.toInteger(), SOBillType.Order.getCode());
    this.delRule.put(EnumPraySource.M5X.toInteger(),
        TOBillType.TransOrder.getCode());
    this.delRule.put(EnumPraySource.MPO.toInteger(),
        MMBillType.PlanOrder.getCode());
    this.delRule.put(EnumPraySource.ICPO.toInteger(),
        MMBillType.PlanOrder.getCode());
    // this.delRule.put(EnumPraySource.ICPO.toInteger(),
    // INVPBillType.PoOrder.getCode());// 库存计划生产订单
    this.delRule.put(EnumPraySource.MPS.toInteger(),
        MMBillType.PlanOrder.getCode());
    this.delRule.put(EnumPraySource.MO.toInteger(),
        MMBillType.ProduceOrder.getCode());
    this.delRule.put(EnumPraySource.M5720.toInteger(),
        ETBillType.CONTRACT.getCode());
  }

  private void initInrule() {
    // 新增时需要回写的单据
    this.inRule.put(EnumPraySource.SO.toInteger(), SOBillType.Order.getCode());
    this.inRule.put(EnumPraySource.M5X.toInteger(),
        TOBillType.TransOrder.getCode());
    this.inRule.put(EnumPraySource.MPO.toInteger(),
        MMBillType.PlanOrder.getCode());
    this.inRule.put(EnumPraySource.ICPO.toInteger(),
        MMBillType.PlanOrder.getCode());
    // this.inRule.put(EnumPraySource.ICPO.toInteger(),
    // INVPBillType.PoOrder.getCode());// 库存计划计划订单
    this.inRule.put(EnumPraySource.MPS.toInteger(),
        MMBillType.PlanOrder.getCode());
    this.inRule.put(EnumPraySource.MO.toInteger(),
        MMBillType.ProduceOrder.getCode());
    this.inRule.put(EnumPraySource.M5720.toInteger(),
        ETBillType.CONTRACT.getCode());
    // 资产卡片只进行并发控制
    this.inRule
        .put(EnumPraySource.M4B36.toInteger(), this.getWorkorderTypeId());
  }

  private void initUprule() {
    // 修改时需要回写的单据

    this.upRule.put(EnumPraySource.SO.toInteger(), SOBillType.Order.getCode());
    this.upRule.put(EnumPraySource.M5X.toInteger(),
        TOBillType.TransOrder.getCode());
    this.upRule.put(EnumPraySource.MPO.toInteger(),
        MMBillType.PlanOrder.getCode());
    this.upRule.put(EnumPraySource.ICPO.toInteger(),
        MMBillType.PlanOrder.getCode());

    // this.upRule.put(EnumPraySource.ICPO.toInteger(),
    // INVPBillType.PoOrder.getCode());// 库存计划计划订单
    this.upRule.put(EnumPraySource.MPS.toInteger(),
        MMBillType.PlanOrder.getCode());
    this.upRule.put(EnumPraySource.MO.toInteger(),
        MMBillType.ProduceOrder.getCode());
    this.upRule.put(EnumPraySource.M5720.toInteger(),
        ETBillType.CONTRACT.getCode());
  }
}
