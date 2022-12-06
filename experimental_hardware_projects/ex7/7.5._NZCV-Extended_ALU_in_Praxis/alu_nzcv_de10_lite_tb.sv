/*
 * 
 */
module alu_nzcv_de10_lite_tb;
	logic [9:0] SW,
		    LEDR;
    	logic [6:0] HEX0,
    		    HEX1,
    		    HEX2;

	alu_nzcv_de10_lite dut( SW, LEDR, HEX0, HEX1, HEX2 );
	
	initial begin
		$dumpfile("dump.vcd");
		$dumpvars;
		
		SW = 10'b00_0100_0100;
		#10;
		assert( LEDR === 10'b10_01xx_xx00 );
		assert( HEX0 === 7'b001_1001 );
		assert( HEX1 === 7'b001_1001 );
		assert( HEX2 === 7'b000_0000 );
		
		SW = 10'b00_0011_1101;
		#10;
		assert( LEDR === 10'b01_10xx_xx00 );
		assert( HEX0 === 7'b010_0001 );
		assert( HEX1 === 7'b011_0000 );
		assert( HEX2 === 7'b100_0000 );
		
		SW = 10'b01_1010_0100;
		#10;
		assert( LEDR === 10'b10_00xx_xx01 );
		assert( HEX0 === 7'b001_1001 );
		assert( HEX1 === 7'b000_1000 );
		assert( HEX2 === 7'b000_1000 );
		
		SW = 10'b10_1001_0110;
		#10;
		assert( LEDR === 10'b01_00xx_xx10 );
		assert( HEX0 === 7'b000_0010 );
		assert( HEX1 === 7'b001_0000 );
		assert( HEX2 === 7'b100_0000 );
		
		SW = 10'b11_0101_0110;
		#10;
		assert( LEDR === 10'b00_00xx_xx11 );
		assert( HEX0 === 7'b000_0010 );
		assert( HEX1 === 7'b001_0010 );
		assert( HEX2 === 7'b111_1000 );
		
		$finish;
	end
endmodule
