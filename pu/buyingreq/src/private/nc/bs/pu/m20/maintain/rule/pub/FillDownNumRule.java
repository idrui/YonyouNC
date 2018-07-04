/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-8 ����07:56:23
 */
package nc.bs.pu.m20.maintain.rule.pub;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.lang.UFDouble;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              �빺�������ۼƶ����������������ε��ݴ���Ĭ��ֵΪ��0��
 * @scene
 *        �빺���������޸�
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����9:47:04
 * @author yanxm5
 */
public class FillDownNumRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] voArray) {
    if (ArrayUtils.isEmpty(voArray)) {
      return;
    }
    for (PraybillVO aggVO : voArray) {
      // ���ñ�������
      this.setDownNum(aggVO);
    }
  }

  private void setDownNum(PraybillVO vo) {
    PraybillItemVO[] items = vo.getBVO();
    if (ArrayUtils.isEmpty(items)) {
      return;
    }
    for (PraybillItemVO item : items) {

      // �ۼƶ�������
      if (item.getNaccumulatenum() == null) {
        item.setNaccumulatenum(new UFDouble(0));
      }

      // ���ɺ�ͬ����
      if (item.getNgenct() == null) {
        item.setNgenct(Integer.valueOf(0));
      }
      // ���ɼ۸�����������
      if (item.getNpriceauditbill() == null) {
        item.setNpriceauditbill(Integer.valueOf(0));
      }
      // ����ѯ���۵�����
      if (item.getNquotebill() == null) {
        item.setNquotebill(Integer.valueOf(0));
      }
    }

  }
}
