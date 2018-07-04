/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-20 上午10:57:10
 */
package nc.impl.pu.m422x.action.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 *            物资需求申请单保存时，对单据进行检查
 * @scene
 *      物资需求申请单保存
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-7-20 上午10:57:10
 * @author wuxla
 */
public class ParaValidateRule implements IRule<StoreReqAppVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(StoreReqAppVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004010_0", "04004010-0011")/*
                                                                   * @res
                                                                   * "要操作的物资需求申请单为空，请检查"
                                                                   */);
    }

    for (StoreReqAppVO vo : vos) {
      if (null == vo) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004010_0", "04004010-0011")/*
                                                                     * @res
                                                                     * "要操作的物资需求申请单为空，请检查"
                                                                     */);
        return;
      }

      if (null == vo.getHVO()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004010_0", "04004010-0012")/*
                                                                     * @res
                                                                     * "要操作的物资需求申请单表头为空，请检查"
                                                                     */);
      }

      if (ArrayUtils.isEmpty(vo.getBVO())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004010_0", "04004010-0013")/*
                                                                     * @res
                                                                     * "要操作的物资需求申请单表体为空，请检查"
                                                                     */);
      }

      List<StoreReqAppItemVO> list = new ArrayList<StoreReqAppItemVO>();
      for (StoreReqAppItemVO itemVO : vo.getBVO()) {
        if (null == itemVO) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004010_0", "04004010-0014")/*
                                                                       * @res
                                                                       * "要操作的物资需求申请单存在空行，请检查"
                                                                       */);
        }
        if (itemVO != null && VOStatus.DELETED != itemVO.getStatus()) {
          list.add(itemVO);
        }
      }
      if (list.size() == 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004010_0", "04004010-0013")/*
                                                                     * @res
                                                                     * "要操作的物资需求申请单表体为空，请检查"
                                                                     */);
      }
    }
  }

}
