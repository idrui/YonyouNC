package nc.vo.pu.m21.rule;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialTaxRateService;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFDouble;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置行默认税率为物料档案上的设置的税率
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-1 下午08:02:50
 */
public class DefaultTaxRateSetter {
  private IKeyValue keyValue;

  public DefaultTaxRateSetter(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：设置行默认税率为物料档案上的设置的税率
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   *          <p>
   * @since 6.0
   * @author zhiwh
   * @time 2011-8-8 下午08:04:33
   */
  public void setDefaultTaxtRate(int[] rows) {
    if (ArrayUtils.isEmpty(rows)) {
      return;
    }
    String[] ids = new String[rows.length];
    int i = 0;
    for (int row : rows) {
      ids[i] =
          (String) this.keyValue.getBodyValue(row, OrderItemVO.PK_MATERIAL);
      i++;
    }
    Map<String, UFDouble> taxs = MaterialTaxRateService.queryTaxRate(ids);

    for (int row : rows) {
      this.keyValue.setBodyValue(row, OrderItemVO.NTAXRATE,
          taxs.get(this.keyValue.getBodyValue(row, OrderItemVO.PK_MATERIAL)));
    }
  }
}
