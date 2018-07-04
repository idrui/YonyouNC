package nc.impl.pu.m21.action.rule.revise;

import java.util.ArrayList;
import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m21.exception.AskReviseToleException;
import nc.vo.pu.pub.constant.PUParaValue.ctrltype;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 *            修订订单中，对数量进行检查，修订后的数量与原数量同正负，且不能小于后续数量
 * @scene
 *      订单修订
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-3-18 上午11:15:42
 * @author wuxla
 */
public class ReviseNumCheckRule implements ICompareRule<OrderVO> {
  private boolean isUserConfirm;

  public ReviseNumCheckRule(boolean isUserConfirm) {
    this.isUserConfirm = isUserConfirm;
  }

  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    if (this.isUserConfirm) {
      return;
    }
    OrderViewVO[] views =
        new BillToViewConvertor<OrderVO, OrderViewVO>(OrderViewVO.class)
            .convert(vos);
    List<String> bidList = new ArrayList<String>();
    for (OrderViewVO view : views) {
      bidList.add(view.getPk_order_b());
    }
    String[] bids = bidList.toArray(new String[bidList.size()]);
    String pk_org = views[0].getPk_org();
    this.checkArrv(pk_org, bids);
    this.checkStore(pk_org, bids);
  }

  private void checkArrv(String pk_org, String[] bids) {
    ctrltype tolerPara = PUSysParamUtil.getPO02(pk_org);
    if (ctrltype.not_control.equals(tolerPara)) {
      return;
    }
    this.toleranceCompare(OrderItemVO.NACCUMARRVNUM, bids,
        MaterialVO.INTOLERANCE, tolerPara);
  }

  private void checkStore(String pk_org, String[] bids) {
    ctrltype tolerPara = PUSysParamUtil.getPO03(pk_org);
    if (ctrltype.not_control.equals(tolerPara)) {
      return;
    }
    this.toleranceCompare(OrderItemVO.NACCUMSTORENUM, bids,
        MaterialVO.INTOLERANCE, tolerPara);
  }

  private String[] getCodeOverToler(String wbNumFiled, String[] srcBids,
      String tolerFiled) {
    // 物料基本信息表
    String mtable = MaterialVO.getDefaultTableName();

    DataAccessUtils utils = new DataAccessUtils();
    IDExQueryBuilder idQueryBuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_15.name());
    String bidsql = idQueryBuilder.buildSQL("t.pk_order_b", srcBids);

    String sql =
        "select m.code from po_order_b " + " t inner join " + mtable
            + " m on t.pk_material=m.pk_material where abs(t.nnum)" + "* cast((1+m."
            + tolerFiled + "/100.0)as decimal (16, 8))< abs(t." + wbNumFiled
            + ") and " + bidsql;

    return utils.query(sql).toOneDimensionStringArray();
  }

  private void toleranceCompare(String wbNumFiled, String[] srcBids,
      String tolerFiled, ctrltype tolerPara) {
    if (ArrayUtils.isEmpty(srcBids)) {
      return;
    }
    String[] results = this.getCodeOverToler(wbNumFiled, srcBids, tolerFiled);

    if (results != null && results.length > 0) {
      // 严格控制
      if (tolerPara.equals(ctrltype.not_save)) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004000_0", "04004000-0100", null, new String[] {
              results[0]
            })/* 物料{0}超出容差控制的行，请检查！ */);
      }
      // 提示
      else if (tolerPara.equals(ctrltype.ask)) {
        ExceptionUtils.wrappException(new AskReviseToleException(
            NCLangResOnserver.getInstance().getStrByID("4004000_0",
                "04004000-0101", null, new String[] {
                  results[0]
                })/* 物料{0}超出容差控制的行，是否继续！ */));
      }
    }
  }
}
