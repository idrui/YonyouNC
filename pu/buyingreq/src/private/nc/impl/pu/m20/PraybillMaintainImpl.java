package nc.impl.pu.m20;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.pu.m20.maintain.PraybillQueryBP;
import nc.bs.pu.m20.maintain.rule.pub.ChangeRateRule;
import nc.bs.pu.m20.maintain.rule.pub.SetPraytypeRule;
import nc.bs.pu.m20.maintain.rule.pub.SetPsnAndDeptRule;
import nc.bs.pu.m20.maintain.rule.pub.SetSctypeRule;
import nc.impl.pu.m20.action.PraybillCloseAction;
import nc.impl.pu.m20.action.PraybillDeleteAction;
import nc.impl.pu.m20.action.PraybillInsertAction;
import nc.impl.pu.m20.action.PraybillUpdateAction;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.itf.pu.m20.IPraybillMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.rule.BDirectRule;
import nc.vo.pu.m20.rule.CalculateDateRule;
import nc.vo.pu.m20.rule.CalculateNumRule;
import nc.vo.pu.m20.rule.SetAstunitRule;
import nc.vo.pu.m20.rule.SetDefaultValueRule;
import nc.vo.pu.m20.rule.SetEmployeeRule;
import nc.vo.pu.m20.rule.SetOrdertrantypeRule;
import nc.vo.pu.m20.rule.SetOrgCurrRule;
import nc.vo.pu.m20.rule.SetOrgNewVersionRule;
import nc.vo.pu.m20.rule.SetPriceRule;
import nc.vo.pu.m20.rule.SetPurchaseorgRule;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-27 ����08:35:13
 */
public class PraybillMaintainImpl implements IPraybillMaintain {

  @Override
  public PraybillVO[] closeBill(PraybillVO[] Vos) throws BusinessException {
    try {
      // ������ǲ���VO��Ϊ�˼���־����Ҫ����Ԫ������ע�����closeBillByFullVO������������closeBill����
      // ��Ϊ�����vo������ȫvo���ܼ�¼ȫ��־
      BillTransferTool<PraybillVO> tool = new BillTransferTool<PraybillVO>(Vos);
      PraybillVO[] fullVOs = tool.getClientFullInfoBill();
      PraybillVO[] returnVOs =
          NCLocator.getInstance().lookup(IPraybillMaintain.class)
              .closeBillByFullVO(fullVOs);
      return tool.getBillForToClient(returnVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public PraybillVO[] closeBillByFullVO(PraybillVO[] Vos)
      throws BusinessException {
    try {
      PraybillVO[] returnVos = new PraybillCloseAction().closeBill(Vos);
      return returnVos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public PraybillVO[] closeBillRow(PraybillVO[] Vos) throws BusinessException {
    try {
      // ��Ҫ�رյ��С�
      Set<String> closePks = new HashSet<String>();
      for (PraybillVO vo : Vos) {
        for (PraybillItemVO itemVo : vo.getBVO()) {
          closePks.add(itemVo.getPk_praybill_b());
        }
      }

      // ������ǲ���VO��Ϊ�˼���־����Ҫ����Ԫ������ע�����closeBillByFullVO������������closeBill����
      // ��Ϊ�����vo������ȫvo���ܼ�¼ȫ��־
      BillTransferTool<PraybillVO> tool = new BillTransferTool<PraybillVO>(Vos);
      PraybillVO[] fullVOs = tool.getClientFullInfoBill();
      PraybillVO[] returnVOs =
          NCLocator.getInstance().lookup(IPraybillMaintain.class)
              .closeBillRowByFullVO(fullVOs, closePks);
      return tool.getBillForToClient(returnVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public PraybillVO[] closeBillRowByFullVO(PraybillVO[] Vos,
      Set<String> closePks) throws BusinessException {
    try {
      PraybillVO[] returnVos =
          new PraybillCloseAction().closeBillRow(Vos, closePks);
      return returnVos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  /**
   * ���෽����д
   * 
   * @see nc.itf.pu.m20.IPraybillMaintain#delete(nc.vo.pu.m20.entity.PraybillVO[])
   */
  @Override
  public void delete(PraybillVO[] Vos) throws BusinessException {
    try {
      new PraybillDeleteAction().delete(Vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

  /**
   * ���෽����д
   * 
   * @see nc.itf.pu.m20.IPraybillMaintain#insert(nc.vo.pu.m20.entity.PraybillVO[])
   */
  @Override
  public PraybillVO[] insert(PraybillVO[] Vos) throws BusinessException {
    PraybillVO[] ret = null;
    try {
      PraybillInsertAction action = new PraybillInsertAction();

      ret = action.insert(Vos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return ret;
  }

  @Override
  public PraybillVO[] openBill(PraybillVO[] Vos) throws BusinessException {
    try {
      // ������ǲ���VO��Ϊ�˼���־����Ҫ����Ԫ������ע�����closeBillByFullVO������������closeBill����
      // ��Ϊ�����vo������ȫvo���ܼ�¼ȫ��־
      BillTransferTool<PraybillVO> tool = new BillTransferTool<PraybillVO>(Vos);
      PraybillVO[] fullVOs = tool.getClientFullInfoBill();
      PraybillVO[] returnVOs =
          NCLocator.getInstance().lookup(IPraybillMaintain.class)
              .openBillByFullVO(fullVOs);
      return tool.getBillForToClient(returnVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public PraybillVO[] openBillByFullVO(PraybillVO[] Vos)
      throws BusinessException {
    try {
      PraybillVO[] returnVos = new PraybillCloseAction().openBill(Vos);
      return returnVos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public PraybillVO[] openBillRow(PraybillVO[] Vos) throws BusinessException {
    try {
      Set<String> openPks = new HashSet<String>();
      for (PraybillVO vo : Vos) {
        for (PraybillItemVO itemVo : vo.getBVO()) {
          openPks.add(itemVo.getPk_praybill_b());
        }
      }

      // ������ǲ���VO��Ϊ�˼���־����Ҫ����Ԫ������ע�����closeBillByFullVO������������closeBill����
      // ��Ϊ�����vo������ȫvo���ܼ�¼ȫ��־
      BillTransferTool<PraybillVO> tool = new BillTransferTool<PraybillVO>(Vos);
      PraybillVO[] fullVOs = tool.getClientFullInfoBill();
      PraybillVO[] returnVOs =
          NCLocator.getInstance().lookup(IPraybillMaintain.class)
              .openBillRowByFullVO(fullVOs, openPks);
      return tool.getBillForToClient(returnVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public PraybillVO[] openBillRowByFullVO(PraybillVO[] Vos, Set<String> openPks)
      throws BusinessException {
    try {
      PraybillVO[] returnVos =
          new PraybillCloseAction().openBillRow(Vos, openPks);
      return returnVos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public PraybillVO[] queryPrayBills(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      return new PraybillQueryBP().query(queryScheme);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * ���෽����д
   * 
   * @see nc.itf.pu.m20.IPraybillMaintain#saveBase(nc.vo.pu.m20.entity.PraybillVO[])
   */
  @Override
  public PraybillVO[] saveBase(PraybillVO[] Vos) throws BusinessException {
    PraybillVO[] savedVos = null;
    try {
      List<PraybillVO> insertVOList = new ArrayList<PraybillVO>();
      List<PraybillVO> updateVOList = new ArrayList<PraybillVO>();
			for (int i = 0, len = Vos.length; i < len; i++) {
				if (VOStatus.NEW == (Vos[i].getHVO().getStatus())) {
					insertVOList.add(Vos[i]);
				} else {
					updateVOList.add(Vos[i]);
				}
			}
      if (insertVOList.size() != 0) {
        // ��¼��־�ô���ʽ
        return NCLocator.getInstance().lookup(IPraybillMaintain.class)
            .insert(insertVOList.toArray(new PraybillVO[insertVOList.size()]));
      }
      if (updateVOList.size() != 0) {
        // ��¼��־�ô���ʽ
        return NCLocator.getInstance().lookup(IPraybillMaintain.class)
            .update(updateVOList.toArray(new PraybillVO[updateVOList.size()]));
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return savedVos;
  }

  @Override
  public PraybillVO[] setDefaultValue(PraybillVO[] vos)
      throws BusinessException {
    try {
      // ������֯���°汾
      new SetOrgNewVersionRule().process(vos);
      // ֱ�˴���
      new BDirectRule().process(vos);
      // ��ȫ�Ƿ�ί�� ���ֵ�
      PraybillVO[] afterSplitVos = new SetSctypeRule().process(vos);
      // Ĭ��ֵ
      new SetDefaultValueRule().process(afterSplitVos);
      // ��ȫ���ұ���
      new SetOrgCurrRule().process(afterSplitVos);
      // ��ȫ�빺����
      new SetPraytypeRule().process(afterSplitVos);
      // ��ȫ�ƻ�Ա�ͼƻ�����
      new SetPsnAndDeptRule().process(afterSplitVos);

      // ��ȫ�ɹ���֯
      new SetPurchaseorgRule().process(afterSplitVos);
      // ��ȫ�ɹ�Ա
      new SetEmployeeRule().process(afterSplitVos);
      // ��ȫ��������
      new SetOrdertrantypeRule().process(afterSplitVos);
      // ��ȫ��λ
      new SetAstunitRule().process(afterSplitVos);
      // ��ȫ������
      new ChangeRateRule().process(afterSplitVos);
      // ��ȫ����
      new CalculateNumRule().process(afterSplitVos);
      // ��ȫ�������ںͽ��鶩������
      new CalculateDateRule().process(afterSplitVos);
      // ��ȫ��˰���ۺͼ�˰�ϼ�
      new SetPriceRule().process(afterSplitVos);

      this.setUFDouble(afterSplitVos);

      return afterSplitVos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * ���෽����д
   * 
   * @see nc.itf.pu.m20.IPraybillMaintain#update(nc.vo.pu.m20.entity.PraybillVO[])
   */
  @Override
  public PraybillVO[] update(PraybillVO[] Vos) throws BusinessException {
    PraybillVO[] ret = null;
    try {
      PraybillUpdateAction action = new PraybillUpdateAction();

      ret = action.update(Vos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return ret;
  }

  private void setUFDouble(PraybillVO[] vos) {
    // ����Ĭ�Ͼ��ȣ������������ǰ̨���������л�ʱ����
    for (PraybillVO vo : vos) {
      PraybillItemVO[] items = vo.getBVO();
      if (ArrayUtils.isEmpty(items)) {
        continue;
      }

      for (PraybillItemVO item : items) {
        UFDouble f = item.getNnum();
        if (null != f) {
          f = f.setScale(UFDouble.DEFAULT_POWER, UFDouble.ROUND_HALF_UP);
          item.setNnum(f);
        }

        f = item.getNastnum();
        if (null != f) {
          f = f.setScale(UFDouble.DEFAULT_POWER, UFDouble.ROUND_HALF_UP);
          item.setNastnum(f);
        }
      }
    }
  }

  @Override
  public PraybillVO[] setDefaultValueForM4B32(PraybillVO[] vos)
      throws BusinessException {
    try {
      // ��ȫ��λ��
      new SetOrgCurrRule().process(vos);
      // ��ȫ����ɹ���֯
      new SetPurchaseorgRule().process(vos);
      // ��ȫ�ɹ�Ա
      new SetEmployeeRule().process(vos);
      // ���õ�λ�������ʣ��̶�������
      new SetAstunitRule().process(vos);
      // ��������
      new CalculateNumRule().process(vos);
      // ��ȫ�������ںͽ��鶩������
      new CalculateDateRule().process(vos);
      // ��ȫ��˰���ۺͼ�˰�ϼ�
      new SetPriceRule().process(vos);
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
