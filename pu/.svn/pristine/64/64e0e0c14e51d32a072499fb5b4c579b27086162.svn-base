/**
 * 
 */
package nc.impl.pu.m20trantype.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.pubitf.pu.m20.pub.IQueryPrayBill;
import nc.vo.pu.m20trantype.entity.BuyrTranTypeVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.VOTool;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.SystemUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version
 * @since
 * @author luojw
 * @time 2014-6-18 下午8:58:46
 */
public class CheckTranTypeRefForBuy implements ICompareRule<BuyrTranTypeVO> {

  /**
   * 可以修改的属性
   */
  private static Set<String> canChgAttSet = new HashSet<>();

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(BuyrTranTypeVO[] vos, BuyrTranTypeVO[] oldVos) {

    Map<String, BuyrTranTypeVO> oldMap = CirVOUtil.createKeyVOMap(oldVos);

    for (BuyrTranTypeVO vo : vos) {
      // 先循环处理
      this.checkInvoiceRef(vo, oldMap.get(vo.getPk_buyrtrantype()));
    }
  }

  /**
   * 方法功能描述：检查请购单是否引用
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo <p>
   * @since 6.0
   * @author luojw
   * @param oldVo
   * @time 2014-11-3 下午03:29:40
   */
  private void checkInvoiceRef(BuyrTranTypeVO vo, BuyrTranTypeVO oldVo) {
    try {
      IQueryPrayBill service =
          NCLocator.getInstance().lookup(IQueryPrayBill.class);
      String[] refTransTypes = service.checkTransTypeReference(new String[] {
        vo.getVtrantypecode()
      });
      if (ArrayUtils.isEmpty(refTransTypes)) {
        return;
      }
      Set<String> chgAttSet =
          new VOTool().getDifferentFieldForPersistent(vo, oldVo);
      if (null == chgAttSet) {
        return;
      }
      chgAttSet.removeAll(CheckTranTypeRefForBuy.canChgAttSet);
      if (chgAttSet.size() > 0) {
        this.getErrMsg(vo);
      }
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  private void getErrMsg(BuyrTranTypeVO vo) {
    if (CheckTranTypeRefForBuy.canChgAttSet.size() == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0156")/*
                                                                   * @res
                                                                   * "此交易类型已被引用，没有属性允许修改。"
                                                                   */
      );
    }
    StringBuilder sb = new StringBuilder();
    for (String canChgAttr : CheckTranTypeRefForBuy.canChgAttSet) {
      sb.append(SystemUtils.LINE_SEPARATOR);
      sb.append("[");
      sb.append(vo.getMetaData().getAttribute(canChgAttr).toString());
      sb.append("]");
    }
    ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("4004000_0", "04004000-0053")/*
                                                                 * @res
                                                                 * "此交易类型已被引用，只允许修改以下属性："
                                                                 */
        + sb.toString());
  }
}
