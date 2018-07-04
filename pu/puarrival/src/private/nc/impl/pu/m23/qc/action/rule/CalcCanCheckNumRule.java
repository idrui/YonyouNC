package nc.impl.pu.m23.qc.action.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * 
 * @description
 * ���㱨����������
 * @scene
 * �������ʼ�
 * @param
 * ��
 *
 * @since 6.3
 * @version 2012-8-15 ����01:22:45
 * @author lixyp
 */


public class CalcCanCheckNumRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] vos) {
    ScaleUtils scale = new ScaleUtils(AppContext.getInstance().getPkGroup());
    UFDouble newNum = null;
    for (ArriveVO vo : vos) {
      for (ArriveItemVO itemVo : vo.getBVO()) {
        newNum = MathTool.sub(itemVo.getNnum(), itemVo.getNaccumbacknum());
        // nnum�����item.getNnum()���ȣ�˵�����˻��������������Ҫ�������úͷ�������
        if (!MathTool.equals(newNum, itemVo.getNnum())) {
          // ������ = ������ - �ۼƱ���������
          itemVo.setNnum(newNum);

          // ��������
          String vchangerate = itemVo.getVchangerate();
          if (vchangerate == null) {
            vchangerate = scale.adjustHslScale("1/1");
            itemVo.setVchangerate(vchangerate);
          }

          String castunitid = itemVo.getCastunitid();
          UFDouble nastnum =
              scale.adjustNumScale(HslParseUtil.hslDivUFDouble(
                  scale.adjustHslScale(vchangerate), itemVo.getNnum()),
                  castunitid);
          itemVo.setNastnum(nastnum);
        }
      }
    }
  }
}
