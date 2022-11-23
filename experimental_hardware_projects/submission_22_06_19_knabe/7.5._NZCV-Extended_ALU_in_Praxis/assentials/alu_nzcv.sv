module alu_nzcv #(parameter N = 64) ( input  logic [N-1:0] i_a,
                                                            i_b,
                                       input  logic [1:0]   i_alu_ctrl,
                                       output logic [N-1:0] o_result,
                                       output logic [3:0]   o_nzcv );
    logic adder_to_and, 
          xor_to_and, 
          xor3_to_and;

    alu_basic #(N) basic_alu( i_a, 
                        i_b, 
                        i_alu_ctrl, 
                        o_result, 
                        adder_to_and );
    
    assign xor_to_and = o_result[N-1] ^ i_a[N-1];
    
    xor_3 x3( i_alu_ctrl[0],
              i_a[N-1],
              i_b[N-1],
              xor3_to_and );

    assign o_nzcv[0] = ~i_alu_ctrl & xor_to_and & ~xor3_to_and;
    assign o_nzcv[1] = adder_to_and & ~i_alu_ctrl[1];
    assign o_nzcv[2] = &(~o_result);
    assign o_nzcv[3] = o_result[N-1];

endmodule
