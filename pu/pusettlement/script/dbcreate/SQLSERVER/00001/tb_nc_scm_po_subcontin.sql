/* tablename: ί�мӹ������ */
create table po_subcontinfi (
pk_stockps nchar(20) not null 
/*ί�мӹ�������*/,
pk_group nvarchar(20) null default '~' 
/*����*/,
pk_org nvarchar(20) null default '~' 
/*�����֯*/,
pk_org_v nvarchar(20) null default '~' 
/*�����֯�汾*/,
pk_corp nvarchar(20) null default '~' 
/*��˾���°�*/,
pk_corp_v nvarchar(20) null default '~' 
/*��˾*/,
cbilltypecode nvarchar(20) null 
/*�������ͱ���*/,
vtrantypecode nvarchar(20) null default '~' 
/*�������ͱ���*/,
pk_busitype nvarchar(20) null default '~' 
/*ҵ������*/,
vbillcode nvarchar(40) null 
/*��ⵥ��*/,
dbilldate nchar(19) null 
/*�������*/,
pk_stordoc nvarchar(20) null default '~' 
/*�ֿ�*/,
pk_dept nvarchar(20) null default '~' 
/*����ԭʼ��Ϣ*/,
pk_dept_v nvarchar(20) null default '~' 
/*����*/,
pk_psndoc nvarchar(20) null default '~' 
/*ҵ��Ա*/,
cwhsmanagerid nvarchar(20) null default '~' 
/*���Ա*/,
billmaker nvarchar(20) null default '~' 
/*�Ƶ���*/,
vnote nvarchar(181) null 
/*��ע*/,
freplenishflag nchar(1) null 
/*�˻���־*/,
modifier nvarchar(20) null default '~' 
/*����޸���*/,
modifiedtime nchar(19) null 
/*����޸�ʱ��*/,
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
creationtime nchar(19) null 
/*����ʱ��*/,
creator nvarchar(20) null default '~' 
/*������*/,
bnormpur nchar(1) null default 'Y' 
/*�Ƿ���ͨ�ɹ�*/,
ctrantypeid nvarchar(20) null default '~' 
/*��������*/,
approver nvarchar(20) null 
/*ǩ����*/,
taudittime nchar(19) null 
/*ǩ������*/,
 constraint pk_po_subcontinfi primary key (pk_stockps),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ί�мӹ��������ϸ */
create table po_subcontinfi_b (
pk_stockps_b nchar(20) not null 
/*ί�мӹ�����ϸ����*/,
crowno nvarchar(20) null 
/*�к�*/,
pk_group nvarchar(20) null default '~' 
/*����*/,
pk_org nvarchar(20) null default '~' 
/*�����֯*/,
pk_org_v nvarchar(20) null default '~' 
/*�����֯�汾*/,
pk_financeorg nvarchar(20) null default '~' 
/*���������֯*/,
pk_financeorg_v nvarchar(20) null default '~' 
/*���������֯�汾*/,
pk_corp nvarchar(20) null default '~' 
/*��˾���°汾*/,
pk_corp_v nvarchar(20) null default '~' 
/*��˾*/,
pk_apfinanceorg nvarchar(20) null default '~' 
/*Ӧ��������֯*/,
pk_apfinanceorg_v nvarchar(20) null default '~' 
/*Ӧ��������֯�汾*/,
pk_costregion nvarchar(20) null default '~' 
/*�ɱ���*/,
pk_purchaseorg nvarchar(20) null default '~' 
/*�ɹ���֯*/,
pk_purchaseorg_v nvarchar(20) null default '~' 
/*�ɹ���֯�汾*/,
pk_apliabcenter nvarchar(20) null default '~' 
/*��������*/,
pk_apliabcenter_v nvarchar(20) null default '~' 
/*�������İ汾*/,
pk_srcmaterial nvarchar(20) null default '~' 
/*����*/,
pk_material nvarchar(20) null default '~' 
/*���ϱ���*/,
pk_batchcode nvarchar(20) null 
/*���ε���*/,
vbatchcode nvarchar(40) null 
/*���κ�*/,
dbizdate nchar(19) null 
/*ҵ������*/,
dtocostapdate nchar(19) null 
/*�ݹ�����*/,
castunitid nvarchar(20) null default '~' 
/*��λ*/,
ninnum decimal(28,8) null 
/*���������*/,
ninassistnum decimal(28,8) null 
/*ʵ�븨����*/,
cprojectid nvarchar(20) null default '~' 
/*��Ŀ*/,
vchangerate nvarchar(60) null 
/*������*/,
csourcetypecode nvarchar(20) null default '~' 
/*��Դ��������*/,
vsourcetrantype nvarchar(20) null default '~' 
/*��Դ��������*/,
csourceid nvarchar(20) null 
/*��Դ���ݱ�ͷ*/,
csourcebid nvarchar(20) null 
/*��Դ���ݱ���*/,
vsourcecode nvarchar(40) null 
/*��Դ���ݺ�*/,
vsourcerowno nvarchar(20) null 
/*��Դ�����к�*/,
cfirsttypecode nvarchar(20) null default '~' 
/*Դͷ��������*/,
vfirsttrantype nvarchar(20) null default '~' 
/*Դͷ���ݽ�������*/,
cfirstid nvarchar(20) null 
/*Դͷ���ݱ�ͷ*/,
cfirstbid nvarchar(20) null 
/*Դͷ���ݱ���*/,
vfirstcode nvarchar(40) null 
/*Դͷ���ݺ�*/,
vfirstrowno nvarchar(20) null 
/*Դͷ�����к�*/,
dfirstbilldate nvarchar(19) null 
/*Դͷ�����Ƶ�����*/,
vnotebody nvarchar(181) null 
/*�б�ע*/,
vordertrantypecode nvarchar(50) null 
/*������������*/,
pk_order nvarchar(20) null 
/*����*/,
pk_order_b nvarchar(20) null 
/*������ϸ*/,
vordercode nvarchar(50) null 
/*������*/,
cproductorid nvarchar(20) null 
/*��������*/,
cstateid nvarchar(20) null 
/*���״̬*/,
cqualitylevelid nvarchar(20) null default '~' 
/*�����ȼ�*/,
pk_supplier nvarchar(20) null default '~' 
/*��Ӧ��*/,
vctcode nvarchar(50) null 
/*�ɹ���ͬ��*/,
cunitid nvarchar(20) null default '~' 
/*����λ*/,
ncostprice decimal(28,8) null 
/*����*/,
ncostmny decimal(28,8) null 
/*���*/,
nplannedprice decimal(28,8) null 
/*�ƻ�����*/,
nplannedmny decimal(28,8) null 
/*�ƻ����*/,
ccurrencyid nvarchar(20) null 
/*��λ��*/,
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
vproductbillcode nvarchar(40) null 
/*����������*/,
vproductbatch nvarchar(40) null 
/*��������*/,
cworkcenterid nvarchar(20) null default '~' 
/*��������*/,
ftoiaflag int null default 0 
/*���ɱ���־*/,
baffectcost nchar(1) null default 'Y' 
/*Ӱ��ɱ���־*/,
bsettlefinish nchar(1) null 
/*�Ƿ�������*/,
naccumsettlenum decimal(28,8) null 
/*�ۼƽ�������*/,
naccestcostwashmny decimal(28,8) null 
/*�ۼƻس��ݹ��ɱ����*/,
naccgoodssettlemny decimal(28,8) null 
/*�ۼƻ��������*/,
naccsettlemny decimal(28,8) null 
/*�ۼƽ�����*/,
naccfeesettlemny decimal(28,8) null 
/*�ۼƷ��ý�����*/,
pk_stockps nchar(20) not null 
/*ί�мӹ������_����*/,
casscustid nvarchar(20) null 
/*�ͻ�*/,
cffileid nvarchar(20) null 
/*������*/,
foutputtype int null 
/*��Ʒ����*/,
 constraint pk_o_subcontinfi_b primary key (pk_stockps_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

