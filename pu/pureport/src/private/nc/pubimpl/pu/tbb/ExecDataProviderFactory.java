package nc.pubimpl.pu.tbb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.ml.NCLangResOnserver;
import nc.pubimpl.pu.tbb.strategy.IExecDataProviderStrategy;
import nc.pubimpl.pu.tbb.strategy.invoicebill.InvoiceMnyExecDataProviderStrategy;
import nc.pubimpl.pu.tbb.strategy.invoicebill.InvoiceNumExecDataProviderStrategy;
import nc.pubimpl.pu.tbb.strategy.invoicebill.InvoiceTaxMnyExecDataProviderStrategy;
import nc.pubimpl.pu.tbb.strategy.orderbill.OrderMnyExecDataProviderStrategy;
import nc.pubimpl.pu.tbb.strategy.orderbill.OrderNumExecDataProviderStrategy;
import nc.pubimpl.pu.tbb.strategy.orderbill.OrderTaxmnyExecDataProviderStrategy;
import nc.pubimpl.pu.tbb.strategy.praybill.PrayBillNumExecDataProviderStrategy;
import nc.vo.pu.tbb.PuBillBusiSysReg;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.tb.obj.NtbParamVO;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>取数策略工厂
 * </ul>
 * <p>
 * <p>
 * 
 * @version 5.7
 * @since 5.7
 * @author wuxla
 * @time 2010-8-23 下午03:13:34
 */
public class ExecDataProviderFactory {
  public static ExecDataProviderFactory instance =
      new ExecDataProviderFactory();

  private Map<String, IExecDataProviderStrategy> strategyMap =
      new HashMap<String, IExecDataProviderStrategy>();

  private ExecDataProviderFactory() {
    this.strategyMap.put(POBillType.PrayBill.getCode() + "-"
        + PuBillBusiSysReg.NNUM, new PrayBillNumExecDataProviderStrategy());
    this.strategyMap.put(POBillType.Order.getCode() + "-"
        + PuBillBusiSysReg.NNUM, new OrderNumExecDataProviderStrategy());
    this.strategyMap.put(POBillType.Order.getCode() + "-"
        + PuBillBusiSysReg.NTAXMNY, new OrderTaxmnyExecDataProviderStrategy());
    this.strategyMap.put(POBillType.Order.getCode() + "-"
        + PuBillBusiSysReg.NMNY, new OrderMnyExecDataProviderStrategy());
    this.strategyMap.put(POBillType.Invoice.getCode() + "-"
        + PuBillBusiSysReg.NNUM, new InvoiceNumExecDataProviderStrategy());
    this.strategyMap
        .put(POBillType.Invoice.getCode() + "-" + PuBillBusiSysReg.NTAXMNY,
            new InvoiceTaxMnyExecDataProviderStrategy());
    this.strategyMap.put(POBillType.Invoice.getCode() + "-"
        + PuBillBusiSysReg.NMNY, new InvoiceMnyExecDataProviderStrategy());
  }

  public static ExecDataProviderFactory getInstance() {
    return ExecDataProviderFactory.instance;
  }

  /**
   * 方法功能描述：根据取数参数返回取数策略
   * <p>
   * <b>参数说明</b>
   * 
   * @param param
   * @return <p>
   * @since 5.7
   * @author wuxla
   * @time 2010-8-23 下午03:36:34
   */
  public IExecDataProviderStrategy getExecDataProviderStrategy(NtbParamVO param) {
    String billtype = param.getBill_type();
    String occur = param.getData_attr();
    if (StringUtils.isEmpty(occur)) {
      return null;
    }
    if (StringUtils.isEmpty(billtype)) {
      return null;
    }

    if (!PuBillBusiSysReg.OCCUR.equals(occur)) {
      return null;
    }
    String dataattr = param.getData_attrExt();
    if (StringUtils.isEmpty(dataattr)) {
      return null;
    }

    if (POBillType.PrayBill.getCode().equals(billtype)) {
      if (PuBillBusiSysReg.NNUM.equals(dataattr)) {
        return new PrayBillNumExecDataProviderStrategy();
      }
      return null;
    }
    else if (POBillType.Order.getCode().equals(billtype)) {
      if (PuBillBusiSysReg.NNUM.equals(dataattr)) {
        return new OrderNumExecDataProviderStrategy();
      }
      else if (PuBillBusiSysReg.NTAXMNY.equals(dataattr)) {
        return new OrderTaxmnyExecDataProviderStrategy();
      }
      else if (PuBillBusiSysReg.NMNY.equals(dataattr)) {
        return new OrderMnyExecDataProviderStrategy();
      }
    }
    else if (POBillType.Invoice.getCode().equals(billtype)) {
      if (PuBillBusiSysReg.NNUM.equals(dataattr)) {
        return new InvoiceNumExecDataProviderStrategy();
      }
      else if (PuBillBusiSysReg.NTAXMNY.equals(dataattr)) {
        return new InvoiceTaxMnyExecDataProviderStrategy();
      }
      else if (PuBillBusiSysReg.NMNY.equals(dataattr)) {
        return new InvoiceMnyExecDataProviderStrategy();
      }
    }
    return null;
  }

  public Map<IExecDataProviderStrategy, NtbParamVO[]> getExecDataProviderStrategy(
      NtbParamVO[] params) {
    Map<IExecDataProviderStrategy, NtbParamVO[]> result =
        new HashMap<IExecDataProviderStrategy, NtbParamVO[]>();
    MapList<IExecDataProviderStrategy, NtbParamVO> tempResult =
        new MapList<IExecDataProviderStrategy, NtbParamVO>();
    for (int i = 0; i < params.length; i++) {
      NtbParamVO vo = params[i];
      if (null == vo) {
        continue;
      }
      String billtype = vo.getBill_type();
      String occur = vo.getData_attr();
      String dataattr = vo.getData_attrExt();
      if (StringUtils.isEmpty(billtype) || StringUtils.isEmpty(occur)
          || StringUtils.isEmpty(dataattr)
          || !PuBillBusiSysReg.OCCUR.equals(occur)) {
        continue;
      }
      IExecDataProviderStrategy strategy =
          this.strategyMap.get(billtype + "-" + dataattr);
      this.checkStrategy(vo, strategy);
      // NtbParamVO[] pmvos = result.get(strategy);
      // if (null == pmvos) {
      // pmvos = new NtbParamVO[params.length];
      // result.put(strategy, pmvos);
      // }
      // pmvos[i] = vo;
      if (strategy != null) {
        tempResult.put(strategy, vo);
      }
    }
    for (Entry<IExecDataProviderStrategy, List<NtbParamVO>> entry : tempResult
        .entrySet()) {
      List<NtbParamVO> voList = entry.getValue();
      NtbParamVO[] pmvos = voList.toArray(new NtbParamVO[voList.size()]);
      result.put(entry.getKey(), pmvos);
    }
    return result;
  }

  private void checkStrategy(NtbParamVO para, IExecDataProviderStrategy strategy) {
    if (strategy == null) {
      String dataattr = para.getData_attr();
      if (PuBillBusiSysReg.OCCUR.equals(dataattr)) {
        /* @res "发生" */
        dataattr =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
                "04004000-0099");
      }
      String billtype = para.getBill_type();
      String dataattrEx = para.getData_attrExt();
      if (PuBillBusiSysReg.NNUM.equals(dataattrEx)) {
        /* @res "数量" */
        dataattrEx =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0002282");
      }
      else if (PuBillBusiSysReg.NTAXMNY.equals(dataattrEx)) {
        /*
         * @res
         * 含税金额
         */
        dataattrEx =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0001162");
      }
      else if (PuBillBusiSysReg.NMNY.equals(dataattrEx)) {
        /* @res "无税金额" */
        dataattrEx =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0002307");
      }
      String msg =
          NCLangResOnserver.getInstance().getStrByID("4004090_0",
              "04004090-0011", null, new String[] {
                billtype, dataattr, dataattrEx
              })/* 不支持此种取数!单据类型:{0}，属性:{1}，取数内容：{2} */;

      ExceptionUtils.wrappBusinessException(msg);
    }
  }
}
