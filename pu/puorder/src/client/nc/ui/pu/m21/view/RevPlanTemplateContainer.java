package nc.ui.pu.m21.view;

import nc.ui.pu.uif2.PUTemplateContainer;
import nc.ui.pu.uif2.PUUif2Utils;
import nc.vo.pu.pub.enumeration.PuNodeCode;
import nc.vo.sm.funcreg.FuncRegisterVO;

import org.apache.commons.lang.StringUtils;

/**
 * �ɹ�����ά�������ƻ�����ĵ���ģ�������
 * 
 * @since 6.0
 * @version 2011-12-9 ����8:48:32
 * @author zhaoyha
 */
public class RevPlanTemplateContainer extends PUTemplateContainer {

  @Override
  public void load() {
    super.load();
    // ���ｫ�ɹ���������Χ���棩�Ľڵ��д�룬Ϊ��֧�����ݶ����ڵ���䵽���ƻ�ģ��
    // ����ֱ��д���ɹ������ڵ��40040400����Ϊ�ɹ������ڵ�����ǽ������ͷ����Ľڵ�
    this.usePoFuncode();
  }

  private void usePoFuncode() {
    String fc = PuNodeCode.n40040400.code();// ���Ϊ�գ���ȡ�ɹ������ڵ��
    FuncRegisterVO frvo = PUUif2Utils.getFuncRegisterVO(this.getContext());
    if (null != frvo) {
      String pocode =
          (String) PUUif2Utils.getUsrObjFromFRVO(frvo,
              PUUif2Utils.PUFRVOAttName.sourrounding_funcode.name());
      fc = StringUtils.isBlank(pocode) ? fc : pocode;
    }
    this.setFuncode(fc);
  }

}
