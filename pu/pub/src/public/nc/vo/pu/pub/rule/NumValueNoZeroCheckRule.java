package nc.vo.pu.pub.rule;

import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;

/**
 * 检查数值型字段不能为零或空的规则
 * 
 * @since 6.0
 * @version 2011-11-4 上午11:28:23
 * @author zhaoyha
 */
public class NumValueNoZeroCheckRule<E extends AbstractBill> implements
    IRule<E> {

  /** 行号 */
  public static final String CROWNO = "crowno";

  /** 单据号 **/
  public static final String VBILLCODE = "vbillcode";

  private MapList<String, String> checkItemMap;

  /**
   * @param checkItemMap {对应元数据名称（如pu.po_order_b），要检查的字段}
   */
  public NumValueNoZeroCheckRule(MapList<String, String> checkItemMap) {
    super();
    this.checkItemMap = checkItemMap;
  }

  @Override
  public void process(E[] vos) {
    StringBuilder msg = new StringBuilder();
    StringBuilder aggVOMsg = new StringBuilder();
    for (E vo : vos) {
      this.checkVO(aggVOMsg, vo);
      if (aggVOMsg.length() > 0) {
        String vbillcode =
            (String) vo.getParent().getAttributeValue(
                NumValueNoZeroCheckRule.VBILLCODE);
        msg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
            "04004000-0129", null, new String[] {
              null == vbillcode ? "" : vbillcode
            })/* 单据号[{0}]以下字段不允许为空或零： */);
        msg.append(SystemUtils.LINE_SEPARATOR).append(aggVOMsg);
      }
    }
    if (aggVOMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(StringUtils.stripEnd(
          msg.toString(), SystemUtils.LINE_SEPARATOR));
    }
  }

  private void checkBodys(StringBuilder singleMsg, E vo) {
    IVOMeta[] metas = vo.getMetaData().getChildren();
    for (IVOMeta meta : metas) {
      List<String> checkItemLst = this.checkItemMap.get(meta.getEntityName());
      ISuperVO[] bodys = vo.getChildren(meta);
      if (CollectionUtils.isEmpty(checkItemLst) || ArrayUtils.isEmpty(bodys)) {
        return;
      }
      this.checkVOItems(singleMsg, bodys, meta, checkItemLst);
    }

  }

  private void checkHead(StringBuilder singleMsg, E vo) {
    String headMetaName = vo.getParent().getMetaData().getEntityName();
    List<String> checkItemLst = this.checkItemMap.get(headMetaName);
    if (null == checkItemLst || checkItemLst.isEmpty()) {
      return;
    }
    ISuperVO checkvo = vo.getParent();
    this.checkVOItems(singleMsg, new ISuperVO[] {
      checkvo
    }, checkvo.getMetaData(), checkItemLst);
  }

  private void checkVO(StringBuilder aggVOMsg, E vo) {
    StringBuilder headMsg = new StringBuilder();
    StringBuilder bodyMsg = new StringBuilder();
    // 先检查表头
    this.checkHead(headMsg, vo);
    if (headMsg.length() > 0) {
      aggVOMsg.append(headMsg);
    }
    // 再检查表体
    this.checkBodys(bodyMsg, vo);
    if (bodyMsg.length() > 0) {
      aggVOMsg.append(bodyMsg);
    }
  }

  private void checkVOItems(StringBuilder msg, ISuperVO[] checkvos,
      IVOMeta meta, List<String> checkItemLst) {
    for (ISuperVO checkvo : checkvos) {
      StringBuilder oneVOMsg = new StringBuilder();
      for (String item : checkItemLst) {
        Object value = checkvo.getAttributeValue(item);
        if (value instanceof UFDouble && !MathTool.isZero((UFDouble) value)) {
          continue;
        }
        oneVOMsg.append("[").append(meta.getAttribute(item)).append("]");
      }
      if (oneVOMsg.length() == 0) {
        continue;
      }
      String no =
          (String) checkvo.getAttributeValue(NumValueNoZeroCheckRule.CROWNO);
      if (!StringUtils.isBlank(no)) {
        msg.append(
            NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
                "04004000-0130", null, new String[] {
                  no
                })/* 第{0}行: */).append(oneVOMsg);
      }
      else {
        msg.append(oneVOMsg);
      }
      msg.append(SystemUtils.LINE_SEPARATOR);
    }
  }
}
