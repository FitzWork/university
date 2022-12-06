/**
 * Top-level module of the extended alu with condition flags.
 * The alu operates on the 4-bit binary numbers in SW[3:0] and SW[7:4].
 * The two input numbers are shown on displays HEX0 and HEX1.
 * The control signal is shown using LEDR1 and LEDR0.
 * The result is shown on display HEX2.
 * The NZCV flags are shown on the leds LEDR9, LEDR8, LEDR7 and LEDR6.
 *
 * @param SW bits of ten switch buttons SW9 - SW0.
 * @param LEDR output bits corresponding to the board's ten leds LEDR9 - LEDR0.
 * @param HEX0 output bits which drive the seven-segment display HEX0.
 * @param HEX1 output bits which drive the seven-segment display HEX1.
 * @param HEX2 output bits which drive the seven-segment display HEX2.
 **/
module alu_nzcv_de10_lite( input  logic [9:0] SW,
                           output logic [9:0] LEDR,
                           output logic [6:0] HEX0,
                           output logic [6:0] HEX1,
                           output logic [6:0] HEX2 );
	logic [1:0] l_alu_ctrl;
	logic [3:0] l_a,
		    l_b,
		    l_result,
		    l_nzcv;
	
	assign l_a = SW[3:0];
	assign l_b = SW[7:4];
	assign l_alu_ctrl = SW[9:8];
	
	alu_nzcv #(4) alu( l_a,
	      		   l_b,
		      	   l_alu_ctrl,
		      	   l_result,
		      	   l_nzcv );
	
	decoder d0( l_a, HEX0 );
	decoder d1( l_b, HEX1 );
	decoder d2( l_result, HEX2 );
	
	assign LEDR[0] = l_alu_ctrl[0];
	assign LEDR[1] = l_alu_ctrl[1];
	assign LEDR[6] = l_nzcv[0];
	assign LEDR[7] = l_nzcv[1];
	assign LEDR[8] = l_nzcv[2];
	assign LEDR[9] = l_nzcv[3];
endmodule
