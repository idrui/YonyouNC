package nc.impl.pu.m4t.action.rule.maintain;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 参数正确性检查：数组元素（VO）是否为空；VO是否有表体；
 * @scene
 * 
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-9-8 上午08:49:47
 * @author wuxla
 */
public class ParaValidityChkRule implements IRule<InitialEstVO> {

  @Override
  public void process(InitialEstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0112")/*
                                                                   * @res
                                                                   * "要操作的期初暂估单为空，请检查"
                                                                   */);
    }

    for (InitialEstVO vo : vos) {
      if (null == vo) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004060_0", "04004060-0112")/*
                                                                     * @res
                                                                     * "要操作的期初暂估单为空，请检查"
                                                                     */);
        return;
      }

      if (null == vo.getHeader()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004060_0", "04004060-0113")/*
                                                                     * @res
                                                                     * "要操作的期初暂估单表头为空，请检查"
                                                                     */);
      }

      if (ArrayUtils.isEmpty(vo.getItems())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004060_0", "04004060-0114")/*
                                                                     * @res
                                                                     * "要操作的期初暂估单表体为空，请检查"
                                                                     */);
      }

      List<InitialEstItemVO> list = new ArrayList<InitialEstItemVO>();
      for (InitialEstItemVO itemVO : vo.getItems()) {
        if (null == itemVO) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004060_0", "04004060-0115")/*
                                                                       * @res
                                                                       * "要操作的期初暂估单存在空行，请检查"
                                                                       */);
        }
        if (itemVO != null && itemVO.getStatus() != VOStatus.DELETED) {
          list.add(itemVO);
        }
      }

      if (list.size() == 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004060_0", "04004060-0114")/*
                                                                     * @res
                                                                     * "要操作的期初暂估单表体为空，请检查"
                                                                     */);
      }
    }

  }

}
