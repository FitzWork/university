/**
 * Top-level module of the custom clocks.
 * The module displays a 10Hz clock signal through LEDR5, a 1Hz signal through LEDR6, and a 0.1Hz signal through LEDR7.
 * Switch button SW0 resets all resettable modules on low.
 *
 * @param MAX10_CLK1_50 MAX10_CLK1_50 clock signal available in the MAX 10 FPGA.
 * @param SW bits of ten switch buttons SW9 - SW0.
 * @param LEDR output bits corresponding to the board's ten leds LEDR9 - LEDR0.
 **/
module clocks_de10_lite( input  logic       MAX10_CLK1_50,
                         input  logic [9:0] SW,
                         output logic [9:0] LEDR );
    logic [2:0] l_roll_over;
    logic l_reset;
    logic [2:0]l_clk;
    
    assign l_reset = ~SW[0];

    // 10 Hz
    counter_mod_k_ro #(22) ro0( MAX10_CLK1_50,
                                l_reset,
                                22'd2_500_000,
                                l_roll_over[0] );
    clock clk0( l_roll_over[0],
                l_reset,
                l_clk[0] );

    // 1 Hz
    counter_mod_k_ro #(25) ro1( MAX10_CLK1_50,
                                l_reset,
                                25'd25_000_000,
                                l_roll_over[1] );
    clock clk1( l_roll_over[1],
                l_reset,
                l_clk[1] );

    // 0.1 Hz
    counter_mod_k_ro #(28) ro2( MAX10_CLK1_50,
                                l_reset,
                                28'd250_000_000,
                                l_roll_over[2] );
    clock clk2( l_roll_over[2],
                l_reset,
                l_clk[2] );

    assign LEDR[5] = l_clk[0];
    assign LEDR[6] = l_clk[1];
    assign LEDR[7] = l_clk[2];

endmodule