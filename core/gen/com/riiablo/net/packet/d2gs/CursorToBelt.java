// automatically generated by the FlatBuffers compiler, do not modify

package com.riiablo.net.packet.d2gs;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class CursorToBelt extends Table {
  public static CursorToBelt getRootAsCursorToBelt(ByteBuffer _bb) { return getRootAsCursorToBelt(_bb, new CursorToBelt()); }
  public static CursorToBelt getRootAsCursorToBelt(ByteBuffer _bb, CursorToBelt obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public CursorToBelt __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public int x() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public int y() { int o = __offset(6); return o != 0 ? bb.getInt(o + bb_pos) : 0; }

  public static int createCursorToBelt(FlatBufferBuilder builder,
      int x,
      int y) {
    builder.startObject(2);
    CursorToBelt.addY(builder, y);
    CursorToBelt.addX(builder, x);
    return CursorToBelt.endCursorToBelt(builder);
  }

  public static void startCursorToBelt(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addX(FlatBufferBuilder builder, int x) { builder.addInt(0, x, 0); }
  public static void addY(FlatBufferBuilder builder, int y) { builder.addInt(1, y, 0); }
  public static int endCursorToBelt(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}

