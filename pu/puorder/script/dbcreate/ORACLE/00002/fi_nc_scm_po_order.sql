/* indexcode: i_pu_21_bb_fk */
create  index i_pu_21_bb_fk on po_order_bb (pk_order_b asc)
/

/* indexcode: i_pu_21_bb_fkh */
create  index i_pu_21_bb_fkh on po_order_bb (pk_order asc)
/

/* indexcode: i_pu_21_b_dtorg */
create  index i_pu_21_b_dtorg on po_order_b (dbilldate asc,
pk_arrvstoorg asc)
/

/* indexcode: i_pu_21_b_pldt */
create  index i_pu_21_b_pldt on po_order_b (dplanarrvdate asc)
/

/* indexcode: i_pu_21_b_src */
create  index i_pu_21_b_src on po_order_b (csourceid asc,
csourcebid asc)
/

/* indexcode: i_pu_21_b_ct */
create  index i_pu_21_b_ct on po_order_b (ccontractid asc,
ccontractrowid asc)
/

/* indexcode: i_pu_21_b_fk */
create  index i_pu_21_b_fk on po_order_b (pk_order asc)
/

/* indexcode: i_pu_21_b_pa */
create  index i_pu_21_b_pa on po_order_b (cpriceauditid asc)
/

/* indexcode: i_pu_21_b_supp */
create  index i_pu_21_b_supp on po_order_b (pk_supplier asc)
/

/* indexcode: i_pu_21_b_ec_fk */
create  index i_pu_21_b_ec_fk on po_order_b_ec (pk_order_b asc)
/

/* indexcode: i_pu_21_pay_date */
create  index i_pu_21_pay_date on po_order_payplan (denddate asc)
/

/* indexcode: i_pu_21_pay_fk */
create  index i_pu_21_pay_fk on po_order_payplan (pk_order asc)
/

/* indexcode: i_pu_21_bb1_dtorg */
create  index i_pu_21_bb1_dtorg on po_order_bb1 (dplanarrvdate asc,
pk_arrvstoorg asc)
/

/* indexcode: i_pu_21_bb1_fk */
create  index i_pu_21_bb1_fk on po_order_bb1 (pk_order_b asc)
/

/* indexcode: i_pu_21_bb1_fkh */
create  index i_pu_21_bb1_fkh on po_order_bb1 (pk_order asc)
/

/* indexcode: i_pu_21_payment_fk */
create  index i_pu_21_payment_fk on po_order_payment (pk_order asc)
/

/* indexcode: i_pu_21_code */
create  index i_pu_21_code on po_order (vbillcode asc)
/

/* indexcode: i_pu_21_dtorg */
create  index i_pu_21_dtorg on po_order (dbilldate asc,
pk_org asc)
/

/* indexcode: i_pu_21_ec_fk */
create  index i_pu_21_ec_fk on po_order_ec (pk_order asc)
/

