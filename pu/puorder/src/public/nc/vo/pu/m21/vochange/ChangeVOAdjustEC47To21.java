package nc.vo.pu.m21.vochange;

import java.util.HashMap;
import java.util.Map;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.OrderVOUtil;
import nc.vo.pu.m21.rule.PlanArriveDate;
import nc.vo.pu.m21.rule.RelationCalculate;
import nc.vo.pu.m21.rule.vat.setter.country.AbstractOrderCountrySetter.CountryType;
import nc.vo.pu.m21.rule.vat.setter.country.OrderReceiveAddressCountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.OrderReceiveCountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.OrderSendAddressCountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.OrderSendCountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.OrderTaxCountrySetter;
import nc.vo.pu.pub.rule.vat.BuysellflagSetter;
import nc.vo.pu.pub.rule.vat.TriatradeflagSetter;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.scmpub.res.billtype.ECBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.StringUtils;

/**
 * @since 6.0
 * @version 2011-6-22 下午12:50:00
 * @author wuxla
 */

public class ChangeVOAdjustEC47To21 extends AbstractOrderChangeVOAdjust {

  private static final String EC47ID = "1001ZF10000000005CWV";

  private Map<String, String> typeMap = new HashMap<String, String>();

  private void setECBilltype(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      for (OrderItemVO item : vo.getBVO()) {
        this.setSrcInfo(item);
        String ecID = item.getCecbillid();
        if (StringUtils.isBlank(ecID)) {
          continue;
        }
        String ec47 = item.getCectypecode();
        if (StringUtils.isBlank(ec47)
            || !ECBillType.EC47.getCode().equals(ec47)) {
          item.setCectypecode(ChangeVOAdjustEC47To21.EC47ID);
        }
        else {
          if (this.typeMap.containsKey(ECBillType.EC47.getCode())) {
            item.setCectypecode(this.typeMap.get(ECBillType.EC47.getCode()));
          }
          else {
            ec47 = PfServiceScmUtil.getTrantypeidByCode(new String[] {
              ECBillType.EC47.getCode()
            }).get(ECBillType.EC47.getCode());
            this.typeMap.put(ECBillType.EC47.getCode(), ec47);
            item.setCectypecode(ec47);
          }
        }
      }
    }
  }

  @Override
  protected String[] getNumStrs() {
    return new String[]{"total_cont_exe", "amount", "num"};
  }
  
  private void setSrcInfo(OrderItemVO item) {
    item.setCsourcebid(null);
    item.setCsourceid(null);
    item.setCsourcetypecode(null);
    item.setVsourcecode(null);
    item.setVsourcerowno(null);
    item.setVsourcetrantype(null);
    // by zhaoyha at 2011.11.21 根据司建的要求保留firstpk，但单据交易类型code我们要转PK
    String btcode = item.getCfirsttypecode();
    String ttcode = item.getVfirsttrantype();
    if (this.typeMap.containsKey(String.valueOf(btcode))
        && this.typeMap.containsKey(String.valueOf(ttcode))) {
      String btvalue = this.typeMap.get(btcode);
      String ttvalue = this.typeMap.get(ttcode);
      item.setCfirsttypecode(btvalue);
      item.setVfirsttrantype(ttvalue);
    }
    else {
      Map<String, String> codeIDMap =
          PfServiceScmUtil.getTrantypeidByCode(new String[] {
            String.valueOf(btcode), String.valueOf(ttcode)
          });
      item.setCfirsttypecode(null != codeIDMap
          && null != codeIDMap.get(String.valueOf(btcode)) ? codeIDMap
          .get(String.valueOf(btcode)) : btcode);
      item.setVfirsttrantype(null != codeIDMap
          && null != codeIDMap.get(String.valueOf(ttcode)) ? codeIDMap
          .get(String.valueOf(ttcode)) : ttcode);
      this.typeMap
          .put(
              String.valueOf(btcode),
              null != codeIDMap
                  && null != codeIDMap.get(String.valueOf(btcode)) ? codeIDMap
                  .get(String.valueOf(btcode)) : btcode);
      this.typeMap
          .put(
              String.valueOf(ttcode),
              null != codeIDMap
                  && null != codeIDMap.get(String.valueOf(ttcode)) ? codeIDMap
                  .get(String.valueOf(ttcode)) : ttcode);

    }
  }

  @Override
  protected void fillOtherInfo(OrderVO[] vos, AggregatedValueObject[] srcVOs) {
    super.fillOtherInfo(vos, srcVOs);
    // 设置EC47的单据类型ID
    this.setECBilltype(vos);

    for (OrderVO vo : vos) {
      BillHelper<OrderVO> helper = new BillHelper<OrderVO>(vo);
      new PlanArriveDate(helper)
          .setPlanArriveDate(0, helper.getItemCount() - 1);
    }
  }

  @Override
  protected void fillVatInfo(OrderVO[] vos) {
    // 2013-6-5 之前购销类型、三角贸易是在VO交换中写死的、国家信息是电商补的。现在统一由采购负责补。
    BillHelper<OrderVO>[] bills = BillHelper.declareArray(vos);

    new OrderReceiveCountrySetter(bills).setCountry();// 收货国
    new OrderSendCountrySetter(bills).setCountry();// 发货国
    new OrderTaxCountrySetter(bills).setCountry();// 报税国
    new OrderSendAddressCountrySetter(CountryType.origCountry, bills)
        .setCountry();// 原产国
    new OrderReceiveAddressCountrySetter(CountryType.destCountry, bills)
        .setCountry();// 目的国
    new BuysellflagSetter().setBodyBuysellFlag(bills);// 购销类型(设置三角贸易之前调用)
    new TriatradeflagSetter().setBodyTriatradeflag(bills);// 三角贸易

    RelationCalculate cal = new RelationCalculate();
    for (OrderVO vo : vos) {
      cal.calculate(vo, OrderItemVO.NQTUNITNUM);
      if (OrderVOUtil.isTaxPrior(vo.getHVO().getPk_org())) {
        cal.calculate(vo, OrderItemVO.NQTORIGTAXPRICE);
      }
      else {
        cal.calculate(vo, OrderItemVO.NQTORIGPRICE);
      }
    }
  }

  @Override
  protected void relationCalculate(RelationCalculate cal, OrderVO vo) {
    // 由于此时还没有主数量，调用父类的联动计算会有问题，所以直接return。
    return;
  }
}
