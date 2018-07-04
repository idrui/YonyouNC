package nc.vo.pu.pub.rule;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.pubitf.pu.m422x.invp.inv9.IReWrite422xForInv9;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              �빺����д�����������뵥
 * @scene
 *        �빺��ɾ�����޸ģ������������뵥ɾ��������
 * @param String pk_billtype ��������
 * @since 6.3
 * @version 2014-10-21 ����9:29:56
 * @author yanxm5
 */
public class WriteBack422xRule<E extends AbstractBill> implements
    ICompareRule<E> {

  private String pk_billtype;

  public WriteBack422xRule(String pk_billtype) {
    this.pk_billtype = pk_billtype;
  }

  @Override
  public void process(E[] vos, E[] originVOs) {
    if (ArrayUtils.isEmpty(originVOs)) {
      // ����
      return;
    }
    else if (ArrayUtils.isEmpty(vos)
        || VOStatus.DELETED == vos[0].getParent().getStatus()) {
      // ɾ��
      this.writeback422XWhenModify(null, originVOs);// ����������ɵľ��������ɾ��������������д
    }
    else {
      // �޸�
      this.writeback422XWhenModify(vos, originVOs);
    }
  }

  /**
   * ��bill ת���� itemvoMap
   * 
   * @param vos
   * @return itemvoMap
   */
  private Map<String, ISuperVO> processBilltoItemMapUtil(E[] vos) {
    if (vos == null) {
      return null;
    }

    Map<String, ISuperVO> itemMap = new HashMap<String, ISuperVO>();
    IVOMeta meta = vos[0].getMetaData().getChildren()[0];
    for (E bill : vos) {
      ISuperVO[] itemVOs = bill.getChildren(meta);
      if (ArrayUtils.isEmpty(itemVOs)) {
        continue;
      }
      for (ISuperVO itemVO : itemVOs) {
        itemMap.put(itemVO.getPrimaryKey(), itemVO);
      }
    }
    return itemMap;
  }

  /**
   * ��֯��д�������
   * 
   * @param vos
   * @return ��д��������ids
   */
  private Map<String, UFDouble> processReWriteParams(E[] vos, E[] originVOs) {
    // ��дǰ���ݴ���
    Map<String, UFDouble> praynum = new HashMap<String, UFDouble>();

    Map<String, ISuperVO> itemvos = this.processBilltoItemMapUtil(vos);

    IVOMeta meta = originVOs[0].getMetaData().getChildren()[0];
    for (E bill : originVOs) {
      ISuperVO[] itemVOs = bill.getChildren(meta);
      if (ArrayUtils.isEmpty(itemVOs)) {
        continue;
      }
      for (ISuperVO itemVO : itemVOs) {
        UFDouble oriNum =
            (UFDouble) itemVO.getAttributeValue(StoreReqAppItemVO.NNUM);
        UFDouble subNum =
            itemvos == null || itemvos.get(itemVO.getPrimaryKey()) == null ? UFDouble.ZERO_DBL
                : (UFDouble) itemvos.get(itemVO.getPrimaryKey())
                    .getAttributeValue(StoreReqAppItemVO.NNUM);
        praynum.put(itemVO.getPrimaryKey(), MathTool.sub(subNum, oriNum));
      }
    }
    return praynum;
  }

  /*
   * ����������ɵľ��������ɾ�����޸�������������������д����
   */
  private void writeback422XWhenModify(E[] vos, E[] originVOs) {
    try {
      IReWrite422xForInv9 reWrite422xFor20 =
          NCLocator.getInstance().lookup(IReWrite422xForInv9.class);

      reWrite422xFor20.reWriteReqForDelete(
          this.processReWriteParams(vos, originVOs), this.pk_billtype);

    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }
}
