package nc.vo.pu.m21.vochange;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.query.supplier.SupplierInfo;
import nc.vo.pu.m21.rule.CurrencyAndExchangerate;
import nc.vo.pu.m21.rule.DefaultTaxRateSetter;
import nc.vo.pu.m21.rule.DirectFinanceorgValue;
import nc.vo.pu.m21.rule.OrganizationDefaultValue;
import nc.vo.pu.m21.rule.RelationCalculate;
import nc.vo.pu.m21.rule.SupplierDefaultValue;
import nc.vo.pu.m21.rule.margin.OrderMarginRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-6-22 上午11:13:54
 * @author wuxla
 */

public class ChangeVOAdjust20To21 extends AbstractOrderChangeVOAdjust {

  @Override
  protected void fillInformation(BillHelper<OrderVO> helper, int[] rows) {
    super.fillInformation(helper, rows);
    // 2012-7-4，王印芬、赵玉行、吴小亮确认请购单走表体的订单类型，如果为空，也不会重新寻找。
    // 补充默认值税率
    new DefaultTaxRateSetter(helper).setDefaultTaxtRate(rows);
    // 为直运补充直运仓库
    UFBoolean direct = (UFBoolean) helper.getHeadValue(OrderHeaderVO.BDIRECT);
    if (UFBoolean.TRUE.equals(direct)) {
      this.fillStordocForDirect(helper);
    }

  }

  @Override
  protected OrderVO[] fillInformation(OrderVO[] vos,
      AggregatedValueObject[] srcVOs) {
    if (ArrayUtils.isEmpty(vos) || ArrayUtils.isEmpty(srcVOs)) {
      return null;
    }
    this.setDept(vos);
    PraybillVO[] prayVOs = ArrayUtil.convertArrayType(srcVOs);
    this.setReqstoorg(vos, prayVOs);
    DirectFinanceorgValue orgValue = new DirectFinanceorgValue(prayVOs, vos);
    orgValue.setDirectFinanceorg();
    return super.fillInformation(vos, srcVOs);
  }

  @Override
  protected void fillOrgSuppValue(BillHelper<OrderVO> helper, int[] rows) {
    // 补充组织相关的信息
    OrganizationDefaultValue orgValue = new OrganizationDefaultValue(helper);
    orgValue.setClear(false);
    orgValue.setDefaultOrganizationValue(rows);
    // 获得供应商信息
    SupplierInfo supplier = this.getSupplierInfo(helper);

    // 设置供应商的默认值
    SupplierDefaultValue vendorDefaultValue = new SupplierDefaultValue(helper);
    vendorDefaultValue.setDefaultValueNotClear(supplier);

    // 补充本位币和汇率的相关信息
    new CurrencyAndExchangerate(helper).setCurrencyAndExchangeRate(rows);
  }

  @Override
  protected void fillOtherInfo(OrderVO[] vos, AggregatedValueObject[] srcVOs) {
    // 根据V61需求变更，20到21不再进行采购计量单位转换
    // AutoSwitchPUAssistUnitRule unit = new AutoSwitchPUAssistUnitRule();
    // unit.process(vos);
  }

  private void setReqstoorg(OrderVO[] vos, PraybillVO[] prayVOs) {
  //库存计划：来源是请购单的需求库存组织、收获库存组织默认取原始需求库存组织，无取主组织。
    if (ArrayUtils.isEmpty(prayVOs) || ArrayUtils.isEmpty(vos)) {
        return;
      }
    
    Map<String,String> orgs= new HashMap<String,String>();
    Map<String,String> orgs_v= new HashMap<String,String>();
    for(PraybillVO vo: prayVOs) {
      PraybillItemVO[] items =vo.getBVO();
      for(PraybillItemVO item: items) {
        if(!orgs.containsKey(item.getPk_praybill_b())&&item.getPk_reqstoorg()!=null){
          orgs.put(item.getPk_praybill_b(),item.getPk_reqstoorg());
          orgs_v.put(item.getPk_praybill_b(),item.getPk_reqstoorg_v());
        }
      }
    }
    for(OrderVO vo: vos) {
      OrderItemVO[] items =vo.getBVO();
      for(OrderItemVO item: items) {
        if(orgs.containsKey(item.getCfirstbid())){
          item.setPk_arrvstoorg(orgs.get(item.getCfirstbid()));
          item.setPk_arrvstoorg_v(orgs_v.get(item.getCfirstbid()));
          item.setPk_reqstoorg(orgs.get(item.getCfirstbid()));
          item.setPk_reqstoorg_v(orgs_v.get(item.getCfirstbid()));
        }
      }
    }
  }

  @Override
  protected void processMargin(OrderVO[] vos, AggregatedValueObject[] srcVos) {
    new OrderMarginRule(POBillType.PrayBill.getCode(), srcVos).process(vos);
  }

  @Override
  protected void relationCalculate(RelationCalculate cal, OrderVO vo) {
    Map<String, UFDouble> map = new HashMap<String, UFDouble>();
    for (OrderItemVO item : vo.getBVO()) {
      map.put(item.getCsourcebid(), item.getNorigtaxprice());
    }
    // 先数量联动计算
    cal.calculate(vo, OrderItemVO.NNUM);
    for (OrderItemVO item : vo.getBVO()) {
      item.setNorigtaxprice(map.get(item.getCsourcebid()));
    }
    // 根据带入的主含税单价计算（请购单只有一个含税单价，对到下游订单的主含税单价）
    cal.calculate(vo, OrderItemVO.NORIGTAXPRICE);
  }

  @Override
  protected void setOrderPrice(OrderVO[] vos) {
    this.setPriceByParaPO16(vos);
  }

}
