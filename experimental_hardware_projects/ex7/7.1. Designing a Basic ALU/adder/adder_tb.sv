/**
 * Testbench of the adder with a carry-in and carry-out.
 */
module adder_tb;
	logic [63:0] l_a, l_b, l_c;
	logic l_carry_in, l_carry_out;

	adder #(64) dut( l_a,
			 l_b,
			 l_carry_in,
			 l_c,
			 l_carry_out );
	initial begin
		$dumpfile("dump.vcd");
		$dumpvars;

		l_carry_in = 1'b0;
		l_a = 64'h5555_5555_5555_5555;
		l_b = 64'haaaa_aaaa_aaaa_aaaa;
		#10;
		assert( l_c === 64'hffff_ffff_ffff_ffff );
		assert( l_carry_out === 1'b0 );

		l_carry_in = 1'b0;
		l_a = 64'h5555_5555_5555_5556;
		l_b = 64'haaaa_aaaa_aaaa_aaaa;
		#10;
		assert( l_c === 64'h0000_0000_0000_0000 );
		assert( l_carry_out === 1'b1 );

		l_carry_in = 1'b0;
		l_a = 64'hffff_ffff_ffff_ffff;
		l_b = 64'hffff_ffff_ffff_ffff;
		#10;
		assert( l_c === 64'hffff_ffff_ffff_fffe );
		assert( l_carry_out === 1'b1 );

		$finish;
	end
endmodule
