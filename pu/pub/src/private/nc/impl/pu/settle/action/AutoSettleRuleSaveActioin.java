/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-18 下午09:13:31
 */
package nc.impl.pu.settle.action;

import nc.bs.bd.cache.CacheProxy;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.vo.pu.m27.entity.InvoiceStockOptionableVO;
import nc.vo.pu.m27.entity.RBInvoiceOptionableVO;
import nc.vo.pu.m27.entity.RBStockOptionableVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>自动结算规则保存动作类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-18 下午09:13:31
 */
public class AutoSettleRuleSaveActioin {
  /**
   * 方法功能描述：保存自动结算规则
   * <p>
   * <b>参数说明</b>
   * 
   * @param rules
   *          0：红蓝入库单结算 1：红蓝发票结算 2：发票与入库单结算
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-18 下午09:14:25
   */
  public SuperVO[] saveAutoSettleRule(SuperVO[] rules) {
    if (ArrayUtils.isEmpty(rules)) {
      return null;
    }

    RBStockOptionableVO rbstock = this.saveRbstock(rules[0]);
    RBInvoiceOptionableVO rbinvoice = this.saveRbinvoice(rules[1]);
    InvoiceStockOptionableVO invoicestock = this.saveInvoiceStock(rules[2]);

    return new SuperVO[] {
      rbstock, rbinvoice, invoicestock
    };
  }

  /**
   * 方法功能描述：检查Ts
   * <p>
   * <b>参数说明</b>
   * 
   * @param ts
   * @param originTs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 上午08:33:30
   */
  private void checkTs(UFDateTime ts, UFDateTime originTs) {
    boolean flag = true;
    if (null == ts) {
      flag = false;
    }
    else if (!ObjectUtils.equals(ts, originTs)) {
      flag = false;
    }

    if (!flag) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0055")/*
                                                                   * @res
                                                                   * "出现并发，请重新查询"
                                                                   */);
    }
  }

  /**
   * 方法功能描述：保存发票与入库单结算规则
   * <p>
   * <b>参数说明</b>
   * 
   * @param rule
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 下午01:22:10
   */
  private InvoiceStockOptionableVO saveInvoiceStock(SuperVO rule) {
    if (null == rule) {
      return null;
    }
    InvoiceStockOptionableVO invoicestock = (InvoiceStockOptionableVO) rule;
    // 通知前台缓存版本变化
    CacheProxy.fireDataUpdated(invoicestock.getTableName());

    if (null == invoicestock.getPk_invoicestock()) {
      VOInsert<InvoiceStockOptionableVO> insert =
          new VOInsert<InvoiceStockOptionableVO>();
      return insert.insert(new InvoiceStockOptionableVO[] {
        invoicestock
      })[0];
    }

    new VOConcurrentTool().lock(new InvoiceStockOptionableVO[] {
      invoicestock
    });

    String pk = invoicestock.getPk_invoicestock();
    VOQuery<InvoiceStockOptionableVO> query =
        new VOQuery<InvoiceStockOptionableVO>(InvoiceStockOptionableVO.class);
    InvoiceStockOptionableVO originInvoiceStock = query.query(new String[] {
      pk
    })[0];

    this.checkTs(invoicestock.getTs(), originInvoiceStock.getTs());

    invoicestock.setPk_invoicestock(null);
    VOInsert<InvoiceStockOptionableVO> insert =
        new VOInsert<InvoiceStockOptionableVO>();
    return insert.insert(new InvoiceStockOptionableVO[] {
      invoicestock
    })[0];

    // VOUpdate<InvoiceStockOptionableVO> update =
    // new VOUpdate<InvoiceStockOptionableVO>();
    // return update.update(new InvoiceStockOptionableVO[] {
    // invoicestock
    // }, new InvoiceStockOptionableVO[] {
    // originInvoiceStock
    // })[0];
  }

  /**
   * 方法功能描述：保存红蓝发票结算规则
   * <p>
   * <b>参数说明</b>
   * 
   * @param rule
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 上午08:42:12
   */
  private RBInvoiceOptionableVO saveRbinvoice(SuperVO rule) {
    if (null == rule) {
      return null;
    }
    RBInvoiceOptionableVO rbinvoice = (RBInvoiceOptionableVO) rule;
    // 通知前台缓存版本变化
    CacheProxy.fireDataUpdated(rbinvoice.getTableName());

    if (null == rbinvoice.getPk_rbinvoice()) {
      VOInsert<RBInvoiceOptionableVO> insert =
          new VOInsert<RBInvoiceOptionableVO>();
      return insert.insert(new RBInvoiceOptionableVO[] {
        rbinvoice
      })[0];
    }

    new VOConcurrentTool().lock(new RBInvoiceOptionableVO[] {
      rbinvoice
    });

    String pk = rbinvoice.getPk_rbinvoice();
    VOQuery<RBInvoiceOptionableVO> query =
        new VOQuery<RBInvoiceOptionableVO>(RBInvoiceOptionableVO.class);
    RBInvoiceOptionableVO originRbInvoice = query.query(new String[] {
      pk
    })[0];

    this.checkTs(rbinvoice.getTs(), originRbInvoice.getTs());

    rbinvoice.setPk_rbinvoice(null);
    VOInsert<RBInvoiceOptionableVO> insert =
        new VOInsert<RBInvoiceOptionableVO>();
    return insert.insert(new RBInvoiceOptionableVO[] {
      rbinvoice
    })[0];

    // VOUpdate<RBInvoiceOptionableVO> update =
    // new VOUpdate<RBInvoiceOptionableVO>();
    // return update.update(new RBInvoiceOptionableVO[] {
    // rbinvoice
    // }, new RBInvoiceOptionableVO[] {
    // originRbInvoice
    // })[0];
  }

  /**
   * 方法功能描述：保存红蓝入库单结算规则
   * <p>
   * <b>参数说明</b>
   * 
   * @param rule
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 上午08:33:35
   */
  private RBStockOptionableVO saveRbstock(SuperVO rule) {
    if (null == rule) {
      return null;
    }
    RBStockOptionableVO rbstock = (RBStockOptionableVO) rule;
    // 通知前台缓存版本变化
    CacheProxy.fireDataUpdated(rbstock.getTableName());

    if (null == rbstock.getPk_rbstock()) {
      VOInsert<RBStockOptionableVO> insert =
          new VOInsert<RBStockOptionableVO>();
      return insert.insert(new RBStockOptionableVO[] {
        rbstock
      })[0];
    }

    new VOConcurrentTool().lock(new RBStockOptionableVO[] {
      rbstock
    });

    String pk = rbstock.getPk_rbstock();
    VOQuery<RBStockOptionableVO> query =
        new VOQuery<RBStockOptionableVO>(RBStockOptionableVO.class);
    RBStockOptionableVO originRbstock = query.query(new String[] {
      pk
    })[0];

    this.checkTs(rbstock.getTs(), originRbstock.getTs());

    rbstock.setPk_rbstock(null);
    VOInsert<RBStockOptionableVO> insert = new VOInsert<RBStockOptionableVO>();
    return insert.insert(new RBStockOptionableVO[] {
      rbstock
    })[0];

    // VOUpdate<RBStockOptionableVO> update = new
    // VOUpdate<RBStockOptionableVO>();
    // return update.update(new RBStockOptionableVO[] {
    // rbstock
    // }, new RBStockOptionableVO[] {
    // originRbstock
    // })[0];
  }

}
