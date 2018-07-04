/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-20 下午01:37:33
 */
package nc.ui.pu.m21.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.validation.IValidationService;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.ui.ml.NCLangRes;
import nc.ui.pu.m21.view.StatusBillFormEditor;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>验证要确认的单据
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-7-20 下午01:37:33
 */
public class ConfirmValidationService implements IValidationService {

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
      Object object = this.editor.getValue();
      if (object instanceof OrderVO) {
        OrderVO vo = (OrderVO) object;
        PoTransTypeVO pvo = this.getTransTypeVO(vo);
        OrderItemVO[] voitems =
            (OrderItemVO[]) ((StatusBillFormEditor) this.editor)
                .getBodySelectedVOs();
        if (obj != null && obj instanceof OrderItemVO[]) {
          voitems = (OrderItemVO[]) obj;
        }
        this.editor.getModel().getSelectedData();
        this.chkNotNullIfNeed(pvo, voitems);
        this.chkValid(pvo, voitems);
      }
    }
    catch (Exception e) {
      List<ValidationFailure> failMsg = new ArrayList<ValidationFailure>();
      ValidationFailure vf = new ValidationFailure();
      vf.setMessage(e.getMessage());
      failMsg.add(vf);
      ValidationException ex = new ValidationException(failMsg);
      throw ex;
    }
  }

  // 检查单据号、确认数量、确认日期
  private void chkNotNullIfNeed(PoTransTypeVO pvo, OrderItemVO[] vos)
      throws Exception {
    String retStr = "";
    if (null == pvo) {
      return;
    }
    for (int i = 0; i < vos.length; i++) {
      String crowno = vos[i].getCrowno();
      // 对方单据号
      if (ValueUtils.getBoolean(pvo.getBconfirmcode())) {
        if (null == vos[i].getVvendorordercode()
            || "".equals(vos[i].getVvendorordercode().trim())) {
          if (!"".equals(retStr.toString())) {
            retStr += ", ";
          }
          retStr +=
              NCLangRes.getInstance().getStrByID("4004030_0", "04004030-0222",
                  null, new String[] {
                    crowno
                  })/* 第{0}行对方订单号不能为空 */;
        }
      }
      // 确认数量
      if (ValueUtils.getBoolean(pvo.getBconfirmnum())) {
        if (null == vos[i].getNconfirmnum()) {
          if (!"".equals(retStr.toString())) {
            retStr += ", ";
          }
          retStr +=
              NCLangRes.getInstance().getStrByID("4004030_0", "04004030-0223",
                  null, new String[] {
                    crowno
                  })/* 第{0}行确认数量不能为空 */;
        }
        else {
          UFDouble connum = ValueUtils.getUFDouble(vos[i].getNconfirmnum());
          if (connum.compareTo(vos[i].getNnum()) > 0) {
            if (!"".equals(retStr.toString())) {
              retStr += ", ";
            }
            retStr +=
                NCLangRes.getInstance().getStrByID("4004030_0",
                    "04004030-0224", null, new String[] {
                      crowno
                    })/* 第{0}行确认数量不能大于订单数量 */;
          }
        }
      }
      // 确认日期
      if (ValueUtils.getBoolean(pvo.getBconfirmdate())) {
        if (null == vos[i].getDconfirmdate()) {
          if (!"".equals(retStr.toString())) {
            retStr += ", ";
          }
          retStr +=
              NCLangRes.getInstance().getStrByID("4004030_0", "04004030-0225",
                  null, new String[] {
                    crowno
                  })/* 第{0}行确认日期不能为空 */;
        }
      }
    }
    if (!"".equals(retStr.toString())) {
      retStr =
          NCLangRes.getInstance().getStrByID("4004030_0", "04004030-0226",
              null, new String[] {
                retStr
              })/* 选择的{0} */;
      throw new BusinessException(retStr);
    }
  }

  // 检验用户输入业务数据的有效性
  private void chkValid(PoTransTypeVO pvo, OrderItemVO[] vos) throws Exception {
    // String retStr = "";
    StringBuilder retStr = new StringBuilder();
    if (null == pvo) {
      return;
    }
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (int i = 0; i < vos.length; i++) {
      String crowno = vos[i].getCrowno();
      // 确认数量不能大于订单数量
      if (ValueUtils.getBoolean(pvo.getBconfirmnum())) {
        if (null != vos[i].getNconfirmnum()) {
          UFDouble connum = ValueUtils.getUFDouble(vos[i].getNconfirmnum());
          if (connum.compareTo(UFDouble.ZERO_DBL) <= 0) {
            if (!"".equals(retStr.toString())) {
              retStr.append(", ");
            }
            retStr.append(NCLangRes.getInstance().getStrByID("4004030_0",
                "04004030-0227", null, new String[] {
                  crowno
                })/* 第{0}行确认数量不能为0或负 */);
          }
          if (connum.compareTo(vos[i].getNnum()) > 0) {
            if (!"".equals(retStr.toString())) {
              retStr.append(", ");
            }
            retStr.append(NCLangRes.getInstance().getStrByID("4004030_0",
                "04004030-0224", null, new String[] {
                  crowno
                })/* 第{0}行确认数量不能大于订单数量 */);
            // retStr += "第" + (i + 1) + "行确认数量不能大于订单数量";
          }
        }
      }
      // 确认日期
      if (ValueUtils.getBoolean(pvo.getBconfirmdate())) {
        if (null != vos[i].getDconfirmdate()) {
          UFDate date = ValueUtils.getUFDate(vos[i].getDconfirmdate());
          UFDate dateNow = AppContext.getInstance().getBusiDate();
          if (!date.isSameDate(dateNow) && date.compareTo(dateNow) > 0) {
            if (!"".equals(retStr.toString())) {
              retStr.append(", ");
            }
            retStr.append(NCLangRes.getInstance().getStrByID("4004030_0",
                "04004030-0228", null, new String[] {
                  crowno
                })/* 第{0}行确认日期不能大于当前日期 */);
          }
        }
      }
    }
    if (!"".equals(retStr.toString())) {
      String errMsg =
          NCLangRes.getInstance().getStrByID("4004030_0", "04004030-0226",
              null, new String[] {
                retStr.toString()
              })/* 选择的{0} */;
      // retStr = "选择的" + retStr;
      throw new BusinessException(errMsg);
    }
  }

  // 获取交易类型
  private PoTransTypeVO getTransTypeVO(OrderVO vo) {
    String vtranType = vo.getHVO().getVtrantypecode();
    PoTransTypeVO pvo = null;
    try {
      HashMap<String, PoTransTypeVO> map =
          NCLocator.getInstance().lookup(IPoTransTypeQuery.class)
              .queryAttrByTypes(new String[] {
                vtranType
              }, null);
      pvo = map.get(vtranType);
    }
    catch (BusinessException ex) {
      // 日志异常
      ExceptionUtils.wrappException(ex);
    }
    return pvo;
  }

}
