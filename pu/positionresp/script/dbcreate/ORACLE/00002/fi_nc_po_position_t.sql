/* indexcode: i_pu_pos_t_psncls */
create  index i_pu_pos_t_psncls on po_position_t (cemployeeid asc,
pk_marclass asc)
/

/* indexcode: i_pu_pos_t_fk */
create  index i_pu_pos_t_fk on po_position_t (pk_position_b asc)
/

