/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-6 ����02:39:41
 */
package nc.bs.pu.est.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ia.IAForEstConfirmServices;
import nc.itf.pu.reference.pcia.PCIAForEstConfirmServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.ICBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * ȡ�������ݹ��ɱ�
 * @scene
 * ȡ���ݹ�-BP
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-23 ����9:50:24
 * @author zhangshqb
 */
public class GoodsUnEstIARule implements IRule<EstVO> {

  @Override
  public void process(EstVO[] vos) {
    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }
    String[][] ids = this.getIDs(vos);
    if (ArrayUtils.isEmpty(ids[0])) {
      return;
    }
    String billtype = vos[0].getParentVO().getBillType();
    if (ICBillType.PurchaseIn.getCode().equals(billtype)) {
      IAForEstConfirmServices.unEstimate(ids[0], ids[1]);
      // mengjian by 20141021 �����������Ĵ������ʱ
      if (SysInitGroupQuery.isPCIAEnabled()) {
        PCIAForEstConfirmServices.unEstimate(ids[0], ids[1]);
      }
    }
    else if (ICBillType.VmiSum.getCode().equals(billtype)) {
      IAForEstConfirmServices.unVMIEstimate(ids[0], ids[1]);
    }
  }

  private String[][] getIDs(EstVO[] vos) {
    List<String> bids = new ArrayList<String>();
    List<String> hids = new ArrayList<String>();
    for (EstVO vo : vos) {
      GoodsEstVO head = vo.getParentVO();
      // δ�ݹ��ɱ�
      if (!EnumToIAFlag.EstimateToIA.value().equals(head.getFtoiaflag())) {
        continue;
      }
      // �Ѿ������
      UFDouble estSttlNum = head.getNaccestcoststtlnum();
      if (!UFDouble.ZERO_DBL.equals(MathTool.nvl(estSttlNum))) {
        continue;
      }
      bids.add(head.getPk_stockps_b());
      hids.add(head.getPk_stockps());
    }
    String[] aryBids = bids.toArray(new String[bids.size()]);
    String[] aryHids = hids.toArray(new String[hids.size()]);
    return new String[][] {
      aryHids, aryBids
    };
  }
}
