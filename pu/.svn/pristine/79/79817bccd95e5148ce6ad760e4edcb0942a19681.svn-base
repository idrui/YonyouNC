package nc.bs.pu.m27;

import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.tool.BillHelper;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.res.billtype.IBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 库存单据用于采购结算的财务副本生成公共处理类
 * 
 * @since 6.0
 * @version 2011-1-20 下午12:24:10
 * @author zhaoyha
 */
public abstract class AbstractStockFIDuplicateBP<E extends AbstractBill, T extends AbstractBill> {

  public void duplicate(E[] stockVos) {
    E[] nStockVos = this.beforeDup(stockVos);
    if (ArrayUtils.isEmpty(nStockVos)) {
      return;
    }
    T[] fivos = this.genFIVO(stockVos);
    AroundProcesser<T> prcr = new AroundProcesser<T>(null);
    this.addRule(prcr);
    prcr.before(fivos);
    this.insertFIVO(fivos);
    prcr.after(fivos);
  }

  private T[] genFIVO(E[] stockVos) {
    AggregatedValueObject[] vos =
        PfServiceScmUtil.executeVOChange(this.getStockBilltype().getCode(),
            this.getFIBilltype().getCode(), stockVos);
    return (T[]) vos;
  }

  private void genFK(T[] fivos) {
    for (T fivo : fivos) {
      String hid = fivo.getPrimaryKey();
      // 只取第1个子表，现在库存的副本最多包括一个子表
      ISuperVO[] items =
          fivo.getChildren(fivos[0].getMetaData().getChildren()[0]);
      if (ArrayUtils.isEmpty(items)) {
        continue;
      }
      String fkname =
          fivos[0].getMetaData().getParent().getPrimaryAttribute().getName();
      for (ISuperVO item : items) {
        item.setAttributeValue(fkname, hid);
      }
    }
  }

  private void insertFIVO(T[] fivos) {
    this.genFK(fivos);// 生成子表的外键
    BillHelper bh = new BillHelper(fivos);
    List<ISuperVO> hlist = bh.getParentList();
    MapList<IVOMeta, ISuperVO> bmap = bh.getItemIndex();
    try {
      BaseDAO dao = new BaseDAO();
      // 插入主表数据
      dao.insertVOArrayWithPK((SuperVO[]) new ListToArrayTool<ISuperVO>()
          .convertToArray(hlist));
      if (bmap.size() == 0) {
        return;
      }
      // 只取第1个子表，现在库存的副本最多包括一个子表
      List<ISuperVO> blist = bmap.get(fivos[0].getMetaData().getChildren()[0]);
      // 插入子表数据
      dao.insertVOArrayWithPK((SuperVO[]) new ListToArrayTool<ISuperVO>()
          .convertToArray(blist));
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
  protected abstract void addRule(AroundProcesser<T> prcr);

  /**
   * 生成副本前进行处理（如过滤）
   * 
   * @param stockVos
   * @return
   */
  protected E[] beforeDup(E[] stockVos) {
    return stockVos;
  }

  /**
   * 获得到库存的单据类型
   * 
   * @return
   */
  protected abstract IBillType getFIBilltype();

  /**
   * 获得结算财务副本的单据类型
   * 
   * @return
   */
  protected abstract IBillType getStockBilltype();
}
