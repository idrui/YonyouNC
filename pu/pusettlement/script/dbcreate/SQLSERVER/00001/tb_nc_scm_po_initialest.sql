/* tablename: �ڳ��ݹ�����ϸ */
create table po_initialest_b (
pk_initialest_b nvarchar(20) not null 
/*�ڳ��ݹ�����ϸ*/,
pk_org nvarchar(20) null 
/*������֯���°汾*/,
pk_org_v nvarchar(20) null 
/*������֯*/,
pk_group nvarchar(20) null 
/*��������*/,
pk_apfinanceorg nvarchar(20) null 
/*Ӧ����֯���°汾*/,
pk_apfinanceorg_v nvarchar(20) null 
/*Ӧ����֯*/,
crowno nvarchar(20) null 
/*�к�*/,
pk_material nvarchar(20) null 
/*����VID*/,
pk_srcmaterial nvarchar(20) null 
/*����OID*/,
castunitid nvarchar(20) null 
/*��λ*/,
nastnum decimal(28,8) null 
/*����*/,
cunitid nvarchar(20) null 
/*����λ*/,
nnum decimal(28,8) null 
/*������*/,
vchangerate nvarchar(60) null 
/*������*/,
ftaxtypeflag int null default 1 
/*��˰���*/,
ntaxrate decimal(9,6) null 
/*˰��*/,
vordertrantype nvarchar(20) null 
/*������������*/,
pk_order nvarchar(20) null 
/*����ID*/,
pk_order_b nvarchar(20) null 
/*������ID*/,
vordercode nvarchar(40) null 
/*������*/,
corderrowno nvarchar(20) null 
/*�����к�*/,
vctcode nvarchar(40) null 
/*��ͬ��*/,
vbatchcode nvarchar(40) null 
/*���κ�*/,
pk_batchcode nvarchar(20) null 
/*���ε���*/,
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
bsettlefinish nchar(1) null default 'N' 
/*�Ƿ�������*/,
naccsettlenum decimal(28,8) null 
/*�ۼƽ�������*/,
naccwashmny decimal(28,8) null 
/*�ۼƳ���ݹ����*/,
bestimateap nchar(1) null default 'N' 
/*�ݹ�Ӧ����־*/,
naccinvoicenum decimal(28,8) null 
/*�ۼƿ�Ʊ����*/,
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
cproductorid nvarchar(20) null 
/*��������*/,
cprojectid nvarchar(20) null 
/*��Ŀ*/,
norigprice decimal(28,8) null 
/*����˰����*/,
norigtaxprice decimal(28,8) null 
/*����˰����*/,
nprice decimal(28,8) null 
/*��������˰��*/,
ntaxprice decimal(28,8) null 
/*�����Һ�˰��*/,
nastorigprice decimal(28,8) null 
/*��˰����*/,
norigmny decimal(28,8) null 
/*��˰���*/,
nastorigtaxprice decimal(28,8) null 
/*��˰����*/,
norigtaxmny decimal(28,8) null 
/*��˰�ϼ�*/,
nastprice decimal(28,8) null 
/*������˰����*/,
nmny decimal(28,8) null 
/*������˰���*/,
nasttaxprice decimal(28,8) null 
/*���Һ�˰����*/,
ntax decimal(28,8) null 
/*˰��*/,
ntaxmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
naccgoodssettlemny decimal(28,8) null 
/*�ۼƻ��������*/,
naccsettlemny decimal(28,8) null 
/*�ۼƽ�����*/,
naccfeesettlemny decimal(28,8) null 
/*�ۼƷ��ý�����*/,
vbmemo nvarchar(181) null 
/*��ע*/,
csourceid nvarchar(20) null 
/*��Դ����*/,
csourcebid nvarchar(20) null 
/*��Դ������ϸ*/,
vsourcetrantype nvarchar(20) null 
/*��Դ��������*/,
vsourcecode nvarchar(40) null 
/*��Դ���ݺ�*/,
vsourcerowno nvarchar(20) null 
/*��Դ�����к�*/,
csourcetypecode nvarchar(20) null 
/*��Դ��������*/,
pk_initialest nchar(20) not null 
/*�ڳ��ݹ���_����*/,
casscustid nvarchar(20) null 
/*�ͻ�*/,
baffectcost nchar(1) null default 'Y' 
/*Ӱ��ɱ���־*/,
csendcountryid nvarchar(20) null 
/*��������/����*/,
crececountryid nvarchar(20) null 
/*�ջ�����/����*/,
ctaxcountryid nvarchar(20) null 
/*��˰����/����*/,
fbuysellflag int null 
/*��������*/,
btriatradeflag nchar(1) null 
/*����ó��*/,
ctaxcodeid nvarchar(20) null 
/*˰��*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
nnosubtaxrate decimal(28,8) null 
/*���ɵֿ�˰��*/,
nnosubtax decimal(28,8) null 
/*���ɵֿ�˰��*/,
ncalcostmny decimal(28,8) null 
/*�Ƴɱ����*/,
bopptaxflag nchar(1) null 
/*������˰*/,
nestcalcostprice decimal(28,8) null 
/*�Ƴɱ�����*/,
pk_apliabcenter nvarchar(20) null 
/*���������������°汾*/,
pk_apliabcenter_v nvarchar(20) null 
/*������������*/,
baffectpccost nchar(1) null 
/*Ӱ���������ĳɱ�*/,
cffileid nvarchar(20) null 
/*������*/,
 constraint pk_po_initialest_b primary key (pk_initialest_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �ڳ��ݹ��� */
create table po_initialest (
pk_initialest nchar(20) not null 
/*�ڳ��ݹ���*/,
pk_group nvarchar(20) null 
/*��������*/,
pk_org nvarchar(20) null 
/*������֯���°汾*/,
pk_org_v nvarchar(20) null 
/*������֯*/,
pk_costregion nvarchar(20) null 
/*�ɱ���*/,
vtrantypecode nvarchar(20) null 
/*�ڳ��ݹ����ͱ���*/,
vbillcode nvarchar(40) null 
/*�ڳ��ݹ�����*/,
pk_stockorg nvarchar(20) null 
/*�����֯���°汾*/,
pk_stockorg_v nvarchar(20) null 
/*�����֯*/,
pk_supplier nvarchar(20) null 
/*��Ӧ��*/,
corigcurrencyid nvarchar(20) null 
/*����*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
ccurrencyid nvarchar(20) null 
/*��λ��*/,
pk_stordoc nvarchar(20) null 
/*�ֿ�*/,
pk_bizpsn nvarchar(20) null 
/*�ɹ�Ա*/,
pk_managepsn nvarchar(20) null 
/*����Ա*/,
pk_purchaseorg_v nvarchar(20) null 
/*�ɹ���֯*/,
pk_purchaseorg nvarchar(20) null 
/*�ɹ���֯���°汾*/,
pk_dept nvarchar(20) null 
/*�ɹ��������°汾*/,
pk_dept_v nvarchar(20) null 
/*�ɹ�����*/,
dbilldate nchar(19) null 
/*�������*/,
billmaker nvarchar(20) null 
/*�Ƶ���*/,
dmakedate nchar(19) null 
/*�Ƶ�����*/,
creationtime nchar(19) null 
/*����ʱ��*/,
approver nvarchar(20) null 
/*������*/,
taudittime nvarchar(19) null 
/*��������*/,
modifier nvarchar(20) null 
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
pk_busitype nvarchar(20) null 
/*ҵ������*/,
vmemo nvarchar(181) null 
/*��ע*/,
fbillstatus int null default 0 
/*����״̬*/,
creator nvarchar(20) null 
/*������*/,
ntotalastnum decimal(28,8) null 
/*������*/,
ntotalorigmny decimal(28,8) null 
/*�ܼ�???�ϼ�*/,
bnormpur nchar(1) null 
/*�Ƿ���ͨ�ɹ�*/,
ctrantypeid nvarchar(20) null 
/*�ڳ��ݹ�����*/,
 constraint pk_po_initialest primary key (pk_initialest),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

