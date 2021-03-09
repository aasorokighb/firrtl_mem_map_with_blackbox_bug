# firrtl_mem_map_with_blackbox_bug


sbt 'runMain  memmapbug.MemtestInst'



Elaborating design...
Done elaborating.
module MemoryInst(
  input         clock,
  input         reset,
  input  [8:0]  io_wAddr,
  input  [63:0] io_wData,
  input         io_wEn,
  input  [8:0]  io_rAddr,
  input         io_rEn,
  output [63:0] io_rData
);
`ifdef RANDOMIZE_MEM_INIT
  reg [63:0] _RAND_0;
`endif // RANDOMIZE_MEM_INIT
`ifdef RANDOMIZE_REG_INIT
  reg [31:0] _RAND_1;
  reg [31:0] _RAND_2;
`endif // RANDOMIZE_REG_INIT
  wire  myBbox_clock; // @[MemMapBug.scala 19:22]
  wire  myBbox_reset; // @[MemMapBug.scala 19:22]
  reg [63:0] memFile [0:511]; // @[MemMapBug.scala 20:28]
  wire [63:0] memFile_MPORT_1_data; // @[MemMapBug.scala 20:28]
  wire [8:0] memFile_MPORT_1_addr; // @[MemMapBug.scala 20:28]
  wire [63:0] memFile_MPORT_data; // @[MemMapBug.scala 20:28]
  wire [8:0] memFile_MPORT_addr; // @[MemMapBug.scala 20:28]
  wire  memFile_MPORT_mask; // @[MemMapBug.scala 20:28]
  wire  memFile_MPORT_en; // @[MemMapBug.scala 20:28]
  reg  memFile_MPORT_1_en_pipe_0;
  reg [8:0] memFile_MPORT_1_addr_pipe_0;
  MyBBox myBbox ( // @[MemMapBug.scala 19:22]
    .clock(myBbox_clock),
    .reset(myBbox_reset)
  );
  assign memFile_MPORT_1_addr = memFile_MPORT_1_addr_pipe_0;
  assign memFile_MPORT_1_data = memFile[memFile_MPORT_1_addr]; // @[MemMapBug.scala 20:28]
  assign memFile_MPORT_data = io_wData;
  assign memFile_MPORT_addr = io_wAddr;
  assign memFile_MPORT_mask = 1'h1;
  assign memFile_MPORT_en = io_wEn;
  assign io_rData = memFile_MPORT_1_data; // @[MemMapBug.scala 25:12]
  assign myBbox_clock = 1'h0;
  assign myBbox_reset = 1'h0;
  always @(posedge clock) begin
    if(memFile_MPORT_en & memFile_MPORT_mask) begin
      memFile[memFile_MPORT_addr] <= memFile_MPORT_data; // @[MemMapBug.scala 20:28]
    end
    memFile_MPORT_1_en_pipe_0 <= io_rEn;
    if (io_rEn) begin
      memFile_MPORT_1_addr_pipe_0 <= io_rAddr;
    end
  end
// Register and memory initialization
`ifdef RANDOMIZE_GARBAGE_ASSIGN
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_INVALID_ASSIGN
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_REG_INIT
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_MEM_INIT
`define RANDOMIZE
`endif
`ifndef RANDOM
`define RANDOM $random
`endif
`ifdef RANDOMIZE_MEM_INIT
  integer initvar;
`endif
`ifndef SYNTHESIS
`ifdef FIRRTL_BEFORE_INITIAL
`FIRRTL_BEFORE_INITIAL
`endif
initial begin
  `ifdef RANDOMIZE
    `ifdef INIT_RANDOM
      `INIT_RANDOM
    `endif
    `ifndef VERILATOR
      `ifdef RANDOMIZE_DELAY
        #`RANDOMIZE_DELAY begin end
      `else
        #0.002 begin end
      `endif
    `endif
`ifdef RANDOMIZE_MEM_INIT
  _RAND_0 = {2{`RANDOM}};
  for (initvar = 0; initvar < 512; initvar = initvar+1)
    memFile[initvar] = _RAND_0[63:0];
`endif // RANDOMIZE_MEM_INIT
`ifdef RANDOMIZE_REG_INIT
  _RAND_1 = {1{`RANDOM}};
  memFile_MPORT_1_en_pipe_0 = _RAND_1[0:0];
  _RAND_2 = {1{`RANDOM}};
  memFile_MPORT_1_addr_pipe_0 = _RAND_2[8:0];
`endif // RANDOMIZE_REG_INIT
  `endif // RANDOMIZE
end // initial
`ifdef FIRRTL_AFTER_INITIAL
`FIRRTL_AFTER_INITIAL
`endif
`endif // SYNTHESIS
endmodule

***** mems.conf *****

Elaborating design...
Done elaborating.
module MemoryInst(
  input         clock,
  input         reset,
  input  [8:0]  io_wAddr,
  input  [63:0] io_wData,
  input         io_wEn,
  input  [8:0]  io_rAddr,
  input         io_rEn,
  output [63:0] io_rData
);
  wire  myBbox_clock; // @[MemMapBug.scala 19:22]
  wire  myBbox_reset; // @[MemMapBug.scala 19:22]
  wire [8:0] memFile_R0_addr; // @[MemMapBug.scala 20:28]
  wire  memFile_R0_en; // @[MemMapBug.scala 20:28]
  wire  memFile_R0_clk; // @[MemMapBug.scala 20:28]
  wire [63:0] memFile_R0_data; // @[MemMapBug.scala 20:28]
  wire [8:0] memFile_W0_addr; // @[MemMapBug.scala 20:28]
  wire  memFile_W0_en; // @[MemMapBug.scala 20:28]
  wire  memFile_W0_clk; // @[MemMapBug.scala 20:28]
  wire [63:0] memFile_W0_data; // @[MemMapBug.scala 20:28]
  MyBBox myBbox ( // @[MemMapBug.scala 19:22]
    .clock(myBbox_clock),
    .reset(myBbox_reset)
  );
  memFile memFile ( // @[MemMapBug.scala 20:28]
    .R0_addr(memFile_R0_addr),
    .R0_en(memFile_R0_en),
    .R0_clk(memFile_R0_clk),
    .R0_data(memFile_R0_data),
    .W0_addr(memFile_W0_addr),
    .W0_en(memFile_W0_en),
    .W0_clk(memFile_W0_clk),
    .W0_data(memFile_W0_data)
  );
  assign io_rData = memFile_R0_data; // @[MemMapBug.scala 25:12]
  assign myBbox_clock = 1'h0;
  assign myBbox_reset = 1'h0;
  assign memFile_R0_addr = io_rAddr; // @[MemMapBug.scala 25:27]
  assign memFile_R0_en = io_rEn; // @[MemMapBug.scala 25:27 MemMapBug.scala 25:27 MemMapBug.scala 20:28]
  assign memFile_R0_clk = clock; // @[MemMapBug.scala 25:27 MemMapBug.scala 25:27]
  assign memFile_W0_addr = io_wAddr; // @[MemMapBug.scala 22:16]
  assign memFile_W0_en = io_wEn; // @[MemMapBug.scala 22:16 MemMapBug.scala 20:28]
  assign memFile_W0_clk = clock; // @[MemMapBug.scala 22:16]
  assign memFile_W0_data = io_wData; // @[MemMapBug.scala 22:16]
endmodule
module memFile(
  input  [8:0]  R0_addr,
  input         R0_en,
  input         R0_clk,
  output [63:0] R0_data,
  input  [8:0]  W0_addr,
  input         W0_en,
  input         W0_clk,
  input  [63:0] W0_data
);
  wire [8:0] memFile_ext_R0_addr;
  wire  memFile_ext_R0_en;
  wire  memFile_ext_R0_clk;
  wire [63:0] memFile_ext_R0_data;
  wire [8:0] memFile_ext_W0_addr;
  wire  memFile_ext_W0_en;
  wire  memFile_ext_W0_clk;
  wire [63:0] memFile_ext_W0_data;
  memFile_ext memFile_ext (
    .R0_addr(memFile_ext_R0_addr),
    .R0_en(memFile_ext_R0_en),
    .R0_clk(memFile_ext_R0_clk),
    .R0_data(memFile_ext_R0_data),
    .W0_addr(memFile_ext_W0_addr),
    .W0_en(memFile_ext_W0_en),
    .W0_clk(memFile_ext_W0_clk),
    .W0_data(memFile_ext_W0_data)
  );
  assign memFile_ext_R0_clk = R0_clk;
  assign memFile_ext_R0_en = R0_en;
  assign memFile_ext_R0_addr = R0_addr;
  assign R0_data = memFile_ext_R0_data;
  assign memFile_ext_W0_clk = W0_clk;
  assign memFile_ext_W0_en = W0_en;
  assign memFile_ext_W0_addr = W0_addr;
  assign memFile_ext_W0_data = W0_data;
endmodule

***** mems.conf *****
name memFile_ext depth 512 width 64 ports write,read  
