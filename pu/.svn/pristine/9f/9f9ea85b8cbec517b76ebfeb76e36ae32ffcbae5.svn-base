/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-2 下午04:58:01
 */
package nc.pubimpl.pu.m21.arap.mf3.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.businessevent.BdUpdateEvent;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.businessevent.IEventType;
import nc.bs.pu.m21.writeback.arap.OrderWriteBackForF3BP;
import nc.pubitf.pu.m21.arap.mf3.IOrderWriteBackParaForF3;
import nc.vo.arap.pay.AggPayBillVO;
import nc.vo.arap.pay.PayBillItemVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-2 下午04:58:01
 */
public class OrderWriteBackForF3UpdateHandler implements IBusinessListener {

  /**
   * 父类方法重写
   * 
   * @see nc.pubimpl.pu.m21.arap.mf3.handler.OrderWriteBackForF3DeleteHandler#doAction(nc.bs.businessevent.IBusinessEvent)
   */
  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    try {
      if (IEventType.TYPE_UPDATE_AFTER.equals(event.getEventType())) {
        BdUpdateEvent e = (BdUpdateEvent) event;
        Object oldObject = e.getOldObject();
        Object newObject = e.getNewObject();
        if (null == oldObject && null == newObject) {
          return;
        }

        Map<String, PayBillItemVO> oldPayItemVOMap =
            this.getPayItemVOMap(oldObject);
        Map<String, PayBillItemVO> newPayItemVOMap =
            this.getPayItemVOMap(newObject);

        this.writebackForF3(oldPayItemVOMap, newPayItemVOMap);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param obj
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-2 下午09:32:41
   */
  private Map<String, PayBillItemVO> getPayItemVOMap(Object obj) {
    if (null == obj) {
      return new HashMap<String, PayBillItemVO>();
    }
    else if (obj.getClass().isArray()) {
      Object[] arr = (Object[]) obj;
      AggPayBillVO[] aggVOs = new AggPayBillVO[arr.length];
      System.arraycopy(obj, 0, aggVOs, 0, arr.length);
      return AggVOUtil.createItemMap(aggVOs);
    }
    else {
      AggPayBillVO aggVO = (AggPayBillVO) obj;
      return AggVOUtil.createItemMap(new AggPayBillVO[] {
        aggVO
      });
    }
  }

  /**
   * 方法功能描述：回写
   * <p>
   * <b>参数说明</b>
   * 
   * @param oldPayItemVOMap
   * @param newPayItemVOMap
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-2 下午09:39:36
   */
  private void writebackForF3(Map<String, PayBillItemVO> oldPayItemVOMap,
      Map<String, PayBillItemVO> newPayItemVOMap) {
    Set<String> bidSet = new HashSet<String>();
    bidSet.addAll(oldPayItemVOMap.keySet());
    bidSet.addAll(newPayItemVOMap.keySet());

    if (bidSet.isEmpty()) {
      return;
    }

    String[] bids = bidSet.toArray(new String[0]);
    List<IOrderWriteBackParaForF3> list =
        new ArrayList<IOrderWriteBackParaForF3>();

    for (int i = 0; i < bids.length; ++i) {
      final PayBillItemVO oldPayItemVO = oldPayItemVOMap.get(bids[i]);
      final PayBillItemVO newPayItemVO = newPayItemVOMap.get(bids[i]);
      if (oldPayItemVO == null) {
        continue;
      }
      if (!POBillType.Order.getCode().equals(oldPayItemVO.getSrc_billtype())) {
        continue;
      }
      IOrderWriteBackParaForF3 para = new IOrderWriteBackParaForF3() {
        @Override
        public String getBID() {
          return oldPayItemVO.getSrc_itemid();
        }

        @Override
        public UFDate getBilldate() {
          return oldPayItemVO.getBilldate();
        }

        @Override
        public String getCurrency() {
          return oldPayItemVO.getPk_currtype();
        }

        @Override
        public UFDouble getDiffNum() {
          return null;
        }

        @Override
        public UFDouble getDiffPaymny() {
          if (null == newPayItemVO) {// 删除
            return MathTool.oppose(oldPayItemVO.getLocal_money_de());
          }
          // 修改
          return MathTool.sub(newPayItemVO.getLocal_money_de(),
              oldPayItemVO.getLocal_money_de());

        }

        @Override
        public UFDouble getDiffPayorgmny() {
          if (null == newPayItemVO) {// 删除
            return MathTool.oppose(oldPayItemVO.getMoney_de());
          }
          // 修改
          // 付款
          return MathTool.sub(newPayItemVO.getMoney_de(),
              oldPayItemVO.getMoney_de());
        }

        @Override
        public String getHID() {
          return oldPayItemVO.getSrc_billid();
        }
      };
      list.add(para);
    }
    if (0 == list.size()) {
      return;
    }
    IOrderWriteBackParaForF3[] paras =
        list.toArray(new IOrderWriteBackParaForF3[list.size()]);
    new OrderWriteBackForF3BP().writeback(paras);
  }
}
