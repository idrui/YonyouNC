package nc.ui.pu.pub.util;

import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.ic.batch.IBatchRefQuery;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.pu.pub.editor.ClientContext;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.card.CardPanelEvent;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.ic.batch.BatchRefViewVO;
import nc.vo.ic.batchcode.BatchDlgParam;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.batchcode.PUBatchDlgParam;
import nc.vo.pu.onhand.entity.OnhandDlgPUHeaderVO;
import nc.vo.pu.report.scale.PUPubMetaNameConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.scmf.ic.mbatchcode.BatchcodeVO;
import nc.vo.uif2.LoginContext;

public class VBatchCodeUtil {
  public static boolean canEdit(String pk_material, String pk_arrvstoorg) {
    if (StringUtil.isEmptyWithTrim(pk_material)
        || StringUtil.isEmptyWithTrim(pk_arrvstoorg)) {
      return false;
    }
    MaterialStockVO[] mrlStockVOArray = null;
    // 查询物料是否批次管理
    mrlStockVOArray =
        MaterialPubService.queryMaterialStockInfoByPks(new String[] {
          pk_material
        }, pk_arrvstoorg, new String[] {
          MaterialStockVO.WHOLEMANAFLAG
        });
    if (null == mrlStockVOArray || mrlStockVOArray.length == 0
        || null == mrlStockVOArray[0]) {
      return false;
    }
    return UFBoolean.TRUE.equals(mrlStockVOArray[0].getWholemanaflag());
  }

  public static BatchDlgParam getBatchDlgParam(CardPanelEvent event, int row,
      Map<String, String> headDims, Map<String, String> bodyDims) {
    BillCardPanel panel = event.getBillCardPanel();
    BatchDlgParam param = new PUBatchDlgParam();
    OnhandDimVO dimvo = param.getOnhandDim();
    if (dimvo == null) {
      dimvo = new OnhandDimVO();
      param.setOnhandDim(dimvo);
    }
    VBatchCodeUtil.dealBodyDims(bodyDims);
    for (Map.Entry<String, String> entry : headDims.entrySet()) {
      Object value = panel.getHeadItem(entry.getValue()).getValueObject();
      if (value != null) {
        dimvo.setAttributeValue(entry.getKey(), value);
      }
    }
    LoginContext context = event.getContext();
    if(context instanceof ClientContext){
      context = ((ClientContext) event.getContext()).convertLoginContext();
    }
    param.setLoginContext(context);

    BillModel bm = panel.getBillModel();
    for (Map.Entry<String, String> entry : bodyDims.entrySet()) {
      BillItem bodyitem = bm.getItemByKey(entry.getValue());
      if (bodyitem == null) {
        continue;
      }
      Object dimValue = bm.getValueObjectAt(row, entry.getValue());
      if (dimValue instanceof DefaultConstEnum) {
        dimValue = ((DefaultConstEnum) dimValue).getValue();
      }
      dimvo.setAttributeValue(entry.getKey(), dimValue);
    }
    OnhandDlgPUHeaderVO headVO = new OnhandDlgPUHeaderVO();
    DataViewMeta dataViewMeta = new DataViewMeta(dimvo.getClass());
    headVO.setDataViewMeta(dataViewMeta);
    headVO.setVO(dimvo);
    // 主单位
    Object dimValue = bm.getValueObjectAt(row, PUPubMetaNameConst.CUNITID);
    if (dimValue instanceof DefaultConstEnum) {
      dimValue = ((DefaultConstEnum) dimValue).getValue();
    }
    headVO.setCunitid(dimValue.toString());
    headVO.setCrowno((String) bm.getValueAt(row, PUPubMetaNameConst.CROWNO));
    headVO.setOnhandshouldnum((UFDouble) bm.getValueAt(row,
        PUPubMetaNameConst.NNUM));
    headVO.setOnhandshouldassnum((UFDouble) bm.getValueAt(row,
        PUPubMetaNameConst.NASTNUM));

    param.setHeadVO(headVO);
    param.setIsNewBatchRef(UFBoolean.TRUE);

    return param;
  }

  /**
   * 处理界面手工输入批次, 需要根据界面的存货、辅计量等查询后台的现存量和批次档案， 如果存在，则根据存货信息带出失效日期、生产日期至界面
   * 如果不存在，也不需清空已经输入批次
   * 
   * @since 6.0
   */
  public static void handleBatchByHand(BillCardPanel card, String inputStr,
      int row, BatchDlgParam queryParam) {

    BatchRefViewVO[] refVOs = null;
    if (inputStr != null && inputStr.length() != 0) {
      queryParam.setVbatchcode(inputStr);
      try {
        refVOs =
            NCLocator.getInstance().lookup(IBatchRefQuery.class)
                .queryBatchNum(queryParam);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    if (refVOs == null || refVOs.length == 0) {
      card.setBodyValueAt(inputStr, row, PUPubMetaNameConst.VBATCHCODE);
      return;
    }
    // 如果是档案被封存，则清空自定义批次号输入框
    if (UFBoolean.TRUE.equals(refVOs[0].getAttributeValue(BatchcodeVO.BSEAL))) {
      return;
    }
  }

  /**
   * 批次参照后赋值
   * 
   * @param voList
   * @param card
   * @param row
   */
  public static void synBatchRef(List<BatchRefViewVO> volist,
      BillCardPanel card, int[] rows) {
    for (int i = 0; i < rows.length; i++) {
      // 生产厂商
      int row = rows[i];
      BatchRefViewVO vo = volist.get(i);
      Object cproductorid = vo.getAttributeValue(OnhandDimVO.CPRODUCTORID);
      if (cproductorid != null) {
        card.setBodyValueAt(cproductorid, row, PUPubMetaNameConst.CPRODUCTORID);
      }
      // 换算率如果有值，就设置，没有就按原来的换算率联动
      Object vchangerate = vo.getAttributeValue(OnhandDimVO.VCHANGERATE);
      Object nnum = vo.getAttributeValue("nnum");
      Object nassistnum = vo.getAttributeValue("nassistnum");
      if (vchangerate != null) {
        card.setBodyValueAt(vchangerate, row, PUPubMetaNameConst.VCHANGERATE);
      }
      card.setBodyValueAt(nnum, row, PUPubMetaNameConst.NNUM);
      card.setBodyValueAt(nassistnum, row, PUPubMetaNameConst.NASTNUM);
    }
  }

  private static void dealBodyDims(Map<String, String> bodyDims) {
    bodyDims.put(OnhandDimVO.PK_GROUP, PUPubMetaNameConst.PK_GROUP);
    bodyDims.put(OnhandDimVO.CMATERIALOID, PUPubMetaNameConst.PK_SRCMATERIAL);
    bodyDims.put(OnhandDimVO.CMATERIALVID, PUPubMetaNameConst.PK_MATERIAL);
    bodyDims.put(OnhandDimVO.CASTUNITID, PUPubMetaNameConst.CASTUNITID);
    bodyDims.put(OnhandDimVO.VCHANGERATE, PUPubMetaNameConst.VCHANGERATE);
    bodyDims.put(OnhandDimVO.VBATCHCODE, PUPubMetaNameConst.VBATCHCODE);
    // 固定辅助属性
    bodyDims.put(OnhandDimVO.CPROJECTID, PUPubMetaNameConst.CPROJECTID);
    bodyDims.put(OnhandDimVO.CASSCUSTID, PUPubMetaNameConst.CASSCUSTID);
    bodyDims.put(OnhandDimVO.CPRODUCTORID, PUPubMetaNameConst.CPRODUCTORID);
    bodyDims.put(OnhandDimVO.CFFILEID, PUPubMetaNameConst.CFFILEID);
    // 物料自由辅助属性
    for (int i = 1; i < 11; i++) {
      bodyDims.put("vfree" + i, "vfree" + i);
    }
  }
}
