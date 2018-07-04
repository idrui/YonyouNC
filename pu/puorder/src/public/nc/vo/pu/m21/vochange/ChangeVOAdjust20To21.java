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
 * @version 2011-6-22 ����11:13:54
 * @author wuxla
 */

public class ChangeVOAdjust20To21 extends AbstractOrderChangeVOAdjust {

  @Override
  protected void fillInformation(BillHelper<OrderVO> helper, int[] rows) {
    super.fillInformation(helper, rows);
    // 2012-7-4����ӡ�ҡ������С���С��ȷ���빺���߱���Ķ������ͣ����Ϊ�գ�Ҳ��������Ѱ�ҡ�
    // ����Ĭ��ֵ˰��
    new DefaultTaxRateSetter(helper).setDefaultTaxtRate(rows);
    // Ϊֱ�˲���ֱ�˲ֿ�
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
    // ������֯��ص���Ϣ
    OrganizationDefaultValue orgValue = new OrganizationDefaultValue(helper);
    orgValue.setClear(false);
    orgValue.setDefaultOrganizationValue(rows);
    // ��ù�Ӧ����Ϣ
    SupplierInfo supplier = this.getSupplierInfo(helper);

    // ���ù�Ӧ�̵�Ĭ��ֵ
    SupplierDefaultValue vendorDefaultValue = new SupplierDefaultValue(helper);
    vendorDefaultValue.setDefaultValueNotClear(supplier);

    // ���䱾λ�Һͻ��ʵ������Ϣ
    new CurrencyAndExchangerate(helper).setCurrencyAndExchangeRate(rows);
  }

  @Override
  protected void fillOtherInfo(OrderVO[] vos, AggregatedValueObject[] srcVOs) {
    // ����V61��������20��21���ٽ��вɹ�������λת��
    // AutoSwitchPUAssistUnitRule unit = new AutoSwitchPUAssistUnitRule();
    // unit.process(vos);
  }

  private void setReqstoorg(OrderVO[] vos, PraybillVO[] prayVOs) {
  //���ƻ�����Դ���빺������������֯���ջ�����֯Ĭ��ȡԭʼ��������֯����ȡ����֯��
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
    // ��������������
    cal.calculate(vo, OrderItemVO.NNUM);
    for (OrderItemVO item : vo.getBVO()) {
      item.setNorigtaxprice(map.get(item.getCsourcebid()));
    }
    // ���ݴ��������˰���ۼ��㣨�빺��ֻ��һ����˰���ۣ��Ե����ζ���������˰���ۣ�
    cal.calculate(vo, OrderItemVO.NORIGTAXPRICE);
  }

  @Override
  protected void setOrderPrice(OrderVO[] vos) {
    this.setPriceByParaPO16(vos);
  }

}
