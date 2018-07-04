package nc.impl.pu.m23.fa;

import nc.bs.pu.m23.fa.CreateFACardBP;
import nc.bs.pu.m23.fa.DeleteFACardBP;
import nc.bs.pu.m23.fa.DeleteTransAssetBP;
import nc.bs.pu.m23.fa.QueryArriveFor4A60BP;
import nc.bs.pu.m23.fa.TransAssetBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.itf.pu.m23.fa.IArriveForFA;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;

public class ArriveForFAImpl implements IArriveForFA {

  @Override
  public ArriveVO[] createFACard(ArriveVO[] voArray) throws BusinessException {
    try {
      // 加锁单据 +检查TS
      BillTransferTool<ArriveVO> tool = new BillTransferTool<ArriveVO>(voArray);
      // 补全前台VO
      ArriveVO[] bills = tool.getClientFullInfoBill();
      // 调用BP
      CreateFACardBP bp = new CreateFACardBP();
      ArriveVO[] resultVOs = bp.createFACard(voArray, bills);
      this.setBillForToClient(bills, resultVOs);
      return tool.getBillForToClient(bills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public ArriveVO[] deleteFACard(ArriveVO[] voArray) throws BusinessException {
    try {
      // 加锁单据 +检查TS
      BillTransferTool<ArriveVO> tool = new BillTransferTool<ArriveVO>(voArray);
      // 补全前台VO
      ArriveVO[] bills = tool.getClientFullInfoBill();
      // 调用BP
      DeleteFACardBP bp = new DeleteFACardBP();
      ArriveVO[] resultVOs = bp.deleteFACard(voArray, bills);
      // 构造返回数据
      this.setBillForToClient(bills, resultVOs);
      return tool.getBillForToClient(bills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public ArriveVO[] deleteTransAsset(ArriveVO[] vos) throws BusinessException {
    try {
      BillTransferTool<ArriveVO> tool = new BillTransferTool<ArriveVO>(vos);
      ArriveVO[] fullVOs = tool.getClientFullInfoBill();

      ArriveVO[] resultVOs =
          new DeleteTransAssetBP().deleteTransAsset(vos, fullVOs);
      this.setBillForToClient(fullVOs, resultVOs);
      return tool.getBillForToClient(fullVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public ArriveVO[] queryArriveFor4A60(ArriveVO[] vos) throws BusinessException {
    try {
      return new QueryArriveFor4A60BP().queryArriveFor4A60(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public ArriveVO[] transAsset(ArriveVO[] vos) throws BusinessException {
    try {
      BillTransferTool<ArriveVO> tool = new BillTransferTool<ArriveVO>(vos);
      ArriveVO[] fullVOs = tool.getClientFullInfoBill();
      ArriveVO[] resultVOs = new TransAssetBP().transAsset(vos, fullVOs);
      this.setBillForToClient(fullVOs, resultVOs);
      return tool.getBillForToClient(fullVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
      return null;
    }
  }

  private void setBillForToClient(ArriveVO[] fullVOs, ArriveVO[] resultVOs) {
    BillIndex index = new BillIndex(resultVOs);
    IVOMeta parentMeta = fullVOs[0].getMetaData().getParent();
    IVOMeta childMeta = fullVOs[0].getMetaData().getVOMeta(ArriveItemVO.class);
    for (ArriveVO fullVO : fullVOs) {
      for (ArriveItemVO itemVO : fullVO.getBVO()) {
        ArriveItemVO resultItem =
            (ArriveItemVO) index.get(childMeta, itemVO.getPk_arriveorder_b());
        if (resultItem != null) {
          itemVO = resultItem;
        }
      }
      ArriveHeaderVO headerVO =
          (ArriveHeaderVO) index.get(parentMeta, fullVO.getHVO()
              .getPk_arriveorder());
      fullVO.setHVO(headerVO);
    }
  }
}
