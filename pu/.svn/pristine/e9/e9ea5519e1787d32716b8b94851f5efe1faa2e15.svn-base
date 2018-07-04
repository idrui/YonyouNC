/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-8 上午10:25:56
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
 * 单据号处理：调用公共代码处理
 * @scene
 * 期初暂估单保存
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-31 上午10:14:20
 * @author wuxla
 */

public class InitialEstCodeProcRule implements ICompareRule<InitialEstVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.ICompareRule#process(E[], E[])
   */
  @Override
	public void process(InitialEstVO[] vos, InitialEstVO[] originVOs) {
		if (null == originVOs) {// 新增保存
			List<InitialEstVO> list = new ArrayList<InitialEstVO>();
			for (InitialEstVO vo : vos) {
				String vbillcode = vo.getHeader().getVbillcode();
				if (vbillcode == null || vbillcode.isEmpty()) {
					list.add(vo);
				}
			}
			this.handleInsertVO(list.toArray(new InitialEstVO[list.size()]));
		} else {// 修改保存
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
