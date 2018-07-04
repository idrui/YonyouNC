package nc.vo.pu.report.adjustor;

import java.io.Serializable;

import nc.itf.iufo.freereport.extend.IAreaCondition;
import nc.itf.iufo.freereport.extend.IReportAdjustor;
import nc.pub.smart.metadata.Field;
import nc.pub.smart.model.SmartModel;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;

import org.apache.commons.lang.StringUtils;

import com.ufida.dataset.IContext;
import com.ufida.iufo.table.exarea.ExtendAreaCell;
import com.ufida.iufo.table.exarea.ExtendAreaModel;
import com.ufida.report.anareport.areaset.AreaContentSet;
import com.ufida.report.anareport.areaset.AreaContentSetUtil;
import com.ufida.report.anareport.areaset.AreaFieldSet;
import com.ufida.report.anareport.model.AbsAnaReportModel;
import com.ufida.report.anareport.model.AnaReportModel;
import com.ufida.report.anareport.util.AnaReportFieldUtil;
import com.ufsoft.table.CellsModel;

/**
 * �ɹ������ʽ������
 * 
 * @since 6.0
 * @version 2011-2-22 ����10:59:28
 * @author yinfy
 */

public class PuReportAdjustor implements IReportAdjustor, Serializable {

  private static final long serialVersionUID = -7048559727020929541L;

  @Override
  public void doAreaAdjust(IContext context, String areaPK,
      IAreaCondition areaCond, AbsAnaReportModel reportModel) {
    // �������õ�����ģ��
    SmartModel smart = reportModel.getAreaData(areaPK).getSmartModel();

    CellsModel cm = reportModel.getFormatModel();
    ExtendAreaModel em = ExtendAreaModel.getInstance(cm);
    ExtendAreaCell ec = em.getExAreaByPK(areaPK);
    Field[] flds = AnaReportFieldUtil.getFieldnInExtendArea(ec, cm);

    PuQueryInfoPara para =
        (PuQueryInfoPara) context.getAttribute(PuQueryInfoPara.QUERY_PARA);
    if (para == null) {
      return;
    }
    // �����ֶ�
    String[] keys = para.getHideKeys();
    int length = keys.length;
    AreaFieldSet[] detailinfo = new AreaFieldSet[length];
    for (int i = 0; i < length; i++) {
      Field feild = this.getFF(flds, keys[i]);
      detailinfo[i] = new AreaFieldSet(feild);
    }
    /**
     * ������������
     */
    AreaContentSet contentSet = new AreaContentSet();
    contentSet.setAreaPk(areaPK);
    contentSet.setSmartModelDefID(smart.getId());
    contentSet.setDetailFldNames(detailinfo); // ���������ֶ�

    // contentSet.setGroupFldNames(groupinfo); // ���ý������
    AreaContentSetUtil.resetExCellByHideFields(contentSet, true, reportModel);
  }

  @Override
  public void doReportAdjust(IContext context, AnaReportModel reportModel) {
    //
  }

  private Field getFF(Field[] flds, String key) {
    for (Field f : flds) {
      if (StringUtils.equalsIgnoreCase(f.getExpression(), key)) {
        return f;
      }
    }
    return null;
  }
}
