package nc.itf.pu;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;

/**
 * ���βɹ����������빺����ѯģ��ӿ�
 * 
 * @author �����
 * 2016-11-23
 */
public interface IQg20RefQueyService {
	//���ε���AggVo
	public PraybillVO[] queryM20ForTJ01(IQueryScheme queryScheme) throws BusinessException;
} 