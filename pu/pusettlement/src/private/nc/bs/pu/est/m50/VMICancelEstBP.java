/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-6 ����06:54:38
 */
package nc.bs.pu.est.m50;

import nc.bs.pu.est.CancelEstBP;
import nc.bs.pu.est.plugin.VMIEstPluginPoint;
import nc.vo.pu.est.entity.m50.VmiFIFDVO;
import nc.vo.pu.est.util.EstVOUtil;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���Ļ���ȡ���ݹ���BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-7-6 ����06:54:38
 */
public class VMICancelEstBP extends CancelEstBP {

  public VMICancelEstBP() {
    super(VMIEstPluginPoint.UNEST_BP, EstVOUtil.getGoodsEstBUpdate(),
        VmiFIFDVO.class);
  }

}
