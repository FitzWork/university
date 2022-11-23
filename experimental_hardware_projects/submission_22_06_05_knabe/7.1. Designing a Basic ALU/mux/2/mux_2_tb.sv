/**
 * Testbench of 2:1 multiplexer.
 */
module mux_2_tb;
	logic [3:0] l_a, l_b, l_c;
	logic       l_s;

	mux_2 #(4) dut ( l_a,
					 l_b,
					 l_s,
					 l_c );

	initial begin
		$dumpfile("dump.vcd");
		$dumpvars;

		l_a = 4'b0000;
		l_b = 4'b0001;
		l_s = 1'b0;
		#10;
		assert( l_c === 4'b0000 );

		l_a = 4'b0000;
		l_b = 4'b0001;
		l_s = 1'b1;
		#10;
		assert( l_c === 4'b0001 );

		l_a = 4'b0001;
		l_b = 4'b0000;
		l_s = 1'b0;
		#10;
		assert( l_c === 4'b0001 );

		$finish;
	end
endmodule
