package nc.impl.pu.m23.qc.action.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m23.entity.ArriveBbVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.qc.pub.util.QCSysParamUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * �����ӱ��кϸ����������ϸ�����
 * @scene
 * �������ʼ�
 * @param
 * isQcCheck
 *
 * @since 6.3
 * @version 2010-9-28 ����04:35:14
 * @author guizhw
 */


public class WriteBackArriveCheckNumRule implements IRule<ArriveVO> {
  private boolean isQcCheck;

  public WriteBackArriveCheckNumRule(boolean isQcCheck) {
    this.isQcCheck = isQcCheck;
  }

  @Override
  public void process(ArriveVO[] vos) {
    String pk_org = vos[0].getHVO().getPk_org();
    // for (int i = 0; i < vos.length; i++) {
    if (!SysInitGroupQuery.isQCEnabled()
        || UFBoolean.FALSE.equals(ValueUtils.getUFBoolean(QCSysParamUtil
            .getINI01(pk_org)))) {
      this.insertBBTable(vos);
      this.updateBTableEligNum(vos);
    }
    // }
  }

  private void insertBBTable(ArriveVO[] vos) {
    // 1����֯���ӱ�����
    List<ArriveBbVO> writeParas = new ArrayList<ArriveBbVO>();
    for (int i = 0; i < vos.length; i++) {
      ArriveItemVO[] bvos = vos[i].getBVO();
      for (ArriveItemVO vo : bvos) {
        if (vo.getNwillelignum().compareTo(UFDouble.ZERO_DBL) > 0) {
          ArriveBbVO bbVO = new ArriveBbVO();
          bbVO.setNnum(vo.getNwillelignum());
          bbVO.setNastnum(vo.getNwillelignum());
          // ����
          bbVO.setPk_group(vo.getPk_group());
          // ����
          bbVO.setPk_arriveorder(vo.getPk_arriveorder());
          bbVO.setPk_arriveorder_b(vo.getPk_arriveorder_b());
          // �Ƿ����
          bbVO.setBcanstore(UFBoolean.TRUE);
          // �Ƿ�ϸ�
          bbVO.setBeligible(UFBoolean.TRUE);
          // �������
          bbVO.setPk_inbatchcode(vo.getPk_batchcode());
          bbVO.setVinbatchcode(vo.getVbatchcode());
          writeParas.add(bbVO);
        }
        if (vo.getNwillnotelignum().compareTo(UFDouble.ZERO_DBL) > 0) {
          ArriveBbVO bbVO = new ArriveBbVO();
          bbVO.setNnum(vo.getNwillnotelignum());
          bbVO.setNastnum(vo.getNwillnotelignum());
          // ����
          bbVO.setPk_group(vo.getPk_group());
          // ����
          bbVO.setPk_arriveorder(vo.getPk_arriveorder());
          bbVO.setPk_arriveorder_b(vo.getPk_arriveorder_b());
          // �Ƿ����
          bbVO.setBcanstore(UFBoolean.FALSE);
          // �Ƿ�ϸ�
          bbVO.setBeligible(UFBoolean.FALSE);
          // �������
          bbVO.setPk_inbatchcode(vo.getPk_batchcode());
          bbVO.setVinbatchcode(vo.getVbatchcode());
          writeParas.add(bbVO);
        }
      }
    }
    ArriveBbVO[] bbvos = writeParas.toArray(new ArriveBbVO[writeParas.size()]);
    if (!ArrayUtils.isEmpty(bbvos)) {
      // 2���������ӱ�����
      VOInsert<ArriveBbVO> util = new VOInsert<ArriveBbVO>();
      util.insert(bbvos);
    }

  }

  private void updateBTableEligNum(ArriveVO[] vos) {
    List<ArriveItemVO> volists = new ArrayList<ArriveItemVO>();
    for (int i = 0; i < vos.length; i++) {
      ArriveItemVO[] bvos = vos[i].getBVO();
      for (int j = 0; j < bvos.length; j++) {
        if (this.isQcCheck) {
          bvos[j].setNaccumchecknum(MathTool.add(bvos[j].getNaccumchecknum(),
              bvos[j].getNchecknum()));
          bvos[j].setNelignum(MathTool.add(bvos[j].getNelignum(),
              bvos[j].getNwillelignum()));
          bvos[j].setNnotelignum(MathTool.add(bvos[j].getNnotelignum(),
              bvos[j].getNwillnotelignum()));
        }
        else {
          bvos[j].setNelignum(UFDouble.ZERO_DBL);
          bvos[j].setNnotelignum(UFDouble.ZERO_DBL);
          bvos[j].setNaccumchecknum(UFDouble.ZERO_DBL);
        }
        volists.add(bvos[j]);
      }
    }
    VOUpdate<ArriveItemVO> util = new VOUpdate<ArriveItemVO>();
    util.update(volists.toArray(new ArriveItemVO[volists.size()]),
        new String[] {
          ArriveItemVO.NACCUMCHECKNUM, ArriveItemVO.NELIGNUM,
          ArriveItemVO.NNOTELIGNUM
        });
  }
}
