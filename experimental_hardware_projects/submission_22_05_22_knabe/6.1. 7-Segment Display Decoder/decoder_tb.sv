module decoder_tb;
    logic [3:0] SW;
    logic [6:0] HEX0;

    decoder_de10_lite dut(SW, HEX0);

    initial begin
        $dumpfile("dump_decoder.vcd");
        $dumpvars;
        
        SW = 4'b0111;
        #10;
        assert(HEX0 === 7'b111_1000);

        SW = 4'b1010;
        #10;
        assert(HEX0 === 7'b000_1000);

        SW = 4'b1110;
        #10;
        assert(HEX0 === 7'b000_0110);

        $finish;
    end
endmodule