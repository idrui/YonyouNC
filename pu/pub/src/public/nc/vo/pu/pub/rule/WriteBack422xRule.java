package nc.vo.pu.pub.rule;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.pubitf.pu.m422x.invp.inv9.IReWrite422xForInv9;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              请购单回写物资需求申请单
 * @scene
 *        请购单删除、修改，物资需求申请单删除、保存
 * @param String pk_billtype 单据类型
 * @since 6.3
 * @version 2014-10-21 上午9:29:56
 * @author yanxm5
 */
public class WriteBack422xRule<E extends AbstractBill> implements
    ICompareRule<E> {

  private String pk_billtype;

  public WriteBack422xRule(String pk_billtype) {
    this.pk_billtype = pk_billtype;
  }

  @Override
  public void process(E[] vos, E[] originVOs) {
    if (ArrayUtils.isEmpty(originVOs)) {
      // 新增
      return;
    }
    else if (ArrayUtils.isEmpty(vos)
        || VOStatus.DELETED == vos[0].getParent().getStatus()) {
      // 删除
      this.writeback422XWhenModify(null, originVOs);// 需求汇总生成的净需求进行删除，进行数量回写
    }
    else {
      // 修改
      this.writeback422XWhenModify(vos, originVOs);
    }
  }

  /**
   * 将bill 转换成 itemvoMap
   * 
   * @param vos
   * @return itemvoMap
   */
  private Map<String, ISuperVO> processBilltoItemMapUtil(E[] vos) {
    if (vos == null) {
      return null;
    }

    Map<String, ISuperVO> itemMap = new HashMap<String, ISuperVO>();
    IVOMeta meta = vos[0].getMetaData().getChildren()[0];
    for (E bill : vos) {
      ISuperVO[] itemVOs = bill.getChildren(meta);
      if (ArrayUtils.isEmpty(itemVOs)) {
        continue;
      }
      for (ISuperVO itemVO : itemVOs) {
        itemMap.put(itemVO.getPrimaryKey(), itemVO);
      }
    }
    return itemMap;
  }

  /**
   * 组织回写对象参数
   * 
   * @param vos
   * @return 回写物资需求ids
   */
  private Map<String, UFDouble> processReWriteParams(E[] vos, E[] originVOs) {
    // 回写前数据处理
    Map<String, UFDouble> praynum = new HashMap<String, UFDouble>();

    Map<String, ISuperVO> itemvos = this.processBilltoItemMapUtil(vos);

    IVOMeta meta = originVOs[0].getMetaData().getChildren()[0];
    for (E bill : originVOs) {
      ISuperVO[] itemVOs = bill.getChildren(meta);
      if (ArrayUtils.isEmpty(itemVOs)) {
        continue;
      }
      for (ISuperVO itemVO : itemVOs) {
        UFDouble oriNum =
            (UFDouble) itemVO.getAttributeValue(StoreReqAppItemVO.NNUM);
        UFDouble subNum =
            itemvos == null || itemvos.get(itemVO.getPrimaryKey()) == null ? UFDouble.ZERO_DBL
                : (UFDouble) itemvos.get(itemVO.getPrimaryKey())
                    .getAttributeValue(StoreReqAppItemVO.NNUM);
        praynum.put(itemVO.getPrimaryKey(), MathTool.sub(subNum, oriNum));
      }
    }
    return praynum;
  }

  /*
   * 需求汇总生成的净需求进行删除、修改主数量，进行数量回写上游
   */
  private void writeback422XWhenModify(E[] vos, E[] originVOs) {
    try {
      IReWrite422xForInv9 reWrite422xFor20 =
          NCLocator.getInstance().lookup(IReWrite422xForInv9.class);

      reWrite422xFor20.reWriteReqForDelete(
          this.processReWriteParams(vos, originVOs), this.pk_billtype);

    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }
}
