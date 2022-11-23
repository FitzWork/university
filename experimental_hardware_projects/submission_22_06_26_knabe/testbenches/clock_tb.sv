/**
 * Testbench of the clock derived from a counter's rollover signal.
 **/
module clock_tb;
    logic l_reset;
    logic l_roll_over;
    logic l_clk;

    initial begin
        l_reset <= 1;
        #3;
        l_reset <= 0;
    end

    always begin
        l_roll_over <= 0;
        #10;
        l_roll_over <= 1;
        #10;
    end

    clock dut( l_roll_over,
               l_reset,
               l_clk );
    
    initial begin
        $dumpfile("dump.vcd");
        $dumpvars;

        #5;
        assert( l_clk === 1'b1 );
        #10;
        assert( l_clk === 1'b0 );
        #10;
        assert( l_clk === 1'b0 );
        #5;

        // 1. Zyklus
        #5;
        assert( l_clk === 1'b1 );
        #10;
        assert( l_clk === 1'b1 );
        #10;
        assert( l_clk === 1'b0 );
        #10;
        assert( l_clk === 1'b0 );
        #5;

        // 2. Zyklus
        #5;
        assert( l_clk === 1'b1 );
        #10;
        assert( l_clk === 1'b1 );
        #10;
        assert( l_clk === 1'b0 );
        #10;
        assert( l_clk === 1'b0 );
        #5;

        // 3. Zyklus
        #5;
        assert( l_clk === 1'b1 );
        #10;
        assert( l_clk === 1'b1 );
        #10;
        assert( l_clk === 1'b0 );
        #10;
        assert( l_clk === 1'b0 );
        #5;

        $finish;
    end
endmodule