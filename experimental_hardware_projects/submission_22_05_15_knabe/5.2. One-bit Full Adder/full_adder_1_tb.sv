/**
  * Testbench of the 1-bit full adder.
  */
module full_adder_1_tb;
    logic l_a, l_b, l_s;
    logic l_carry_in, l_carry_out;

    full_adder_1 dut( l_a,
                      l_b,
                      l_carry_in,
                      l_s,
                      l_carry_out );

    initial begin
        $dumpfile("dump_full_adder_1.vcd");
        $dumpvars;

        l_a = 1'b0;
        l_b = 1'b0;
        l_carry_in = 1'b0;
        #10;
        assert( l_s === 1'b0 );
        assert( l_carry_out === 1'b0 );

        l_a = 1'b1;
        l_b = 1'b0;
        l_carry_in = 1'b0;
        #10;
        assert( l_s === 1'b1 );
        assert( l_carry_out === 1'b0 );

        // TODO: add additional tests
        l_a = 1'b0;
        l_b = 1'b1;
        l_carry_in = 1'b0;
        #10;
        assert( l_s === 1'b1 );
        assert( l_carry_out === 1'b0 );

        l_a = 1'b0;
        l_b = 1'b0;
        l_carry_in = 1'b1;
        #10;
        assert( l_s === 1'b1 );
        assert( l_carry_out === 1'b0 );

        l_a = 1'b1;
        l_b = 1'b1;
        l_carry_in = 1'b0;
        #10;
        assert( l_s === 1'b0 );
        assert( l_carry_out === 1'b1 );

        l_a = 1'b1;
        l_b = 1'b0;
        l_carry_in = 1'b1;
        #10;
        assert( l_s === 1'b0 );
        assert( l_carry_out === 1'b1 );

        l_a = 1'b0;
        l_b = 1'b1;
        l_carry_in = 1'b1;
        #10;
        assert( l_s === 1'b0 );
        assert( l_carry_out === 1'b1 );

        l_a = 1'b1;
        l_b = 1'b1;
        l_carry_in = 1'b1;
        #10;
        assert( l_s === 1'b1 );
        assert( l_carry_out === 1'b1 );

        $finish;
    end
endmodule