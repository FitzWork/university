/**
 * Testbench 4:1 multiplexer.
 */
module mux_4_tb;
    logic [3:0] l_1, l_2, l_3, l_4, l_c;
    logic [1:0] l_s;

    mux_4 #(4) dut ( l_1,
                     l_2,
                     l_3,
                     l_4,
                     l_s,
                     l_c );
    initial begin
        $dumpfile("dump.vcd");
        $dumpvars;

        l_1 = 4'b0000;
        l_2 = 4'b0001;
        l_3 = 4'b0010;
        l_4 = 4'b0011;
        l_s = 2'b00;
        #10;
        assert( l_c === 4'b0000);

        l_1 = 4'b0000;
        l_2 = 4'b0001;
        l_3 = 4'b0010;
        l_4 = 4'b0011;
        l_s = 2'b01;
        #10;
        assert( l_c === 4'b0001);

        l_1 = 4'b0000;
        l_2 = 4'b0001;
        l_3 = 4'b0010;
        l_4 = 4'b0011;
        l_s = 2'b10;
        #10;
        assert( l_c === 4'b0010);

        l_1 = 4'b0000;
        l_2 = 4'b0001;
        l_3 = 4'b0010;
        l_4 = 4'b0011;
        l_s = 2'b11;
        #10;
        assert( l_c === 4'b0011);

        $finish;
    end
endmodule