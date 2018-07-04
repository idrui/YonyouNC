/* tablename: ��λ���� */
create table po_position (
name4 nvarchar(300) null 
/*��λ����4*/,
name6 nvarchar(300) null 
/*��λ����6*/,
name5 nvarchar(300) null 
/*��λ����5*/,
pk_position nchar(20) not null 
/*��λ����*/,
name3 nvarchar(300) null 
/*��λ����3*/,
name2 nvarchar(300) null 
/*��λ����2*/,
name nvarchar(300) null 
/*��λ����*/,
pk_group nvarchar(20) null default '~' 
/*����*/,
pk_org nvarchar(20) null default '~' 
/*��֯*/,
code nvarchar(30) null 
/*��λ����*/,
cemployeeid nvarchar(20) null default '~' 
/*�ƻ�Ա*/,
vmemo nvarchar(50) null 
/*��ע*/,
fpositiontype int null 
/*��λ����*/,
 constraint pk_po_position primary key (pk_position),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ��λ�����ӱ� */
create table po_position_b (
pk_position_b nchar(20) not null 
/*��λ�����ӱ�*/,
pk_org nvarchar(20) null default '~' 
/*��֯*/,
pk_marbasclass nvarchar(20) null default '~' 
/*���ϻ�������*/,
marbasclass_code nvarchar(50) null 
/*���ϻ����������*/,
pk_marpuclass nvarchar(20) null default '~' 
/*���ϲɹ�����*/,
marpuclass_code nvarchar(50) null 
/*���ϲɹ��������*/,
pk_material nvarchar(20) null default '~' 
/*����*/,
material_code nvarchar(50) null 
/*���ϱ���*/,
fflag int null default 1 
/*Ӧ��/�ų�*/,
pk_position nchar(20) not null 
/*��λ����_����*/,
pk_srcmaterial nvarchar(20) null 
/*����oid*/,
 constraint pk_po_position_b primary key (pk_position_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

