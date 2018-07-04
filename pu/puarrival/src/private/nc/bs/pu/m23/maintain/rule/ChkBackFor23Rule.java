package nc.bs.pu.m23.maintain.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m23.pubquery.IArrivePubQuery;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * ���ڻ���ԭ�������˻������������Ƿ��ܹ��˻�
 * @scene
 * 
 * @param
 * ��
 *
 * @since 6.3
 * @version 2012-8-14 ����03:45:43
 * @author lixyp
 */

public class ChkBackFor23Rule implements IRule<ArriveVO> {

  private List<String> checkResults = new ArrayList<String>();

  @Override
  public void process(ArriveVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    ArriveItemVO[] backItemVos = this.querySourceArriveItems(vos);
    if (ArrayUtils.isEmpty(backItemVos)) {
      // �����˻������ǻ���ԭ�������˻��ĳ�����
      return;
    }

    for (ArriveItemVO itemVo : backItemVos) {
      // �豸��Ƭ���
      this.checkFA(itemVo);
      // ת�̼��
      this.checkTransasset(itemVo);
      // �����⡣
      this.checkPurchaseIn(itemVo);
      // ����������
      this.checkUrgenLetGo(itemVo);
    }

    // ƴ���쳣��Ϣ��
    if (!this.checkResults.isEmpty()) {
      StringBuffer buffer = new StringBuffer();
      for (String result : this.checkResults) {
        buffer.append(result);
        buffer.append("\n");
      }

      ExceptionUtils.wrappBusinessException(buffer.toString());
    }
  }

  /**
   * ����Ƿ��������豸��Ƭ
   * 
   * @param itemVo ����������VO
   */
  private void checkFA(ArriveItemVO itemVo) {
    if (itemVo.getBfaflag() != null
        && UFBoolean.TRUE.equals(itemVo.getBfaflag())) {
      this.checkResults.add(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004040_0", "04004040-0196", null, new String[] {
            itemVo.getCrowno()
          })/*
             * @res
             * "��{0}���Ѿ������豸��Ƭ���������˻���"
             */);
    }
  }

  /**
   * ����Ƿ���ڲɹ���⡣
   * 
   * @param itemVo ����������VO
   */
  private void checkPurchaseIn(ArriveItemVO itemVo) {
    if (MathTool.greaterThan(itemVo.getNaccumstorenum(), UFDouble.ZERO_DBL)) {
      this.checkResults.add(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004040_0", "04004040-0198", null, new String[] {
            itemVo.getCrowno()
          })/*
             * @res
             * "��{0}�е������ɲɹ���ⵥ���������˻���"
             */);
    }
  }

  /**
   * ����Ƿ��Ѿ�ת�̶��ʲ�
   * 
   * @param itemVo ����������VO
   */
  private void checkTransasset(ArriveItemVO itemVo) {
    if (itemVo.getBtransasset() != null
        && UFBoolean.TRUE.equals(itemVo.getBtransasset())) {
      this.checkResults.add(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004040_0", "04004040-0197", null, new String[] {
            itemVo.getCrowno()
          })/*
             * @res
             * "��{0}���Ѿ�ת�̶��ʲ����������˻���"
             */);
    }
  }

  /**
   * ����������
   * 
   * @param itemVo ����������VO
   */
  private void checkUrgenLetGo(ArriveItemVO itemVo) {
    // �ϸ�����
    UFDouble nelignum = itemVo.getNelignum();
    // ���ϸ�����
    UFDouble nnotelignum = itemVo.getNnotelignum();
    // �ϸ����� + ���ϸ����� = 0�����ۼƽ�����������������0��˵�������ɽ������е�����û���ʼ���ɣ������˻���
    // ��������״̬��־�ڴ˲����ã��ñ�־���ڽ������е�������ʱ����Ϊtrue�ġ������޷����ָո����ɽ������е����ʼ���������ֳ�����
    if (MathTool.isZero(MathTool.add(nelignum, nnotelignum))
        && MathTool.greaterThan(itemVo.getNaccumletgonum(), UFDouble.ZERO_DBL)) {
      this.checkResults.add(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004040_0", "04004040-0200", null, new String[] {
            itemVo.getCrowno()
          })/*
             * @res
             * "��{0}���Ѿ������������е������ʼ�δ��ɣ��������˻���"
             */);
    }
  }

  /**
   * ��ѯԭ�������������ݡ�
   * 
   * @param arriveVos
   * @return
   */
  private ArriveItemVO[] querySourceArriveItems(ArriveVO[] arriveVos) {
    List<String> bids = new ArrayList<String>();
    try {

      for (ArriveVO arriveVo : arriveVos) {
        // ֻ����˻�����
        if (!arriveVo.getHVO().getBisback().booleanValue()) {
          continue;
        }
        for (ArriveItemVO itemVo : arriveVo.getBVO()) {
          // ֻ������ԭ�������˻��������
          if (itemVo.getCsourcearrivebid() == null) {
            continue;
          }
          bids.add(itemVo.getCsourcearrivebid());
        }
      }

      if (bids.isEmpty()) {
        return null;
      }

      IArrivePubQuery arriveQuery =
          NCLocator.getInstance().lookup(IArrivePubQuery.class);
      return arriveQuery
          .queryItemVOByBids(bids.toArray(new String[bids.size()]));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
}
