package nc.bs.pu.m27;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

import org.apache.commons.lang.ArrayUtils;

/**
 * 库存单据用于采购结算的财务副本生成公共处理类
 * 
 * @since 6.0
 * @version 2011-1-20 下午02:42:22
 * @author zhaoyha
 */
public abstract class AbstractStockFICancelDupBP<E extends AbstractBill> {
  private Class<E> clazz;

  /**
   * @param clazz 副本的单据VO类
   */
  public AbstractStockFICancelDupBP(Class<E> clazz) {
    this.clazz = clazz;
  }

  public void cancelDuplicate(String[] hids) {
    E[] vos = this.queryFIVO(hids);
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    AroundProcesser<E> prcr = new AroundProcesser<E>(null);
    this.addRule(prcr);
    prcr.before(vos);
    this.realDelFIVO(hids, vos);
    prcr.after(vos);
  }

  private E[] queryFIVO(String[] hids) {
    BillQuery<E> bq = new BillQuery<E>(this.clazz);
    return bq.query(hids);
  }

  private void realDelFIVO(String[] hids, E[] vos) {
    try {
      BaseDAO dao = new BaseDAO();
      CircularlyAccessibleValueObject[] items = AggVOUtil.getCombinItemVOs(vos);
      if (!ArrayUtils.isEmpty(items)) {
        // 先删子表
        String[] bids =
            (String[]) AggVOUtil.getDistinctFieldArray(items,
                ((SuperVO) items[0]).getMetaData().getPrimaryAttribute()
                    .getName(), String.class);
        dao.deleteByPKs(items[0].getClass(), bids);
      }
      // 后删主表
      dao.deleteByPKs(vos[0].getParent().getClass(), hids);
    }
    catch (DAOException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 加入业务规则
   * 
   * @param prcr
   */
  protected abstract void addRule(AroundProcesser<E> prcr);

}
