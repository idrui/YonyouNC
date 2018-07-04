package nc.ui.pu.m20.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.BodyDelLineAction;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.enumeration.PuRefBillTypeIdEnum;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单单表体删行按钮处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-25 上午08:34:57
 */
public class PraybillDelLineAction extends BodyDelLineAction {

  private static final long serialVersionUID = 6113648250538466001L;

  /**
   * 方法功能描述：得到要删除的表体
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-7 下午06:10:00
   */
  private PraybillItemVO[] getSelectedItemVOs() {
    BillCardPanel panel = this.getCardPanel();
    int[] selectIndex = panel.getBodyPanel().getTable().getSelectedRows();
    if (null == selectIndex || 0 == selectIndex.length) {
      return null;
    }

    PraybillVO vo =
        (PraybillVO) panel.getBillValueVO(PraybillVO.class.getName(),
            PraybillHeaderVO.class.getName(), PraybillItemVO.class.getName());
    // Map<String, PraybillItemVO> itemMap =
    // AggVOUtil.createItemMap(new PraybillVO[] {
    // vo
    // });
    BillIndex index = new BillIndex(new PraybillVO[] {
      vo
    });
    IVOMeta meta = vo.getMetaData().getVOMeta(PraybillItemVO.class);

    List<PraybillItemVO> list = new ArrayList<PraybillItemVO>();
    for (int i = 0; i < selectIndex.length; ++i) {
      String pk_Praybill_b =
          (String) panel.getBodyValueAt(selectIndex[i],
              PraybillItemVO.PK_PRAYBILL_B);
      if (!StringUtil.isEmptyWithTrim(pk_Praybill_b)) {
        // list.add(itemMap.get(pk_Praybill_b));
        list.add((PraybillItemVO) index.get(meta, pk_Praybill_b));
      }
    }

    if (0 == list.size()) {
      return null;
    }

    ListToArrayTool<PraybillItemVO> tool =
        new ListToArrayTool<PraybillItemVO>();
    return tool.convertToArray(list);
  }

  @Override
  protected boolean doBeforeAction(ActionEvent e) {
    super.doBeforeAction(e);

    // 如果是新增状态，不必检查
    if (AppUiState.ADD.getUiState() == this.getModel().getUiState()) {
      return true;
    }

    // 得到要删除的表体VO数组
    PraybillItemVO[] itemVOs = this.getSelectedItemVOs();
    if (ArrayUtils.isEmpty(itemVOs)) {
      return true;
    }
    StringBuilder sb = new StringBuilder();
    for (PraybillItemVO itemVO : itemVOs) {
      if (PuRefBillTypeIdEnum.M4A08.getBillTypeId().equals(
          itemVO.getCsourcetypecode())) {
        sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4004020_0", "04004020-0110", null, new String[] {
              itemVO.getCrowno()
            })/* @res "第{0}行来源于资产配置申请,不能删除\n" */);
      }
      if (null != itemVO.getBrowclose() && itemVO.getBrowclose().booleanValue()) {
        sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4004020_0", "04004020-0009", null, new String[] {
              itemVO.getCrowno()
            })/* @res "第{0}行已经关闭 \n" */);
      }

      if (MathTool.compareTo(itemVO.getNaccumulatenum(), UFDouble.ZERO_DBL) > 0
          || null != itemVO.getNgenct() && itemVO.getNgenct().intValue() > 0
          || null != itemVO.getNpriceauditbill()
          && itemVO.getNpriceauditbill().intValue() > 0
          || null != itemVO.getNquotebill()
          && itemVO.getNquotebill().intValue() > 0
          || null != itemVO.getBpublishtoec()
          && itemVO.getBpublishtoec().booleanValue()) {
        sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4004020_0", "04004020-0010", null, new String[] {
              itemVO.getCrowno()
            })/* @res "第{0}行已经有后续单据 \n" */);
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
      return false;
    }

    return true;
  }

}
