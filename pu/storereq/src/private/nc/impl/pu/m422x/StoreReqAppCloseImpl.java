/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-28 上午09:32:04
 */
package nc.impl.pu.m422x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.pu.m422x.state.StoreReqAppStateMachine;
import nc.bs.pu.m422x.state.bill.StoreReqAppBillCloseState;
import nc.bs.pu.m422x.state.row.StoreReqAppRowCloseState;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.itf.pu.m422x.IStoreReqAppClose;
import nc.vo.pu.m422x.entity.StoreReqAppCloseVO;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.enumeration.EnumBillStatus;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>关闭实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-28 上午09:32:04
 */
public class StoreReqAppCloseImpl implements IStoreReqAppClose {

  @Override
  public StoreReqAppVO[] billClose(StoreReqAppVO[] vos)
      throws BusinessException {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    try {
      BillTransferTool<StoreReqAppVO> tool =
          new BillTransferTool<StoreReqAppVO>(vos);
      StoreReqAppVO[] fullInfoBill = tool.getClientFullInfoBill();

      StoreReqAppCloseVO[] closeVOs = StoreReqAppVO.getCloseVO(fullInfoBill);

      StoreReqAppBillCloseState state =
          new StoreReqAppBillCloseState((Integer) EnumBillStatus.CLOSE.value());
      new StoreReqAppStateMachine().setState(state,
          StoreReqAppCloseVO.getBillVO(closeVOs));

      StoreReqAppVO[] tempVOs = StoreReqAppCloseVO.getBillVO(closeVOs);
      AggVOUtil.reSortVO(tempVOs, fullInfoBill);

      return tool.getBillForToClient(tempVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public StoreReqAppVO[] billOpen(StoreReqAppVO[] vos) throws BusinessException {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    try {
      BillTransferTool<StoreReqAppVO> tool =
          new BillTransferTool<StoreReqAppVO>(vos);
      StoreReqAppVO[] fullInfoBill = tool.getClientFullInfoBill();

      StoreReqAppCloseVO[] openVOs = StoreReqAppVO.getCloseVO(fullInfoBill);

      StoreReqAppBillCloseState state =
          new StoreReqAppBillCloseState(
              (Integer) POEnumBillStatus.APPROVE.value());
      new StoreReqAppStateMachine().setState(state,
          StoreReqAppCloseVO.getBillVO(openVOs));

      StoreReqAppVO[] tempVOs = StoreReqAppCloseVO.getBillVO(openVOs);
      AggVOUtil.reSortVO(tempVOs, fullInfoBill);

      return tool.getBillForToClient(tempVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public StoreReqAppVO[] rowClose(StoreReqAppVO[] vos) throws BusinessException {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    try {
      BillTransferTool<StoreReqAppVO> tool =
          new BillTransferTool<StoreReqAppVO>(vos);
      StoreReqAppVO[] fullInfoBill = tool.getClientFullInfoBill();

      StoreReqAppCloseVO[] closeVOs = this.getCloseVO(vos, fullInfoBill);

      StoreReqAppRowCloseState state =
          new StoreReqAppRowCloseState(UFBoolean.TRUE);
      new StoreReqAppStateMachine().setState(state, closeVOs);

      StoreReqAppVO[] tempVOs = StoreReqAppCloseVO.getBillVO(closeVOs);
      this.setVOWhenReturn(fullInfoBill, tempVOs);
      return tool.getBillForToClient(fullInfoBill);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public StoreReqAppVO[] rowOpen(StoreReqAppVO[] vos) throws BusinessException {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    try {
      BillTransferTool<StoreReqAppVO> tool =
          new BillTransferTool<StoreReqAppVO>(vos);
      StoreReqAppVO[] fullInfoBill = tool.getClientFullInfoBill();

      StoreReqAppCloseVO[] openVOs = this.getCloseVO(vos, fullInfoBill);

      StoreReqAppRowCloseState state =
          new StoreReqAppRowCloseState(UFBoolean.FALSE);
      new StoreReqAppStateMachine().setState(state, openVOs);

      StoreReqAppVO[] tempVOs = StoreReqAppCloseVO.getBillVO(openVOs);
      this.setVOWhenReturn(fullInfoBill, tempVOs);
      return tool.getBillForToClient(fullInfoBill);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private StoreReqAppCloseVO[] getCloseVO(StoreReqAppVO[] vos,
      StoreReqAppVO[] fullInfoBill) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    List<StoreReqAppCloseVO> closeList = new ArrayList<StoreReqAppCloseVO>();
    Map<String, StoreReqAppVO> voMap = AggVOUtil.createVOMap(fullInfoBill);
    // Map<String, StoreReqAppItemVO> itemMap =
    // AggVOUtil.createItemMap(fullInfoBill);
    BillIndex index = new BillIndex(fullInfoBill);
    IVOMeta meta = vos[0].getMetaData().getVOMeta(StoreReqAppItemVO.class);
    for (StoreReqAppVO vo : vos) {
      if (null == vo) {
        continue;
      }
      StoreReqAppVO fullVO = voMap.get(vo.getPrimaryKey());
      StoreReqAppHeaderVO fullHeaderVO = fullVO.getHVO();
      for (StoreReqAppItemVO itemVO : vo.getBVO()) {
        // StoreReqAppItemVO fullItemVO = itemMap.get(itemVO.getPrimaryKey());
        StoreReqAppItemVO fullItemVO =
            (StoreReqAppItemVO) index.get(meta, itemVO.getPrimaryKey());
        StoreReqAppCloseVO closeVO = new StoreReqAppCloseVO();
        closeVO.setVO(fullHeaderVO);
        closeVO.setVO(fullItemVO);
        closeList.add(closeVO);
      }
    }

    if (closeList.size() > 0) {
      return new ListToArrayTool<StoreReqAppCloseVO>()
          .convertToArray(closeList);
    }

    return null;
  }

  private void setVOWhenReturn(StoreReqAppVO[] fullInfoBill,
      StoreReqAppVO[] tempVOs) {
    // Map<String, StoreReqAppItemVO> itemMap =
    // AggVOUtil.createItemMap(tempVOs);
    BillIndex index = new BillIndex(fullInfoBill);
    IVOMeta meta =
        fullInfoBill[0].getMetaData().getVOMeta(StoreReqAppItemVO.class);

    // Set<String> keySet = itemMap.keySet();
    List<String> keyList = new ArrayList<String>();
    for (StoreReqAppVO fullVO : fullInfoBill) {
      for (StoreReqAppItemVO itemVO : fullVO.getBVO()) {
        keyList.add(itemVO.getPk_storereq_b());
      }
    }

    Map<String, StoreReqAppVO> voMap = AggVOUtil.createVOMap(tempVOs);
    for (StoreReqAppVO fullVO : fullInfoBill) {
      // 设置表体
      StoreReqAppItemVO[] itemVOs = fullVO.getBVO();
      StoreReqAppItemVO[] tempItemVOs = new StoreReqAppItemVO[itemVOs.length];
      for (int i = 0; i < itemVOs.length; ++i) {
        if (keyList.contains(itemVOs[i].getPrimaryKey())) {
          // tempItemVOs[i] = itemMap.get(itemVOs[i].getPrimaryKey());
          tempItemVOs[i] =
              (StoreReqAppItemVO) index.get(meta, itemVOs[i].getPrimaryKey());
        }
        else {
          tempItemVOs[i] = itemVOs[i];
        }
      }
      fullVO.setBVO(tempItemVOs);

      // 设置表头，行操作时可能改变表头
      fullVO.setHVO(voMap.get(fullVO.getPrimaryKey()).getHVO());
    }
  }
}
