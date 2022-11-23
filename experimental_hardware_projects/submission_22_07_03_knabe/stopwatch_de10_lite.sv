module stopwatch_de10_lite( input logic       MAX10_CLK1_50,
                            input logic [9:0] SW,
                            output logic [9:0] LEDR,
                            output logic [6:0] HEX0,
                            output logic [7:0] HEX1,
                            output logic [6:0] HEX2,
                            output logic [6:0] HEX3,
                            output logic [6:0] HEX4, 
                            output logic [6:0] HEX5 );
                            
    logic 	l_reset;
    logic [5:0] l_roll_over;
    logic [4:0] l_clk;
    logic [3:0] l_count0;
    logic [3:0] l_count1;
    logic [3:0] l_count2;
    logic [3:0] l_count3;
    logic [3:0] l_count4;

    assign l_reset = ~SW[0];
    assign HEX1[7] = 1'b0;
    assign HEX3[5:0] = 6'b11_1111;
    
// 10 Hz
    // LED
    counter_mod_k_ro #(22) ro0( MAX10_CLK1_50,
                                l_reset,
                                22'd2_500_000,
                                l_roll_over[0] );
    clock clk0( l_roll_over[0],
                l_reset,
                l_clk[0] );
    // HEX0
    counter_mod_k_count #(4) c0( l_clk[0],
                                 l_reset,
                                 4'd10,
                                 l_count0 );

// 1 Hz
    // LED
    counter_mod_k_ro #(25) ro1( MAX10_CLK1_50,
                                l_reset,
                                25'd25_000_000,
                                l_roll_over[1] );
    clock clk1( l_roll_over[1],
                l_reset,
                l_clk[1] );
    // HEX1
    counter_mod_k_count #(4) c1( l_clk[1],
                                 l_reset,
                                 4'd10,
                                 l_count1 );
    // HEX3
    clock hex( l_roll_over[1],
                l_reset,
                HEX3[6] );
                
// 0.1 Hz
    // LED
    counter_mod_k_ro #(28) ro2( MAX10_CLK1_50,
                                l_reset,
                                28'd250_000_000,
                                l_roll_over[2] );
    clock clk2( l_roll_over[2],
                l_reset,
                l_clk[2] );
    // HEX2
    counter_mod_k_count #(3) c2( l_clk[2],
                                 l_reset,
                                 3'd6,
                                 l_count2[2:0] );

// 1/60 Hz
    // LED
    counter_mod_k_ro #(31) ro3( MAX10_CLK1_50,
                                l_reset,
                                31'd1_500_000_000,
                                l_roll_over[3] );
    clock clk3( l_roll_over[3],
                l_reset,
                l_clk[3] );
    // HEX
    counter_mod_k_count #(4) c3( l_clk[3],
    				 l_reset,
    				 4'd10,
    				 l_count3 );

// 1/600 Hz
    // LED
    counter_mod_k_ro #(34) ro4( MAX10_CLK1_50,
                                l_reset,
                                34'd15_000_000_000,
                                l_roll_over[4] );
    clock clk4( l_roll_over[4],
                l_reset,
                l_clk[4] );
    // HEX
    counter_mod_k_count #(4) c4( l_clk[4],
    			      l_reset,
    			      4'd10,
    			      l_count4 );
	 
	 
	 
    assign LEDR[5] = l_clk[0];
    assign LEDR[6] = l_clk[1];
    assign LEDR[7] = l_clk[2];
    assign LEDR[8] = l_clk[3];
    assign LEDR[9] = l_clk[4];
    
    decoder d0( l_count0, HEX0);
    decoder d1( l_count1, HEX1[6:0]);
    decoder d2( l_count2, HEX2);
    decoder d4( l_count3, HEX4);
    decoder d5( l_count4, HEX5);

endmodule

