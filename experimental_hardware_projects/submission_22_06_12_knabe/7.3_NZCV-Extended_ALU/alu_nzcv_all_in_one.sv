module alu_nzcv_all_in_one #(parameter N=64) ( input  logic [N-1:0] i_a,
                                                                      i_b,
                                                 input  logic [1:0]   i_alu_ctrl,
                                       		 output logic [N-1:0] o_result,
                                       		 output logic [3:0]   o_nzcv );
    logic [N-1:0] l_b_tmp;
    logic [N-1:0] l_sum;
    logic [N-1:0] l_res_11 ;
    logic [N-1:0] l_res_10;
    
    logic l_carry_out ; // added
    
    mux_2 #(N) mux_b( i_b, 
                      ~i_b, 
                      i_alu_ctrl[0], 
                      l_b_tmp );
                   
    adder #(N) add_ab( i_a, 
                       l_b_tmp, 
                       i_alu_ctrl[0], 
                       l_sum, 
                       l_carry_out );
    
    assign l_res_10 = i_a & i_b;
    assign l_res_11 = i_a | i_b;
    
    mux_4 #(N) m_result( l_sum, 
                         l_sum, 
                         l_res10, 
                         l_res11, 
                         i_alu_ctrl, 
                         o_result );
    
    // added:
    
    logic xnor_and;
    
    assign l_res_xor = l_sum[N-1] ^ i_a[N-1];
    
    xor_3 x3( i_alu_ctrl[0], 
              i_a[N-1], 
              i_b[N-1], 
              l_res_xor );

    assign o_nzcv[0] = ~i_alu_ctrl & l_res_xor & ~l_res_xor;
    assign o_nzcv[1] = l_carry_out & ~i_alu_ctrl[1];
    assign o_nzcv[2] = &(~o_result);
    assign o_nzcv[3] = o_result[N-1];

endmodule

