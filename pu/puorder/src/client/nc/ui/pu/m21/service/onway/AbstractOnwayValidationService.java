/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-12 下午04:56:31
 */
package nc.ui.pu.m21.service.onway;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.uif2.validation.IValidationService;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.ui.pu.m21.view.OnwayStatusBillForm;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.m21.entity.OrderOnwayVO;
import nc.vo.pu.m21.rule.OnwayValidationTool;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>在途状态前台校验服务类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-12 下午04:56:31
 */
public abstract class AbstractOnwayValidationService implements
    IValidationService {

  private BillForm editor;

  public BillForm getEditor() {
    return this.editor;
  }

  public void setEditor(BillForm editor) {
    this.editor = editor;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.bs.uif2.validation.IValidationService#validate(java.lang.Object)
   */
  @Override
  public void validate(Object obj) throws ValidationException {
    try {
      OrderOnwayItemVO[] voitems =
          (OrderOnwayItemVO[]) ((OnwayStatusBillForm) this.editor)
              .getBodySelectedVOs();
      if (obj != null && obj instanceof OrderOnwayItemVO[]) {
        voitems = (OrderOnwayItemVO[]) obj;
      }

      Object valueObj = this.editor.getValue();
      if (valueObj instanceof OrderOnwayVO) {
        OrderOnwayVO vo = (OrderOnwayVO) valueObj;
        // 取得交易类型VO
        String vtranType = vo.getHVO().getVtrantypecode();

        Map<String, PoTransTypeVO> tranTypeMap =
            OnwayValidationTool.getTransTypeVO(new String[] {
              vtranType
            });
        PoTransTypeVO transtypeVO = tranTypeMap.get(vtranType);
        this.docheck(transtypeVO, voitems);
      }
    }
    catch (BusinessException e) {
      List<ValidationFailure> failMsg = new ArrayList<ValidationFailure>();
      ValidationFailure vf = new ValidationFailure();
      vf.setMessage(e.getMessage());
      failMsg.add(vf);
      ValidationException ex = new ValidationException(failMsg);
      throw ex;
    }
  }

  /**
   * 方法功能描述：分不同的在途状态进行校验
   * <p>
   * <b>参数说明</b>
   * 
   * @param transtypeVO
   * @param voitems
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-2 上午10:09:01
   */
  abstract void docheck(PoTransTypeVO transtypeVO, OrderOnwayItemVO[] voitems)
      throws BusinessException;
}
