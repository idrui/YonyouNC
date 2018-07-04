/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-8 ����10:25:56
 */
package nc.bs.pu.m4t.maintain.rule.maintain;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 * ���ݺŴ������ù������봦��
 * @scene
 * �ڳ��ݹ�������
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-31 ����10:14:20
 * @author wuxla
 */

public class InitialEstCodeProcRule implements ICompareRule<InitialEstVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.ICompareRule#process(E[], E[])
   */
  @Override
	public void process(InitialEstVO[] vos, InitialEstVO[] originVOs) {
		if (null == originVOs) {// ��������
			List<InitialEstVO> list = new ArrayList<InitialEstVO>();
			for (InitialEstVO vo : vos) {
				String vbillcode = vo.getHeader().getVbillcode();
				if (vbillcode == null || vbillcode.isEmpty()) {
					list.add(vo);
				}
			}
			this.handleInsertVO(list.toArray(new InitialEstVO[list.size()]));
		} else {// �޸ı���
			this.handleUpdateVO(vos, originVOs);
		}
	}

  private void handleInsertVO(InitialEstVO[] vos) {
    PUBillCodeUtils.getInitialEstCode().createBillCode(vos);
  }

  private void handleUpdateVO(InitialEstVO[] vos, InitialEstVO[] orgVos) {
    PUBillCodeUtils.getInitialEstCode().upadteBillCode(vos, orgVos);
  }

}
