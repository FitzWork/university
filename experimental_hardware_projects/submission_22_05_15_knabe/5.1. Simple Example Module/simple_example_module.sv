module simple_example_module( input logic  i_a,
                              input logic  i_b,
                              output logic o_c );
    assign o_c = ~i_a | i_b;
endmodule