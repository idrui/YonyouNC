package nc.bs.pu.m20.maintain.rule.pub;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * @description
 *              �빺�����ݺ�Ψһ�Լ��
 * @scene
 *        �빺���������޸�
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����10:12:15
 * @author yanxm5
 */
public class BillCodeCheckUniqueRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    List<PraybillVO> voLst = this.filterChkVO(vos);
    if (voLst.size() > 0) {
      PUBillCodeUtils.getPraybillCode().checkUnique(voLst.toArray(new PraybillVO[voLst.size()]));
    }
  }

  private List<PraybillVO> filterChkVO(PraybillVO[] vos) {
    List<PraybillVO> voLst = new ArrayList<PraybillVO>();
    for (PraybillVO vo : vos) {
      // �������޶�ʱ����鵥�ݺ�
      if (null != vo.getHVO().getNversion()
          && vo.getHVO().getNversion().intValue() > 1) {
        continue;
      }
      voLst.add(vo);
    }
    return voLst;
  }

}
