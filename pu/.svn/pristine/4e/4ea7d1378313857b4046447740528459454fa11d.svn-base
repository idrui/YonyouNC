/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-9 上午09:34:08
 */
package nc.pubimpl.pu.m21.arap.mf1.handler;

import java.util.ArrayList;
import java.util.List;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.pu.m21.writeback.arap.OrderWriteBackForF1BP;
import nc.pubitf.pu.m21.arap.mf1.IOrderWriteBackParaForF1;
import nc.vo.arap.global.ArapBillDealVOConsts;
import nc.vo.arap.payable.PayableBillItemVO;
import nc.vo.arap.verify.AggverifyVO;
import nc.vo.arap.verify.VerifyDetailVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>反核销回写采购订单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-9 上午09:34:08
 */
public class OrderWriteBackForF1UnVerifyHandler implements IBusinessListener {

  @SuppressWarnings("unchecked")
  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    try {
      if (ArapBillDealVOConsts.TYPE_UNVERIFY_AFTER.equals(event.getEventType())) {
        BusinessEvent e = (BusinessEvent) event;
        Object value = e.getObject();
        if (null == value) {
          return;
        }

        PayableBillItemVO[] payableBillItems = null;
        MapList<String, VerifyDetailVO> detailVOMap = null;
        OrderWriteBackForF1Util util = OrderWriteBackForF1Util.getInstance();

        if (value.getClass().isArray()) {
          Object[] obj = (Object[]) value;
          AggverifyVO[] aggVOs = new AggverifyVO[obj.length];
          System.arraycopy(obj, 0, aggVOs, 0, obj.length);
          payableBillItems = util.getPayableBillItems(aggVOs);
          detailVOMap = util.getDetailVOMap(aggVOs);
        }
        else if (value.getClass().getName().equals(AggverifyVO.class.getName())) {
          AggverifyVO aggVO = (AggverifyVO) value;
          payableBillItems = util.getPayableBillItems(new AggverifyVO[] {
            aggVO
          });
          detailVOMap = util.getDetailVOMap(new AggverifyVO[] {
            aggVO
          });
        }
        else if (value.getClass().getName().equals(ArrayList.class.getName())) {
          List<AggverifyVO> list = (ArrayList<AggverifyVO>) value;
          if (list.isEmpty()) {
            return;
          }
          AggverifyVO[] aggVOs = new AggverifyVO[list.size()];
          for (int i = 0; i < aggVOs.length; ++i) {
            aggVOs[i] = list.get(i);
          }

          payableBillItems = util.getPayableBillItems(aggVOs);
          detailVOMap = util.getDetailVOMap(aggVOs);
        }

        if (ArrayUtils.isEmpty(payableBillItems) || null == detailVOMap) {
          return;
        }

        this.writebackForF1(detailVOMap, payableBillItems);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  private void writebackForF1(MapList<String, VerifyDetailVO> detailVOMap,
      PayableBillItemVO[] payableBillItems) {
    List<IOrderWriteBackParaForF1> paraList =
        new ArrayList<IOrderWriteBackParaForF1>();
    for (int i = 0; i < payableBillItems.length; ++i) {
      final PayableBillItemVO payableBillItem = payableBillItems[i];
      if (null == payableBillItem) {
        continue;
      }
      List<VerifyDetailVO> list =
          detailVOMap.get(payableBillItem.getPk_payableitem());
      if (CollectionUtils.isEmpty(list)) {
        continue;
      }
      for (final VerifyDetailVO detailVO : list) {
        if (null == detailVO) {
          continue;
        }
        Integer busiflag = detailVO.getBusiflag();
        final boolean rbverify =
            busiflag != null
                && busiflag.intValue() == ArapBillDealVOConsts.RBVERIFY_FLAG
                    .intValue();
        IOrderWriteBackParaForF1 para = new IOrderWriteBackParaForF1() {

          @Override
          public String getBID() {
            return payableBillItem.getSrc_itemid();
          }

          @Override
          public UFDate getBillDate() {
            return detailVO.getBusidate();
          }

          @Override
          public String getCurrency() {
            return detailVO.getPk_currtype();
          }

          @Override
          public UFDouble getDiffMny() {
            if (rbverify
                && MathTool.compareTo(detailVO.getLocal_money_cr(),
                    UFDouble.ZERO_DBL) == 0) {
              return MathTool.oppose(detailVO.getLocal_money_de());
            }

            return MathTool.oppose(detailVO.getLocal_money_cr());
          }

          @Override
          public UFDouble getDiffNum() {
            return null;
          }

          @Override
          public UFDouble getDiffOrgMny() {
            if (rbverify
                && MathTool
                    .compareTo(detailVO.getMoney_cr(), UFDouble.ZERO_DBL) == 0) {
              return MathTool.oppose(detailVO.getMoney_de());
            }

            return MathTool.oppose(detailVO.getMoney_cr());
          }

          @Override
          public String getHID() {
            return payableBillItem.getSrc_billid();
          }

          @Override
          public boolean isVerify() {
            return false;
          }
        };
        paraList.add(para);
      }
    }
    if (0 == paraList.size()) {
      return;
    }
    IOrderWriteBackParaForF1[] paras =
        paraList.toArray(new IOrderWriteBackParaForF1[paraList.size()]);
    new OrderWriteBackForF1BP().writeback(paras);
  }
}
