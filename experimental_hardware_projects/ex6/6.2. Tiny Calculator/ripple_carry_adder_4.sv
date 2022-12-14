/**
  * 4-bit ripple-carry adder.
  *
  * @param i_a input A.
  * @param i_b input B.
  * @param i_carry_in carry in.
  * @param o_s sum.
  * @param o_carry_out carry out.
  **/
module ripple_carry_adder_4( input  logic [3:0] i_a,
                             input  logic [3:0] i_b,
                             input  logic i_carry_in,
                             output logic [3:0] o_s,
                             output logic o_carry_out );
    // TODO: implement ripple-carry adder here
    
    logic [2:0] l_carry;
    full_adder_1 fa_0(i_a[0], i_b[0], i_carry_in, o_s[0], l_carry[0]);
    full_adder_1 fa_1(i_a[1], i_b[1], l_carry[0], o_s[1], l_carry[1]);
    full_adder_1 fa_2(i_a[2], i_b[2], l_carry[1], o_s[2], l_carry[2]);
    full_adder_1 fa_3(i_a[3], i_b[3], l_carry[2], o_s[3], o_carry_out);
    
endmodule
