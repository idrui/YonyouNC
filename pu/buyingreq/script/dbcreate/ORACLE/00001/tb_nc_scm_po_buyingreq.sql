/* tablename: �빺���ӱ� */
create table po_praybill_b (pk_praybill_b char(20) not null 
/*�빺���ӱ�*/,
pk_group varchar2(20) null 
/*����???��*/,
pk_org varchar2(20) null 
/*�����֯���°汾*/,
pk_org_v varchar2(20) null 
/*�����֯*/,
crowno varchar2(20) null 
/*�к�*/,
pk_srcmaterial varchar2(20) null 
/*������Ϣ*/,
pk_material varchar2(20) null 
/*�������°汾*/,
castunitid varchar2(20) null 
/*��λ*/,
nastnum number(28,8) null 
/*����*/,
vchangerate varchar2(60) null 
/*������*/,
cunitid varchar2(20) null 
/*����λ*/,
nnum number(28,8) null 
/*������*/,
pk_reqdept varchar2(20) null 
/*���������°汾*/,
pk_reqdept_v varchar2(20) null 
/*������*/,
ntaxprice number(28,8) null 
/*�����Һ�˰����*/,
ntaxmny number(28,8) null 
/*���Ҽ�˰�ϼ�*/,
dreqdate varchar2(19) null 
/*��������*/,
dsuggestdate varchar2(19) null 
/*���鶩������*/,
pk_purchaseorg varchar2(20) null 
/*�ɹ���֯���°汾*/,
pk_purchaseorg_v varchar2(20) null 
/*�ɹ���֯*/,
bcanpurchaseorgedit char(1) null 
/*�ɹ���֯�ɱ༭*/,
cordertrantypecode varchar2(20) null 
/*��������*/,
pk_suggestsupplier varchar2(20) null 
/*���鹩Ӧ��*/,
pk_reqstor varchar2(20) null 
/*����ֿ�*/,
csourcetypecode varchar2(20) null 
/*��Դ��������*/,
csourceid varchar2(20) null 
/*��Դ���ݱ�ʶ*/,
csourcebid varchar2(20) null 
/*��Դ���ݷ�¼��ʶ*/,
vsourcecode varchar2(40) null 
/*��Դ���ݺ�*/,
vsourcerowno varchar2(20) null 
/*��Դ�����к�*/,
vsrctrantypecode varchar2(20) null 
/*��Դ��������*/,
cfirsttypecode varchar2(20) null 
/*Դͷ��������*/,
cfirstid varchar2(20) null 
/*Դͷ���ݱ�ʶ*/,
cfirstbid varchar2(20) null 
/*Դͷ���ݷ�¼��ʶ*/,
vfirstcode varchar2(40) null 
/*Դͷ���ݺ�*/,
vfirstrowno varchar2(20) null 
/*Դͷ�����к�*/,
vfirsttrantype varchar2(20) null 
/*Դͷ��������*/,
cprojectid varchar2(20) null 
/*��Ŀ*/,
cproductorid varchar2(20) null 
/*��������*/,
pk_batchcode varchar2(20) null 
/*���ε���*/,
vbatchcode varchar2(40) null 
/*���κ�*/,
pk_employee varchar2(20) null 
/*�ɹ�Ա*/,
vfree1 varchar2(101) null 
/*���ɸ�������1*/,
vfree2 varchar2(101) null 
/*���ɸ�������2*/,
vfree3 varchar2(101) null 
/*���ɸ�������3*/,
vfree4 varchar2(101) null 
/*���ɸ�������4*/,
vfree5 varchar2(101) null 
/*���ɸ�������5*/,
vfree6 varchar2(101) null 
/*���ɸ�������6*/,
vfree7 varchar2(101) null 
/*���ɸ�������7*/,
vfree8 varchar2(101) null 
/*���ɸ�������8*/,
vfree9 varchar2(101) null 
/*���ɸ�������9*/,
vfree10 varchar2(101) null 
/*���ɸ�������10*/,
vbdef1 varchar2(101) null 
/*�Զ�����1*/,
vbdef2 varchar2(101) null 
/*�Զ�����2*/,
vbdef3 varchar2(101) null 
/*�Զ�����3*/,
vbdef4 varchar2(101) null 
/*�Զ�����4*/,
vbdef5 varchar2(101) null 
/*�Զ�����5*/,
vbdef6 varchar2(101) null 
/*�Զ�����6*/,
vbdef7 varchar2(101) null 
/*�Զ�����7*/,
vbdef8 varchar2(101) null 
/*�Զ�����8*/,
vbdef9 varchar2(101) null 
/*�Զ�����9*/,
vbdef10 varchar2(101) null 
/*�Զ�����10*/,
vbdef11 varchar2(101) null 
/*�Զ�����11*/,
vbdef12 varchar2(101) null 
/*�Զ�����12*/,
vbdef13 varchar2(101) null 
/*�Զ�����13*/,
vbdef14 varchar2(101) null 
/*�Զ�����14*/,
vbdef15 varchar2(101) null 
/*�Զ�����15*/,
vbdef16 varchar2(101) null 
/*�Զ�����16*/,
vbdef17 varchar2(101) null 
/*�Զ�����17*/,
vbdef18 varchar2(101) null 
/*�Զ�����18*/,
vbdef19 varchar2(101) null 
/*�Զ�����19*/,
vbdef20 varchar2(101) null 
/*�Զ�����20*/,
vbmemo varchar2(181) null 
/*��ע*/,
naccumulatenum number(28,8) null 
/*�ۼƶ���������*/,
ngenct integer null 
/*���ɺ�ͬ����*/,
npriceauditbill integer null 
/*���ɼ۸�����������*/,
nquotebill integer null 
/*����ѯ���۵�����*/,
pk_product varchar2(20) null 
/*��Ʒ*/,
pk_product_v varchar2(20) null 
/*��Ʒ�汾*/,
pk_customer varchar2(20) null 
/*���ۿͻ�*/,
browclose char(1) null 
/*�йر�*/,
dbilldate char(19) null 
/*�빺����*/,
bpublishtoec char(1) null 
/*��������������*/,
pk_praybill char(20) not null 
/*�빺������_����*/,
casscustid varchar2(20) null 
/*�ͻ�*/,
cprojecttaskid varchar2(20) null 
/*��Ŀ����*/,
bisgensaorder char(1) null 
/*��������������*/,
pk_reqstoorg_v varchar2(20) null 
/*ԭʼ��������֯*/,
pk_reqstoorg varchar2(20) null 
/*ԭʼ��������֯���°汾*/,
cffileid varchar2(20) null 
/*������*/,
bisarrange char(1) null 
/*�Ѱ���*/,
 constraint pk_po_praybill_b primary key (pk_praybill_b),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: �빺������ */
create table po_praybill (pk_praybill char(20) not null 
/*�빺��*/,
pk_group varchar2(20) null 
/*��������*/,
pk_org varchar2(20) null 
/*�����֯���°汾*/,
pk_org_v varchar2(20) null 
/*�����֯*/,
vbillcode varchar2(40) null 
/*�빺����*/,
dbilldate char(19) null 
/*�빺����*/,
bsctype char(1) null 
/*ί��*/,
fpraysource integer null 
/*�빺��Դ*/,
vtrantypecode varchar2(20) null 
/*�빺���ͱ���*/,
pk_planpsn varchar2(20) null 
/*�ƻ�Ա*/,
pk_plandept varchar2(20) null 
/*�ƻ��������°汾*/,
pk_plandept_v varchar2(20) null 
/*�ƻ�����*/,
bdirecttransit char(1) null 
/*ֱ��*/,
ntotalastnum number(28,8) null 
/*������*/,
ntotaltaxmny number(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ccurrencyid varchar2(20) null 
/*���ұ���*/,
nversion integer null 
/*�汾��*/,
vdef1 varchar2(101) null 
/*�Զ�����1*/,
vdef2 varchar2(101) null 
/*�Զ�����2*/,
vdef3 varchar2(101) null 
/*�Զ�����3*/,
vdef4 varchar2(101) null 
/*�Զ�����4*/,
vdef5 varchar2(101) null 
/*�Զ�����5*/,
vdef6 varchar2(101) null 
/*�Զ�����6*/,
vdef7 varchar2(101) null 
/*�Զ�����7*/,
vdef8 varchar2(101) null 
/*�Զ�����8*/,
vdef9 varchar2(101) null 
/*�Զ�����9*/,
vdef10 varchar2(101) null 
/*�Զ�����10*/,
vdef11 varchar2(101) null 
/*�Զ�����11*/,
vdef12 varchar2(101) null 
/*�Զ�����12*/,
vdef13 varchar2(101) null 
/*�Զ�����13*/,
vdef14 varchar2(101) null 
/*�Զ�����14*/,
vdef15 varchar2(101) null 
/*�Զ�����15*/,
vdef16 varchar2(101) null 
/*�Զ�����16*/,
vdef17 varchar2(101) null 
/*�Զ�����17*/,
vdef18 varchar2(101) null 
/*�Զ�����18*/,
vdef19 varchar2(101) null 
/*�Զ�����19*/,
vdef20 varchar2(101) null 
/*�Զ�����20*/,
vmemo varchar2(181) null 
/*��ע*/,
fbillstatus integer null 
/*����״̬*/,
creator varchar2(20) null 
/*������*/,
creationtime char(19) null 
/*����ʱ��*/,
billmaker varchar2(20) null 
/*�Ƶ���*/,
dmakedate char(19) null 
/*�Ƶ�����*/,
approver varchar2(20) null 
/*������*/,
taudittime varchar2(19) null 
/*��������*/,
creviseoperid varchar2(20) null 
/*�޶���*/,
trevisiontime varchar2(19) null 
/*�޶�����*/,
modifier varchar2(20) null 
/*����޸���*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
iprintcount integer null 
/*��ӡ����*/,
bislatest char(1) default 'Y' null 
/*���°汾*/,
pk_srcpraybill varchar2(20) null 
/*�빺�����°汾*/,
ctrantypeid varchar2(20) null 
/*�빺����*/,
 constraint pk_po_praybill primary key (pk_praybill),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

