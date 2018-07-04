/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-13 上午09:50:42
 */
package nc.ui.pu.est.model;

import nc.vo.bd.meta.IBDObject;
import nc.vo.bd.meta.IBDObjectAdapterFactory;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-13 上午09:50:42
 */
public class EstBDObjectAdapterFactory implements IBDObjectAdapterFactory {

  /**
   * 父类方法重写
   * 
   * @see nc.vo.bd.meta.IBDObjectAdapterFactory#createBDObject(java.lang.Object)
   */
  @Override
  public IBDObject createBDObject(Object obj) {
    if (!(obj instanceof AggregatedValueObject)) {
      ExceptionUtils
          .wrappBusinessException("served error,not support vo structure!!");
    }
    final CircularlyAccessibleValueObject header =
        ((AggregatedValueObject) obj).getParentVO();
    return new IBDObject() {

      @Override
      public Object getCode() {
        return null == header ? null : header
            .getAttributeValue(GoodsEstVO.VBILLCODE);
      }

      @Override
      public Object getId() {
        try {
          return null == header ? null : header.getPrimaryKey();
        }
        catch (BusinessException e) {
          ExceptionUtils.wrappException(e);
        }
        return null;
      }

      @Override
      public Object getName() {
        return null;
      }

      @Override
      public Object getPId() {
        return null;
      }

      @Override
      public Object getPk_group() {
        return null == header ? null : header
            .getAttributeValue(GoodsEstVO.PK_GROUP);
      }

      @Override
      public Object getPk_org() {
        return null;
      }

    };
  }

}
