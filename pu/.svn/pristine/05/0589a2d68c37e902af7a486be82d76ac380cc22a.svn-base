package nc.ui.pu.position.ref;

import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.ml.NCLangRes;

/**
 * 采购岗设置参照实现类
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置参照的表和数据列。
 * <li>重写环境查询条件取得方法。
 * </ul>
 * <p>
 *
 * @since 6.0
 * @version 2010-12-2 下午04:50:36
 * @author 高光荣
 */
public class PlanPositionRefModel extends AbstractRefModel {

  public PlanPositionRefModel(String refNodeName) {
    this.setRefNodeName(refNodeName);
  }

  @Override
  public void setRefNodeName(String refNodeName) {
    this.m_strRefNodeName = refNodeName;

    this.setFieldCode(new String[] {
      "code", "name", "cemployeeid"
    });
    // 需要多语言处理？？？
    this.setFieldName(new String[] {
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0001669")/*@res "岗位编码"*/, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0001657")/*@res "岗位名称"*/, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0004084")/*@res "采购员"*/
    });
    this.setHiddenFieldCode(new String[] {
      "pk_position"
    });
    this.setPkFieldCode("pk_position");
    this.setRefCodeField("code");
    this.setRefNameField("name");

    this.setTableName("po_position");
    this.setRefTitle(NCLangRes.getInstance().getStrByID("4004080_0", "04004080-0011")/*岗位设置*/);
    this.resetFieldName();
  }

  @Override
  protected String getEnvWherePart() {
    return "(pk_group = '" + this.getPk_group()
        + "' and dr = 0 and fpositiontype = 1)";
  }
}