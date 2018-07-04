package nc.bs.pu.m4201;

import java.util.ArrayList;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.pu.m27.rule.ContranctInfoRedundanceRule;
import nc.bs.pu.m27.rule.FillNormalPurFlagRule;
import nc.bs.pu.m27.rule.FillRowAffectCostFlagRule;
import nc.bs.pu.m27.rule.StockFIDuplicateCheckRule;
import nc.bs.pu.m4201.rule.CalCostPriceRule;
import nc.bs.pu.m4201.rule.ChangeDataForITAfterRule;
import nc.bs.pu.m4201.rule.FillRowAffectPCCostFlagRule;
import nc.bs.pub.pf.PfUtilTools;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����������Ϣ��BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-8-27 ����04:46:05
 */
public class StockFinanceInsertBP {
  public void insert(PurchaseInVO[] purchaseinVos) {

    // ���˵�����Ҫ�ݹ��������
    PurchaseInVO[] vosFiltered = this.filterRow(purchaseinVos);

    if (ArrayUtils.isEmpty(vosFiltered)) {
      return;
    }

    // ִ��VO��������ÿ������VO
    PurchaseinFIVO[] voaStock = this.runChangeData(vosFiltered);
    try {
      // ��ͨ�ɹ����Ǽ��ɱ�־
      new FillNormalPurFlagRule<PurchaseinFIVO>().process(voaStock);
      // �Ƿ�Ӱ��ɱ���־
      new FillRowAffectCostFlagRule<PurchaseinFIVO>().process(voaStock);
      // �Ƿ�Ӱ���������ĳɱ���־
      new FillRowAffectPCCostFlagRule().process(voaStock);
      // ���ݼ�飬����Ӱ��ɱ���־֮��
      new StockFIDuplicateCheckRule<PurchaseinFIVO>().process(voaStock);
      // �����ͬ��
      new ContranctInfoRedundanceRule<PurchaseinFIVO>(
          PurchaseinFIItemVO.VORDERTRANTYPECODE, POBillType.Order)
          .process(voaStock);

      // ��ⵥ�ǳɱ�����
      new CalCostPriceRule().process(voaStock);

      // �������ǩ�ֱ���ʱvo�����������
      new ChangeDataForITAfterRule(vosFiltered).process(voaStock);

      List<PurchaseinFIHeaderVO> headList =
          new ArrayList<PurchaseinFIHeaderVO>();
      List<PurchaseinFIItemVO> itemList = new ArrayList<PurchaseinFIItemVO>();
      for (PurchaseinFIVO vo : voaStock) {
        headList.add(vo.getParentVO());
        for (PurchaseinFIItemVO itemVO : vo.getChildrenVO()) {
          itemList.add(itemVO);
        }
      }
      PurchaseinFIHeaderVO[] headvos =
          headList.toArray(new PurchaseinFIHeaderVO[headList.size()]);
      PurchaseinFIItemVO[] itemvos =
          itemList.toArray(new PurchaseinFIItemVO[itemList.size()]);
      BaseDAO dao = new BaseDAO();
      dao.insertVOArrayWithPK(headvos);
      dao.insertVOArrayWithPK(itemvos);
      // for (int i = 0; i < voaStock.length; i++) {
      // dao.insertVOWithPK(voaStock[i].getParentVO());
      // dao.insertVOArrayWithPK(voaStock[i].getChildrenVO());
      // }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * �����������������˵�VMI����Ʒ��ⵥ�У��ɹ�������ݹ���������Щ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos ��ⵥVO����
   * @return ���˺����ⵥVO����
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-8-27 ����04:58:20
   */
  private PurchaseInVO[] filterRow(PurchaseInVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }
    List<PurchaseInVO> voList = new ArrayList<PurchaseInVO>();
    for (PurchaseInVO vo : vos) {
      PurchaseInVO filterVO = new PurchaseInVO();
      filterVO.setParent(vo.getHead());
      List<PurchaseInBodyVO> bodyList = new ArrayList<PurchaseInBodyVO>();
      PurchaseInBodyVO[] bodys = vo.getBodys();
      if (ArrayUtils.isEmpty(bodys)) {
        continue;
      }
      for (PurchaseInBodyVO body : bodys) {
        if (StringUtil.isEmptyWithTrim(body.getCvmivenderid())
            && UFBoolean.FALSE.equals(body.getFlargess())) { // ����Ʒ
          bodyList.add(body);
        }
      }
      if (0 == bodyList.size()) {
        continue;
      }
      filterVO.setChildrenVO(bodyList.toArray(new PurchaseInBodyVO[bodyList
          .size()]));
      voList.add(filterVO);
    }
    return voList.toArray(new PurchaseInVO[voList.size()]);
  }

  private PurchaseinFIVO[] runChangeData(PurchaseInVO[] vosFiltered) {
    PurchaseinFIVO[] voaStock = new PurchaseinFIVO[vosFiltered.length];
    try {
      // �˴������ݵĽ������Ϳ��ܲ�ͬ����˲���ʹ��ͳһ�Ľ������ͽ��н�����
      for (int i = 0; i < vosFiltered.length; i++) {
        voaStock[i] =
            (PurchaseinFIVO) PfUtilTools.runChangeData(
                ICBillType.PurchaseIn.getCode(),
                POBillType.PurchaseInFinance.getCode(), vosFiltered[i]);
        voaStock[i].getParent().setAttributeValue("dr", Integer.valueOf(0));
        for (int j = 0; j < voaStock[i].getChildrenVO().length; j++) {
          // �������
          voaStock[i].getChildrenVO()[j].setPk_stockps(voaStock[i]
              .getParentVO().getPk_stockps());
          voaStock[i].getChildrenVO()[j].setAttributeValue("dr",
              Integer.valueOf(0));
        }
      }
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return voaStock;
  }
}
