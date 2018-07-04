/**
 * $�ļ�˵��$
 *
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-29 ����10:22:20
 */
package nc.bs.pu.m20.maintain.rule.pub;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              �빺������������
 * @scene
 *        �빺���������޸�
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����9:47:51
 * @author yanxm5
 */
public class NumCheckRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] voArray) {
    if (ArrayUtils.isEmpty(voArray)) {
      return;
    }
    for (PraybillVO aggVO : voArray) {
      // ����������
      this.checknum(aggVO);
    }
  }

  private void checknum(PraybillVO vo) {
    PraybillItemVO[] items = vo.getBVO();
    if (ArrayUtils.isEmpty(items)) {
      return;
    }
    for (PraybillItemVO item : items) {

      // ������Ϊ�գ�����0
    	
      /*
       * if (item.getNastnum() == null || item.getNastnum().doubleValue() <= 0) {
       * add by wandl ��Ŀ����ϲ�����������������֧���빺��¼�븺��
       */
    	if (item.getNastnum() == null || item.getNastnum().doubleValue() == 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0028")/*
                                                                     * @res
                                                                     * "��������Ϊ�գ��������"
                                                                     */);
      }
    }

  }

}
