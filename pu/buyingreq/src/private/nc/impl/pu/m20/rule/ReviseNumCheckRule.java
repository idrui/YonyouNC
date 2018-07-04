package nc.impl.pu.m20.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.rule.ToleranceCalcRule;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�޶������У����������м�飬�޶����������ԭ����ͬ�������Ҳ���С�ں�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author yangtian
 * @time 2012-5-18 ����11:15:42
 */
public class ReviseNumCheckRule extends ToleranceCalcRule implements
    ICompareRule<PraybillVO> {
  private UFBoolean isUserConfirm;

  public ReviseNumCheckRule(UFBoolean isUserConfirm) {
    this.isUserConfirm = isUserConfirm;
  }

  @Override
  public String getBidFiled() {
    return PraybillItemVO.PK_PRAYBILL_B;
  }

  @Override
  public String getNumField() {
    return PraybillItemVO.NNUM;
  }

  @Override
  public String getTable() {
    return PUEntity.M20_B_TABLE;
  }

  @Override
  public void process(PraybillVO[] vos, PraybillVO[] originVOs) {

    PraybillViewVO[] views =
        new BillToViewConvertor<PraybillVO, PraybillViewVO>(
            PraybillViewVO.class).convert(vos);
    List<String> bidList = new ArrayList<String>();
    for (PraybillViewVO view : views) {
      bidList.add(view.getPk_praybill_b());
    }
    String[] bids = bidList.toArray(new String[bidList.size()]);
    String pk_org = views[0].getPk_purchaseorg();

    this.toleranceCompare(PraybillItemVO.NACCUMULATENUM, bids,
        MaterialVO.INTOLERANCE, PUSysParamUtil.getPO47(pk_org),
        this.isUserConfirm);
  }
}
