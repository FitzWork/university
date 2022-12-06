module alu #(parameter N=64) ( input  logic [N-1:0] i_a,
                                                    i_b,
                               input  logic [1:0]   i_alu_ctrl,
                               output logic [N-1:0] o_result,
                               output logic         o_carry_out );
    logic [N-1:0] tmp_0, tmp_1, tmp_2, tmp_3;

    assign tmp_0 = i_a | i_b;
    assign tmp_1 = i_a & i_b;
    mux_2 #(N) m2( i_b, ~i_b, i_alu_ctrl[0], tmp_2 );
    adder #(N) add( i_a, tmp_2, i_alu_ctrl[0], tmp_3, o_carry_out );
    mux_4 #(N) m4( tmp_3, tmp_3, tmp_1, tmp_0, i_alu_ctrl, o_result );
endmodule