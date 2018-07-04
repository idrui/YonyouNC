package nc.vo.pu.m4t.vochange;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.bd.userdef.UserDefCheckUtils;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pf.change.IChangeVOAdjust;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.rule.CostregionSetter;
import nc.vo.pu.m4t.rule.DbilldateValue;
import nc.vo.pu.m4t.rule.InitialEstVatValue;
import nc.vo.pu.m4t.rule.OrganizationValue;
import nc.vo.pu.m4t.rule.RelationCalculate;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;

public class InitialEstChangeVOAdjust implements IChangeVOAdjust {

  @Override
  public AggregatedValueObject adjustAfterChange(AggregatedValueObject srcVO,
      AggregatedValueObject destVO, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    // ������������
    return this.batchAdjustAfterChange(new AggregatedValueObject[] {
      srcVO
    }, new AggregatedValueObject[] {
      destVO
    }, adjustContext)[0];
  }

  @Override
  public AggregatedValueObject adjustBeforeChange(AggregatedValueObject srcVO,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    // ������������
    return this.batchAdjustBeforeChange(new AggregatedValueObject[] {
      srcVO
    }, adjustContext)[0];
  }

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    InitialEstVO[] vos = (InitialEstVO[]) ArrayUtil.convertArrayType(destVOs);
    // �Զ�������
    UserDefCheckUtils.check(vos, new Class[] {
      InitialEstHeaderVO.class, InitialEstItemVO.class
    });
    // UFDate date = AppContext.getInstance().getBusiDate();
    IKeyValue[] keyValues = new IKeyValue[vos.length];
    for (int j = 0; j < vos.length; ++j) {
      InitialEstVO vo = vos[j];
      InitialEstItemVO[] itemVOs = vo.getItems();
      int[] rows = new int[itemVOs.length];
      for (int i = 0; i < itemVOs.length; ++i) {
        rows[i] = i;
      }
      BillHelper<InitialEstVO> helper = new BillHelper<InitialEstVO>(vo);
      keyValues[j] = helper;
      // ��֯
      new OrganizationValue(helper).setFinanceOrg(rows);
      // �������
      new DbilldateValue(helper).setDbilldate();
      // ������Դ���������ж��Ƿ����������������������
      this.calBySourceNum(vo);
      // ��λ�Һͻ���
      this.processCurrency(vo);
      // �ɱ���
      new CostregionSetter(helper).setCostregion();
    }
    // ����������˰
    new InitialEstVatValue().setOppTax(keyValues);
    return vos;
  }

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    // ����ǰ����
    return srcVOs;
  }

  /**
   * ������Դ���������ж��Ƿ����������������������
   * 
   * @param vo
   */
  private void calBySourceNum(InitialEstVO vo) {
    List<InitialEstItemVO> calItems = new ArrayList<InitialEstItemVO>();

    for (InitialEstItemVO item : vo.getItems()) {
      if (MathTool.equals(item.getNnum(), item.getNsourcenum())) {
        continue;
      }
      calItems.add(item);
    }
    // һ��ȫ������������Ҫ��������
    if (calItems.size() == 0) {
      return;
    }
    InitialEstVO newVO = new InitialEstVO();
    newVO.setParent(vo.getHeader());
    newVO
        .setChildrenVO(calItems.toArray(new InitialEstItemVO[calItems.size()]));
    RelationCalculate cal = new RelationCalculate();
    cal.setForceFixChgRate(true);
    cal.calculate(newVO, InitialEstItemVO.NNUM);
  }

  private void calculate(InitialEstVO vo) {
    String pk_org = vo.getHeader().getPk_org();
    boolean isTax = this.isTaxPricePriorToPrice(pk_org);
    RelationCalculate cal = new RelationCalculate();
    cal.setForceFixChgRate(true);
    if (isTax) {
      cal.calculate(vo, InitialEstItemVO.NASTORIGTAXPRICE);
    }
    else {
      cal.calculate(vo, InitialEstItemVO.NASTORIGPRICE);
    }
  }

  private boolean isTaxPricePriorToPrice(String pk_org) {
    boolean flag = true;
    PricePriority pricePriority = PUSysParamUtil.getPO28(pk_org);
    if (!PricePriority.TAXPRICE_PRIOR_TO_PRICE.equals(pricePriority)) {
      flag = false;
    }
    return flag;
  }

  /**
   * ת����λ�ҵĴ��������Դ�����ı�λ���뵱ǰ��֯��λ�Ҳ�һ�£���Ҫ�������ñ��ң����Ҹ���ԭ�����㱾��
   * 
   * @param vo
   */
  private void processCurrency(InitialEstVO vo) {
    // ���ñ���
    String ccurrencyid = vo.getHeader().getCcurrencyid();
    String pk_org = vo.getHeader().getPk_org();
    String queryCurrencyid = OrgUnitPubService.queryOrgCurrByPk(pk_org);
    if (queryCurrencyid.equals(ccurrencyid)) {
      // ����һ�£����账��
      return;
    }
    UFDate date = AppContext.getInstance().getBusiDate();
    vo.getHeader().setCcurrencyid(ccurrencyid);
    String corigcurrencyid = vo.getHeader().getCorigcurrencyid();
    UFDouble rate =
        CurrencyRate.getCurrencySellRateByOrg(pk_org, corigcurrencyid,
            ccurrencyid, date);
    vo.getHeader().setNexchangerate(rate);
    // ���ֲ�һ�£�����ԭ�����㱾�ҵ���ϵ��
    this.calculate(vo);

  }
}
