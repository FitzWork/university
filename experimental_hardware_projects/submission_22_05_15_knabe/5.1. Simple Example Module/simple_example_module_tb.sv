module simple_example_module_tb;
    logic l_a, l_b, l_c;

    simple_example_module dut( l_a,
                               l_b,
                               l_c );
    
    initial begin
        $dumpfile("dump_simple_example_module.vcd");
        $dumpvars;

        l_a = 1'b0;
        l_b = 1'b0;
        #10;
        assert( l_c === 1'b1 );

        l_a = 1'b1;
        l_b = 1'b0;
        #10;
        assert( l_c === 1'b0 );

        $finish;
    end
endmodule