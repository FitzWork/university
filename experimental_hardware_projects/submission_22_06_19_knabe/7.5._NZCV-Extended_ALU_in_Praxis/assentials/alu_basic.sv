/**
 * Basic ALU support additions, subtractions, ands, and ors.
 *
 * @param i_a first value.
 * @param i_b second value.
 * @param i_alu_ctrl control signal.
                     00: add, 01: sub, 10: and, 11: or 
 * @param o_result result of the operation.
 * @param o_carry_out output carry.
 **/
module alu_basic #(parameter N=64) ( input  logic [N-1:0] i_a,
	                                                  i_b,
	                             input  logic [1:0]   i_alu_ctrl,
	                             output logic [N-1:0] o_result,
	                             output logic         o_carry_out );
  logic [N-1:0] l_b_tmp;
  logic [N-1:0] l_sum;
  logic [N-1:0] l_res_10;
  logic [N-1:0] l_res_11;

  mux_2 #(N) mux_b( i_b,
                    ~i_b,
                    i_alu_ctrl[0],
                    l_b_tmp );

  adder #(N) adder_ab( i_a,
                       l_b_tmp,
                       i_alu_ctrl[0],
                       l_sum,
                       o_carry_out );

  assign l_res_10 = i_a & i_b;
  assign l_res_11 = i_a | i_b;

  mux_4 #(N) mux_result( l_sum,
                         l_sum,
                         l_res_10,
                         l_res_11,
                         i_alu_ctrl,
                         o_result );
endmodule
