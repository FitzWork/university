/**
  * 1-bit full adder.
  *
  * @param i_a input A.
  * @param i_b input B.
  * @param i_carry_in carry in.
  * @param o_s sum.
  * @param o_carry_out carry out.
  **/
module full_adder_1( input  logic i_a,
                     input  logic i_b,
                     input  logic i_carry_in,
                     output logic o_s,
                     output logic o_carry_out );
    // TODO: implement full adder
    assign o_s = i_a ^ i_b ^ i_carry_in;
    assign o_carry_out = i_a & i_b | ( i_a ^ i_b ) & i_carry_in;
endmodule
