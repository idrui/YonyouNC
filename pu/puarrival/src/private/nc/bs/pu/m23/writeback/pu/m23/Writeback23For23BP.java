package nc.bs.pu.m23.writeback.pu.m23;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.pubitf.pu.m23.pubquery.IArrivePubQuery;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * ���ڵ������˻��Ļ�д�������ڻ�д�������ۼ��˻���������
 * 
 * @since 6.0
 * @version 2012-8-11 ����09:23:59
 * @author lixyp
 */
public class Writeback23For23BP {

  private List<RewritePara> rewriteParas = null;

  /**
   * ��д������⹫���Ľӿڷ�����
   * 
   * @param paras ��д����
   */
  public void writeBack(List<RewritePara> paras) {
    try {
      if (paras == null || paras.isEmpty()) {
        return;
      }
      this.rewriteParas = paras;

      // ������Դ����������ID��ѯԭ����������VO��������������
      ArriveVO[] vos = this.queryOriginVos();
      if (ArrayUtils.isEmpty(vos)) {
        return;
      }

      ArriveItemVO[] itemVos = this.getUpdatedItemVos(vos);

      // �������ݿ⡣
      VOUpdate<ArriveItemVO> bo = new VOUpdate<ArriveItemVO>();
      bo.update(itemVos, new String[] {
        ArriveItemVO.NACCUMBACKNUM
      });
    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
  }

  private void checkTs(Map<String, UFDateTime> tsChkMap, ArriveVO[] vos) {
    UFDateTime time = null;
    for (ArriveVO vo : vos) {
      time = tsChkMap.get(vo.getHVO().getPk_arriveorder());
      if (time != null && !time.equals(vo.getHVO().getTs())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004040_0", "04004040-0206")/*
                                                                     * @res
                                                                     * "���ֲ����������²���"
                                                                     */);
      }
      for (ArriveItemVO itemVo : vo.getBVO()) {
        time = tsChkMap.get(itemVo.getPk_arriveorder_b());
        if (time != null && !time.equals(itemVo.getTs())) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004040_0", "04004040-0206")/*
                                                                       * @res
                                                                       * "���ֲ����������²���"
                                                                       */);
        }
      }
    }
  }

  /**
   * ��ȡ����VO���飬ͬʱ���±����ֵ��
   * 
   * @param vos ��Դ������VO����
   * @param paras ��д����
   * @return ����������VO����
   */
  private ArriveItemVO[] getUpdatedItemVos(ArriveVO[] vos) {
    List<ArriveItemVO> itemVos = new ArrayList<ArriveItemVO>();
    for (ArriveVO vo : vos) {
      for (ArriveItemVO itemVo : vo.getBVO()) {
        for (RewritePara rewritePara : this.rewriteParas) {
          if (itemVo.getPk_arriveorder_b().equals(rewritePara.getCsrcbid())) {
            UFDouble wbNum = rewritePara.getNnum();
            // ��Ϊ�˻������Ǹ���������������ȡһ�·���
            wbNum = MathTool.oppose(wbNum);

            UFDouble naccumBackNum =
                MathTool.add(itemVo.getNaccumbacknum(), wbNum);
            if (MathTool.lessThan(naccumBackNum, UFDouble.ZERO_DBL)) {
              String message =
                  nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                      "4004040_0", "04004040-0195")/* @res "�ۼ��˻������ѳ���������ԭ��������" */;
              ExceptionUtils.wrappBusinessException(message);
            }

            itemVo.setNaccumbacknum(naccumBackNum);
            itemVos.add(itemVo);
          }
        }
      }
    }

    return itemVos.toArray(new ArriveItemVO[itemVos.size()]);
  }

  /**
   * ���ݻ�д�����е���Դ����ID�����õ�������ѯ�ӿڣ���ѯ��Դ��������
   * 
   * @return ��Դ������
   * @throws BusinessException
   */
  private ArriveVO[] queryOriginVos() {
    try {
      Map<String, UFDateTime> tsChkMap = new HashMap<String, UFDateTime>();
      List<String> bidList = new ArrayList<String>();
      for (RewritePara rewritePara : this.rewriteParas) {
        tsChkMap.put(rewritePara.getCsrcid(), rewritePara.getSrcTS());

        if (rewritePara.getCsrcbid() != null) {
          tsChkMap.put(rewritePara.getCsrcbid(), rewritePara.getSrcbTS());
          bidList.add(rewritePara.getCsrcbid());
        }
      }
      if (bidList.isEmpty()) {
        return null;
      }

      String[] bids = bidList.toArray(new String[bidList.size()]);
      LockOperator lock = new LockOperator();
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
              "04004040-0205")/* @res "����Դ����������ʧ��" */;
      lock.lock(bids, message);

      IArrivePubQuery arriveQuery =
          NCLocator.getInstance().lookup(IArrivePubQuery.class);
      ArriveVO[] vos = arriveQuery.queryAggVOByBids(bids);

      this.checkTs(tsChkMap, vos);
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    return null;
  }

}
