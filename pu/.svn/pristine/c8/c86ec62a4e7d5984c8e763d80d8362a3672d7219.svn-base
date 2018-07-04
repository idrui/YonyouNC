/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-2 下午04:41:32
 */
package nc.ui.pu.m20.editor.card.afteredit.body.pub;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.rule.SetOrdertrantype;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

/**
 * 设置订单类型默认值和参照
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
 * @author GGR
 * @time 2010-2-2 下午04:41:32
 */
public class SetOrdertrantypeF {
  /**
   * 根据请购单表体的物料或者对应的物料分类+采购组织匹配物料订单类型设置中找到相应的采购订单交易类型，<br>
   * 其中匹配时物料及物料分类按照明细优先的规则进行。<br>
   * 如果匹配物料订单类型设置未匹配到对应值，则匹配上下游单据接口关系定义获取默认值。<br>
   * 如果表头“委外”勾选，则匹配上下游单据接口关系定义获取默认值，并且手工编辑时只能参照委外订单的交易类型。
   * <p>
   * <b>参数说明</b>
   * 
   * @param card
   *          卡片面板
   * @param row
   *          当然选择行
   * @since 6.0
   * @author GGR
   * @throws BusinessException
   * @time 2010-2-2 下午04:43:52
   */
  public void setDefOrdertrantypecode(BillCardPanel card, IKeyValue keyValue,
      int[] rows) {
    Boolean bsctype =
        (Boolean) card.getHeadItem(PraybillHeaderVO.BSCTYPE).getValueObject();
    FilterTransTypeRefUtils filter =
        new FilterTransTypeRefUtils(
            card.getBodyItem(PraybillItemVO.CORDERTRANTYPECODE), (String) card
                .getHeadItem(PraybillHeaderVO.PK_ORG).getValueObject());
    if (null != bsctype && bsctype.booleanValue()) {
      // 设置订单类型只能参照委外订单的交易类型
      filter.filterTranType(new String[] {
        SCBillType.Order.getCode()
      });
    }
    else {
      // 设置订单交易类型参照过滤
      filter.filterTranType(new String[] {
        POBillType.Order.getCode()
      });
    }

    new SetOrdertrantype().setOrdertrantype(keyValue, rows);
  }
}
