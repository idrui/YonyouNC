/* tablename: �빺���ӱ� */
create table po_praybill_b (
pk_praybill_b nchar(20) not null 
/*�빺���ӱ�*/,
pk_group nvarchar(20) null 
/*����???��*/,
pk_org nvarchar(20) null 
/*�����֯���°汾*/,
pk_org_v nvarchar(20) null 
/*�����֯*/,
crowno nvarchar(20) null 
/*�к�*/,
pk_srcmaterial nvarchar(20) null 
/*������Ϣ*/,
pk_material nvarchar(20) null 
/*�������°汾*/,
castunitid nvarchar(20) null 
/*��λ*/,
nastnum decimal(28,8) null 
/*����*/,
vchangerate nvarchar(60) null 
/*������*/,
cunitid nvarchar(20) null 
/*����λ*/,
nnum decimal(28,8) null 
/*������*/,
pk_reqdept nvarchar(20) null 
/*���������°汾*/,
pk_reqdept_v nvarchar(20) null 
/*������*/,
ntaxprice decimal(28,8) null 
/*�����Һ�˰����*/,
ntaxmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
dreqdate nvarchar(19) null 
/*��������*/,
dsuggestdate nvarchar(19) null 
/*���鶩������*/,
pk_purchaseorg nvarchar(20) null 
/*�ɹ���֯���°汾*/,
pk_purchaseorg_v nvarchar(20) null 
/*�ɹ���֯*/,
bcanpurchaseorgedit nchar(1) null 
/*�ɹ���֯�ɱ༭*/,
cordertrantypecode nvarchar(20) null 
/*��������*/,
pk_suggestsupplier nvarchar(20) null 
/*���鹩Ӧ��*/,
pk_reqstor nvarchar(20) null 
/*����ֿ�*/,
csourcetypecode nvarchar(20) null 
/*��Դ��������*/,
csourceid nvarchar(20) null 
/*��Դ���ݱ�ʶ*/,
csourcebid nvarchar(20) null 
/*��Դ���ݷ�¼��ʶ*/,
vsourcecode nvarchar(40) null 
/*��Դ���ݺ�*/,
vsourcerowno nvarchar(20) null 
/*��Դ�����к�*/,
vsrctrantypecode nvarchar(20) null 
/*��Դ��������*/,
cfirsttypecode nvarchar(20) null 
/*Դͷ��������*/,
cfirstid nvarchar(20) null 
/*Դͷ���ݱ�ʶ*/,
cfirstbid nvarchar(20) null 
/*Դͷ���ݷ�¼��ʶ*/,
vfirstcode nvarchar(40) null 
/*Դͷ���ݺ�*/,
vfirstrowno nvarchar(20) null 
/*Դͷ�����к�*/,
vfirsttrantype nvarchar(20) null 
/*Դͷ��������*/,
cprojectid nvarchar(20) null 
/*��Ŀ*/,
cproductorid nvarchar(20) null 
/*��������*/,
pk_batchcode nvarchar(20) null 
/*���ε���*/,
vbatchcode nvarchar(40) null 
/*���κ�*/,
pk_employee nvarchar(20) null 
/*�ɹ�Ա*/,
vfree1 nvarchar(101) null 
/*���ɸ�������1*/,
vfree2 nvarchar(101) null 
/*���ɸ�������2*/,
vfree3 nvarchar(101) null 
/*���ɸ�������3*/,
vfree4 nvarchar(101) null 
/*���ɸ�������4*/,
vfree5 nvarchar(101) null 
/*���ɸ�������5*/,
vfree6 nvarchar(101) null 
/*���ɸ�������6*/,
vfree7 nvarchar(101) null 
/*���ɸ�������7*/,
vfree8 nvarchar(101) null 
/*���ɸ�������8*/,
vfree9 nvarchar(101) null 
/*���ɸ�������9*/,
vfree10 nvarchar(101) null 
/*���ɸ�������10*/,
vbdef1 nvarchar(101) null 
/*�Զ�����1*/,
vbdef2 nvarchar(101) null 
/*�Զ�����2*/,
vbdef3 nvarchar(101) null 
/*�Զ�����3*/,
vbdef4 nvarchar(101) null 
/*�Զ�����4*/,
vbdef5 nvarchar(101) null 
/*�Զ�����5*/,
vbdef6 nvarchar(101) null 
/*�Զ�����6*/,
vbdef7 nvarchar(101) null 
/*�Զ�����7*/,
vbdef8 nvarchar(101) null 
/*�Զ�����8*/,
vbdef9 nvarchar(101) null 
/*�Զ�����9*/,
vbdef10 nvarchar(101) null 
/*�Զ�����10*/,
vbdef11 nvarchar(101) null 
/*�Զ�����11*/,
vbdef12 nvarchar(101) null 
/*�Զ�����12*/,
vbdef13 nvarchar(101) null 
/*�Զ�����13*/,
vbdef14 nvarchar(101) null 
/*�Զ�����14*/,
vbdef15 nvarchar(101) null 
/*�Զ�����15*/,
vbdef16 nvarchar(101) null 
/*�Զ�����16*/,
vbdef17 nvarchar(101) null 
/*�Զ�����17*/,
vbdef18 nvarchar(101) null 
/*�Զ�����18*/,
vbdef19 nvarchar(101) null 
/*�Զ�����19*/,
vbdef20 nvarchar(101) null 
/*�Զ�����20*/,
vbmemo nvarchar(181) null 
/*��ע*/,
naccumulatenum decimal(28,8) null 
/*�ۼƶ���������*/,
ngenct int null 
/*���ɺ�ͬ����*/,
npriceauditbill int null 
/*���ɼ۸�����������*/,
nquotebill int null 
/*����ѯ���۵�����*/,
pk_product nvarchar(20) null 
/*��Ʒ*/,
pk_product_v nvarchar(20) null 
/*��Ʒ�汾*/,
pk_customer nvarchar(20) null 
/*���ۿͻ�*/,
browclose nchar(1) null 
/*�йر�*/,
dbilldate nchar(19) null 
/*�빺����*/,
bpublishtoec nchar(1) null 
/*��������������*/,
pk_praybill nchar(20) not null 
/*�빺������_����*/,
casscustid nvarchar(20) null 
/*�ͻ�*/,
cprojecttaskid nvarchar(20) null 
/*��Ŀ����*/,
bisgensaorder nchar(1) null 
/*��������������*/,
pk_reqstoorg_v nvarchar(20) null 
/*ԭʼ��������֯*/,
pk_reqstoorg nvarchar(20) null 
/*ԭʼ��������֯���°汾*/,
cffileid nvarchar(20) null 
/*������*/,
bisarrange nchar(1) null 
/*�Ѱ���*/,
 constraint pk_po_praybill_b primary key (pk_praybill_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �빺������ */
create table po_praybill (
pk_praybill nchar(20) not null 
/*�빺��*/,
pk_group nvarchar(20) null 
/*��������*/,
pk_org nvarchar(20) null 
/*�����֯���°汾*/,
pk_org_v nvarchar(20) null 
/*�����֯*/,
vbillcode nvarchar(40) null 
/*�빺����*/,
dbilldate nchar(19) null 
/*�빺����*/,
bsctype nchar(1) null 
/*ί��*/,
fpraysource int null 
/*�빺��Դ*/,
vtrantypecode nvarchar(20) null 
/*�빺���ͱ���*/,
pk_planpsn nvarchar(20) null 
/*�ƻ�Ա*/,
pk_plandept nvarchar(20) null 
/*�ƻ��������°汾*/,
pk_plandept_v nvarchar(20) null 
/*�ƻ�����*/,
bdirecttransit nchar(1) null 
/*ֱ��*/,
ntotalastnum decimal(28,8) null 
/*������*/,
ntotaltaxmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ccurrencyid nvarchar(20) null 
/*���ұ���*/,
nversion int null 
/*�汾��*/,
vdef1 nvarchar(101) null 
/*�Զ�����1*/,
vdef2 nvarchar(101) null 
/*�Զ�����2*/,
vdef3 nvarchar(101) null 
/*�Զ�����3*/,
vdef4 nvarchar(101) null 
/*�Զ�����4*/,
vdef5 nvarchar(101) null 
/*�Զ�����5*/,
vdef6 nvarchar(101) null 
/*�Զ�����6*/,
vdef7 nvarchar(101) null 
/*�Զ�����7*/,
vdef8 nvarchar(101) null 
/*�Զ�����8*/,
vdef9 nvarchar(101) null 
/*�Զ�����9*/,
vdef10 nvarchar(101) null 
/*�Զ�����10*/,
vdef11 nvarchar(101) null 
/*�Զ�����11*/,
vdef12 nvarchar(101) null 
/*�Զ�����12*/,
vdef13 nvarchar(101) null 
/*�Զ�����13*/,
vdef14 nvarchar(101) null 
/*�Զ�����14*/,
vdef15 nvarchar(101) null 
/*�Զ�����15*/,
vdef16 nvarchar(101) null 
/*�Զ�����16*/,
vdef17 nvarchar(101) null 
/*�Զ�����17*/,
vdef18 nvarchar(101) null 
/*�Զ�����18*/,
vdef19 nvarchar(101) null 
/*�Զ�����19*/,
vdef20 nvarchar(101) null 
/*�Զ�����20*/,
vmemo nvarchar(181) null 
/*��ע*/,
fbillstatus int null 
/*����״̬*/,
creator nvarchar(20) null 
/*������*/,
creationtime nchar(19) null 
/*����ʱ��*/,
billmaker nvarchar(20) null 
/*�Ƶ���*/,
dmakedate nchar(19) null 
/*�Ƶ�����*/,
approver nvarchar(20) null 
/*������*/,
taudittime nvarchar(19) null 
/*��������*/,
creviseoperid nvarchar(20) null 
/*�޶���*/,
trevisiontime nvarchar(19) null 
/*�޶�����*/,
modifier nvarchar(20) null 
/*����޸���*/,
modifiedtime nchar(19) null 
/*����޸�ʱ��*/,
iprintcount int null 
/*��ӡ����*/,
bislatest nchar(1) null default 'Y' 
/*���°汾*/,
pk_srcpraybill nvarchar(20) null 
/*�빺�����°汾*/,
ctrantypeid nvarchar(20) null 
/*�빺����*/,
 constraint pk_po_praybill primary key (pk_praybill),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

