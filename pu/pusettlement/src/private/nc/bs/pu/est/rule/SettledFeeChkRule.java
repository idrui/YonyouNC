/**
 * $�ļ�˵��$
 *
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-19 ����09:09:18
 */
package nc.bs.pu.est.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.m27.settlebill.SettleBillItemQueryBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����ݹ��������Ƿ��Ѿ���������
 * <li>��Ҫ�ɽ����ṩ���񣬲��̯��ϸ��ȷ���Ƿ���й�����
 * <li>����Ҳ�𵽲������Ƶ�����(�����ݹ�����ý���֮��Ĳ���)
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-6-19 ����09:09:18
 */
public class SettledFeeChkRule implements IRule<EstVO> {

  @Override
  public void process(EstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    List<List<String>> bidFeeoidList = this.getFeeOID(vos);
    if (bidFeeoidList.size() > 0) {
      int size = bidFeeoidList.get(0).size();
      String[] bids = bidFeeoidList.get(0).toArray(new String[size]);
      size = bidFeeoidList.get(1).size();
      String[] feeoids = bidFeeoidList.get(1).toArray(new String[size]);
      UFBoolean settled =
          new SettleBillItemQueryBP().queryFeeItemIsFeeSettled(bids, feeoids);
      if (UFBoolean.TRUE.equals(settled)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0064")/*@res "�����Ѿ����й�����ķ�������ܶ��ٶ�������ݹ���"*/);
      }
    }
  }

  private List<List<String>> getFeeOID(EstVO[] vos) {
    List<List<String>> retList = new ArrayList<List<String>>();
    List<String> bidList = new ArrayList<String>();
    List<String> feeoidList = new ArrayList<String>();
    for (EstVO vo : vos) {
      FeeEstVO[] fees = vo.getChildrenVO();
      if (ArrayUtils.isEmpty(fees)) {
        continue;
      }
      for (FeeEstVO fee : fees) {
        bidList.add(fee.getPk_stockps_b());
        feeoidList.add(fee.getPk_srcfeematerial());
      }
    }
    if (bidList.size() > 0) {
      retList.add(bidList);
      retList.add(feeoidList);
    }
    return retList;
  }
}