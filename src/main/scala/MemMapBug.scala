package memmapbug

import chisel3._
import chisel3.util._

class MemoryInst(addRes: Boolean) extends Module {
  val bitsDatNb = 64
  val bitsAddrNb = 9

  val io = IO(new Bundle {
    val wAddr = Input(UInt(bitsAddrNb.W))
    val wData = Input(UInt(bitsDatNb.W))
    val wEn   = Input(Bool())
    val rAddr = Input(UInt(bitsAddrNb.W))
    val rEn   = Input(Bool())
    val rData = Output(UInt(bitsDatNb.W))
  })

  val myBbox = Module( new MyBBox(addRes))
  val memFile = SyncReadMem(1<<bitsAddrNb, UInt(bitsDatNb.W))

  when(io.wEn) {
    memFile.write(io.wAddr, io.wData)
  }
  io.rData := memFile.read(io.rAddr, io.rEn)
}
class MyBBox(addRes: Boolean) extends BlackBox() with HasBlackBoxResource  {

  val io = IO(new Bundle {
    val clock = Input(Clock())
    val reset = Input(Bool())
  })
  if (addRes) addResource("/someverilog.v")
}

object MemtestInst extends App {
  println((new chisel3.stage.ChiselStage).emitVerilog(new MemoryInst(addRes = true), Array("--repl-seq-mem", "-c:MemoryInst:-o:mems.conf")))
  println("***** mems.conf *****")
  println(scala.io.Source.fromFile("mems.conf").mkString)
  println((new chisel3.stage.ChiselStage).emitVerilog(new MemoryInst(addRes = false), Array("--repl-seq-mem", "-c:MemoryInst:-o:mems.conf")))
  println("***** mems.conf *****")
  println(scala.io.Source.fromFile("mems.conf").mkString)
}
