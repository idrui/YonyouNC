/**
 * $文件说明$
 *
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-11-3 上午11:08:20
 */
package nc.impl.pu.m25trantype.rule;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.pubitf.pu.m25.pu.pub.IQueryInvoice;
import nc.vo.pu.m25trantype.entity.InvcTranTypeVO;
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
 * <li>检验交易类型是否被引用
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-11-3 上午11:08:20
 */
public class CheckTranTypeRefForInv implements ICompareRule<InvcTranTypeVO> {

  private static Set<String> canChgAttSet =
      new HashSet<String>(Arrays.asList(new String[] {
        InvcTranTypeVO.BSENDPAY
      }));

  /**
   * 父类方法重写
   *
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(InvcTranTypeVO[] vos, InvcTranTypeVO[] oldVos) {

    Map<String, InvcTranTypeVO> oldMap = CirVOUtil.createKeyVOMap(oldVos);

    for (InvcTranTypeVO vo : vos) {
      // 先循环处理
      this.checkInvoiceRef(vo, oldMap.get(vo.getPk_invctrantype()));
    }
  }

  /**
   * 方法功能描述：检查发票是否引用
   * <p>
   * <b>参数说明</b>
   *
   * @param vo <p>
   * @since 6.0
   * @author wanghuid
   * @param oldVo
   * @time 2010-11-3 下午03:29:40
   */
  private void checkInvoiceRef(InvcTranTypeVO vo, InvcTranTypeVO oldVo) {
    try {
      IQueryInvoice service =
          NCLocator.getInstance().lookup(IQueryInvoice.class);
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
      chgAttSet.removeAll(CheckTranTypeRefForInv.canChgAttSet);
      if (chgAttSet.size() > 0) {
        this.getErrMsg(vo);
      }
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  private void getErrMsg(InvcTranTypeVO vo) {
    StringBuilder sb = new StringBuilder();
    for (String canChgAttr : CheckTranTypeRefForInv.canChgAttSet) {
      sb.append(SystemUtils.LINE_SEPARATOR);
      sb.append("[");
      sb.append(vo.getMetaData().getAttribute(canChgAttr).toString());
      sb.append("]");
    }
    ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0053")/*@res "此交易类型已被引用，只允许修改以下属性："*/
        + sb.toString());
  }
}