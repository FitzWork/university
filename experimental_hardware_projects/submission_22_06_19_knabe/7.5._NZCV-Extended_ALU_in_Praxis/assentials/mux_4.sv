module mux_4 #(parameter N=64) ( input  logic [N-1:0] i_in0, 
                                                      i_in1,
                                                      i_in2,
                                                      i_in3,
                                 input  logic [1:0]   i_s,
                                 output logic [N-1:0] o_out );
    logic [N-1:0] tmp0, tmp1;

    mux_2 #(N) m0( i_in0, i_in1, i_s[0], tmp0 );
    mux_2 #(N) m1( i_in2, i_in3, i_s[0], tmp1 );
    mux_2 #(N) m2( tmp0, tmp1, i_s[1], o_out );
endmodule