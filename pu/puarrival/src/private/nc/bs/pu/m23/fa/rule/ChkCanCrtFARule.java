package nc.bs.pu.m23.fa.rule;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialStockClassPubService;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 * ������Ҫ������¹��ܣ�
 * ���δ�����ʲ��������������ʲ���
 * ��ԴΪί��ĵ����������������ʲ���
 * @scene
 * �����ʲ���Ƭʱ
 * @param
 * ��
 *
 * @since 6.3
 * @version 2010-5-7 ����11:26:00
 * @author hanbin
 */


public class ChkCanCrtFARule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] voArray) {
    // ����ʲ��Ƿ�����
    this.chkAIMModuleEnable();

    for (ArriveVO aggVO : voArray) {
      // ����Ƿ���Դ��ί��
      this.chkIsFromSC(aggVO);
      // ����Ƿ����������ε���
      this.chkIsHaveDownBill(aggVO);
    }

    // ����ջ��ֿ���Ϊ�ʲ���
    // �ʲ��������Ѿ�ȥ��
    // this.chkStoreIsCapital(voArray);
  }

  private void chkAIMModuleEnable() {
    boolean isEnable = false;
    isEnable = SysInitGroupQuery.isAIMEnabled();
    if (!isEnable) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0182")/*
                                                                   * @res
                                                                   * "�ʲ���Ϣ����ģ��δ����,�޷������豸��Ƭ!"
                                                                   */);
    }
  }

  private void chkIsFromSC(ArriveVO aggVO) {
    boolean isFromSC = ArrivePublicUtil.isArriveFromSC(aggVO);
    if (isFromSC) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
              "04004040-0067")/* @res "��֧����ԴΪί��ĵ����������豸��Ƭ��" */;
      ExceptionUtils.wrappBusinessException(msg);
    }
  }

  private void chkIsHaveDownBill(ArriveVO vo) {
    ArriveItemVO[] items = vo.getBVO();
    for (ArriveItemVO item : items) {
      if (MathTool.nvl(item.getNaccumstorenum()).doubleValue() > 0) {
        String msg =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0068")/* @res "�Ѿ�������ⵥ���������������豸�ʲ���Ƭ��" */;
        ExceptionUtils.wrappBusinessException(msg);
      }
      if (MathTool.nvl(item.getNaccumreplnum()).doubleValue() > 0) {
        String msg =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0069")/* @res "�Ѿ����ɲ����������������������豸��Ƭ��" */;
        ExceptionUtils.wrappBusinessException(msg);
      }
      if (MathTool.nvl(item.getNaccumletgonum()).doubleValue() > 0) {
        String msg =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0070")/* @res "�Ѿ����ɽ������е����������������豸��Ƭ��" */;
        ExceptionUtils.wrappBusinessException(msg);
      }
      // modify by liugxa 2011��7��8��11:21:02
      if (MathTool.nvl(item.getNaccumchecknum()).doubleValue() > 0
          && this.getIscheckStock(item)) {
        String msg =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0071")/* @res "�Ѿ����ɱ��쵥���������������豸��Ƭ��" */;
        ExceptionUtils.wrappBusinessException(msg);

      }
    }
  }

  // private void chkStoreIsCapital(ArriveVO[] vos) {
  // // <�ջ��ֿ�����,�ֿ�VO>
  // Map<String, StordocVO> storePKToStoreVOMap = null;
  //
  // // �ջ��ֿ���������
  // String key = ArriveItemVO.PK_RECEIVESTORE;
  // String[] stores =
  // (String[]) AggVOUtil.getDistinctItemFieldArray(vos, key, String.class);
  // try {
  // // ������ѯ�ջ��ֿ��Ƿ��ʲ���
  // String[] fields = new String[1];
  // fields[0] = StordocVO.ISCAPITALSTOR;
  // StordocVO[] sroreVOArray =
  // StordocPubService.queryStordocInfoByPks(stores, fields);
  // if (!ArrayUtils.isEmpty(sroreVOArray)) {
  // storePKToStoreVOMap = CirVOUtil.createKeyVOMap(sroreVOArray);
  // }
  // }
  // catch (BusinessException ex) {
  // ExceptionUtils.wrappException(ex);
  // }
  // if (storePKToStoreVOMap == null) {
  // return;
  // }
  //
  // StringBuffer errorLins = new StringBuffer();
  // ArriveItemVO[] items = AggVOUtil.getCombinItemVOs(vos);
  // for (int i = 0, len = items.length; i < len; i++) {
  // // �ջ��ֿ�Ϊ��(�ջ��ֿ�Ϊ��Ҳ���������ʲ���Ƭ)
  // // if (StringUtils.isEmpty(items[i].getPk_receivestore())) {
  // // errorLins.append("[" + items[i].getCrowno() + "]");
  // // continue;
  // // }
  // // �ջ��ֿⲻ���ʲ���
  // StordocVO storVO = storePKToStoreVOMap.get(items[i].getPk_receivestore());
  // if (storVO == null) {
  // errorLins.append("[" + items[i].getCrowno() + "]");
  // continue;
  // }
  // if (!storVO.getIscapitalstor().booleanValue()) {
  // errorLins.append("[" + items[i].getCrowno() + "]");
  // continue;
  // }
  // }
  // if (errorLins.length() > 0) {
  // String msg = "�к�:" + errorLins.toString() + "�����ʲ���Ĳֿⲻ�������ʲ���Ƭ!";
  // ExceptionUtils.wrappBusinessException(msg);
  // }
  // }

  /**
   * ����Ƿ���ݼ��������
   * 
   * @param item
   */
  private boolean getIscheckStock(ArriveItemVO item) {
    String mar = item.getPk_material();
    String org = item.getPk_org();
    Map<String, MaterialStockVO> map =
        MaterialStockClassPubService.queryMaterialStockInfoByPks(new String[] {
          mar
        }, new String[] {
          org
        }, new String[] {
          MaterialStockVO.ISRETINSTOBYCHK
        });
    UFBoolean ufb = map.get(mar + org).getIsretinstobychk();
    if (ufb == null || !ufb.booleanValue()) {
      return false;
    }
    return true;
  }
}
