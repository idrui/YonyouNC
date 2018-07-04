/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-8 下午02:27:58
 */
package nc.bs.pu.m4201.rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>自动取消确认成本和应付
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-8 下午02:27:58
 */
public abstract class CancelConfirmTOFIRule implements IRule<PurchaseinFIVO> {
  protected final int AP = 1;

  protected final int Confirm = 1;

  protected final int Est = 0;

  protected final int IA = 0;

  @Override
  public void process(PurchaseinFIVO[] vos) {
    String[] ids = this.getCancelIDs(vos);
    if (ArrayUtils.isEmpty(ids)) {
      return;
    }
    PurchaseinFIItemVO[] estItems = this.getCancelItems(vos, ids, this.Est);
    PurchaseinFIItemVO[] confirmItems =
        this.getCancelItems(vos, ids, this.Confirm);
    if (!ArrayUtils.isEmpty(estItems)) {
      this.cancelEst(estItems);
    }
    if (!ArrayUtils.isEmpty(confirmItems)) {
      this.cancelConfirm(confirmItems);
    }
  }

  private String[] getCancelIDs(PurchaseinFIVO[] vos) {
    List<String> idList = new ArrayList<String>();
    for (PurchaseinFIVO vo : vos) {
      UFBoolean autoFi = vo.getParentVO().getBautotofi();
      if (ValueUtils.getBoolean(autoFi)) {
        idList.add(vo.getParentVO().getPk_stockps());
      }
    }
    return idList.toArray(new String[idList.size()]);
  }

  private PurchaseinFIItemVO[] getCancelItems(PurchaseinFIVO[] vos,
      String[] autoFiHIDs, int toFiType) {
    PurchaseinFIItemVO[] items = AggVOUtil.getCombinItemVOs(vos);
    Set<String> autoFiIDSet = new HashSet<String>(Arrays.asList(autoFiHIDs));
    List<PurchaseinFIItemVO> itemList = new ArrayList<PurchaseinFIItemVO>();
    for (PurchaseinFIItemVO item : items) {
      String pk_stockps = item.getPk_stockps();
      if (!autoFiIDSet.contains(pk_stockps)) {
        continue;
      }
      if ((this.Est == toFiType)
          && this.isNeedCancelEst(item, this.getFIModule())) {
        itemList.add(item);
      }
      else if ((this.Confirm == toFiType)
          && this.isNeedCancelConfirm(item, this.getFIModule())) {
        itemList.add(item);
      }
    }
    return itemList.toArray(new PurchaseinFIItemVO[itemList.size()]);
  }

  private boolean isNeedCancelConfirm(PurchaseinFIItemVO item, int FIModule) {
    if (this.IA == FIModule) {
      return EnumToIAFlag.ConfirmToIA.value().equals(item.getFtoiaflag()) ? true
          : false;
    }
    else if (this.AP == FIModule) {
      return EnumToAPFlag.ConfirmToAP.value().equals(item.getFtoapflag()) ? true
          : false;
    }
    else {
      return false;
    }
  }

  private boolean isNeedCancelEst(PurchaseinFIItemVO item, int FIModule) {
    if (this.IA == FIModule) {
      return EnumToIAFlag.EstimateToIA.value().equals(item.getFtoiaflag()) ? true
          : false;
    }
    else if (this.AP == FIModule) {
      return EnumToAPFlag.EstimateToAP.value().equals(item.getFtoapflag()) ? true
          : false;
    }
    else {
      return false;
    }
  }

  protected abstract void cancelConfirm(PurchaseinFIItemVO[] items);

  protected abstract void cancelEst(PurchaseinFIItemVO[] items);

  protected abstract int getFIModule();

}
