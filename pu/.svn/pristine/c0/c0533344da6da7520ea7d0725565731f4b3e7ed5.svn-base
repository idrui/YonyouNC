/**
 * $文件说明$
 *
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-11-3 上午11:08:20
 */
package nc.impl.pu.m21transtype.rule;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.pubitf.pu.m20.pub.IQueryPrayBill;
import nc.pubitf.pu.m21.pub.IOrderPubQuery;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
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
public class CheckTranTypeReference implements ICompareRule<PoTransTypeVO> {
  private static Set<String> canChgAttSet = new HashSet<String>(
      Arrays.asList(new String[] {
        PoTransTypeVO.IPRTOPOLIMIT, PoTransTypeVO.BCHECKCENPUR,
        PoTransTypeVO.IONWAYEND
      }));

  private static Set<String> notCheckAttSet = new HashSet<String>(
      Arrays.asList(new String[] {
        PoTransTypeVO.IAPPROVEAFT, PoTransTypeVO.ICONFIRMAFT,
        PoTransTypeVO.ICONSIGNAFT, PoTransTypeVO.ICUSTOMAFT,
        PoTransTypeVO.IOUTCUSTOMAFT, PoTransTypeVO.IOUTPUTAFT,
        PoTransTypeVO.ILOADAFT
      }));

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PoTransTypeVO[] vos, PoTransTypeVO[] oldVos) {

    Map<String, PoTransTypeVO> oldMap = CirVOUtil.createKeyVOMap(oldVos);
    for (PoTransTypeVO vo : vos) {
      // 循环检查，前台一般只修改一个交易类型
      this.check(vo, oldMap.get(vo.getPk_potrantype()));
    }
  }

  private void check(PoTransTypeVO vo, PoTransTypeVO oldVo) {
    try {
      String[] transTypes = new String[] {
        vo.getVtrantypecode()
      };
      IOrderPubQuery service =
          NCLocator.getInstance().lookup(IOrderPubQuery.class);
      String[] refs = service.checkTransTypeReference(transTypes);
      // 此处应该是检查请购单表体是否引用的订单类型
      if (ArrayUtils.isEmpty(refs)) {

        String[] ctrantypeids = new String[] {
          vo.getCtrantypeid()
        };
        IQueryPrayBill service1 =
            NCLocator.getInstance().lookup(IQueryPrayBill.class);
        refs = service1.checkOrderTransTypeReference(ctrantypeids);
      }
      if (ArrayUtils.isEmpty(refs)) {
        return;
      }
      // 特殊处理一下，正式盘中（预置脚本正确的情况下）可以删除
      if (null == oldVo.getIonwayend()) {
        oldVo.setIonwayend(Integer.valueOf(0));
      }
      if (null == oldVo.getIonwaybegin()) {
        oldVo.setIonwaybegin(Integer.valueOf(0));
      }
      Set<String> chgAttSet =
          new VOTool().getDifferentFieldForPersistent(vo, oldVo);
      if (null == chgAttSet) {
        return;
      }
      chgAttSet.removeAll(CheckTranTypeReference.canChgAttSet);
      chgAttSet.removeAll(CheckTranTypeReference.notCheckAttSet);
      if (chgAttSet.size() > 0) {
        this.getErrMsg(vo);
      }
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  private void getErrMsg(PoTransTypeVO vo) {
    StringBuilder sb = new StringBuilder();
    for (String canChgAttr : CheckTranTypeReference.canChgAttSet) {
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
